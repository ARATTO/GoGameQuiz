package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 14/07/2017.
 */
public class MostrarLideres {
    private String carnet;
    private String puntaje;

    public MostrarLideres(String carnet, String puntaje) {
        this.carnet = carnet;
        this.puntaje = puntaje;
    }

    public MostrarLideres() {
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }
}
