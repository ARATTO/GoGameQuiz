package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class Respuesta {
    private int idRespuesta;
    private int idPregunta;
    private int esCorrecta;
    private String Alternativas;

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(int esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public String getAlternativas() {
        return Alternativas;
    }

    public void setAlternativas(String alternativas) {
        Alternativas = alternativas;
    }

    public Respuesta(int idRespuesta, int idPregunta, int esCorrecta, String alternativas) {

        this.idRespuesta = idRespuesta;
        this.idPregunta = idPregunta;
        this.esCorrecta = esCorrecta;
        Alternativas = alternativas;
    }

    public Respuesta() {

    }
}
