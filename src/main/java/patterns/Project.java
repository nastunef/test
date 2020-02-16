package patterns;

public class Project {
    private String id;
    private String nameProject;
    private String dateFrom;
    private String dateBefore;
    private String idUser;

    public Project(String id, String nameProject, String dateFrom, String dateBefore, String idUser) {
        this.id = id;
        this.nameProject = nameProject;
        this.dateFrom = dateFrom;
        this.dateBefore = dateBefore;
        this.idUser = idUser;
    }

    public Project(String id, String nameProject, String idUser) {
        this.id = id;
        this.nameProject = nameProject;
        this.idUser = idUser;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateBefore(String dateBefore) {
        this.dateBefore = dateBefore;
    }

    public String getId() {
        return id;
    }

    public String getNameProject() {
        return nameProject;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateBefore() {
        return dateBefore;
    }

    public String getIdUser() {
        return idUser;
    }



}
