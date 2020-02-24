package database;

public class Const {

    private Const() {
        throw new IllegalStateException("Utility class");
    }

    public static final String USER_TABLE = "users";
    public static final String USERS_ID = "idusers";
    public static final String USERS_FIRSTNAME = "firstname";
    public static final String USERS_LASTNAME = "lastname";
    public static final String USERS_USERNAME = "username";
    public static final String USERS_PASSWORD = "password";

    public static final String PROJECT_TABLE = "project";
    public static final String PROJECT_ID = "idproject";
    public static final String PROJECT_ID_USER = "iduser";
    public static final String PROJECT_NAME = "name";
    public static final String PROJECT_DATA_FROM = "datefrom";
    public static final String PROJECT_DATA_BEFORE = "datebefor";
    public static final String PROJECT_TYPE ="projecttype";

    public static final String PROJECT_TYPE_PROJECT = "project";
    public static final String PROJECT_TYPE_CURRICULUM = "curriculum";

    public static final String TASK_TABLE = "task";
    public static final String TASK_ID = "idtask";

    public static final String TASK_ID_PROJECT = "idproject";
    public static final String TASK_TEXT = "text";
    public static final String TASK_STATE = "state";
    public static final String TASK_ID_USER = "iduser";
    public static final String TASK_DATE_STRING = "taskdate";
    public static final String TASK_DAY_OF_WEEK = "dayofweek";
    public static final int TASK_COLUMN_DATE_TASK = 6;
    public static final int TASK_COLUMN_TEXT_TASK = 3;

    public static final String TASK_STATUS_DONE = "Выполнена";
    public static final String TASK_STATUS_NOT_EXECUTABLE = "Не выполнена";

    public static final String TASK_NOT_PROJECT = "не указан";

    public static final String INSERT_INTO = "INSERT INTO ";
    public static final String WHERE = " WHERE ";
    public static final String SELECT_ALL_FROM = "SELECT * FROM ";
    public static final String EQUAL_QUATION_AND = "=? AND ";
    public static final String EQUAL_QUATION = "=?;";
}
