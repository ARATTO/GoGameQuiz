package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class Materia {
    private String idMateria;
    private String codigoMateria;
    private String nombreMateria;
    private int esTecnicaElectiva;
    private String imagenMateria;
    private String idGrupo;
    private String nombreDocente;
    private String nomTipoGrupo;
    private String codigoGrupo;
    private String codigoCiclo;

    public Materia() {
    }

    public Materia(String idMateria, String codigoMateria, String nombreMateria, String imagenMateria, int esTecnicaElectiva, String nomTipoGrupo) {
        this.idMateria = idMateria;
        this.codigoMateria = codigoMateria;
        this.nombreMateria = nombreMateria;
        this.imagenMateria = imagenMateria;
        this.esTecnicaElectiva = esTecnicaElectiva;
        this.nomTipoGrupo = nomTipoGrupo;
    }

    public Materia(String nombreMateria, String codigoMateria, String codigoGrupo, String imagenMateria){
        this.nombreMateria = nombreMateria;
        this.codigoMateria = codigoMateria;
        this.codigoGrupo = codigoGrupo;
        this.imagenMateria = imagenMateria;
    }

    public String getNombreDocente() { return nombreDocente; }

    public void setNombreDocente(String nombreDocente) { this.nombreDocente = nombreDocente; }

    public String getNombreGrupo() { return codigoGrupo; }

    public void setNombreGrupo(String nombreGrupo) { this.codigoGrupo = nombreGrupo; }

    public String getIdGrupo() { return idGrupo; }

    public void setIdGrupo(String idGrupo) { this.idGrupo = idGrupo; }

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public String getCodigoCiclo() { return codigoCiclo; }

    public void setCodigoCiclo(String codigoCiclo){ this.codigoCiclo = codigoCiclo; }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public String getImagenMateria() {
        return imagenMateria;
    }

    public void setImagenMateria(String imagenMateria) {
        this.imagenMateria = imagenMateria;
    }

    public int getEsTecnicaElectiva() {
        return esTecnicaElectiva;
    }

    public void setEsTecnicaElectiva(int esTecnicaElectiva) {
        this.esTecnicaElectiva = esTecnicaElectiva;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNomTipoGrupo() {
        return nomTipoGrupo;
    }

    public void setNomTipoGrupo(String nomTipoGrupo) {
        this.nomTipoGrupo = nomTipoGrupo;
    }
}
