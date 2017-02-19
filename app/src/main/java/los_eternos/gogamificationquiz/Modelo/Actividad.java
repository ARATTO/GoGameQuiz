package los_eternos.gogamificationquiz.Modelo;

import java.util.Date;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class Actividad {
    private int idActividad;
    private int idGrupo;
    private int idDetallePunto;
    private String idTipoActividad;
    private String nombreActividad;

    public Actividad() {
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdDetallePunto() {
        return idDetallePunto;
    }

    public void setIdDetallePunto(int idDetallePunto) {
        this.idDetallePunto = idDetallePunto;
    }

    public String getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(String idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public Actividad(int idActividad, int idGrupo, int idDetallePunto, String idTipoActividad, String nombreActividad) {

        this.idActividad = idActividad;
        this.idGrupo = idGrupo;
        this.idDetallePunto = idDetallePunto;
        this.idTipoActividad = idTipoActividad;
        this.nombreActividad = nombreActividad;
    }
}
