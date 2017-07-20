package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 02/10/2016.
 */
public class MostrarMedallas {
    private String nommedalla;
    private String foto;
    private String puntosminimos;

    public MostrarMedallas(String nommedalla, String foto, String puntosminimos) {
        this.nommedalla = nommedalla;
        this.foto = foto;
        this.puntosminimos = puntosminimos;
    }

    public MostrarMedallas() {
    }

    public String getPuntosminimos() {
        return puntosminimos;
    }

    public void setPuntosminimos(String puntosminimos) {
        this.puntosminimos = puntosminimos;
    }

    public String getNommedalla() {
        return nommedalla;
    }

    public void setNommedalla(String nommedalla) {
        this.nommedalla = nommedalla;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
