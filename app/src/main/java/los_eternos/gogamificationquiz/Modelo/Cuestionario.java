package los_eternos.gogamificationquiz.Modelo;

import java.util.Date;

/**
 * Created by Bryan Lobos1 on 18/02/2017.
 */
public class Cuestionario {
    private int idCuestionario;
    private int idDocente;
    private Date horaInicioCuestionario;
    private Date horaFinCuestionario;
    private Date duracionCueationario;

    public Cuestionario(int idCuestionario, int idDocente, Date horaInicioCuestionario, Date horaFinCuestionario, Date duracionCueationario) {
        this.idCuestionario = idCuestionario;
        this.idDocente = idDocente;
        this.horaInicioCuestionario = horaInicioCuestionario;
        this.horaFinCuestionario = horaFinCuestionario;
        this.duracionCueationario = duracionCueationario;
    }

    public Cuestionario() {
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public Date getHoraInicioCuestionario() {
        return horaInicioCuestionario;
    }

    public void setHoraInicioCuestionario(Date horaInicioCuestionario) {
        this.horaInicioCuestionario = horaInicioCuestionario;
    }

    public Date getHoraFinCuestionario() {
        return horaFinCuestionario;
    }

    public void setHoraFinCuestionario(Date horaFinCuestionario) {
        this.horaFinCuestionario = horaFinCuestionario;
    }

    public Date getDuracionCueationario() {
        return duracionCueationario;
    }

    public void setDuracionCueationario(Date duracionCueationario) {
        this.duracionCueationario = duracionCueationario;
    }

    public int getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }
}
