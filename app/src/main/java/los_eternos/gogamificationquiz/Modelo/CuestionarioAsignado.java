package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class CuestionarioAsignado {
    private int idCuestionarioAsignado;
    private int idPerfil;
    private int idCuestionario;

    public CuestionarioAsignado(int idCuestionarioAsignado, int idCuestionario, int idPerfil) {
        this.idCuestionarioAsignado = idCuestionarioAsignado;
        this.idCuestionario = idCuestionario;
        this.idPerfil = idPerfil;
    }

    public CuestionarioAsignado() {
    }

    public int getIdCuestionarioAsignado() {
        return idCuestionarioAsignado;
    }

    public void setIdCuestionarioAsignado(int idCuestionarioAsignado) {
        this.idCuestionarioAsignado = idCuestionarioAsignado;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }
}
