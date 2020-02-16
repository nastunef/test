package app;

import database.DatabaseHandler;
import org.junit.Assert;
import org.junit.Test;
import patterns.Plan;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FillingOutThePlanTest {

    @Test
    public void resultSetSize() throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Plan plan = Plan.getInstance();
        plan.setIdUser("10");
        ResultSet resultSet = databaseHandler.getTomorrowTasks(plan);
        int expected = FillingOutThePlan.resultSetSize(resultSet);
        int actual = 0;
        Assert.assertEquals(expected, actual);
    }
}