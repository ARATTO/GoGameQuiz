package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class Materia {
    private int idMateria;
    private String codigoMateria;
    private String nombreMateria;
    private int esTecnicaElectiva;
    private String imagenMateria;

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getEsTecnicaElectiva() {
        return esTecnicaElectiva;
    }

    public void setEsTecnicaElectiva(int esTecnicaElectiva) {
        this.esTecnicaElectiva = esTecnicaElectiva;
    }

    public String getImagenMateria() {
        return imagenMateria;
    }

    public void setImagenMateria(String imagenMateria) {
        this.imagenMateria = imagenMateria;
    }

    public Materia(int idMateria, String codigoMateria, String nombreMateria, int esTecnicaElectiva, String imagenMateria) {

        this.idMateria = idMateria;
        this.codigoMateria = codigoMateria;
        this.nombreMateria = nombreMateria;
        this.esTecnicaElectiva = esTecnicaElectiva;
        this.imagenMateria = imagenMateria;
    }

    public Materia() {

    }
}
