package los_eternos.gogamificationquiz.Controladores;

/**
 * Created by rodrigo on 07-07-17.
 */

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import los_eternos.gogamificationquiz.Modelo.Materia;
import los_eternos.gogamificationquiz.Modelo.MostrarActividades;
import los_eternos.gogamificationquiz.Modelo.MostrarAlumnos;
import los_eternos.gogamificationquiz.Modelo.MostrarCuestionario;
import los_eternos.gogamificationquiz.Modelo.MostrarLideres;
import los_eternos.gogamificationquiz.Modelo.MostrarMedallas;

import los_eternos.gogamificationquiz.Modelo.Perfil;

public class ControlServicio {

    //Metodo de Rodrigo para obtener las peticiones y respuestas
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

    //Metodo de Rodrigo para obtener el perfil
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

    public static String obtenerRespuestaMateria(String email, int resultado){

        String respuesta = " ";

        HttpClient cliente = new DefaultHttpClient();
        String url = "";
        Conexion conn = new Conexion();
        url += conn.getURLLocal() + "materiasExistentes";

        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("content-type", "application/json");
        try{
            JSONObject dato = new JSONObject();
            dato.put("email", email);
            dato.put("resultado", resultado);
            StringEntity entity = new StringEntity(dato.toString());
            httpPost.setEntity(entity);
            HttpResponse resp = cliente.execute(httpPost);

            StatusLine estado = resp.getStatusLine();
            System.out.println("estado: " + estado);
            //int codigoEstado = estado.getStatusCode();
            respuesta = EntityUtils.toString(resp.getEntity());

        }catch (Exception e){
            e.printStackTrace();
            Log.v("ERROR_DESCONOCIDO",e.getMessage());

        }

        return respuesta;


    }

    public static List<Materia> obtenerMaterias(String email, int resultado, Context ctx) {
        String json = obtenerRespuestaMateria(email, resultado);
        List<Materia> listaMateria = new ArrayList<Materia>();
        try {
            JSONArray librosJSON = new JSONArray(json);
            for (int i = 0; i < librosJSON.length(); i++) {
                JSONObject obj = librosJSON.getJSONObject(i);
                Materia materia = new Materia();
                materia.setIdMateria(obj.getInt("IDMATERIA"));
                materia.setIdGrupo(obj.getInt("IDGRUPO"));
                materia.setNombreDocente(obj.getString("NOMBREDOCENTE"));
                materia.setNombreMateria(obj.getString("NOMBREMATERIA"));
                materia.setCodigoMateria(obj.getString("CODIGOMATERIA"));
                materia.setNombreGrupo(obj.getString("CODIGOGRUPO"));
                materia.setImagenMateria(obj.getString("IMAGENMATERIA"));

                listaMateria.add(materia);
            }
            return listaMateria;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseo de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }



    //Metodo de Alam para verificar los datos del usuario en el login
    public static int obtenerRespuestaLogin(String email, String password){

        //Verifica que el email y password ingresados existan
        int resultado = 6;
        HttpClient cliente = new DefaultHttpClient();
        String url = "";
        Conexion conn = new Conexion();
        url += conn.getURLLocal() + "loginapp";
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("content-type", "application/json");
        try{
            JSONObject dato = new JSONObject();
            dato.put("email", email);
            dato.put("password", password);
            StringEntity entity = new StringEntity(dato.toString());
            httpPost.setEntity(entity);
            HttpResponse resp = cliente.execute(httpPost);

            StatusLine estado = resp.getStatusLine();
            System.out.println("estado: " + estado);
            //int codigoEstado = estado.getStatusCode();
            resultado = Integer.parseInt(EntityUtils.toString(resp.getEntity()));

        }catch (Exception e){
            e.printStackTrace();
            Log.v("ERROR_DESCONOCIDO",e.getMessage());

        }

        return resultado;

    }

    public static String obtenerRespuestaAlumnos(String idmateria, String idGrupo, Context ctx){
        String resultado=null;
        HttpClient cliente = new DefaultHttpClient();
        String url = "";
        Conexion conn = new Conexion();
        url += conn.getURLLocal() + "alumnos";
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("content-type", "application/json");
        try{

            JSONObject dato = new JSONObject();
            dato.put("Materia", idmateria);
            dato.put("Grupo", idGrupo);
            StringEntity entity = new StringEntity(dato.toString());
            httpPost.setEntity(entity);

            HttpResponse resp = cliente.execute(httpPost);

            StatusLine estado = resp.getStatusLine();
            System.out.println("estado: " + estado);
            //int codigoEstado = estado.getStatusCode();
            resultado = (EntityUtils.toString(resp.getEntity()));

        }catch (Exception e){
            e.printStackTrace();
            Log.v("ERROR_DESCONOCIDO",e.getMessage());

        }

        return resultado;
    }

    public static ArrayList<MostrarAlumnos> obtenerAlumnos( String im, String ig,Context ctx) {
        String json = obtenerRespuestaAlumnos(im,ig, ctx);
        ArrayList<MostrarAlumnos> listaAlumnos = new ArrayList<MostrarAlumnos>();
        try {
            JSONArray alumnosJSON = new JSONArray(json);
            for (int i = 0; i < alumnosJSON.length(); i++) {
                JSONObject obj = alumnosJSON.getJSONObject(i);
                MostrarAlumnos alumnos = new MostrarAlumnos();
                alumnos.setCARNET(obj.getString("email"));
                alumnos.setNOMALUMNO(obj.getString("nombreestudiante"));
                alumnos.setFOTOPERFIL(obj.getString("imagenperfil"));

                listaAlumnos.add(alumnos);
            }
            return listaAlumnos;
        } catch (Exception e) {
            System.out.println(e);
            Toast.makeText(ctx, "Error en parseo de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    //OBTENER LAS MEDALLAS DE LA MATERIA
    public static String obtenerRespuestaMedallas(String idmateria,Context ctx){
        String resultado = "";
        HttpClient cliente = new DefaultHttpClient();
        String url = "";
        Conexion conn = new Conexion();
        url += conn.getURLLocal() + "medallasapp";
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("content-type", "application/json");
        try{
            JSONObject dato = new JSONObject();
            dato.put("Materia", idmateria);
            StringEntity entity = new StringEntity(dato.toString());
            httpPost.setEntity(entity);
            HttpResponse resp = cliente.execute(httpPost);

            StatusLine estado = resp.getStatusLine();
            System.out.println("estado: " + estado);
            //int codigoEstado = estado.getStatusCode();
            resultado = (EntityUtils.toString(resp.getEntity()));

        }catch (Exception e){

            e.printStackTrace();
            Log.v("ERROR_DESCONOCIDO",e.getMessage());

        }

        return resultado;
    }

    public static ArrayList<MostrarMedallas> obtenerMedallas( String im,Context ctx) {
        String json = obtenerRespuestaMedallas(im, ctx);
        ArrayList<MostrarMedallas> listaMedallas = new ArrayList<MostrarMedallas>();
        try {
            JSONArray medallasJSON = new JSONArray(json);
            for (int i = 0; i < medallasJSON.length(); i++) {
                JSONObject obj = medallasJSON.getJSONObject(i);
                MostrarMedallas medallas = new MostrarMedallas();
                medallas.setId_medalla(obj.getString("id"));
                medallas.setFoto(obj.getString("imagenmedalla"));
                medallas.setNommedalla(obj.getString("nombremedalla"));

                listaMedallas.add(medallas);
            }
            return listaMedallas;
        } catch (Exception e) {
            System.out.println(e);
            Toast.makeText(ctx, "Error en parseo de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public static String obtenerRespuestaActividades(String idMateria,String idGrupo, Context ctx){
        String resultado = "";
        HttpClient cliente = new DefaultHttpClient();
        String url = "";
        Conexion conn = new Conexion();
        url += conn.getURLLocal() + "actividadesapp";
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("content-type", "application/json");
        try{
            JSONObject dato = new JSONObject();
            dato.put("Grupo", idGrupo);
            dato.put("Materia", idMateria);
            StringEntity entity = new StringEntity(dato.toString());
            httpPost.setEntity(entity);
            HttpResponse resp = cliente.execute(httpPost);

            StatusLine estado = resp.getStatusLine();
            System.out.println("estado: " + estado);
            //int codigoEstado = estado.getStatusCode();
            resultado = (EntityUtils.toString(resp.getEntity()));
        }catch (Exception e){
            e.printStackTrace();
            Log.v("ERROR_DESCONOCIDO",e.getMessage());

        }

        return resultado;
    }

    public static ArrayList<MostrarActividades> obtenerActividades( String im, String ig,Context ctx) {
        String json = obtenerRespuestaActividades(im,ig, ctx);
        ArrayList<MostrarActividades> listaActividades = new ArrayList<MostrarActividades>();
        try {
            JSONArray actividadesJSON = new JSONArray(json);
            for (int i = 0; i < actividadesJSON.length(); i++) {
                JSONObject obj = actividadesJSON.getJSONObject(i);
                MostrarActividades actividades = new MostrarActividades();
                actividades.setIdtipo(obj.getString("puntosactividad"));
                actividades.setNombre(obj.getString("nombreactividad"));

                listaActividades.add(actividades);
            }
            return listaActividades;
        } catch (Exception e) {
            System.out.println(e);
            Toast.makeText(ctx, "Error en parseo de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public static String obtenerRespuestaCuestionario(String idMateria,String idGrupo, Context ctx){
        String resultado = "";
        HttpClient cliente = new DefaultHttpClient();
        String url = "";
        Conexion conn = new Conexion();
        url += conn.getURLLocal() + "cuestionariosapp";
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("content-type", "application/json");
        try{
            JSONObject dato = new JSONObject();
            dato.put("Grupo", idGrupo);
            dato.put("Materia", idMateria);
            StringEntity entity = new StringEntity(dato.toString());
            httpPost.setEntity(entity);
            HttpResponse resp = cliente.execute(httpPost);

            StatusLine estado = resp.getStatusLine();
            System.out.println("estado: " + estado);
            //int codigoEstado = estado.getStatusCode();
            resultado = (EntityUtils.toString(resp.getEntity()));
            System.out.println("resultado: "+resultado);
        }catch (Exception e){
            e.printStackTrace();
            Log.v("ERROR_DESCONOCIDO",e.getMessage());

        }

        return resultado;
    }

    public static ArrayList<MostrarCuestionario> obtenerCuestionario(String im, String ig, Context ctx) {
        String json = obtenerRespuestaCuestionario(im,ig, ctx);
        ArrayList<MostrarCuestionario> listaCuestionario = new ArrayList<MostrarCuestionario>();
        try {
            JSONArray cuestionariosJSON = new JSONArray(json);
            for (int i = 0; i < cuestionariosJSON.length(); i++) {
                JSONObject obj = cuestionariosJSON.getJSONObject(i);
                MostrarCuestionario cuestionario = new MostrarCuestionario();
                cuestionario.setNombre(obj.getString("nombrecuestionario"));
                cuestionario.setDuracion(obj.getString("duracioncuestionario"));

                listaCuestionario.add(cuestionario);
            }
            return listaCuestionario;
        } catch (Exception e) {
            System.out.println(e);
            Toast.makeText(ctx, "Error en parseo de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public static String obtenerRespuestaDescripcionMateria(String idMateria, Context ctx){
        String resultado = "";
        HttpClient cliente = new DefaultHttpClient();
        String url = "";
        Conexion conn = new Conexion();
        url += conn.getURLLocal() + "materiasapp";
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("content-type", "application/json");
        try{
            JSONObject dato = new JSONObject();
            dato.put("Materia", idMateria);
            StringEntity entity = new StringEntity(dato.toString());
            httpPost.setEntity(entity);
            HttpResponse resp = cliente.execute(httpPost);

            StatusLine estado = resp.getStatusLine();
            System.out.println("estado: " + estado);
            resultado = (EntityUtils.toString(resp.getEntity()));
            System.out.println("resultado: "+resultado);
        }catch (Exception e){
            e.printStackTrace();
            Log.v("ERROR_DESCONOCIDO",e.getMessage());

        }

        return resultado;
    }

    public static Materia obtenerDescripcionMateria(String im, Context ctx) {
        String json = obtenerRespuestaDescripcionMateria(im, ctx);


            Materia materia = new Materia();
            try{
                JSONArray materiasJSON = new JSONArray(json);
                if(materiasJSON.length()!=0){

                    JSONObject obj = materiasJSON.getJSONObject(0);

                    materia.setNombreMateria(obj.getString("NOMBREMATERIA"));
                    materia.setCodigoMateria(obj.getString("CODIGOMATERIA"));
                    materia.setImagenMateria(obj.getString("IMAGENMATERIA"));

                } else{
                    System.out.print("error");
                }

            }catch(Exception e){
                e.printStackTrace();
                Log.v("ERROR_DESCONOCIDO",e.getMessage());
                return null;

            }

        return materia;

    }


    public static String obtenerRespuestaLideres(String idMateria,String idGrupo, Context ctx){
        String resultado = "";
        HttpClient cliente = new DefaultHttpClient();
        String url = "";
        Conexion conn = new Conexion();
        url += conn.getURLLocal() + "lideresapp";
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("content-type", "application/json");
        try{
            JSONObject dato = new JSONObject();
            dato.put("Materia", idMateria);
            dato.put("Grupo", idGrupo);

            StringEntity entity = new StringEntity(dato.toString());
            httpPost.setEntity(entity);
            HttpResponse resp = cliente.execute(httpPost);

            StatusLine estado = resp.getStatusLine();
            System.out.println("estado: " + estado);
            resultado = (EntityUtils.toString(resp.getEntity()));
            System.out.println("resultado: "+resultado);
        }catch (Exception e){
            e.printStackTrace();
            Log.v("ERROR_DESCONOCIDO",e.getMessage());

        }

        return resultado;
    }

    public static ArrayList<MostrarLideres> obtenerLideres(String im, String ig, Context ctx) {
        String json = obtenerRespuestaLideres(im,ig,ctx);
        ArrayList<MostrarLideres> listaLideres = new ArrayList<MostrarLideres>();
        try {
            JSONArray lideresJSON = new JSONArray(json);
            for (int i = 0; i < lideresJSON.length(); i++) {
                JSONObject obj = lideresJSON.getJSONObject(i);
                MostrarLideres lider = new MostrarLideres();
                lider.setCarnet(obj.getString("carnetestudiante"));
                lider.setPuntaje(obj.getString("puntajeacumulado"));

                listaLideres.add(lider);
            }
            return listaLideres;
        } catch (Exception e) {
            System.out.println(e);
            Toast.makeText(ctx, "Error en parseo de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }






}
