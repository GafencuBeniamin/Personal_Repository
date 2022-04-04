
package ro.axonsoft.internship.api;

/**
 *  Clasa de eroare careia i se face throw in cazul unui RegistrationPlate invalid
 */
public class InvalidRoRegPlateException extends Throwable{
    
    public InvalidRoRegPlateException(){
        super();
    }
    public InvalidRoRegPlateException(String message){
        super(message);
    }
    
}
