package los_eternos.gogamificationquiz.Controladores;

/**
 * Created by rodrigo on 07-07-17.
 */

import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;
import android.content.ContentResolver;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import los_eternos.gogamificationquiz.Modelo.Perfil;

public class ControlServicio {


    public static String obtenerRespuestaPeticion(String url, Context ctx) {
        String respuesta = " ";

// Creando objetos de conexion
        HttpClient cliente = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        System.out.println("antes del try");
        httpGet.setHeader("content-type", "application/json");

        try {
            System.out.println("Entro en el try");
            HttpResponse httpRespuesta = cliente.execute(httpGet);
            System.out.println("ejecuto el get");
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            System.out.println("Codigo de estado:" +codigoEstado);
            if (codigoEstado == 200) {
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
            }
        } catch (Exception e) {
            System.out.println("Error: "+e);
            Toast.makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG).show();
// Desplegando el error en el LogCat
            Log.v("Error de Conexion", e.toString());
        }
        return respuesta;
    }


    public static List<Perfil> obtenerPerfil(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);
        List<Perfil> listaPerfil = new ArrayList<Perfil>();
        try {
            JSONArray librosJSON = new JSONArray(json);
            for (int i = 0; i < librosJSON.length(); i++) {
                JSONObject obj = librosJSON.getJSONObject(i);
                Perfil perfil = new Perfil();
                perfil.setIdPerfil(obj.getInt("id"));
                perfil.setNombrePerfil(obj.getString("NOMBREPERFIL"));
                perfil.setApodo(obj.getString("APODO"));
                perfil.setDescripcionPerfil(obj.getString("DESCRIPCIONPERFIL"));
                perfil.setImagenPerfil(obj.getString("IMAGENPERFIL"));

                listaPerfil.add(perfil);
            }
            return listaPerfil;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseo de JSON", Toast.LENGTH_LONG).show();
            return null;
        }


    }





}
