package los_eternos.gogamificationquiz.Pantallas;

        import android.content.Context;
        import android.content.Intent;
        import android.content.res.Resources;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
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

        import los_eternos.gogamificationquiz.R;


/**
 * Created by Bryan Lobos1 on 30/09/2016.
 */
public class Actividades extends Fragment {

    ArrayList<String> nombre=null;
    ArrayList<String> idtipo=null;
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
            b= getArguments().getStringArrayList("nombre");
            c = getArguments().getStringArrayList("puntos");
            d += getArguments().getString("idgrupo");
            e += getArguments().getString("idmateria");

        }

        idgrupo=d;
        nommateria=e;

        ViewHolder.idgrupo=idgrupo;
        ViewHolder.nommateria=nommateria;

        nombre = b;
        idtipo = c;

        //return inflater.inflate(R.layout.item_alumnos, null);
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);

        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(),nombre,idtipo);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView idtipo;
        public static String idgrupo="";
        public static String nommateria="";


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_actividades, parent, false));

            name = (TextView) itemView.findViewById(R.id.nombre);
            idtipo = (TextView) itemView.findViewById(R.id.descripcion);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*String nom,id;
                    nom = nommateria;
                    id = idgrupo;

                    System.out.println("valor del idGrupo en la lista: "+id);
                    System.out.println("valor del nombre en la lista: "+nom);

                    Context context = v.getContext();
                    Intent intent = new Intent(context, ActividadesActivity.class);
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
            holder.idtipo.setText(descripcion[position % descripcion.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }




    }



}
