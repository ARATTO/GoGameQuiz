package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class DetallePuntoActividad {
    private int idDetallePuntoActividad;
    private int idPerfil;
    private int idActividad;
    private int puntajeAcumulado;

    public int getIdDetallePuntoActividad() {
        return idDetallePuntoActividad;
    }

    public void setIdDetallePuntoActividad(int idDetallePuntoActividad) {
        this.idDetallePuntoActividad = idDetallePuntoActividad;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getPuntajeAcumulado() {
        return puntajeAcumulado;
    }

    public void setPuntajeAcumulado(int puntajeAcumulado) {
        this.puntajeAcumulado = puntajeAcumulado;
    }

    public DetallePuntoActividad() {

    }

    public DetallePuntoActividad(int idDetallePuntoActividad, int idPerfil, int idActividad, int puntajeAcumulado) {

        this.idDetallePuntoActividad = idDetallePuntoActividad;
        this.idPerfil = idPerfil;
        this.idActividad = idActividad;
        this.puntajeAcumulado = puntajeAcumulado;
    }
}
