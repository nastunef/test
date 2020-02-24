package patterns;

public class Task {
    private String id;
    private String name;
    private final String idProject;
    private String date;
    private String status;

    public String getIdProject() {
        return idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Task(String id, String name, String idProject, String date, String status) {
        this.id = id;
        this.name = name;
        this.idProject = idProject;
        this.date = date;
        this.status = status;
    }
    public Task( String name, String idProject, String date, String status) {

        this.name = name;
        this.idProject = idProject;
        this.date = date;
        this.status = status;
    }

}
