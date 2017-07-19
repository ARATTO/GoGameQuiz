package los_eternos.gogamificationquiz.Pantallas;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import los_eternos.gogamificationquiz.R;
/**
 * Created by Bryan Lobos1 on 12/07/2017.
 */
public class ListadoCuestionario extends Fragment {
    ArrayList<String> nombre=null;
    ArrayList<String> duracion=null;
    String idgrupo="";
    String nommateria="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<String> b = null;
        ArrayList<String> c = null;
        String d = "";
        String e = "";
        if( getArguments() != null){
            b= getArguments().getStringArrayList("nombrec");
            c = getArguments().getStringArrayList("duracionc");
            d += getArguments().getString("idgrupo");
            e += getArguments().getString("idmateria");

        }

        idgrupo=d;
        nommateria=e;

        ViewHolder.idgrupo=idgrupo;
        ViewHolder.nommateria=nommateria;

        nombre = b;
        duracion = c;

        //return inflater.inflate(R.layout.item_alumnos, null);
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);

        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(),nombre,duracion);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView duracion;
        //public FloatingActionButton flotante;
        public static String idgrupo="";
        public static String nommateria="";


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_cuestionario, parent, false));

            name = (TextView) itemView.findViewById(R.id.nombre);
            duracion = (TextView) itemView.findViewById(R.id.cuestionario);
            //flotante = (FloatingActionButton) itemView.findViewById(R.id.fab);
            /*flotante.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                System.out.print("Replace with your own action");

                                            }
            });*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.print("Resolver Cuestionario");
                    /*String nom,id;
                    nom = nommateria;
                    id = idgrupo;

                    System.out.println("valor del idGrupo en la lista: "+id);
                    System.out.println("valor del nombre en la lista: "+nom);

                    Context context = v.getContext();
                    Intent intent = new Intent(context, DocenteActivity.class);
                    intent.putExtra("idActividad",idtipo.getText());
                    intent.putExtra("nommateria",nom);
                    intent.putExtra("idgrupo",id);
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
        private String[] duracion;

        public ContentAdapter(Context context, ArrayList<String> nombres, ArrayList<String> duracionc) {
            LENGTH = nombres.size();
            nombre = new String[nombres.size()];
            nombre = nombres.toArray(nombre);
            duracion = new String[duracionc.size()];
            duracion = duracionc.toArray(duracion);

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.name.setText(nombre[position % nombre.length]);
            holder.duracion.setText("Duracion de Cuestionario hh:mm:ss: "+duracion[position % duracion.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
