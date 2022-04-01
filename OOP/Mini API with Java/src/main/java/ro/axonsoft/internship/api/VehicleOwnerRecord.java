
package ro.axonsoft.internship.api;

import java.util.Date;

/**
 *  Interfata ce contine proprietatile unui Owner (pot fi accesate prin getteri)
 */
public interface VehicleOwnerRecord {
    // getters for record properties

    /**
     * @return proprietatile IdCard-ului
     */
    public RoIdCardProperties getIdCard();

    /**
     * @return proprietatile RegPlate-ului
     */
    public RoRegPlateProperties getRegPlate();

    /**
     * @return data emiterii IdCard-ului
     */
    public Date getIdCardIssueDate();
}
