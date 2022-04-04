
package ro.axonsoft.internship.api;

/**
 *  Interfata ce filtreaza RegistrationPlates
 */
public interface RoRegPlateParser {
    /**
     *
     * @param registrationPlate
     *          -String cu continutul placutei de inmatriculare
     * @return proprietatile placutiei de inmatriculare (Judet, Digiti, Litere)
     * @throws InvalidRoRegPlateException
     *          daca continutul placutei de inmatriculare este invalid
     */
    public RoRegPlateProperties parseRegistrationPlate(String registrationPlate) throws InvalidRoRegPlateException;
}
