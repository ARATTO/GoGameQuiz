package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class Estudiante {
    private String carnetEstudiante;
    private int idPerfil;
    private String nombreEstudiante;

    public Estudiante(String carnetEstudiante, int idPerfil, String nombreEstudiante) {
        this.carnetEstudiante = carnetEstudiante;
        this.idPerfil = idPerfil;
        this.nombreEstudiante = nombreEstudiante;
    }

    public Estudiante() {
    }

    public String getCarnetEstudiante() {
        return carnetEstudiante;
    }

    public void setCarnetEstudiante(String carnetEstudiante) {
        this.carnetEstudiante = carnetEstudiante;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }
}
