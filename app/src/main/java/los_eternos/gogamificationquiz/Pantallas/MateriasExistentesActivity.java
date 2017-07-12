package los_eternos.gogamificationquiz.Pantallas;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import los_eternos.gogamificationquiz.Modelo.Materia;
import los_eternos.gogamificationquiz.R;

public class MateriasExistentesActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.activity_materias_existentes, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagenMateria;
        public TextView nombreMateria;
        public TextView codigoMateria;
        public TextView nombreGrupo;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.card_view, parent, false));
            imagenMateria = (ImageView) itemView.findViewById(R.id.materia_imagen);
            nombreMateria = (TextView) itemView.findViewById(R.id.materia_nombre);
            codigoMateria = (TextView) itemView.findViewById(R.id.materia_codigo);
            nombreGrupo = (TextView) itemView.findViewById(R.id.grupo_nombre);
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;
        private final String[] mPlaces;
        private final String[] mPlaceDesc;
        private final String[] mCosas;
        private final Drawable[] mPlacePictures;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mPlaces = resources.getStringArray(R.array.places);
            mPlaceDesc = resources.getStringArray(R.array.place_desc);
            mCosas = resources.getStringArray(R.array.places);
            TypedArray a = resources.obtainTypedArray(R.array.places_picture);
            mPlacePictures = new Drawable[a.length()];
            for (int i = 0; i < mPlacePictures.length; i++) {
                mPlacePictures[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.imagenMateria.setImageDrawable(mPlacePictures[position % mPlacePictures.length]);
            holder.nombreMateria.setText(mPlaces[position % mPlaces.length]);
            holder.codigoMateria.setText(mPlaceDesc[position % mPlaceDesc.length]);
            holder.nombreGrupo.setText(mCosas[position % mCosas.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }


        //recibir el correo del login
        // Bundle datos = null;

        //String datos = getIntent().getExtras();
        //int tipoUsuario = datos.getInt("resultado");


        //buscar el perfil
        /*
            List<Perfil> perfill = null;
            Conexion c = new Conexion();
            String url = "";

            url+= c.getURLLocal()+ "perfil/" +datos.getString("email");

             perfil = ControlServicio.obtenerPerfil(url,getActivity());

         */


        //buscar materias inscritas

        //hacer el metodo



    }

    public void navegabilidad(View view) {

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
