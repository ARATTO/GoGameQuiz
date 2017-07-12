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
    private List<MostrarAlumnos> lista_alumnos;
    private ArrayList<String> lista_alumnos_nombres;
    private ArrayList<String> lista_alumnos_carnets;
    private ArrayList<String> lista_medallas_ids;

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
            lista_alumnos_nombres = getArguments().getStringArrayList("nombres_alumnos");
            lista_alumnos_carnets = getArguments().getStringArrayList("carnets_alumno");
            lista_medallas_ids= getArguments().getStringArrayList("ids_medalla");
        }

        Nombre = b;
        Foto = c;
        //new CargarAlumnosTask(id_grupo,nombre_materia,container.getContext()).execute();


        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(),
                Nombre,
                Foto,
                lista_alumnos_nombres,
                lista_alumnos_carnets,
                lista_medallas_ids);
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
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });*/
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        private int LENGTH;

        private  String[] nombres;
        private  String[] fotos;
        private  String[] ids;
        private  Context contexto;
        private  ArrayList<String> alumnos;
        private  ArrayList<String> carnets;
        private ArrayList<String> ids_medallas;



        //private String[] mPlaceDesc;
        // private final Drawable[] mPlaceAvators;

        public ContentAdapter(Context context,ArrayList<String> nombre, ArrayList<String> foto,
                              ArrayList<String> alumnos,ArrayList<String> carnets,
                              ArrayList<String> ids_medalla) {

            Resources resources = context.getResources();
            //nombre = resources.getStringArray(R.array.places);
            //mPlaceDesc = resources.getStringArray(R.array.place_desc);
            //imagen
            //   TypedArray a = resources.obtainTypedArray(R.array.place_avator);
            // mPlaceAvators = new Drawable[a.length()];
            //for (int i = 0; i < mPlaceAvators.length; i++) {
            //   mPlaceAvators[i] = a.getDrawable(i);
            // }
            //a.recycle();

            LENGTH = nombre.size();
            nombres = new String[nombre.size()];
            nombres = nombre.toArray(nombres);


            //LENGTH = foto.size();
            fotos = new String[foto.size()];
            fotos = foto.toArray(fotos);

            ids= new String[ids_medalla.size()];
            ids=ids_medalla.toArray(ids);

            //asignar medallas inicia here
            contexto=context;
            this.alumnos=alumnos;
            this.carnets=carnets;
            this.ids_medallas=ids_medalla;


        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.picture.setImageBitmap(decodeBase64(fotos[position % fotos.length]));
            holder.name.setText(nombres[position % nombres.length]);
            holder.id.setText(ids[position % ids.length]);
            holder.picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String[] names_alumnos =alumnos.toArray(new String[alumnos.size()]);
                    final String[] carnets_alumnos =carnets.toArray(new String[carnets.size()]);
                    final String[] id_medallas = ids_medallas.toArray(new String[ids_medallas.size()]);

                    AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                    builder.setTitle("Seleccione un Estudiante");
                    builder.setIcon(R.drawable.gogame);
                    builder.setNegativeButton(R.string.btn_cancel,null);
                    builder.setPositiveButton(R.string.btn_asignar_todos, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String text="";

                            for (String nombre: names_alumnos) {
                                text+=nombre;
                                text+=" ";
                            }

                            text+=holder.id.getText();
                            Toast.makeText(contexto, text, Toast.LENGTH_SHORT).show();

                        }
                    });
                    builder.setItems(names_alumnos, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            // Do something with the selection
                            Toast.makeText(contexto, names_alumnos[item]+" "+carnets_alumnos[item]+" "+ holder.id.getText(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });
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

    public void asignar_alumnos(List<MostrarAlumnos> alumnos){
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
    }
}
