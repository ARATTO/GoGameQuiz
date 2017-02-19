package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class TipoPregunta {
    private int idTipoPregunta;
    private int nombreTipoPregunta;

    public TipoPregunta() {
    }

    public TipoPregunta(int idTipoPregunta, int nombreTipoPregunta) {

        this.idTipoPregunta = idTipoPregunta;
        this.nombreTipoPregunta = nombreTipoPregunta;
    }

    public int getIdTipoPregunta() {

        return idTipoPregunta;
    }

    public void setIdTipoPregunta(int idTipoPregunta) {
        this.idTipoPregunta = idTipoPregunta;
    }

    public int getNombreTipoPregunta() {
        return nombreTipoPregunta;
    }

    public void setNombreTipoPregunta(int nombreTipoPregunta) {
        this.nombreTipoPregunta = nombreTipoPregunta;
    }
}
