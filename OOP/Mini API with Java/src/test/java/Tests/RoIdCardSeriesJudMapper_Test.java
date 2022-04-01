package Tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import ro.axonsoft.internship.api.InvalidRoIdCardSeriesException;
import ro.axonsoft.internship.api.Judet;
import ro.axonsoft.internship.impl.RoIdCardSeriesJudMapperImpl;

public class RoIdCardSeriesJudMapper_Test {
    @Test
    public void testJudet() throws InvalidRoIdCardSeriesException{
       RoIdCardSeriesJudMapperImpl testMapper=new RoIdCardSeriesJudMapperImpl();
       Judet testJudet=testMapper.mapIdCardToJud("XV");
       assertEquals("Should get the judet", Judet.SV, testJudet);
    }
}
