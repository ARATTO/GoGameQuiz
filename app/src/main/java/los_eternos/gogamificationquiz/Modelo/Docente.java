package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class Docente {
    private String idDocente;
    private String nomDocente;
    private String carnetDocente;

    public Docente() {
    }

    public Docente(String idDocente, String nomDocente, String carnetDocente) {
        this.idDocente = idDocente;
        this.nomDocente = nomDocente;
        this.carnetDocente = carnetDocente;
    }

    public String getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(String idDocente) {
        this.idDocente = idDocente;
    }

    public String getNomDocente() {
        return nomDocente;
    }

    public void setNomDocente(String nomDocente) {
        this.nomDocente = nomDocente;
    }

    public String getCarnetDocente() {
        return carnetDocente;
    }

    public void setCarnetDocente(String carnetDocente) {
        this.carnetDocente = carnetDocente;
    }
}
