package app;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import patterns.Plan;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;



public class AddTaskWindowTest {

    @BeforeClass
    public static void createOperations() {
        String idUser = "10";
        Plan plan = Plan.getInstance();
        plan.setIdUser(idUser);
    }

    @AfterClass
    public static void deleteOperations(){ }

    @Test
    public void getListNameProjects() throws SQLException {
        Map<String, String> expected =  new HashMap<>();
        expected.put("pattern", "12");
        expected.put("кукушка", "15");

        Map<String,String> actual = FillingOutThePlan.getAllProjects();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void isEmptyString() {
        boolean expected = AddTaskWindow.isEmptyString("",null);
        Assert.assertTrue(expected);
    }
}