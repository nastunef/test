package app;

import database.DatabaseHandler;
import patterns.Plan;
import patterns.Project;
import patterns.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FillingOutThePlan {

    private FillingOutThePlan() {
        throw new IllegalStateException("Utility class");
    }

    public static void fillingOutProjects(ResultSet resultSet, Plan plan) throws SQLException {
        int counter = resultSetSize(resultSet);
        if (counter > 0) {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                Project project = new Project(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(3));
                plan.addProject(project);
            }
        }
    }


    public static void fillingOutTodayTask(ResultSet resultSet, Plan plan) throws SQLException {
        int counter = resultSetSize(resultSet);
        if (counter > 0) {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                Task task = new Task(resultSet.getString(1),resultSet.getString(3),
                        resultSet.getString(2),resultSet.getString(6),
                        resultSet.getString(4));
                plan.addTodayTask(task);
            }
        }
    }

    public static int resultSetSize(ResultSet resultSet) throws SQLException {
        int counter = 0;

            while (resultSet.next()) {
                counter++;
            }
            return counter;
    }

    public static Map<String,String> getAllProjects() throws SQLException {
        Map<String,String> projectId = new HashMap<>();
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet projects = dbHandler.getListProjects(Plan.getInstance());

        int counter = resultSetSize(projects);
        if(counter!=0) {
            projects.beforeFirst();
            while(projects.next()){
                String nameProject = projects.getString(2);
                projectId.put(nameProject, projects.getString(1));
            }
        }
        return projectId;
    }
}
