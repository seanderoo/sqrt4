package sqrt4.mijninzet.model.Beschikbaarheid;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DagTest {

    @Test
    public void nlDagNaarJava() {
        //arrange
        String a = "maandag";
        String b = "dinsdag";

        String c = "zondag"; //systeem is gebasseerd op maandag tot en met vrijdag

        //Zondag = 1, maandag = 2, etc..
        int expectedA = 2;//verwacht maandag
        int expectedB = 3;// verwacht dinsdag
        int expectedC = 7;// verwacht zondag, echter staat niet in onze range van dagen.

        //activate
        Dag dagA = new Dag("maandag", 20, 2019);
        Dag dagB = new Dag("dinsdag", 12, 2015);
        Dag dagC = new Dag(a,10,2019);
        int result = dagA.nederlandsDagNaarJava(a);
        int resultB = dagB.nederlandsDagNaarJava(b);
        int resultC = dagC.nederlandsDagNaarJava(c);

        //assert
        assertEquals(expectedA, result);
        assertEquals(expectedB, resultB);
        assertNotEquals(expectedC, resultC);

    }

    @Test
    public void setDatumDag() {
    }
}