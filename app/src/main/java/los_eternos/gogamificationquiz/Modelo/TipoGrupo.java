package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class TipoGrupo {
    private String idTipoGrupo;
    private String nombreTipoGrupo;

    public TipoGrupo() {
    }

    public TipoGrupo(String idTipoGrupo, String nombreTipoGrupo) {

        this.idTipoGrupo = idTipoGrupo;
        this.nombreTipoGrupo = nombreTipoGrupo;
    }

    public String getIdTipoGrupo() {

        return idTipoGrupo;
    }

    public void setIdTipoGrupo(String idTipoGrupo) {
        this.idTipoGrupo = idTipoGrupo;
    }

    public String getNombreTipoGrupo() {
        return nombreTipoGrupo;
    }

    public void setNombreTipoGrupo(String nombreTipoGrupo) {
        this.nombreTipoGrupo = nombreTipoGrupo;
    }
}
