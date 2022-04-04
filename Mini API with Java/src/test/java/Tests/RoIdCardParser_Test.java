package Tests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import ro.axonsoft.internship.api.InvalidRoIdCardException;
import ro.axonsoft.internship.api.Judet;
import ro.axonsoft.internship.impl.RoIdCardParserImpl;

public class RoIdCardParser_Test {
    @Test
    public void testJudet() throws InvalidRoIdCardException{
       RoIdCardParserImpl testParser=new RoIdCardParserImpl();
       Judet testJudet=testParser.parseIdCard(" k x 146 572").getJudet();
       assertEquals("Should get the judet", Judet.CJ, testJudet);
    }

    @Test
    public void testSeries() throws InvalidRoIdCardException{
       RoIdCardParserImpl testParser=new RoIdCardParserImpl();
       String testSeries=testParser.parseIdCard(" k x 146 572 ").getSeries();
       assertEquals("Should get the Series", "KX", testSeries);
    }
    @Test
    public void testNumber() throws InvalidRoIdCardException{
       RoIdCardParserImpl testParser=new RoIdCardParserImpl();
       Integer testNumber=testParser.parseIdCard("kx 14657 2").getNumber();
       assertEquals("Should get the number", (int)146572, (int)testNumber);
    }
}
