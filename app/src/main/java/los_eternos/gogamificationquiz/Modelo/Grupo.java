package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class Grupo {
    private int idGrupo;
    private int idDocente;
    private int idTipoGrupo;
    private int idMateriaImpartida;
    private String codigoGrupo;

    public Grupo() {
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public int getIdTipoGrupo() {
        return idTipoGrupo;
    }

    public void setIdTipoGrupo(int idTipoGrupo) {
        this.idTipoGrupo = idTipoGrupo;
    }

    public int getIdMateriaImpartida() {
        return idMateriaImpartida;
    }

    public void setIdMateriaImpartida(int idMateriaImpartida) {
        this.idMateriaImpartida = idMateriaImpartida;
    }

    public String getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(String codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public Grupo(int idGrupo, int idDocente, int idTipoGrupo, int idMateriaImpartida, String codigoGrupo) {

        this.idGrupo = idGrupo;
        this.idDocente = idDocente;
        this.idTipoGrupo = idTipoGrupo;
        this.idMateriaImpartida = idMateriaImpartida;
        this.codigoGrupo = codigoGrupo;
    }
}
