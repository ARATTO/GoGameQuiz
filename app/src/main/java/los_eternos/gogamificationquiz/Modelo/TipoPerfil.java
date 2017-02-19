package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class TipoPerfil {
    private String idTipoPerfil;
    private String nombreTipoPerfil;
    private String descripcionTipoPerfil;
    /*private String esprivado;
    private String esgeneral;
    private String espublico;*/

    public String getIdTipoPerfil() {
        return idTipoPerfil;
    }

    public void setIdTipoPerfil(String idTipoPerfil) {
        this.idTipoPerfil = idTipoPerfil;
    }

    public String getNombreTipoPerfil() {
        return nombreTipoPerfil;
    }

    public void setNombreTipoPerfil(String nombreTipoPerfil) {
        this.nombreTipoPerfil = nombreTipoPerfil;
    }

    public String getDescripcionTipoPerfil() {
        return descripcionTipoPerfil;
    }

    public void setDescripcionTipoPerfil(String descripcionTipoPerfil) {
        this.descripcionTipoPerfil = descripcionTipoPerfil;
    }

    public TipoPerfil() {

    }

    public TipoPerfil(String idTipoPerfil, String nombreTipoPerfil, String descripcionTipoPerfil) {

        this.idTipoPerfil = idTipoPerfil;
        this.nombreTipoPerfil = nombreTipoPerfil;
        this.descripcionTipoPerfil = descripcionTipoPerfil;
    }
}
