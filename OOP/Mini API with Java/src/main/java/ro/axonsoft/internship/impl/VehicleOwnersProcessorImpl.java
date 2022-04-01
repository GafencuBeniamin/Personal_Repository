
package ro.axonsoft.internship.impl;

//import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.*;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
//import StringUtils
import org.apache.commons.lang3.StringUtils;
//import org.yaml.snakeyaml.*
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
//api package
import ro.axonsoft.internship.api.*;

/**
 * implementarea interfetei de rezultat necesara la scrierea in fisierul tinta
 */
class SerializableResult implements VehicleOwnersProcessResult{
    //instances
    private VehicleOwnersAggregator vehicleownersaggregator;
    public Integer oddToEvenRatio;
    public Map<String, Integer> unregCarsCountByJud;
    public Integer passedRegChangeDueDate;
    public Set<VehicleOwnerParseError> errors;
    
    //methods
    public void setVehicleownersaggregator(VehicleOwnersAggregator vehicleownersaggregator) {
        this.vehicleownersaggregator = vehicleownersaggregator;
    }
    /*
    * Raportul dintre numerele de înmatriculare fără soț și cele cu soț*
    * înmuțit cu 100 și rotunjit half-up fără zecimale.
    */
    @Override
    public Integer getOddToEvenRatio(){
        double ni=0;
        double np=0;
        for (VehicleOwnerRecord record : vehicleownersaggregator.getRecords()){
            if(record.getRegPlate() != null){
                if (record.getRegPlate().getDigits() % 2 ==0)
                    np++;
                else if (record.getRegPlate().getDigits() % 2 ==1)
                        ni++;
            }
        }
        double result=ni/np*100;
        result=Math.round(result);
        return (int)result;
    }
    /**
     * Numărul de mașini străine deținute de cetățeni românii pe fiecare județ
     * în parte.
     */
    @Override
    public Map<String, Integer> getUnregCarsCountByJud(){
        //cream un hashmap
        HashMap<String, Integer> counter= new HashMap<String, Integer>();
        //iteram prin clasa enum pentru a avea valorile de tip String pe care le adaugam in HashMap
        Judet judete[]= Judet.values();
        for (Judet j : judete){
            counter.put(j.toString(),0);
        }
        for (VehicleOwnerRecord record : vehicleownersaggregator.getRecords()){
            if(record.getRegPlate()==null)
                if(record.getIdCard()!= null){
                    Integer count=counter.get(record.getIdCard().getJudet().toString());
                    counter.put(record.getIdCard().getJudet().toString(),count+1);
            }
        }
        return counter;
    }
    /**
     * Numărul de autovehicule deținute de persoane având domiciliul într-un
     * județ și autovehiculul înmatriculat în alt județ și au trecut mai multe
     * de 30 de zile de la data emiterii cărții de indentitate.
     */
    @Override
    public Integer getPassedRegChangeDueDate(){
        Integer counter=0;
        for (VehicleOwnerRecord record : vehicleownersaggregator.getRecords())
            if (record.getRegPlate() != null && record.getIdCard() != null && record.getIdCardIssueDate() != null)
                if (!record.getRegPlate().getJudet().equals(record.getIdCard().getJudet())){
                    //diferenta intre 2 date in milisecunde /1000/60/60/24 => diferenta in zile
                    if(( vehicleownersaggregator.getReferenceDate().getTime() - record.getIdCardIssueDate().getTime() )/1000/60/60/24 > 30)
                        counter++;    
                }
        return counter;
    }
    /**
     * Erorile de procesare.
     */
    @Override
    public Set<VehicleOwnerParseError> getErrors(){
        Set<VehicleOwnerParseError> myset=new HashSet();
        Integer index=0;
        for (VehicleOwnerRecord record : vehicleownersaggregator.getRecords()){
            index++;
            if(record.getIdCardIssueDate()==null && record.getIdCard()==null && record.getRegPlate()==null)
                myset.add(new OwnerErrorParser(index,0));
            else{
                if(record.getIdCard()==null)
                    myset.add(new OwnerErrorParser(index,1));
                if(record.getIdCardIssueDate()==null)
                    myset.add(new OwnerErrorParser(index,2));
            }
        }
        return myset;
    }
}

/**
 * implementarea interfetei de error record necesara la afisarea lui GetErrors
 */
class OwnerErrorParser implements VehicleOwnerParseError{
    //instances
    public Integer getLine;
    public Integer getType;
    //constructor
    public OwnerErrorParser(Integer getLine, Integer getType) {
        this.getLine = getLine;
        this.getType = getType;
    }
    
    //getters
    @Override
    public Integer getLine(){
        return getLine;
    }
    @Override
    public Integer getType(){
        return getType;
    }  
}

/*
*   clasa record ce implementeaza VehicleOwnerRecord
*
*   clasa filtreaza datele de pe linie in forma VehicleOwnerRecord
*
*/
class Record implements VehicleOwnerRecord{
    //instances
    //linia citita
    private String line;
    private RoIdCardParser idCardParser;
    private RoRegPlateParser regPlateParser;

    public Record(String line, RoIdCardParser idCardParser, RoRegPlateParser regPlateParser) {
        this.line = line;
        this.idCardParser = idCardParser;
        this.regPlateParser = regPlateParser;
    }
    
    @Override
    public RoIdCardProperties getIdCard() {
        //impartim celulele din CSV si le punem intr-un vector
        String[] properties=line.split(";",0);
        try {
            return idCardParser.parseIdCard(properties[0]);
        } catch (InvalidRoIdCardException ex) {
            return null;
        }
    }

    @Override
    public RoRegPlateProperties getRegPlate() {
        //impartim celulele din CSV si le punem intr-un vector
        String[] properties=line.split(";",0);
        try {
            try{
                return regPlateParser.parseRegistrationPlate(properties[2]);
            } catch(ArrayIndexOutOfBoundsException e){
                return null;
            }
        } catch (InvalidRoRegPlateException ex) {
            return null;
        }
    }

    @Override
    public Date getIdCardIssueDate() {
        //impartim celulele din CSV si le punem intr-un vector
        String[] properties=line.split(";",0);
        try { 
            return new SimpleDateFormat("yyyy-MM-dd").parse(properties[1]);
        } catch (ParseException ex) {
            return null;
        }
    }
    
}

/**
 * implementarea procesatorului de date proprietati de autovehicule.
 */
public class VehicleOwnersProcessorImpl implements VehicleOwnersProcessor{
    //instances
    private RoIdCardParser idCardParser;
    private RoRegPlateParser regPlateParser;
    private Date referenceDate;
    private VehicleOwnersAggregator vehicleownersaggregator;

    /**
     *  constructor
     * @param idCardParser
     * @param regPlateParser
     * @param referenceDate
     */
    public VehicleOwnersProcessorImpl(RoIdCardParser idCardParser, RoRegPlateParser regPlateParser, Date referenceDate){
        this.idCardParser=idCardParser;
        this.regPlateParser=regPlateParser;
        this.referenceDate=referenceDate;
    }

    /**
     *  procesator de date
     * @param ciCarRegNbInputStream
     * @param processResultOutputStream
     */
    @Override
    public void process(InputStream ciCarRegNbInputStream, OutputStream processResultOutputStream) throws IOException{
        
        //initializam writer-ul si il formatam
        PrintWriter writer = new PrintWriter(processResultOutputStream);
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        //initializam reader-ul
        BufferedReader reader = new BufferedReader(new InputStreamReader(ciCarRegNbInputStream));
        
        //citim liniile din inputStream pe rand si le adaugam in aggregator daca nu sunt goale
        vehicleownersaggregator=new VehicleOwnersAggregator(referenceDate);
        String line = reader.readLine();
        while(line != null){
            if(!StringUtils.isBlank(line))
                vehicleownersaggregator=vehicleownersaggregator.aggregate(new Record(line,idCardParser,regPlateParser));
            line=reader.readLine();
        }
        
        //cream rezultatul serializabil in care adaugam agregatorul cu record-urile noastre
        SerializableResult result=new SerializableResult();
        result.setVehicleownersaggregator(vehicleownersaggregator);
        
        //stabilim valorile instantelor din rezultat
        result.oddToEvenRatio=result.getOddToEvenRatio();
        result.unregCarsCountByJud=result.getUnregCarsCountByJud();
        result.passedRegChangeDueDate=result.getPassedRegChangeDueDate();
        result.errors=result.getErrors();
        
        //scriem rezultatul
        yaml.dump(result,writer);
        
        //inchidem fisierele;
        writer.close();
        reader.close();
    }
}
