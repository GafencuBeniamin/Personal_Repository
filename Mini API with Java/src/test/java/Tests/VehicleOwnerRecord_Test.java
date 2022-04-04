package Tests;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import ro.axonsoft.internship.api.InvalidRoIdCardException;
import ro.axonsoft.internship.api.InvalidRoRegPlateException;
import ro.axonsoft.internship.api.Judet;
import ro.axonsoft.internship.api.RoIdCardParser;
import ro.axonsoft.internship.api.RoIdCardProperties;
import ro.axonsoft.internship.api.RoRegPlateParser;
import ro.axonsoft.internship.api.RoRegPlateProperties;
import ro.axonsoft.internship.api.VehicleOwnerRecord;
import ro.axonsoft.internship.impl.RoIdCardParserImpl;
import ro.axonsoft.internship.impl.RoRegPlateParserImpl;

//copy paste la clasa din vehicle owners processors necesara pentru teste
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
            return regPlateParser.parseRegistrationPlate(properties[2]);
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
//end copypaste
public class VehicleOwnerRecord_Test {
    @Test
    public void testRecord() throws InvalidRoIdCardException{
       RoIdCardParserImpl idCardParser = new RoIdCardParserImpl();
       RoRegPlateParserImpl regPlateParser= new RoRegPlateParserImpl();
       Record testRecord=new Record("KX636141;2012-09-25;CJ84ADC;comentariu",idCardParser,regPlateParser);
       assertEquals("Should get the IdCard Series",testRecord.getIdCard().getSeries() ,"KX");
       assertEquals("Should get the IdCard Judet", testRecord.getIdCard().getJudet(), Judet.CJ);
       assertEquals("Should get the IdCard Number", (int)testRecord.getIdCard().getNumber(),636141);
    }
}
