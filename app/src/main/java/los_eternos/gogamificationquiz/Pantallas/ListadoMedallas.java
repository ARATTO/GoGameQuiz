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
    ArrayList<String> Punto;
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
        ArrayList<String> d = null;

        if( getArguments() != null){
            b = getArguments().getStringArrayList("nommedalla");
            c = getArguments().getStringArrayList("fotome");
            d = getArguments().getStringArrayList("puntosmi");
            id_grupo=getArguments().getString("idgrupo");
            nombre_materia=getArguments().getString("idmateria");
        }

        Nombre = b;
        Foto = c;
        Punto=d;


        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(),
                Nombre,
                Foto,
                Punto);
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
        public TextView puntos;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_medallas, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.tile_picture);
            name = (TextView) itemView.findViewById(R.id.tile_title);
            puntos =(TextView)itemView.findViewById(R.id.id_oculto);
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
        private  String[] puntos;

        public ContentAdapter(Context context,ArrayList<String> nombre, ArrayList<String> foto,ArrayList<String> punto) {
            LENGTH = nombre.size();
            nombres = new String[nombre.size()];
            nombres = nombre.toArray(nombres);
            fotos = new String[foto.size()];
            fotos = foto.toArray(fotos);
            puntos = new String[punto.size()];
            puntos = punto.toArray(puntos);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.picture.setImageBitmap(decodeBase64(fotos[position % fotos.length]));
            holder.name.setText(nombres[position % nombres.length]);
            holder.puntos.setText("Puntos para Ganarla: "+puntos[position % puntos.length]);
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
}
