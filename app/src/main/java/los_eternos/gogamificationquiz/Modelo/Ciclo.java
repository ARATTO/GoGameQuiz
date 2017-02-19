package los_eternos.gogamificationquiz.Modelo;

import java.util.Date;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class Ciclo {
    private int idciclo;
    private String codciclo;
    private Date desde;
    private Date hasta;
    private int estaActivo;

    public Ciclo(int idciclo, String codciclo, Date desde, Date hasta, int estaActivo) {
        this.idciclo = idciclo;
        this.codciclo = codciclo;
        this.desde = desde;
        this.hasta = hasta;
        this.estaActivo = estaActivo;
    }

    public Ciclo() {
    }

    public int getIdciclo() {

        return idciclo;
    }

    public void setIdciclo(int idciclo) {
        this.idciclo = idciclo;
    }

    public String getCodciclo() {
        return codciclo;
    }

    public void setCodciclo(String codciclo) {
        this.codciclo = codciclo;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public int getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(int estaActivo) {
        this.estaActivo = estaActivo;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }
}
