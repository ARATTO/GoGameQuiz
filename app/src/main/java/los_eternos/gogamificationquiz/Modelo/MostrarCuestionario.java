package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 11/07/2017.
 */
public class MostrarCuestionario {
    private String nombre;
    private String duracion;
    private String idC;

    public MostrarCuestionario(String nombre, String idC, String duracion) {
        this.nombre = nombre;
        this.idC = idC;
        this.duracion = duracion;
    }

    public MostrarCuestionario() {
    }

    public String getIdC() {
        return idC;
    }

    public void setIdC(String idC) {
        this.idC = idC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}