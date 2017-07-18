package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 11/07/2017.
 */
public class MostrarCuestionario {
    private String nombre;
    private String duracion;

    public MostrarCuestionario(String nombre, String duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public MostrarCuestionario() {
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