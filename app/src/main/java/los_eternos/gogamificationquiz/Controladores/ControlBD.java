package los_eternos.gogamificationquiz.Controladores;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bryan Lobos1 on 11/08/2016.
 */
public class ControlBD {

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    public ControlBD(Context ctx) {
        this. context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "gogamificationquiz.s3db" ;
        private static final int VERSION = 1;
        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("create table ACTIVIDAD\n" +
                        "(\n" +
                        "   IDACTIVIDAD          int not null auto_increment,\n" +
                        "   IDTIPOACTIVIDAD      int,\n" +
                        "   IDDETALLEPUNTO       int,\n" +
                        "   IDGRUPO              int,\n" +
                        "   NOMBREACTIVIDAD      varchar(50) not null,\n" +
                        "   primary key (IDACTIVIDAD)\n" +
                        ");");
                db.execSQL("create table CATEGORIA\n" +
                        "(\n" +
                        "   IDCATEGORIA          int not null auto_increment,\n" +
                        "   NOMBRECATEGORIA      varchar(30) not null,\n" +
                        "   primary key (IDCATEGORIA)\n" +
                        ");");
                db.execSQL("create table CICLO\n" +
                        "(\n" +
                        "   CODCICLO             varchar(7) not null,\n" +
                        "   DESDE                date not null,\n" +
                        "   HASTA                date not null,\n" +
                        "   primary key (CODCICLO)\n" +
                        ");");
                db.execSQL("create table CATEGORIACUESTIONARIO\n" +
                        "(\n" +
                        "   IDCATEGORIACUESTIONARIO int not null auto_increment,\n" +
                        "   IDCUESTIONARIO       int,\n" +
                        "   IDCATEGORIA          int,\n" +
                        "   CANTIDADPREGUNTAS    int,\n" +
                        "   PORCENTAJECATEGORIA  float,\n" +
                        "   primary key (IDCATEGORIACUESTIONARIO)\n" +
                        ");");

                db.execSQL("create table CICLO\n" +
                        "(\n" +
                        "   IDCLICO              int not null auto_increment,\n" +
                        "   CODIGOCICLO          char(7) not null,\n" +
                        "   FECHAINICIO          date not null,\n" +
                        "   FECHAFIN             date not null,\n" +
                        "   ESTAACTIVOCICLO      bool,\n" +
                        "   primary key (IDCLICO)\n" +
                        ");");
                db.execSQL("create table CUESTIONARIO\n" +
                        "(\n" +
                        "   IDCUESTIONARIO       int not null auto_increment,\n" +
                        "   IDDOCENTE            int,\n" +
                        "   FECHACUESTIONARIO    date not null,\n" +
                        "   HORAINICIOCUESTIONARIO time not null,\n" +
                        "   HORAFINCUESTIONARIO  time not null,\n" +
                        "   DURACIONCUESTIONARIO time not null,\n" +
                        "   primary key (IDCUESTIONARIO)\n" +
                        ");");
                db.execSQL("create table CUESTIONARIOASIGNADO\n" +
                        "(\n" +
                        "   IDCUESTIONARIOASIGNADO int not null auto_increment,\n" +
                        "   IDPERFIL             int,\n" +
                        "   IDCUESTIONARIO       int,\n" +
                        "   primary key (IDCUESTIONARIOASIGNADO)\n" +
                        ");");
                db.execSQL("create table CUESTIONARIOMATERIA\n" +
                        "(\n" +
                        "   IDCUESTIONADOASIGNADO int not null auto_increment,\n" +
                        "   IDMATERIAIMPARTIDA   int,\n" +
                        "   IDCUESTIONARIO       int,\n" +
                        "   primary key (IDCUESTIONADOASIGNADO)\n" +
                        ");");
                db.execSQL("create table DETALLEMEDALLA\n" +
                        "(\n" +
                        "   IDDETALLEMEDALLA     int not null auto_increment,\n" +
                        "   IDMATERIA            int,\n" +
                        "   IDMEDALLA            int,\n" +
                        "   CANTIDADMINIMAPUNTOS int not null,\n" +
                        "   primary key (IDDETALLEMEDALLA)\n" +
                        ");");
                db.execSQL("create table DETALLEPUNTO\n" +
                        "(\n" +
                        "   IDDETALLEPUNTO       int not null auto_increment,\n" +
                        "   IDTIPOPUNTO          int,\n" +
                        "   IDMATERIAIMPARTIDA   int,\n" +
                        "   ESTAACTIVOPUNTO      bool,\n" +
                        "   PUNTOSACTIVIDAD      int not null,\n" +
                        "   primary key (IDDETALLEPUNTO)\n" +
                        ");");
                db.execSQL("create table DETALLEPUNTOACTIVIDAD\n" +
                        "(\n" +
                        "   IDDETALLEPUNTOACTIVIDAD int not null auto_increment,\n" +
                        "   IDPERFIL             int,\n" +
                        "   IDACTIVIDAD          int,\n" +
                        "   PUNTAJEGANADO        int,\n" +
                        "   primary key (IDDETALLEPUNTOACTIVIDAD)\n" +
                        ");");
                db.execSQL("create table DOCENTE\n" +
                        "(\n" +
                        "   IDDOCENTE            int not null auto_increment,\n" +
                        "   NOMBREDOCENTE        varchar(30) not null,\n" +
                        "   CARNETDOCENTE        char(15) not null,\n" +
                        "   primary key (IDDOCENTE)\n" +
                        ");");
                db.execSQL("create table ESTUDIANTE\n" +
                        "(\n" +
                        "   IDESTUDIANTE         int not null auto_increment,\n" +
                        "   NOMBREESTUDIANTE     varchar(30) not null,\n" +
                        "   CARNETESTUDIANTE     char(7) not null,\n" +
                        "   primary key (IDESTUDIANTE)\n" +
                        ");");
                db.execSQL("create table GRUPO\n" +
                        "(\n" +
                        "   IDGRUPO              int not null,\n" +
                        "   IDDOCENTE            int,\n" +
                        "   IDTIPOGRUPO          int,\n" +
                        "   IDMATERIAIMPARTIDA   int,\n" +
                        "   CODIGOGRUPO          char(5) not null,\n" +
                        "   primary key (IDGRUPO)\n" +
                        ");");
                db.execSQL("create table INSCRIPCION\n" +
                        "(\n" +
                        "   IDINSCRIPCION        int not null auto_increment,\n" +
                        "   IDESTUDIANTE         int,\n" +
                        "   IDGRUPO              int,\n" +
                        "   primary key (IDINSCRIPCION)\n" +
                        ");");
                db.execSQL("create table MATERIA\n" +
                        "(\n" +
                        "   IDMATERIA            int not null auto_increment,\n" +
                        "   CODIGOMATERIA        char(8) not null,\n" +
                        "   NOMBREMATERIA        varchar(50) not null,\n" +
                        "   ESTECNICAELECTIVA    bool not null,\n" +
                        "   IMAGENMATERIA        longblob,\n" +
                        "   primary key (IDMATERIA)\n" +
                        ");");
                db.execSQL("create table MATERIAIMPARTIDA\n" +
                        "(\n" +
                        "   IDMATERIAIMPARTIDA   int not null auto_increment,\n" +
                        "   IDCLICO              int,\n" +
                        "   IDMATERIA            int,\n" +
                        "   primary key (IDMATERIAIMPARTIDA)\n" +
                        ");");
                db.execSQL("create table MEDALLA\n" +
                        "(\n" +
                        "   IDMEDALLA            int not null auto_increment,\n" +
                        "   NOMBREMEDALLA        varchar(30) not null,\n" +
                        "   DESCRIPCIONMEDALLA   text not null,\n" +
                        "   ESCUANTITATIVA       bool not null,\n" +
                        "   IMAGENMEDALLA        longblob,\n" +
                        "   primary key (IDMEDALLA)\n" +
                        ");");
                db.execSQL("create table MEDALLAGANADA\n" +
                        "(\n" +
                        "   IDMEDALLAGANADA      int not null auto_increment,\n" +
                        "   IDMEDALLA            int,\n" +
                        "   IDPERFIL             int,\n" +
                        "   primary key (IDMEDALLAGANADA)\n" +
                        ");\n");
                db.execSQL("create table OPCIONSELECCIONADA\n" +
                        "(\n" +
                        "   IDOPCIONSELECCIONADA int not null auto_increment,\n" +
                        "   IDCUESTIONARIOASIGNADO int,\n" +
                        "   IDRESPUESTA          int,\n" +
                        "   IDPREGUNTA           int,\n" +
                        "   primary key (IDOPCIONSELECCIONADA)\n" +
                        ");");
                db.execSQL("create table PERFIL\n" +
                        "(\n" +
                        "   IDPERFIL             int not null auto_increment,\n" +
                        "   IDESTUDIANTE         int,\n" +
                        "   IDDOCENTE            int,\n" +
                        "   NOMBREPERFIL         varchar(30) not null,\n" +
                        "   PASSWORD             varchar(30) not null,\n" +
                        "   IMAGENPERFIL         longblob,\n" +
                        "   DESCRIPCIONPERFIL    text,\n" +
                        "   APODO                varchar(30),\n" +
                        "   primary key (IDPERFIL)\n" +
                        ");");
                db.execSQL("create table PREGUNTA\n" +
                        "(\n" +
                        "   IDPREGUNTA           int not null auto_increment,\n" +
                        "   IDCATEGORIA          int,\n" +
                        "   IDTIPOPREGUNTA       int,\n" +
                        "   PREGUNTA             varchar(255) not null,\n" +
                        "   primary key (IDPREGUNTA)\n" +
                        ");\n");
                db.execSQL("create table RESPUESTA\n" +
                        "(\n" +
                        "   IDRESPUESTA          int not null auto_increment,\n" +
                        "   IDPREGUNTA           int,\n" +
                        "   ESCORRECTA           bool,\n" +
                        "   ALTERNATIVA          text not null,\n" +
                        "   primary key (IDRESPUESTA)\n" +
                        ");");
                db.execSQL("create table TIPOACTIVIDAD\n" +
                        "(\n" +
                        "   IDTIPOACTIVIDAD      int not null,\n" +
                        "   NOMBRETIPOACTIVIDAD  varchar(30) not null,\n" +
                        "   primary key (IDTIPOACTIVIDAD)\n" +
                        ");\n");
                db.execSQL("create table TIPOGRUPO\n" +
                        "(\n" +
                        "   IDTIPOGRUPO          int not null,\n" +
                        "   NOMBRETIPOGRUPO      varchar(15) not null,\n" +
                        "   primary key (IDTIPOGRUPO)\n" +
                        ");");
                db.execSQL("create table TIPOPREGUNTA\n" +
                        "(\n" +
                        "   IDTIPOPREGUNTA       int not null auto_increment,\n" +
                        "   NOMBRETIPOPREGUNTA   varchar(30) not null,\n" +
                        "   primary key (IDTIPOPREGUNTA)\n" +
                        ");\n");
                db.execSQL("create table TIPOPUNTO\n" +
                        "(\n" +
                        "   IDTIPOPUNTO          int not null auto_increment,\n" +
                        "   NOMBREPARAMETRO      varchar(30) not null,\n" +
                        "   primary key (IDTIPOPUNTO)\n" +
                        ");");

                db.execSQL("insert  into `tipoperfil`(`IDTIPOPERFIL`,`NOMBRETIPOPERFIL`,`DESCRIPCIONTIPOPERFIL`,`NIVELACCESO`) values ('1','ESTUDIANTE-BURDEL','Estudiante con perfil totalmente pÃºblico','1'),('2','ESTUDIANTE-GENERAL','Estudiante que solo quiere mostrar aspectos generales de su perfil','2'),('3','ESTUDIANTE_PRIVADO','Estudiante que solo mostrarÃ¡ su nombre e imagen','3');");

               }catch(SQLException e){
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        }
    }
    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }
    public void cerrar(){
        DBHelper.close();
    }
}
