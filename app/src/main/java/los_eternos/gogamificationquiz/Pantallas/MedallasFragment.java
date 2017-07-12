package los_eternos.gogamificationquiz.Pantallas;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.Controladores.MedallaAdapter;
import los_eternos.gogamificationquiz.Modelo.Medalla;
import los_eternos.gogamificationquiz.R;

/**
 * Created by rodrigo on 02-26-17.
 */

public class MedallasFragment extends Fragment {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.content_perfil, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView description;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.medallas_card, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.imagen);
            name = (TextView) itemView.findViewById(R.id.nombre);
            description = (TextView) itemView.findViewById(R.id.visitas);
        }
    }


    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static int LENGTH=0;
        private final String[] mPlaces;
        private final String[] mPlaceDesc;
        private final String[] mPlacePictures;


        public ContentAdapter(Context context) {
            Resources resources = context.getResources();

            List<Medalla> medallas = null;

            Conexion con = new Conexion();
            String url="";


            url += con.getURLLocal()+"medallasPerfil/elias_barrera@hotmail.com";


            medallas = ControlServicio.obtenerMedalla(url,context);

            String [] nombre = new String[medallas.size()];
            String [] descripcion = new String[medallas.size()];
            String [] imagen = new String[medallas.size()];

            for (int i=0;i<medallas.size();i++){
                nombre[i] = medallas.get(i).getNombreMedalla();
                descripcion[i] = medallas.get(i).getDescripcionMedalla();
                imagen[i] = medallas.get(i).getImagenMedalla();
            }


            mPlaces = nombre;
            mPlaceDesc = descripcion;
            //TypedArray a = resources.obtainTypedArray(R.array.places_picture);
            mPlacePictures = imagen;
            LENGTH = medallas.size();

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.picture.setImageBitmap(decodeBase64(mPlacePictures[position % mPlacePictures.length]));
            holder.name.setText(mPlaces[position % mPlaces.length]);
            holder.description.setText(mPlaceDesc[position % mPlaceDesc.length]);

        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }

        //Metodo para decodificar imagenes en Base64
        public static Bitmap decodeBase64(String input) {
            byte[] decodedByte = Base64.decode(input, 0);
            return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
        }
    }
}
