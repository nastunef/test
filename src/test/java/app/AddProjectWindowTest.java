package app;

import database.DatabaseHandler;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import patterns.Plan;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddProjectWindowTest {
    private static Plan plan;
    private static DatabaseHandler databaseHandler;
    private static String nameProject;

    @BeforeClass
    public static void createOperations() {
        String idUser = "10";
        plan = Plan.getInstance();
        plan.setIdUser(idUser);
        nameProject = "newProject";
        databaseHandler  = new DatabaseHandler();

    }
    @AfterClass
    public static void deleteRow(){
        databaseHandler.deleteProject(nameProject);
    }

    @Test
    public void addInDataBase() throws SQLException {

        databaseHandler.addProject(plan,nameProject);
        boolean expected = false;
        ResultSet resultSet = databaseHandler.getListProjects(plan);
        while (resultSet.next()) {
            if( resultSet.getString(2).equals(nameProject)){
                expected = true;
            }
        }
        Assert.assertTrue(expected);
    }
}