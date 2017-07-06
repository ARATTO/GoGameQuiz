package los_eternos.gogamificationquiz.Controladores;

/**
 * Created by Bryan Lobos1 on 06/07/2017.
 */
import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

 class ServiciosController
{
    public static String obtenerRespuestaPeticion(String url, Context ctx) {
        String respuesta = " ";
// Estableciendo tiempo de espera del servicio
        HttpParams parametros = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(parametros, 3000);
        HttpConnectionParams.setSoTimeout(parametros, 5000);
// Creando objetos de conexion
        HttpClient cliente = new DefaultHttpClient(parametros);
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpRespuesta = cliente.execute(httpGet);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            System.out.println("Codigo de estado:" +codigoEstado);
            if (codigoEstado == 200) {
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
            }
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG).show();
// Desplegando el error en el LogCat
            Log.v("Error de Conexion", e.toString());
        }
        return respuesta;
    }

    public static String obtenerRespuestaPeticion(String url) {
        String respuesta = "{respuesta:0}"; // Estableciendo tiempo de espera del servicio
        HttpParams parametros = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(parametros, 3000);
        HttpConnectionParams.setSoTimeout(parametros, 5000); // Creando objetos de conexion
        HttpClient cliente = new DefaultHttpClient(parametros);

        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpRespuesta = cliente.execute(httpGet);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            if (codigoEstado == 200) {
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
            }
            System.out.println("EXITO DE CONEXION obtenerRespuestaPeticion");
        } catch (Exception e) {
            //Log.v("ERROR_CONEXION", e.getMessage());
            System.out.println("ERROR DE CONEXION obtenerRespuestaPeticion");
            e.printStackTrace();
        }
        return respuesta;
    }

    public static void  get_perfil(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion,ctx);
        boolean IS_DOCENTE;
        try
        {
            JSONObject resultado = new JSONObject(json);
            SessionManager sesion = new SessionManager(ctx);

            if(resultado.getString("IS_DOCENTE").equals("0"))
                IS_DOCENTE=false;
            else
                IS_DOCENTE=true;

            sesion.CrearSesion( resultado.getString("USUARIO"),resultado.getString("NOMBRE"),resultado.getInt("IDPERFIL"),
                    resultado.getString("IDTIPOPERFIL"),resultado.getString("NOMPERFIL"),resultado.getString("FOTOPERFIL"),
                    resultado.getString("DESCRIPCIONPERFIL"),resultado.getString("APODO"),IS_DOCENTE,resultado.getString("EMAIL"));

        } catch (JSONException e)
        {
            Log.v("ERROR_PARSEO", e.getMessage());
            e.printStackTrace();
        }

    }

    public static boolean  login(String peticion) {
        String json = obtenerRespuestaPeticion(peticion);
        try
        {
            JSONObject resultado = new JSONObject(json);
            int respuesta = resultado.getInt("respuesta");
            if (respuesta == 1)
                return true;
        } catch (JSONException e)
        {
            Log.v("ERROR_PARSEO", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
