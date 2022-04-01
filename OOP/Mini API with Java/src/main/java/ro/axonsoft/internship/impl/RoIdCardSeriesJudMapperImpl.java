
package ro.axonsoft.internship.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import ro.axonsoft.internship.api.InvalidRoIdCardSeriesException;
import ro.axonsoft.internship.api.Judet;
import ro.axonsoft.internship.api.RoIdCardSeriesJudMapper;

/**
 * implementarea interfetei ce mapeaza seria cartii de identitate cu judetul corespunzator
 */
public class RoIdCardSeriesJudMapperImpl implements RoIdCardSeriesJudMapper{
    
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
    @Override
    public Judet mapIdCardToJud(String idCardSeries) throws InvalidRoIdCardSeriesException{
        //citim din fisierul jcis.yml
        InputStream inputStream=null;
        try {
            inputStream = new FileInputStream(new File("jcis.yml"));
        } catch (FileNotFoundException ex) {
            throw new InvalidRoIdCardSeriesException();
        }
        //cream mapa cu ajutorul bibliotecii SnakeYaml
        Yaml yaml = new Yaml();
        Map<String, ArrayList<String>> data = (Map<String, ArrayList<String>>) yaml.load(inputStream);
        //inchidem fisierul
        try {
            inputStream.close();
        } catch (IOException ex) {
            throw new InvalidRoIdCardSeriesException();
        }
        //afisam judetul corespunzator
        for (String key : data.keySet())
            if (data.get(key).contains(idCardSeries))
                return Judet.valueOf(key);
        return null;
        
        /* VARIANTA FARA CITIRE DIN FISIER
        switch (idCardSeries){
            case "AX":
                return Judet.AB;
            case "TR":
                return Judet.TR;
            case "AR":
                return Judet.AR;
            case "ZR":
                return Judet.AR;
            case "XC":
                return Judet.BC;
            case "ZC":
                return Judet.BC;
            case "MM":
                return Judet.MM;
            case "XM":
                return Judet.MM;
            case "XB":
                return Judet.BN;
            case "XT":
                return Judet.BT;
            case "BV":
                return Judet.BV;
            case "ZV":
                return Judet.BV;
            case "XR":
                return Judet.BR;
            case "DP":
                return Judet.B;
            case "DR":
                return Judet.B;
            case "DT":
                return Judet.B;
            case "RD":
                return Judet.B;
            case "RR":
                return Judet.B;
            case "RT":
                return Judet.B;
            case "RX":
                return Judet.B;
            case "RK":
                return Judet.B;
            case "IF":
                return Judet.IF;
            case "XZ":
                return Judet.BZ;
            case "KL":
                return Judet.CL;
            case "KX":
                return Judet.CJ;
            case "CJ":
                return Judet.CJ;
            case "KT":
                return Judet.CT;
            case "KZ":
                return Judet.CT;
            case "DX":
                return Judet.DJ;
            case "DZ":
                return Judet.DJ;
            case "HD":
                return Judet.HD;
            case "VN":
                return Judet.VN;
            case "GL":
                return Judet.GL;
            case "ZL":
                return Judet.GL;
            case "GG":
                return Judet.GR;
            case "MX":
                return Judet.IS;
            case "MZ":
                return Judet.IS;
            case "IZ":
                return Judet.IS;
            case "MH":
                return Judet.MH;
            case "HR":
                return Judet.HR;
            case "ZH":
                return Judet.BH;
            case "XH":
                return Judet.BH;
            case "NT":
                return Judet.NT;
            case "AS":
                return Judet.AG;
            case "AZ":
                return Judet.AG;
            case "PH":
                return Judet.PH;
            case "PX":
                return Judet.PH;
            case "KS":
                return Judet.CS;
            case "VX":
                return Judet.VL;
            case "SM":
                return Judet.SM;
            case "KV":
                return Judet.CV;
            case "SB":
                return Judet.SB;
            case "OT":
                return Judet.OT;
            case "SZ":
                return Judet.IL;
            case "SV":
                return Judet.SV;
            case "XV":
                return Judet.SV;
            case "TM":
                return Judet.TM;
            case "TZ":
                return Judet.TM;
            case "DD":
                return Judet.DB;
            case "GZ":
                return Judet.GJ;
            case "ZS":
                return Judet.MS;
            case "MS":
                return Judet.MS;
            case "TC":
                return Judet.TL;
            case "VS":
                return Judet.VS;
            case "SX":
                return Judet.SJ;
            default:
                throw new InvalidRoIdCardSeriesException();          
        }
*/
    }
}
