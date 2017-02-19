package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class Categoria {
    private int idCategoria;
    private String nomCategoria;

    public Categoria() {
    }

    public Categoria(int idCategoria, String nomCategoria) {

        this.idCategoria = idCategoria;
        this.nomCategoria = nomCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }
}
