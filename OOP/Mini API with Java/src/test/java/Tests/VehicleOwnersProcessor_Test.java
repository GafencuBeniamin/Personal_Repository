package Tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import ro.axonsoft.internship.impl.RoIdCardParserImpl;
import ro.axonsoft.internship.impl.RoRegPlateParserImpl;
import ro.axonsoft.internship.impl.VehicleOwnersProcessorImpl;


public class VehicleOwnersProcessor_Test {
    @Test
    public void testProcess() throws IOException {
       RoIdCardParserImpl idCardParser = new RoIdCardParserImpl();
       RoRegPlateParserImpl regPlateParser= new RoRegPlateParserImpl();
       Calendar calendar = Calendar.getInstance();
       calendar.set(2022,03,29);
       Date referencedate= calendar.getTime();
       VehicleOwnersProcessorImpl api = new VehicleOwnersProcessorImpl(idCardParser,regPlateParser,referencedate);
       FileInputStream fileIn = new FileInputStream("Input.csv");
       FileOutputStream fileOut = new FileOutputStream("Output.yml");
       api.process(fileIn, fileOut);
       fileOut.close();
       fileIn.close();
       assertEquals("Should get to the assert with no errors. See if Output.yml is ok","","");
    }
}
