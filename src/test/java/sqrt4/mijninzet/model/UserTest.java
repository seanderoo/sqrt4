package sqrt4.mijninzet.model;

import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;


public class UserTest {

    @Test
    public void getRoleList() {
        //arrange
        ArrayList<String> testList = new ArrayList<>();
        User admin = new User("Admin", "Admin123", "ADMIN,DOCENT", "", "Adje", "de Admin", "");
        //expected
        testList.add("ADMIN");
        testList.add("DOCENT");
//        testList.add("Coordinator");
//        testList.add("Roosteraar");
//        testList.add("God");
        //assert
        assertEquals(testList, admin.getRoleList());
    }
}