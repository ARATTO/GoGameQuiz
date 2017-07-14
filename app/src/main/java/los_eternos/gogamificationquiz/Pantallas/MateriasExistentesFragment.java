package los_eternos.gogamificationquiz.Pantallas;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import los_eternos.gogamificationquiz.Modelo.Materia;

import los_eternos.gogamificationquiz.R;

/**
 * Created by Hunter on 12/7/2017.
 */

public class MateriasExistentesFragment extends Fragment {

    ArrayList<String> nombreMateria;
    ArrayList<String> codigoMateria;
    ArrayList<String> nombreGrupo;
    ArrayList<String> imagenMateria;
    String idGrupo = "";
    String idMateria = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<String> nomMateria = null;
        ArrayList<String> codiMateria = null;
        ArrayList<String> nomGrupo = null;
        ArrayList<String> imagMateria = null;

        if (getArguments() != null) {
            nomMateria = getArguments().getStringArrayList("nombrem");
            codiMateria = getArguments().getStringArrayList("codma");
            nomGrupo = getArguments().getStringArrayList("nomgru");
            imagMateria = getArguments().getStringArrayList("foto");

        }

        nombreMateria = nomMateria;
        codigoMateria = codiMateria;
        nombreGrupo = nomGrupo;
        imagenMateria = imagMateria;

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        MateriasExistentesFragment.ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(), nombreMateria, codigoMateria, nombreGrupo, imagenMateria);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    public static class MateriaViewHolder extends RecyclerView.ViewHolder {

        // Campos respectivos de un item
        public TextView nommateria;
        public TextView codmateria;
        public TextView nombgrupo;
        public ImageView imagmateria;
        public RelativeLayout relative;


        public MateriaViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.card_view, parent, false));

            nommateria = (TextView) itemView.findViewById(R.id.materia_nombre);
            codmateria = (TextView) itemView.findViewById(R.id.materia_codigo);
            nombgrupo = (TextView) itemView.findViewById(R.id.grupo_nombre);
            imagmateria = (ImageView) itemView.findViewById(R.id.materia_imagen);
            relative = (RelativeLayout) itemView.findViewById(R.id.main_content);

        }


    }

    public static class ContentAdapter extends RecyclerView.Adapter<MateriaViewHolder> {
        // Set numbers of List in RecyclerView.


        ArrayList<Materia> materias = new ArrayList<Materia>();

        private int LENGTH;
        private  String[] nombreMaterias ;
        private  String[] codigoMaterias ;
        private  String[] nombreGrupos ;
        private  String[] imagenMaterias;
        //private String[] mPlaceDesc;
        // private final Drawable[] mPlaceAvators;

        public ContentAdapter(Context context, ArrayList<String> nom, ArrayList<String> cod, ArrayList<String> gru, ArrayList<String> ima) {
            Resources resources = context.getResources();


            LENGTH = nom.size();
            nombreMaterias = new String[nom.size()];
            nombreMaterias = nom.toArray(nombreMaterias);


            codigoMaterias = new String[cod.size()];
            codigoMaterias = cod.toArray(codigoMaterias);


            nombreGrupos = new String[gru.size()];
            nombreGrupos = gru.toArray(nombreGrupos);

            imagenMaterias = new String[ima.size()];
            imagenMaterias = ima.toArray(imagenMaterias);


        }

        @Override
        public MateriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MateriaViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(MateriaViewHolder holder, int position) {
            holder.imagmateria.setImageBitmap(decodeBase64(imagenMaterias[position % imagenMaterias.length]));
            holder.nommateria.setText(nombreMaterias[position % nombreMaterias.length]);
            holder.codmateria.setText(codigoMaterias[position % codigoMaterias.length]);
            holder.nombgrupo.setText(nombreGrupos[position % nombreGrupos.length]);
            //holder.mano.setTag(R.id.TAG_COLOR_ID,"gray");
            //holder.punto.setTag(R.id.TAG_COLOR_ID,"gray");

        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }

        public static Bitmap decodeBase64(String input){
            byte[] decodedByte = Base64.decode(input, 0);
            return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
        }

    }



}
