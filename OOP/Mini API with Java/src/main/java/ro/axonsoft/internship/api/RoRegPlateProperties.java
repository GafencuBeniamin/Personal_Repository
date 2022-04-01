
package ro.axonsoft.internship.api;

/**
 *  Interfata ce contine proprietatile unui RegPlate (pot fi accesate prin getteri)
 */
public interface RoRegPlateProperties {
    
    //getters

    /**
     * @return judetul de pe RegPlate
     */
    public Judet getJudet();

    /**
     * @return digitii de pe RegPlate
     */
    public Short getDigits();

    /**
     * @return literele de pe RegPlate
     */
    public String getLetters();
}
