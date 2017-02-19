package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class CuestionarioMateria {
    private int idCuestionarioAsignado;
    private int idMateriaImpartida;
    private int idCuestionario;

    public int getIdCuestionarioAsignado() {
        return idCuestionarioAsignado;
    }

    public void setIdCuestionarioAsignado(int idCuestionarioAsignado) {
        this.idCuestionarioAsignado = idCuestionarioAsignado;
    }

    public int getIdMateriaImpartida() {
        return idMateriaImpartida;
    }

    public void setIdMateriaImpartida(int idMateriaImpartida) {
        this.idMateriaImpartida = idMateriaImpartida;
    }

    public int getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public CuestionarioMateria() {

    }

    public CuestionarioMateria(int idCuestionarioAsignado, int idMateriaImpartida, int idCuestionario) {

        this.idCuestionarioAsignado = idCuestionarioAsignado;
        this.idMateriaImpartida = idMateriaImpartida;
        this.idCuestionario = idCuestionario;
    }
}
