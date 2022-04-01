
package ro.axonsoft.internship.impl;


import ro.axonsoft.internship.api.InvalidRoIdCardException;
import ro.axonsoft.internship.api.InvalidRoIdCardSeriesException;
import ro.axonsoft.internship.api.Judet;
import ro.axonsoft.internship.api.RoIdCardParser;
import ro.axonsoft.internship.api.RoIdCardProperties;
import ro.axonsoft.internship.api.RoIdCardSeriesJudMapper;
/**
 * implementarea interfetei de proprietati necesara la conceperea filtrului pentru IdCards
 */
class PropertiesCard implements RoIdCardProperties{
    //instances
    private Judet judet;
    private String series;
    private Integer number;
    //getters override
    @Override
    public Judet getJudet() {
        return this.judet;
    }

    @Override
    public String getSeries() {
       return this.series;
    }

    @Override
    public Integer getNumber() {
        return this.number;
    }
    //setters
    public void setJudet(Judet judet) {
        this.judet = judet;
    }

    public void setSeries(String serie) {
        this.series = serie;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    
    
}
        
/**
 * implementarea filtrului pentru IdCards
 */
public class RoIdCardParserImpl implements RoIdCardParser{
    /**
     *
     * @param idCard
     *          -String de intrare ce contine datele cardului
     * @return proprietatile cardului (Judet,Series,Number)
     * @throws InvalidRoIdCardException
     *          daca datele cartii de identitate sunt invalide
     */
    @Override
    public RoIdCardProperties parseIdCard(String idCard) throws InvalidRoIdCardException{ 
        
        RoIdCardSeriesJudMapper roIdCardSeriesJudMapper= new RoIdCardSeriesJudMapperImpl();
        PropertiesCard card=new PropertiesCard();
        
        //delete spaces
        idCard = idCard.replaceAll(" ", "");
        if (idCard.length()!=8)
            throw new InvalidRoIdCardException("Invalid length of IdCard");
        
        //assign to judet the parser's result
        try {
            card.setJudet(roIdCardSeriesJudMapper.mapIdCardToJud(idCard.substring(0,2).toUpperCase()));
            //assign to Series the parser's result if judet was valid
            card.setSeries(idCard.substring(0,2).toUpperCase());
        } catch (InvalidRoIdCardSeriesException ex) {
            throw new InvalidRoIdCardException("Invalid Series on IdCard");
        }
        
        //assign to Number thee parser's result
        try{
            card.setNumber(Integer.parseInt(idCard.substring(2)));
        }
        catch (NumberFormatException ex){
            throw new InvalidRoIdCardException("Invalid Number on IdCard");
        }
        return card;
    }
}
