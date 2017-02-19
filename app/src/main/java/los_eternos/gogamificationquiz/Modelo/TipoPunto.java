package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class TipoPunto {
    private int idTipoPunto;
    private int nombreTipoPunto;

    public TipoPunto() {
    }

    public TipoPunto(int idTipoPunto, int nombreTipoPunto) {

        this.idTipoPunto = idTipoPunto;
        this.nombreTipoPunto = nombreTipoPunto;
    }

    public int getIdTipoPunto() {

        return idTipoPunto;
    }

    public void setIdTipoPunto(int idTipoPunto) {
        this.idTipoPunto = idTipoPunto;
    }

    public int getNombreTipoPunto() {
        return nombreTipoPunto;
    }

    public void setNombreTipoPunto(int nombreTipoPunto) {
        this.nombreTipoPunto = nombreTipoPunto;
    }
}
