package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class Pregunta {
    private String idPregunta;
    private int idCategoria;
    private int idTipoPregunta;
    private String Pregunta;

    public String getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(String idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdTipoPregunta() {
        return idTipoPregunta;
    }

    public void setIdTipoPregunta(int idTipoPregunta) {
        this.idTipoPregunta = idTipoPregunta;
    }

    public String getPregunta() {
        return Pregunta;
    }

    public void setPregunta(String pregunta) {
        Pregunta = pregunta;
    }

    public Pregunta() {

    }

    public Pregunta(String idPregunta, int idCategoria, int idTipoPregunta, String pregunta) {

        this.idPregunta = idPregunta;
        this.idCategoria = idCategoria;
        this.idTipoPregunta = idTipoPregunta;
        Pregunta = pregunta;
    }
}
