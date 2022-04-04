
package ro.axonsoft.internship.api;

/**
 *  Filtru pentru IdCards
 */
public interface RoIdCardParser {
    
    /**
     *
     * @param idCard
     *      - seria actului de identitate
     * @return proprietatile actului de identitate (Serie, Numar, Judet)
     * @throws InvalidRoIdCardException
     *         dacă avem seria sau numarul invalid
     */
    public RoIdCardProperties parseIdCard(String idCard) throws InvalidRoIdCardException;
}
