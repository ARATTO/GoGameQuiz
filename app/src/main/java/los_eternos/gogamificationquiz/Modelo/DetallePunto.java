package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class DetallePunto {
    private int idDetallePunto;
    private int idTipoPunto;
    private int idMateriaImpartida;
    private int estaActivoPunto;
    private int puntosActividad;

    public int getIdDetallePunto() {
        return idDetallePunto;
    }

    public void setIdDetallePunto(int idDetallePunto) {
        this.idDetallePunto = idDetallePunto;
    }

    public int getIdTipoPunto() {
        return idTipoPunto;
    }

    public void setIdTipoPunto(int idTipoPunto) {
        this.idTipoPunto = idTipoPunto;
    }

    public int getIdMateriaImpartida() {
        return idMateriaImpartida;
    }

    public void setIdMateriaImpartida(int idMateriaImpartida) {
        this.idMateriaImpartida = idMateriaImpartida;
    }

    public int getEstaActivoPunto() {
        return estaActivoPunto;
    }

    public void setEstaActivoPunto(int estaActivoPunto) {
        this.estaActivoPunto = estaActivoPunto;
    }

    public int getPuntosActividad() {
        return puntosActividad;
    }

    public void setPuntosActividad(int puntosActividad) {
        this.puntosActividad = puntosActividad;
    }

    public DetallePunto() {

    }

    public DetallePunto(int idDetallePunto, int idTipoPunto, int idMateriaImpartida, int estaActivoPunto, int puntosActividad) {

        this.idDetallePunto = idDetallePunto;
        this.idTipoPunto = idTipoPunto;
        this.idMateriaImpartida = idMateriaImpartida;
        this.estaActivoPunto = estaActivoPunto;
        this.puntosActividad = puntosActividad;
    }
}
