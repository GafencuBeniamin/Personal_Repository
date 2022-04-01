
package ro.axonsoft.internship.api;

/**
 *  Clasa de eroare careia i se face throw in cazul unui IdCard invalid
 * 
 */
public class InvalidRoIdCardException extends Throwable{
    public InvalidRoIdCardException(){
        super();
    }
    public InvalidRoIdCardException(String message){
        super(message);
    }
}
