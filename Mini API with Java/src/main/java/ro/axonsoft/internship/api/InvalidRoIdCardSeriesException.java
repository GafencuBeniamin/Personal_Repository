
package ro.axonsoft.internship.api;

/**
 *  Clasa de eroare careia i se face throw in cazul unei serii de IdCard invalide
 * 
 */
public class InvalidRoIdCardSeriesException extends Throwable{
    
    public InvalidRoIdCardSeriesException(){
        super();
    }
    public InvalidRoIdCardSeriesException(String message){
        super(message);
    }
    
}
