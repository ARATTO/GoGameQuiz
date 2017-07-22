package los_eternos.gogamificationquiz.Pantallas;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.Modelo.MostrarAlumnos;
import los_eternos.gogamificationquiz.R;

/**
 * Created by Bryan Lobos1 on 30/09/2016.
 */
public class ListadoAlumnos extends Fragment {

    //ArrayList<MostrarAlumnos> alumnos = new ArrayList<MostrarAlumnos>();
    ArrayList<String> Nombre;
    ArrayList<String> Carnet;
    ArrayList<String> Foto;
    String materia="";
    public static String grupo;
    // static Conexion con;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<String> b = null;
        ArrayList<String> c = null;
        ArrayList<String> d = null;
        String idgrupo="";
        String nommateri="";
        //   con = new Conexion();

        if( getArguments() != null){
            b = getArguments().getStringArrayList("nombre");
            c = getArguments().getStringArrayList("carnet");
            d = getArguments().getStringArrayList("foto");
            idgrupo += getArguments().getString("idgrupo");
            nommateri += getArguments().getString("idmateria");
        }

        Nombre = b;
        Carnet = c;
        Foto = d;
        grupo = idgrupo;
        materia = nommateri;


        AlumnoViewHolder.idgrupo= grupo;
        AlumnoViewHolder.idmateria= materia;

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(), Nombre, Carnet, Foto);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        return recyclerView;
    }


    public static class AlumnoViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nommateria;
        public TextView descripcion;
        public ImageView mano;
        public ImageView punto;
        public static String idgrupo = "";
        public static String idmateria = "";
        public static Conexion con = new Conexion();


        public AlumnoViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_alumnos, parent, false));

            imagen = (ImageView) itemView.findViewById(R.id.list_avatar);
            nommateria = (TextView) itemView.findViewById(R.id.list_title);
            descripcion = (TextView) itemView.findViewById(R.id.list_desc);
            mano = (ImageView) itemView.findViewById(R.id.list_avatar3);
            punto = (ImageView) itemView.findViewById(R.id.list_avatar2);

            //Perfil del alumno
            imagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.print("Agrege logica de mostrar perfil");
                    Context context = v.getContext();
                    Intent intent = new Intent(context.getApplicationContext(), PerfilActivity.class);
                    intent.putExtra("email", descripcion.getText());
                    context.startActivity(intent);
                }
            });


            mano.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//participacion
                    String resultado =  ControlServicio.asignarPuntos(descripcion.getText().toString(),-2, grupo);

                    System.out.println("Parcipacion resultado: "+resultado);

                    Toast.makeText(v.getContext(), resultado, Toast.LENGTH_LONG).show();

                }
                        /*NO SE UTILIZA/*if(idActividad.isEmpty()){
                            System.out.println("no a seleccionado actividad: ");
                            Toast.makeText(v.getContext(), "Seleccione una actividad", Toast.LENGTH_SHORT).show();
                        }else{
                            //logica de asignar puntos
                            System.out.println("SI a seleccionado actividad: "+ idActividad);
                        }
                        System.out.println("SI a seleccionado grupo: "+ idgrupo);
                        System.out.println("SI a seleccionado materia: "+ nommateri);NO SE UTILIZA*/

                    /*String URL = "";
                    URL = con.getURLLocal()+"Servicios.php?accion=8&CARNET="+descripcion.getText()+"&IDGRUPO="+idgrupo+"&IDACTIVIDAD="+idActividad;

                    if(ServiciosController.asignarPuntos(URL)==true){
                        mano.setImageResource(R.drawable.ic_participacion_green);
                        Toast.makeText(v.getContext(), "Se ha asignado puntos a: "+descripcion.getText(), Toast.LENGTH_SHORT).show();
                    }else{
                        mano.setImageResource(R.drawable.ic_participacion_gray);
                        Toast.makeText(v.getContext(), "No ha sido posible la asignacion de puntos a: "+descripcion.getText(), Toast.LENGTH_SHORT).show();
                    }
                }
            });*/
            });

            punto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v2) {//asistencia
                    String resultado = ControlServicio.asignarPuntos(descripcion.getText().toString(),-1,grupo);

                    System.out.println("Asistencia resultado: "+resultado);

                    Toast.makeText(v2.getContext(), resultado, Toast.LENGTH_LONG).show();
                }
            });

        }
    }


    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<AlumnoViewHolder> {
        // Set numbers of List in RecyclerView.


        ArrayList<MostrarAlumnos> alumnos = new ArrayList<MostrarAlumnos>();
        private int LENGTH;
        private  String[] nombres ;
        private  String[] carnets ;
        private  String[] fotos ;

        public ContentAdapter(Context context, ArrayList<String> nom, ArrayList<String> carnet, ArrayList<String> foto) {
            LENGTH = nom.size();
            nombres = new String[nom.size()];
            nombres = nom.toArray(nombres);
            carnets = new String[carnet.size()];
            carnets = carnet.toArray(carnets);
            fotos = new String[foto.size()];
            fotos = foto.toArray(fotos);
        }

        @Override
        public AlumnoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new AlumnoViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(AlumnoViewHolder holder, int position) {
            holder.imagen.setImageBitmap(decodeBase64(fotos[position % fotos.length]));
            holder.nommateria.setText(nombres[position % nombres.length]);
            holder.descripcion.setText(carnets[position % carnets.length]);
            holder.mano.setImageResource(R.drawable.ic_participacion_gray);
            holder.punto.setImageResource(R.drawable.ic_asistencia_gray);

        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }

        public static Bitmap decodeBase64(String input){
            byte[] decodedByte = Base64.decode(input, 0);
            return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
        }
    }
}