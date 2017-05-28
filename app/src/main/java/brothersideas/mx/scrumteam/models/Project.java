package brothersideas.mx.scrumteam.models;

/**
 * Created by dsantillanes on 27/05/17.
 */

public class Project {

    private String nombre;
    private String status;
    private String sprints;

    public Project(String nombre, String status, String sprints) {
        this.nombre = nombre;
        this.status = status;
        this.sprints = sprints;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSprints() {
        return sprints;
    }

    public void setSprints(String sprints) {
        this.sprints = sprints;
    }
}
