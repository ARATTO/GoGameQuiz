package los_eternos.gogamificationquiz.Controladores;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import los_eternos.gogamificationquiz.Pantallas.LoginActivity;

/**
 * Created by Bryan Lobos1 on 06/07/2017.
 */
public class SessionManager {
    public static final String IDIOMA = "idioma";
    private static final String PREF_NAME = "GGPreferences";// Sharedpref file name
    // Datos que se almacenaran en preferencias
    private static final String IS_LOGIN = "is_login";
    private static final String USUARIO = "usuario";
    private static final String NOMBRE = "nombre";
    private static final String ID_PERFIL = "id_perfil";
    private static final String ID_TIPO_PERFIL = "id_tipo_perfil";
    private static final String NOM_PERFIL = "nom_perfil";
    private static final String FOTO_PERFIL = "foto_perfil";
    private static final String DESCRIPCION_PERFIL = "descripcion_perfil";
    private static final String APODO = "apodo";
    private static final String EMAIL = "email";
    private static final String IS_DOCENTE =   "is_docente";
    private static int PRIVATE_MODE = 0;// Shared pref mode
    // Shared Preferences

    SharedPreferences pref;   SharedPreferences.Editor editor;// Editor for Shared preferences
    Context context;

    // Constructor
    public SessionManager(Context ctx) {
        this.context = ctx;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
    }


    public void CrearSesion(String usuario, String nombre,int id_perfil, String id_tipo, String nom_perfil, String foto, String descripcion, String apodo,Boolean is_docente ,String email) {

        editor = pref.edit();
        editor.putBoolean(IS_LOGIN, true);// guardando el estado de la sesion
        editor.putString(USUARIO, usuario);
        editor.putString(NOMBRE, nombre);
        editor.putInt(ID_PERFIL, id_perfil);
        editor.putString(ID_TIPO_PERFIL, id_tipo);
        editor.putString(NOM_PERFIL, nom_perfil);
        editor.putString(FOTO_PERFIL, foto);
        editor.putString(DESCRIPCION_PERFIL, descripcion);
        editor.putString(APODO, apodo);
        editor.putBoolean(IS_DOCENTE, is_docente);
        editor.putString(EMAIL,email);
        editor.apply();// guardando los cambios
    }


    //Metodo que sirve para saber si ya se encuentra alguien logeado
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    //Este metodo sirve para saber si es estudiante o docente
    public boolean isEstudiante() {
        String tipo = pref.getString(ID_TIPO_PERFIL, "1");
        if (tipo.equals("1") || tipo.equals("2") || tipo.equals("3"))//--Los tipos de perfiles creados anteriormente
            return true;
        else
            return false;
    }


    public void logOut() {

        // Clearing all data from Shared Preferences menos el idioma
        String idioma = pref.getString(IDIOMA, "en");
        editor.clear();
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(IDIOMA, idioma);
        editor.apply();

        // After logout redirect user to Loing Activity
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Closing all the Activities
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// Add new Flag to start new Activity
        context.startActivity(intent); // Staring Login Activity
    }

    public void guardarIdioma(String idioma) {
        editor.putString(IDIOMA, idioma);
        editor.apply();
    }

    public String obtenerIdioma() {
        return pref.getString(IDIOMA, "en");
    }
}
