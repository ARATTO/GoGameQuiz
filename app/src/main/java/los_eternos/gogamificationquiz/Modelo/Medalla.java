package los_eternos.gogamificationquiz.Modelo;

import java.sql.Blob;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class Medalla {
    private int idMedalla;
    private String nombreMedalla;
    private String descripcionMedalla;
    private int esCuantitativa;
    private String imagenMedalla;
    private int imagen;

    public int getIdMedalla() {
        return idMedalla;
    }

    public void setIdMedalla(int idMedalla) {
        this.idMedalla = idMedalla;
    }

    public String getNombreMedalla() {
        return nombreMedalla;
    }

    public void setNombreMedalla(String nombreMedalla) {
        this.nombreMedalla = nombreMedalla;
    }

    public String getDescripcionMedalla() {
        return descripcionMedalla;
    }

    public void setDescripcionMedalla(String descripcionMedalla) {
        this.descripcionMedalla = descripcionMedalla;
    }

    public int getEsCuantitativa() {
        return esCuantitativa;
    }

    public void setEsCuantitativa(int esCuantitativa) {
        this.esCuantitativa = esCuantitativa;
    }

    public String getImagenMedalla() {
        return imagenMedalla;
    }

    public void setImagenMedalla(String imagenMedalla) {
        this.imagenMedalla = imagenMedalla;
    }

    public Medalla() {

    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public Medalla(int idMedalla, String nombreMedalla, String descripcionMedalla, int esCuantitativa, String imagenMedalla, int imagen) {

        this.idMedalla = idMedalla;
        this.nombreMedalla = nombreMedalla;
        this.descripcionMedalla = descripcionMedalla;
        this.esCuantitativa = esCuantitativa;
        this.imagenMedalla = imagenMedalla;
        this.imagen = imagen;
    }

    public Medalla(int imagen, String Descripcion, int puntos){
        this.imagen = imagen;
        this.descripcionMedalla = Descripcion;
        this.esCuantitativa = puntos;
    }
}
