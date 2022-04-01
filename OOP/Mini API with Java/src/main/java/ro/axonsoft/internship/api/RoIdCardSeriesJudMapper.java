
package ro.axonsoft.internship.api;

/**
 *  Interfata ce mapeaza seria cartii de identitate cu judetul corespunzator
 */
public interface RoIdCardSeriesJudMapper {
    /*
    *
    *   Functia mapeaza seria cartii de identitate de la intrare cu judetul corespunzator
    *
    * @param idCardSeries
    *            - string de intrare pentru a mapa o serie de carte de identitate
    * @throws InvalidRoIdCardSeriesException
    *            dacă apare o eroare în procesare
    * @return judetul corespunzator seriei de carte de identitate
    */
    public Judet mapIdCardToJud(String idCardSeries) throws InvalidRoIdCardSeriesException;
}
