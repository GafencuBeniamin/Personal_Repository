
package ro.axonsoft.internship.api;

/**
 *  Interfata ce contine proprietatile unui IdCard (pot fi accesate prin getteri)
 */
public interface RoIdCardProperties {
    
    //getters for Id Card Properties

    /**
     * @return judetul din IdCard
     */
    public Judet getJudet();

    /**
     * @return seria din IdCard
     */
    public String getSeries();

    /**
     * @return numarul din IdCard
     */
    public Integer getNumber();
    
}
