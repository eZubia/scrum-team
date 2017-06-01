package brothersideas.mx.scrumteam.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dsantillanes on 27/05/17.
 */

public class Project {

    private String _id;
    private String nombreProyecto;
    private String descripcionProy;
    private String scrumMaster;
    private String proyectManager;
    private ArrayList<Usuario> desarrolladores;
    private Boolean abierto;

    public Project(String nombreProyecto, String descripcionProy, String scrumMaster, String proyectManager, ArrayList<Usuario> desarrolladores, Boolean abierto) {
        this.nombreProyecto = nombreProyecto;
        this.descripcionProy = descripcionProy;
        this.scrumMaster = scrumMaster;
        this.proyectManager = proyectManager;
        this.desarrolladores = desarrolladores;
        this.abierto = abierto;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcionProy() {
        return descripcionProy;
    }

    public void setDescripcionProy(String descripcionProy) {
        this.descripcionProy = descripcionProy;
    }

    public String getScrumMaster() {
        return scrumMaster;
    }

    public void setScrumMaster(String scrumMaster) {
        this.scrumMaster = scrumMaster;
    }

    public String getProyectManager() {
        return proyectManager;
    }

    public void setProyectManager(String proyectManager) {
        this.proyectManager = proyectManager;
    }

    public ArrayList<Usuario> getDesarrolladores() {
        return desarrolladores;
    }

    public void setDesarrolladores(ArrayList<Usuario> desarrolladores) {
        this.desarrolladores = desarrolladores;
    }

    public Boolean getAbierto() {
        return abierto;
    }

    public void setAbierto(Boolean abierto) {
        this.abierto = abierto;
    }
}
