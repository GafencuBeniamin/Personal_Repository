
package ro.axonsoft.internship.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ro.axonsoft.internship.api.VehicleOwnerRecord;

/**
 * agregator de Owner records
 */
public class VehicleOwnersAggregator{
    private Date referenceDate;
    private List<VehicleOwnerRecord> records;
    /**
     * constructor
     * @param referenceDate
     */
    public VehicleOwnersAggregator(Date referenceDate){
        this.records = new ArrayList<VehicleOwnerRecord>();
        this.referenceDate=referenceDate;
    }
    // getters

    /**
     * @return data de referinta
     */
    public Date getReferenceDate() {
        return referenceDate;
    }
    
    /**
     * @return lista cu Owner Records
     */
    public List<VehicleOwnerRecord> getRecords() {
        return records;
    }
    
    /**
     * functie ce agrega un nou element in lista de Owner Records
     * @param record
     * @return
     */
    public VehicleOwnersAggregator aggregate(VehicleOwnerRecord record){
        records.add(record);
        return this;
    }
}
