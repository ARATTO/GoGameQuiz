package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class MateriaImpartida {
    private int idMateriaImpartida;
    private int idCiclo;
    private int idMateria;


    public MateriaImpartida() {
    }

    public int getIdMateriaImpartida() {
        return idMateriaImpartida;
    }

    public void setIdMateriaImpartida(int idMateriaImpartida) {
        this.idMateriaImpartida = idMateriaImpartida;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public MateriaImpartida(int idMateriaImpartida, int idCiclo, int idMateria) {

        this.idMateriaImpartida = idMateriaImpartida;
        this.idCiclo = idCiclo;
        this.idMateria = idMateria;
    }
}
