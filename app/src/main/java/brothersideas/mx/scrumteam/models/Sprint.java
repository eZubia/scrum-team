package brothersideas.mx.scrumteam.models;

/**
 * Created by erikzubia on 5/29/17.
 */

public class Sprint {

    private String _id;
    private Boolean finalizo;
    private String nombreSprint;
    private String descripcionSprint;

    public Sprint(String _id, Boolean finalizo, String nombreSprint, String descripcionSprint) {
        this._id = _id;
        this.finalizo = finalizo;
        this.nombreSprint = nombreSprint;
        this.descripcionSprint = descripcionSprint;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Boolean getFinalizo() {
        return finalizo;
    }

    public void setFinalizo(Boolean finalizo) {
        this.finalizo = finalizo;
    }

    public String getNombreSprint() {
        return nombreSprint;
    }

    public void setNombreSprint(String nombreSprint) {
        this.nombreSprint = nombreSprint;
    }

    public String getDescripcionSprint() {
        return descripcionSprint;
    }

    public void setDescripcionSprint(String descripcionSprint) {
        this.descripcionSprint = descripcionSprint;
    }

}
