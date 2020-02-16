package database;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import patterns.Plan;
import patterns.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandlerTest {

    private static Plan plan;
    private  static DatabaseHandler databaseHandler;
    private static String nameProject;

    @BeforeClass
    public static void createOperations() throws SQLException {
        String idUser = "10";
        plan = Plan.getInstance();
        plan.setIdUser(idUser);
        databaseHandler = new DatabaseHandler();
        nameProject = "newName";
        databaseHandler.getPlan();
    }

    @AfterClass
    public static void deleteOperations(){
    }

    @Test
    public void getUser() throws SQLException {
        User user = new User("Анастасия","Васильевна", "nas666", "wtf666");
        ResultSet expected = databaseHandler.getUser(user);
        Assert.assertFalse(expected.next());
    }

    @Test
    public void getUser2() throws SQLException {
        User user = new User("admin","1", "1234", "admin");
        ResultSet expected = databaseHandler.getUser(user);
        Assert.assertTrue(expected.next());
    }

    @Test
    public void getListProjects() throws SQLException {
        ResultSet expected = databaseHandler.getListProjects(plan);
        Assert.assertTrue(expected.next());
    }

    @Test
    public void deleteProject() throws SQLException {
        boolean expected = false;
        databaseHandler.addProject(plan,nameProject);
        databaseHandler.deleteProject(nameProject);
        ResultSet resultSet = databaseHandler.getListProjects(plan);
        while (resultSet.next()) {
            if( resultSet.getString(2).equals(nameProject)){
                expected = true;
            }
        }
        Assert.assertFalse(expected);
    }

    @Test
    public void getTomorrowTasks() {
        boolean expected = false;
        if(plan.getTomorrowTask() == null){
            expected = true;
        }
        Assert.assertTrue(expected);
    }

    @Test
    public void getTodayTasks() {
        boolean expected = false;
        if(plan.getTodayTask() == null){
            expected = true;
        }
        Assert.assertTrue(expected);
    }
}