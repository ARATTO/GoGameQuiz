package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 16/10/2016.
 */
public class MostrarActividades {
    private String nombre;
    private String idtipo;

    public MostrarActividades(String nombre, String idtipo) {
        this.nombre = nombre;
        this.idtipo = idtipo;
    }

    public MostrarActividades() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(String idtipo) {
        this.idtipo = idtipo;
    }
}
