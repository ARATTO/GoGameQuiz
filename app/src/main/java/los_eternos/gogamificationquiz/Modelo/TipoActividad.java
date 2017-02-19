package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class TipoActividad {
    private String tipoActividad;
    private String monmreTipoActividad;

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getMonmreTipoActividad() {
        return monmreTipoActividad;
    }

    public void setMonmreTipoActividad(String monmreTipoActividad) {
        this.monmreTipoActividad = monmreTipoActividad;
    }

    public TipoActividad(String tipoActividad, String monmreTipoActividad) {

        this.tipoActividad = tipoActividad;
        this.monmreTipoActividad = monmreTipoActividad;
    }

    public TipoActividad() {

    }
}
