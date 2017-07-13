package los_eternos.gogamificationquiz.Pantallas;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
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

import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.R;


/**
 * Created by Bryan Lobos1 on 30/09/2016.
 */
public class Actividades extends Fragment {

    ArrayList<String> nombre=null;
    ArrayList<String> puntos=null;
    String idgrupo="";
    String idmateria="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<String> b = null;
        ArrayList<String> c = null;
        String d = "";
        String e = "";
        if( getArguments() != null){
            b= getArguments().getStringArrayList("nombre");
            c = getArguments().getStringArrayList("puntos");
            d += getArguments().getString("idgrupo");
            e += getArguments().getString("idmateria");

        }

        idgrupo=d;
        idmateria=e;

        ViewHolder.idgrupo=idgrupo;
        ViewHolder.idmateria=idmateria;

        nombre = b;
        puntos = c;

        //return inflater.inflate(R.layout.item_alumnos, null);
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);

        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(),nombre,puntos);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView puntos;
        public static String idgrupo="";
        public static String idmateria="";


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_actividades, parent, false));

            name = (TextView) itemView.findViewById(R.id.nombre);
            puntos = (TextView) itemView.findViewById(R.id.descripcion);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String idgru,idmat;
                    idmat = idmateria;
                    idgru = idgrupo;

                    System.out.println("Agrege funcionalidad a este boton");

                    /*System.out.println("valor del idGrupo en la lista: "+idgru);
                    System.out.println("valor del nombre en la lista: "+idmat);

                    Context context = v.getContext();
                    Intent intent = new Intent(context, DocenteActivity.class);
                    intent.putExtra("puntos",puntos.getText());
                    intent.putExtra("idmateria",idmat);
                    intent.putExtra("idgrupo",idgru);
                    context.startActivity(intent);*/
                }
            });


        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of Card in RecyclerView.
        private int LENGTH;
        private  String[] nombre ;
        private String[] descripcion;

        public ContentAdapter(Context context, ArrayList<String> nombres, ArrayList<String> puntos) {
            LENGTH = nombres.size();
            nombre = new String[nombres.size()];
            nombre = nombres.toArray(nombre);
            descripcion = new String[puntos.size()];
            descripcion = puntos.toArray(descripcion);

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.name.setText(nombre[position % nombre.length]);
            holder.puntos.setText("Puntos de actividad: "+descripcion[position % descripcion.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
