package Tests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import ro.axonsoft.internship.api.InvalidRoRegPlateException;
import ro.axonsoft.internship.api.Judet;
import ro.axonsoft.internship.impl.RoRegPlateParserImpl;

public class RoRegPlateParser_Test {
    @Test
    public void testJudet() throws InvalidRoRegPlateException{
       RoRegPlateParserImpl testParser=new RoRegPlateParserImpl();
       Judet testJudet=testParser.parseRegistrationPlate("  cj 01 cjc  ").getJudet();
       assertEquals("Should get the judet", Judet.CJ, testJudet);
    }

    @Test
    public void testLetters() throws InvalidRoRegPlateException{
       RoRegPlateParserImpl testParser=new RoRegPlateParserImpl();
       String testLetters=testParser.parseRegistrationPlate("  cj 01 cjc  ").getLetters();
       assertEquals("Should get the letters", "CJC", testLetters);
    }
    @Test
    public void testDigits() throws InvalidRoRegPlateException{
       RoRegPlateParserImpl testParser=new RoRegPlateParserImpl();
       Short testDigits=testParser.parseRegistrationPlate("  b101 c jc  ").getDigits();
       assertEquals("Should get the digits", (short)101, (short)testDigits);
    }
}

