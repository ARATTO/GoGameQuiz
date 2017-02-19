package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class MedallaGanada {
    private int idMedalla;
    private int idMedallaGanada;
    private int idPerfil;

    public int getIdMedalla() {
        return idMedalla;
    }

    public void setIdMedalla(int idMedalla) {
        this.idMedalla = idMedalla;
    }

    public int getIdMedallaGanada() {
        return idMedallaGanada;
    }

    public void setIdMedallaGanada(int idMedallaGanada) {
        this.idMedallaGanada = idMedallaGanada;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public MedallaGanada(int idMedalla, int idMedallaGanada, int idPerfil) {

        this.idMedalla = idMedalla;
        this.idMedallaGanada = idMedallaGanada;
        this.idPerfil = idPerfil;
    }

    public MedallaGanada() {

    }
}
