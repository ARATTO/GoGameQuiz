package los_eternos.gogamificationquiz.Modelo;

/**
 * Created by Bryan Lobos1 on 01/10/2016.
 */
public class MostrarAlumnos {
    private String FOTOPERFIL;
    private String NOMALUMNO;
    private String CARNET;

    public MostrarAlumnos(String FOTOPERFIL, String NOMALUMNO, String CARNET) {
        this.FOTOPERFIL = FOTOPERFIL;
        this.NOMALUMNO = NOMALUMNO;
        this.CARNET = CARNET;
    }

    public MostrarAlumnos() {
    }

    public String getFOTOPERFIL() {
        return FOTOPERFIL;
    }

    public void setFOTOPERFIL(String FOTOPERFIL) {
        this.FOTOPERFIL = FOTOPERFIL;
    }

    public String getNOMALUMNO() {
        return NOMALUMNO;
    }

    public void setNOMALUMNO(String NOMALUMNO) {
        this.NOMALUMNO = NOMALUMNO;
    }

    public String getCARNET() {
        return CARNET;
    }

    public void setCARNET(String CARNET) {
        this.CARNET = CARNET;
    }
}
