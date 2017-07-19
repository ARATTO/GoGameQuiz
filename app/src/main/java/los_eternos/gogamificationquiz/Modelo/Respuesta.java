package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class Respuesta {
    private int idRespuesta;
    private String idPregunta;
    private String esCorrecta;
    private String Alternativas;

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(String idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(String esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public String getAlternativas() {
        return Alternativas;
    }

    public void setAlternativas(String alternativas) {
        Alternativas = alternativas;
    }

    public Respuesta(int idRespuesta, String idPregunta, String esCorrecta, String alternativas) {

        this.idRespuesta = idRespuesta;
        this.idPregunta = idPregunta;
        this.esCorrecta = esCorrecta;
        Alternativas = alternativas;
    }

    public Respuesta() {

    }
}
