
package ro.axonsoft.internship.api;
/**
 * Eroare la o linie din datele de intrare proprietari autovehicule.
 *
 */
public interface VehicleOwnerParseError {

    /**
     * Numărul liniei la care a apărut eroarea.
     */
    Integer getLine();

    /**
     * 0 - invalid line, 1 - invalid CI, 2 - invalid date
     */
    Integer getType();
}