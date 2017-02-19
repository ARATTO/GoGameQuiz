package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class OpcionSeleccionada {
    private int idOpcionSeleccionada;
    private int idCuestionarioAsignado;
    private int idPregunta;
    private int idRespuesta;

    public int getIdOpcionSeleccionada() {
        return idOpcionSeleccionada;
    }

    public void setIdOpcionSeleccionada(int idOpcionSeleccionada) {
        this.idOpcionSeleccionada = idOpcionSeleccionada;
    }

    public int getIdCuestionarioAsignado() {
        return idCuestionarioAsignado;
    }

    public void setIdCuestionarioAsignado(int idCuestionarioAsignado) {
        this.idCuestionarioAsignado = idCuestionarioAsignado;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public OpcionSeleccionada(int idOpcionSeleccionada, int idCuestionarioAsignado, int idPregunta, int idRespuesta) {

        this.idOpcionSeleccionada = idOpcionSeleccionada;
        this.idCuestionarioAsignado = idCuestionarioAsignado;
        this.idPregunta = idPregunta;
        this.idRespuesta = idRespuesta;
    }

    public OpcionSeleccionada() {

    }
}
