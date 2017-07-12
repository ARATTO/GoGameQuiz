package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 02/10/2016.
 */
public class MostrarMedallas {
    private String nommedalla;
    private String foto;
    private String id_medalla;

    public String getId_medalla() {
        return id_medalla;
    }

    public void setId_medalla(String id_medalla) {
        this.id_medalla = id_medalla;
    }

    public MostrarMedallas(String nommedalla, String foto) {
        this.nommedalla = nommedalla;
        this.foto = foto;
    }

    public MostrarMedallas() {
    }

    public String getNommedalla() {
        return nommedalla;
    }

    public void setNommedalla(String nommedalla) {
        this.nommedalla = nommedalla;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
