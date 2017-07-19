package los_eternos.gogamificationquiz.Pantallas;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import los_eternos.gogamificationquiz.R;


/**
 * Created by Hunter on 12/7/2017.
 */

public class MateriasExistentesFragment extends Fragment{

    //Declaracion de variables globales
    public ArrayList<String> nombreMateria;
    public ArrayList<String> codigoMateria;
    public ArrayList<String> nombreGrupo;
    public ArrayList<String> imagenMateria;
    public ArrayList<String> codigoCiclo;
    public ArrayList<String> idMateria;
    public ArrayList<String> idGrupo;
    public ArrayList<String> idPerfil;
    public static String email;
    public static int resultado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inicializando las variables
        ArrayList<String> nomMateria = null;
        ArrayList<String> codiMateria = null;
        ArrayList<String> nomGrupo = null;
        ArrayList<String> imagMateria = null;
        ArrayList<String> codiCiclo = null;
        ArrayList<String> idMate = null;
        ArrayList<String> idGru = null;
        ArrayList<String> idPer = null;
        String correo="";
        int result=0;

        //Recibe los parametros de la Activity
        if (getArguments() != null) {
            nomMateria = getArguments().getStringArrayList("nombrem");
            codiMateria = getArguments().getStringArrayList("codma");
            nomGrupo = getArguments().getStringArrayList("nomgru");
            imagMateria = getArguments().getStringArrayList("foto");
            codiCiclo = getArguments().getStringArrayList("codci");
            idMate = getArguments().getStringArrayList("idmate");
            idGru = getArguments().getStringArrayList("idgru");
            idPer = getArguments().getStringArrayList("idper");
            correo = getArguments().getString("email");
            result = getArguments().getInt("resultado");

        }

        nombreMateria = nomMateria;
        codigoMateria = codiMateria;
        nombreGrupo = nomGrupo;
        imagenMateria = imagMateria;
        codigoCiclo = codiCiclo;
        idMateria = idMate;
        idGrupo = idGru;
        idPerfil = idPer;
        email = correo;
        resultado = result;


        System.out.println(codigoCiclo);
        System.out.println(idGrupo);
        System.out.println(idMateria);
        System.out.println(idPerfil);
        System.out.println(email);
        System.out.println(resultado);

        //Enlazando la activity con el RecyclerView
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        MateriasExistentesFragment.ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(), nombreMateria, codigoMateria, nombreGrupo, codigoCiclo, idMateria, idGrupo, idPerfil, imagenMateria);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;

    }

    //CLASE ANIDADA
    public static class MateriaViewHolder extends RecyclerView.ViewHolder {

        // Campos respectivos de un item
        public TextView nommateria;
        public TextView codmateria;
        public TextView nombgrupo;
        public TextView codciclo;
        public TextView idmateria;
        public TextView idgrupo;
        public TextView idperfil;
        public ImageView imagmateria;
        public RelativeLayout relative;

        private final Context context;
        //MateriasExistentesFragment mef = new MateriasExistentesFragment(); //Instancia de la clase externa

        public MateriaViewHolder(LayoutInflater inflater, ViewGroup parent) {

            //Enlazando el CardView con el RecyclerView
            super(inflater.inflate(R.layout.card_view, parent, false));

            nommateria = (TextView) itemView.findViewById(R.id.materia_nombre);
            codmateria = (TextView) itemView.findViewById(R.id.materia_codigo);
            nombgrupo = (TextView) itemView.findViewById(R.id.grupo_nombre);
            codciclo = (TextView) itemView.findViewById(R.id.codigo_ciclo);
            idmateria = (TextView) itemView.findViewById(R.id.id_materia);
            idgrupo = (TextView) itemView.findViewById(R.id.id_grupo);
            idperfil = (TextView) itemView.findViewById(R.id.id_perfil);
            imagmateria = (ImageView) itemView.findViewById(R.id.materia_imagen);
            relative = (RelativeLayout) itemView.findViewById(R.id.main_content);
            context = itemView.getContext();
            //otro = codciclo.getText().toString();


            //Evento que se activa al presionar un item del RecyclerView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Si el que esta registrado es estudiante
                    if(resultado == 1){

                        System.out.println("Resultado: " + resultado);
                        System.out.println("Id materia: " + idmateria.getText().toString());
                        System.out.println("Id grupo: " + idgrupo.getText().toString());
                        System.out.println("Codigo ciclo: " + codciclo.getText().toString());
                        System.out.println("Id perfil: " + idperfil.getText().toString());
                        System.out.println("Email: " + email);

                        Intent intent = new Intent(context.getApplicationContext(), EstudianteActivity.class);
                        intent.putExtra("idmateria", idmateria.getText().toString());
                        intent.putExtra("idgrupo", idgrupo.getText().toString());
                        intent.putExtra("email", email);
                        intent.putExtra("codigociclo", codciclo.getText().toString());
                        intent.putExtra("idperfil", idperfil.getText().toString());
                        intent.putExtra("resultado", resultado);
                        context.startActivity(intent);

                    }

                    //Si el que esta registrado es docente
                    else {

                        System.out.println("Resultado: " + resultado);
                        System.out.println("Id materia: " + idmateria.getText().toString());
                        System.out.println("Id grupo: " + idgrupo.getText().toString());
                        System.out.println("Codigo ciclo: " +  codciclo.getText().toString());
                        System.out.println("Id perfil: " + idperfil.getText().toString());
                        System.out.println("Email: " + email);

                        Intent intent = new Intent(context.getApplicationContext(), DocenteActivity.class);
                        intent.putExtra("idmateria", idmateria.getText().toString());
                        intent.putExtra("idgrupo", idgrupo.getText().toString());
                        intent.putExtra("email", email);
                        intent.putExtra("codigociclo", codciclo.getText().toString());
                        intent.putExtra("resultado", resultado);
                        context.startActivity(intent);

                    }

                }
            });

        }

    }

    //FIN DE CLASE ANIDADA

    //CLASE ANIDADA
    public static class ContentAdapter extends RecyclerView.Adapter<MateriaViewHolder> {
        // Set numbers of List in RecyclerView.


        private int LENGTH;
        private String[] nombreMaterias ;
        private String[] codigoMaterias ;
        private String[] nombreGrupos ;
        private String[] idMaterias;
        private String[] idGrupos;
        private String[] imagenMaterias;
        private String[] codigoCiclos;
        private String[] idPerfiles;
        Context context;
         //private String[] mPlaceDesc;
        // private final Drawable[] mPlaceAvators;



        public ContentAdapter(Context context, ArrayList<String> nom, ArrayList<String> cod, ArrayList<String> gru, ArrayList<String> cic, ArrayList<String> idm, ArrayList<String> idg, ArrayList<String> idp, ArrayList<String> ima) {
            Resources resources = context.getResources();

            this.context = context;

            LENGTH = nom.size();
            nombreMaterias = new String[nom.size()];
            nombreMaterias = nom.toArray(nombreMaterias);

            codigoMaterias = new String[cod.size()];
            codigoMaterias = cod.toArray(codigoMaterias);

            idMaterias = new String[idm.size()];
            idMaterias = idm.toArray(idMaterias);

            idGrupos = new String[idg.size()];
            idGrupos = idg.toArray(idGrupos);

            idPerfiles = new String[idp.size()];
            idPerfiles = idp.toArray(idPerfiles);

            nombreGrupos = new String[gru.size()];
            nombreGrupos = gru.toArray(nombreGrupos);

            codigoCiclos = new String[cic.size()];
            codigoCiclos = cic.toArray(codigoCiclos);

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
            holder.codciclo.setText(codigoCiclos[position % codigoCiclos.length]);
            holder.nombgrupo.setText(nombreGrupos[position % nombreGrupos.length]);
            holder.idmateria.setText(idMaterias[position % idMaterias.length]);
            holder.idgrupo.setText(idGrupos[position % idGrupos.length]);
            holder.idperfil.setText(idPerfiles[position % idPerfiles.length]);
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
    //FIN DE CLASE ANIDADA

}
