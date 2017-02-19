package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class DetalleMedalla {
    private int idDetalleMedalla;
    private int idMedalla;
    private int idMateria;
    private int cantidadMinimaPuntos;

    public int getIdDetalleMedalla() {
        return idDetalleMedalla;
    }

    public void setIdDetalleMedalla(int idDetalleMedalla) {
        this.idDetalleMedalla = idDetalleMedalla;
    }

    public int getIdMedalla() {
        return idMedalla;
    }

    public void setIdMedalla(int idMedalla) {
        this.idMedalla = idMedalla;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getCantidadMinimaPuntos() {
        return cantidadMinimaPuntos;
    }

    public void setCantidadMinimaPuntos(int cantidadMinimaPuntos) {
        this.cantidadMinimaPuntos = cantidadMinimaPuntos;
    }

    public DetalleMedalla() {

    }

    public DetalleMedalla(int idDetalleMedalla, int idMedalla, int idMateria, int cantidadMinimaPuntos) {

        this.idDetalleMedalla = idDetalleMedalla;
        this.idMedalla = idMedalla;
        this.idMateria = idMateria;
        this.cantidadMinimaPuntos = cantidadMinimaPuntos;
    }
}
