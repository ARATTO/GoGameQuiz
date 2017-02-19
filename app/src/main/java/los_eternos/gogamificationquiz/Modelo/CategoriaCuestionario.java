package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class CategoriaCuestionario {
    private int idCategoriaCuestionario;
    private int idCuestionario;
    private int idCategoria;
    private int cantidadPreguntas;
    private double porcentajeCategoria;

    public CategoriaCuestionario(int idCategoriaCuestionario, int idCuestionario, int cantidadPreguntas, int idCategoria, double porcentajeCategoria) {
        this.idCategoriaCuestionario = idCategoriaCuestionario;
        this.idCuestionario = idCuestionario;
        this.cantidadPreguntas = cantidadPreguntas;
        this.idCategoria = idCategoria;
        this.porcentajeCategoria = porcentajeCategoria;
    }

    public CategoriaCuestionario() {
    }

    public int getIdCategoriaCuestionario() {
        return idCategoriaCuestionario;
    }

    public void setIdCategoriaCuestionario(int idCategoriaCuestionario) {
        this.idCategoriaCuestionario = idCategoriaCuestionario;
    }

    public int getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public int getCantidadPreguntas() {
        return cantidadPreguntas;
    }

    public void setCantidadPreguntas(int cantidadPreguntas) {
        this.cantidadPreguntas = cantidadPreguntas;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public double getPorcentajeCategoria() {
        return porcentajeCategoria;
    }

    public void setPorcentajeCategoria(double porcentajeCategoria) {
        this.porcentajeCategoria = porcentajeCategoria;
    }
}

