package los_eternos.gogamificationquiz.Pantallas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.Modelo.MostrarAlumnos;
import los_eternos.gogamificationquiz.R;

/**
 * Created by Bryan Lobos1 on 30/09/2016.
 */
public class ListadoMedallas extends Fragment {

    ArrayList<String> Nombre;
    ArrayList<String> Foto;
    String id_grupo="";
    String nombre_materia="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<String> b = null;
        ArrayList<String> c = null;

        if( getArguments() != null){
            b = getArguments().getStringArrayList("nommedalla");
            c = getArguments().getStringArrayList("fotome");
            id_grupo=getArguments().getString("idgrupo");
            nombre_materia=getArguments().getString("idmateria");
        }

        Nombre = b;
        Foto = c;


        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(),
                Nombre,
                Foto);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        // Set padding for Tiles
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView id;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_medallas, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.tile_picture);
            name = (TextView) itemView.findViewById(R.id.tile_title);
            id =(TextView)itemView.findViewById(R.id.id_oculto);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Asignar Medallas");
                    /*Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);*/
                }
            });
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        private int LENGTH;

        private  String[] nombres;
        private  String[] fotos;

        public ContentAdapter(Context context,ArrayList<String> nombre, ArrayList<String> foto) {
            LENGTH = nombre.size();
            nombres = new String[nombre.size()];
            nombres = nombre.toArray(nombres);
            fotos = new String[foto.size()];
            fotos = foto.toArray(fotos);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.picture.setImageBitmap(decodeBase64(fotos[position % fotos.length]));
            holder.name.setText(nombres[position % nombres.length]);
        }

        @Override
        public int getItemCount() {
            //return 0;
            return LENGTH;
        }

        public static Bitmap decodeBase64(String input){
            byte[] decodedByte = Base64.decode(input, 0);
            return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
        }
    }

    /*public void asignar_alumnos(List<MostrarAlumnos> alumnos){
        this.lista_alumnos=alumnos;
        // Log.v("TAG","ASIGNADO ALUMNOS");
    }
    public void asignar_disponibilidad(Boolean permiso){
        // Log.v("TAG","ASIGNADO");
        //this.IS_LOADED=permiso;
    }
    private class CargarAlumnosTask extends AsyncTask<Void, Void, List<MostrarAlumnos>> {


        String id_grupo;
        String id_materia;
        Context contexto;

        //Constructor que recibe el carnet o id del usuario que acaba de logearse
        public CargarAlumnosTask(String id_grupo, String id_materia,Context context) {
            this.id_grupo=id_grupo;
            this.id_materia=id_materia;
            this.contexto=context;
        }

        //Este metodo se encarga de la conexion y recuperacion de los datos de las materias
        @Override
        protected List<MostrarAlumnos> doInBackground(Void... voids) {
            try {

                List<MostrarAlumnos> alumnos= ControlServicio.obtenerAlumnos(id_materia,id_grupo,contexto);

                return alumnos;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<MostrarAlumnos> alumnos) {
            super.onPostExecute(alumnos);
            asignar_alumnos(alumnos);
            asignar_disponibilidad(true);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }*/
}
