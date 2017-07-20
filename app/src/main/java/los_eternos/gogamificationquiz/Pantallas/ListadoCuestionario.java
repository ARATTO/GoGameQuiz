package los_eternos.gogamificationquiz.Pantallas;


import android.content.Context;
import android.content.Intent;
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
    ArrayList<String> ids=null;
    String idgrupo="";
    String nommateria="";
    String perfil="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<String> b = null;
        ArrayList<String> c = null;
        ArrayList<String> c1 = null;
        String d = "";
        String e = "";
        String f = "";
        if( getArguments() != null){
            b= getArguments().getStringArrayList("nombrec");
            c = getArguments().getStringArrayList("duracionc");
            c1 = getArguments().getStringArrayList("ids");
            d += getArguments().getString("idgrupo");
            e += getArguments().getString("idmateria");
            f += getArguments().getString("idperfil");
        }

        idgrupo=d;
        nommateria=e;
        perfil=f;

        ViewHolder.idgrupo=idgrupo;
        ViewHolder.nommateria=nommateria;
        ViewHolder.perfil=perfil;


        nombre = b;
        duracion = c;
        ids=c1;

        //return inflater.inflate(R.layout.item_alumnos, null);
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);

        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(),nombre,duracion,ids);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView duracion;
        public TextView id;
        //public FloatingActionButton flotante;
        public static String idgrupo="";
        public static String nommateria="";
        public static String perfil="";


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_cuestionario, parent, false));

            name = (TextView) itemView.findViewById(R.id.nombre);
            duracion = (TextView) itemView.findViewById(R.id.cuestionario);
            id = (TextView) itemView.findViewById(R.id.id);
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
                    Context context = v.getContext();
                    Intent intent = new Intent(context, CuestionarioActivity.class);
                    System.out.println(id.getText());
                    System.out.println(perfil);
                    System.out.println(idgrupo);
                    System.out.println(nommateria);
                    intent.putExtra("idcuestionario",id.getText());
                    intent.putExtra("idperfil",perfil);
                    intent.putExtra("idgrupo",idgrupo);
                    intent.putExtra("idmateria",nommateria);

                    context.startActivity(intent);
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
        private String[] ids;

        public ContentAdapter(Context context, ArrayList<String> nombres, ArrayList<String> duracionc,ArrayList<String> idc) {
            LENGTH = nombres.size();
            nombre = new String[nombres.size()];
            nombre = nombres.toArray(nombre);
            duracion = new String[duracionc.size()];
            duracion = duracionc.toArray(duracion);
            ids = new String[idc.size()];
            ids = idc.toArray(ids);

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.name.setText(nombre[position % nombre.length]);
            holder.duracion.setText("Duracion de Cuestionario hh:mm:ss: "+duracion[position % duracion.length]);
            holder.id.setText(ids[position % ids.length]);

        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
