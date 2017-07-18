package los_eternos.gogamificationquiz.Pantallas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
    ArrayList<String> ids=null;
    String idgrupo="";
    String idmateria="";
    private ArrayList<String> lista_alumnos_nombres;
    private ArrayList<String> lista_alumnos_carnets;
    private ArrayList<String> lista_idact;


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
            lista_alumnos_nombres = getArguments().getStringArrayList("nombres_alumnos");
            lista_alumnos_carnets = getArguments().getStringArrayList("carnets_alumno");
            lista_idact = getArguments().getStringArrayList("idactividades");

        }

        idgrupo=d;
        idmateria=e;

        ViewHolder.idgrupo=idgrupo;
        ViewHolder.idmateria=idmateria;

        nombre = b;
        puntos = c;

        //return inflater.inflate(R.layout.item_alumnos, null);
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);

        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(),nombre,puntos,lista_alumnos_nombres,lista_alumnos_carnets,lista_idact);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView puntos;
        public TextView ids;
        public static String idgrupo="";
        public static String idmateria="";


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_actividades, parent, false));

            name = (TextView) itemView.findViewById(R.id.nombre);
            puntos = (TextView) itemView.findViewById(R.id.descripcion);
            ids = (TextView) itemView.findViewById(R.id.idAct);
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
        private String[] idas;
        private  Context contexto;
        private  ArrayList<String> alumnos;
        private  ArrayList<String> carnets;

        public ContentAdapter(Context context, ArrayList<String> nombres, ArrayList<String> puntos,ArrayList<String> alumnos,ArrayList<String> carnets,ArrayList<String> ids) {
            LENGTH = nombres.size();
            nombre = new String[nombres.size()];
            nombre = nombres.toArray(nombre);
            descripcion = new String[puntos.size()];
            descripcion = puntos.toArray(descripcion);
            idas = new String[ids.size()];
            idas = ids.toArray(idas);

            contexto=context;
            this.alumnos=alumnos;
            this.carnets=carnets;


        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final ViewHolder holder1=holder;
            holder1.name.setText(nombre[position % nombre.length]);
            holder1.puntos.setText("Puntos de actividad: "+descripcion[position % descripcion.length]);
            holder1.ids.setText(idas[position% idas.length]);
            holder1.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String[] names_alumnos =alumnos.toArray(new String[alumnos.size()]);
                    final String[] carnets_alumnos =carnets.toArray(new String[carnets.size()]);

                    AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                    builder.setTitle("Seleccione un Estudiante");
                    builder.setIcon(R.drawable.gogame);
                    builder.setNegativeButton(R.string.btn_cancel,null);
                    builder.setPositiveButton(R.string.btn_asignar_todos, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String text="";
                            // Asignar medalla a un alumno
                            for (String nombre: carnets_alumnos) {
                                text+=nombre;
                                text+=" ";
                            }

                            text+=holder1.ids.getText();
                            Toast.makeText(contexto, text, Toast.LENGTH_SHORT).show();

                        }
                    });
                    builder.setItems(names_alumnos, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            // Asignar medallas a todos los alumnos
                            //modulo para asginar puntos
                            Toast.makeText(contexto, names_alumnos[item]+" "+carnets_alumnos[item]+" "+ holder1.ids.getText(), Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
