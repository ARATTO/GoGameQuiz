package los_eternos.gogamificationquiz.Pantallas;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.Modelo.Perfil;
import los_eternos.gogamificationquiz.R;

/**
 * Created by rodrigo on 02-26-17.
 */

public class PerfilFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //codigo de conexion
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View view = inflater.inflate(R.layout.perfil_fragment, container,false);

        TextView nombre = (TextView) view.findViewById(R.id.nombrePerfil);
        ImageView foto = (ImageView) view.findViewById(R.id.fotoPerfil);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcionPerfil);

        Conexion con = new Conexion();
        String url="";

        String correo =getArguments().getString("correo");

        url += con.getURLLocal()+"perfil/"+correo;

        System.out.println("url: "+url);
        List<Perfil> perfil = null;

        perfil = ControlServicio.obtenerPerfil(url, getActivity());


        for (int i =0; i<perfil.size();i++){
            nombre.setText(perfil.get(i).getNombrePerfil());

            byte[] decodedByte = Base64.decode(perfil.get(i).getImagenPerfil(), 0);

            foto.setImageBitmap(BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length));
            String des = perfil.get(i).getDescripcionPerfil();


            if(des.compareTo("null") == 0){

            }else {

                descripcion.setText(perfil.get(i).getDescripcionPerfil());
            }


        }




        return view;
    }
}
