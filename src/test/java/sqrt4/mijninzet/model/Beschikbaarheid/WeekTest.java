package sqrt4.mijninzet.model.Beschikbaarheid;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class WeekTest {

    @Test
    public void optellenDagenVanWeek(){
        //arrange
        int a = 20;
        int b = 2019;
        int c = 2;
        int d = 2020;
        int e = 53;
        int f = 2020;
        int g = 53;
        int h = 2019;
        int expected = 5;
        int expectedFail= 6;

        Week instanceA = new Week(a,b);
        Week instanceB = new Week(c,d);
        Week instanceC = new Week(e,f);
        Week instanceD = new Week(g,h);


        //activate
        int result = instanceA.getDaysInTheWeek();
        int resultB = instanceB.getDaysInTheWeek();
        int resultC = instanceC.getDaysInTheWeek();
        int resultD = instanceD.getDaysInTheWeek();

        //Assert
        assertEquals(expected,result);
        assertNotEquals(expectedFail, resultB);
        assertEquals(expected, resultC);
        assertEquals(expected, resultD);

    }

}