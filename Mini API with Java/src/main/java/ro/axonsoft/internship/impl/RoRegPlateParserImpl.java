
package ro.axonsoft.internship.impl;

import org.apache.commons.lang3.EnumUtils;
import ro.axonsoft.internship.api.InvalidRoRegPlateException;
import ro.axonsoft.internship.api.Judet;
import ro.axonsoft.internship.api.RoRegPlateParser;
import ro.axonsoft.internship.api.RoRegPlateProperties;
import ro.axonsoft.internship.api.RoIdCardSeriesJudMapper;
/**
 * implementarea interfetei de proprietati necesara la conceperea filtrului pentru RegPlates
 */
class PropertiesRegPlate implements RoRegPlateProperties{

    private Judet judet;
    private Short digits;
    private String letters;
    @Override
    public Judet getJudet() {
        return judet;
    }

    @Override
    public Short getDigits() {
        return digits;
    }

    @Override
    public String getLetters() {
        return letters;
    }

    public void setJudet(Judet judet) {
        this.judet = judet;
    }

    public void setDigits(Short digits) {
        this.digits = digits;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }
    
}

/**
 *  implementarea filtrului de RegPlate
 */
public class RoRegPlateParserImpl implements RoRegPlateParser{
    public RoIdCardSeriesJudMapper RoRegPlateParserImpl(RoIdCardSeriesJudMapper idCardSeriesJudMapper){
        return null;
    }

    /**
     *
     * @param registrationPlate
     *          -String cu continutul placutei de inmatriculare
     * @return proprietatile placutiei de inmatriculare (Judet, Digiti, Litere)
     * @throws InvalidRoRegPlateException
     *          daca continutul placutei de inmatriculare este invalid
     */
    @Override
    public RoRegPlateProperties parseRegistrationPlate(String registrationPlate)throws InvalidRoRegPlateException{
        PropertiesRegPlate reg=new PropertiesRegPlate();
        //delete spaces
        registrationPlate = registrationPlate.replaceAll(" ", "");
        //verificam lungimea
        if (registrationPlate.length() != 7)
            throw new InvalidRoRegPlateException("Invalid regPlate length");
        //punem tot pe uppercase
        registrationPlate= registrationPlate.toUpperCase();
        //setam judetul in cazul in care e format din 2 litere (NU Bucuresti)
        if (EnumUtils.isValidEnum( Judet.class, registrationPlate.substring(0,2) )){
            reg.setJudet(Judet.valueOf(registrationPlate.substring(0,2)));
            //setam digitii (2 digiti)
            try{
                reg.setDigits((short)Integer.parseInt(registrationPlate.substring(2,4)));
            }
            catch (NumberFormatException ex){
                throw new InvalidRoRegPlateException("Invalid Number on RegPlate"+Integer.parseInt(registrationPlate.substring(2,4)));
            }
        }
        //CAZ BUCURESTI
        else{
            if (registrationPlate.substring(0,1).equals("B")){
                reg.setJudet(Judet.B);
                //setam digitii (3 digiti)
                try{
                    reg.setDigits((short)Integer.parseInt(registrationPlate.substring(1,4)));
                }
                catch (NumberFormatException ex){
                    throw new InvalidRoRegPlateException("Invalid Number on RegPlate"+Integer.parseInt(registrationPlate.substring(2,4)));
                }       
            }
            else throw new InvalidRoRegPlateException("Invalid Jud on RegPlate");
        }
        //setam literele
        boolean result = registrationPlate.substring(4).matches("[A-Z]+");
        if (result)
            reg.setLetters(registrationPlate.substring(4));
        else throw new InvalidRoRegPlateException("Invalid Letters on RegPlate");
            
        return reg;
    }
}
