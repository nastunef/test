package database;

import app.FillingOutThePlan;
import patterns.Plan;
import patterns.Task;
import patterns.User;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHosts + ":"
                + dbPort + "/" + dbName + "?useUnicode=true&characterEncoding=utf8";

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user) throws SQLException {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USERS_FIRSTNAME + ", " + Const.USERS_LASTNAME + ", " +
                Const.USERS_USERNAME + ", " + Const.USERS_PASSWORD + ")" +
                "VALUES(?,?,?,?)";
        try{
            PreparedStatement prSt =  getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getPassword());
            prSt.executeUpdate();
           
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user) throws SQLException {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?;";

        try {
            PreparedStatement  prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        return resSet;
    }

    public void getPlan() throws SQLException {
        Plan plan = Plan.getInstance();
        getProjects(plan);
        getTodayTasks(plan);
        getTomorrowTasks(plan);
    }

    public void getProjects(Plan plan) throws SQLException {
        ResultSet resSet;
        String select = "SELECT * FROM " + Const.PROJECT_TABLE + " WHERE " +
                Const.PROJECT_ID_USER + "=?;";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, plan.getIdUser());
            resSet = prSt.executeQuery();
            FillingOutThePlan.fillingOutProjects(resSet, plan);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
    }

    public ResultSet getListProjects(Plan plan) throws SQLException {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.PROJECT_TABLE + " WHERE " +
                Const.PROJECT_ID_USER + "=?;";
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, plan.getIdUser());
            resSet = prSt.executeQuery();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        return resSet;
    }
    public void deleteProject(String nameProject){
        String select = "DELETE FROM " + Const.PROJECT_TABLE + " WHERE " +
                Const.PROJECT_NAME + "=?;";
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,nameProject);
            prSt.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void getTodayTasks(Plan plan) throws SQLException {
        ResultSet resSet;
        String todayData = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String select = "SELECT * FROM " + Const.TASK_TABLE + " WHERE " +
                Const.TASK_ID_USER + "=? AND " + Const.TASK_DATE_STRING + "=?;";
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, plan.getIdUser());
            prSt.setString(2, todayData);
            resSet = prSt.executeQuery();
            FillingOutThePlan.fillingOutTodayTask(resSet, plan);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
    }

    public ResultSet getTomorrowTasks(Plan plan) throws SQLException {
        ResultSet resSet = null;
        LocalDate todaydata = LocalDate.now();
        todaydata = todaydata.plusDays(1);
        String tommorowData = todaydata.toString();
        String select = "SELECT * FROM " + Const.TASK_TABLE + " WHERE " +
                Const.TASK_ID_USER + "=? AND " + Const.TASK_DATE_STRING + "=?;";

        try {
            PreparedStatement  prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, plan.getIdUser());
            prSt.setString(2, tommorowData);
            resSet = prSt.executeQuery();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void addProject(Plan plan, String nameNewProject) throws SQLException {
        String insert = "INSERT INTO " + Const.PROJECT_TABLE + "(" +
                Const.PROJECT_ID_USER + ", " + Const.PROJECT_NAME + ")" +
                "VALUES(?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, plan.getIdUser());
            prSt.setString(2, nameNewProject);
            prSt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addTask(Plan plan, Task task) throws SQLException {

        String insert;
        if (task.getIdProject().equals("0")) {
            insert = "INSERT INTO " + Const.TASK_TABLE + "(" +
                    Const.TASK_TEXT + ", " + Const.TASK_STATE + ", " + Const.TASK_DATE_STRING + ", " + Const.TASK_ID_USER + ")" +
                    "VALUES(?,?,?,?)";
        } else {
            insert = "INSERT INTO " + Const.TASK_TABLE + "(" +
                    Const.TASK_TEXT + ", " + Const.TASK_STATE + ", " + Const.TASK_DATE_STRING + ", " + Const.TASK_ID_USER + ", " + Const.TASK_ID_PROJECT + ")" +
                    "VALUES(?,?,?,?,?)";
        }


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, task.getName());
            prSt.setString(2, task.getStatus());
            prSt.setString(3, task.getDate());
            prSt.setString(4, plan.getIdUser());
            prSt.executeUpdate();
            if (!task.getIdProject().equals("0")) {
                prSt.setString(5, task.getIdProject());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
