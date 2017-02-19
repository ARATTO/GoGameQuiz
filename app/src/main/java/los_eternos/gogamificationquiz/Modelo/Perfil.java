package los_eternos.gogamificationquiz.Modelo;

import java.sql.Blob;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class Perfil {
    private int idPerfil;
    private int idEstudiante;
    private int idDocente;
    private String nombrePerfil;
    private String password;
    private String imagenPerfil;
    private String descripcionPerfil;
    private String apodo;

    public Perfil() {
    }

    public Perfil(int idPerfil, int idEstudiante, int idDocente, String nombrePerfil, String password, String imagenPerfil, String descripcionPerfil, String apodo) {

        this.idPerfil = idPerfil;
        this.idEstudiante = idEstudiante;
        this.idDocente = idDocente;
        this.nombrePerfil = nombrePerfil;
        this.password = password;
        this.imagenPerfil = imagenPerfil;
        this.descripcionPerfil = descripcionPerfil;
        this.apodo = apodo;
    }

    public int getIdPerfil() {

        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }

    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }
}
