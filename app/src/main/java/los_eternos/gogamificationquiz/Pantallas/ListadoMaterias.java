package los_eternos.gogamificationquiz.Pantallas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
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

import java.util.ArrayList;

import los_eternos.gogamificationquiz.R;

/**
 * Created by Bryan Lobos1 on 12/07/2017.
 */
public class ListadoMaterias extends Fragment {
    String nombre=null;
    String codigo=null;
    String imagen=null;
    String idgrupo="";
    String idmateria="";
    public TextView name;
    public TextView cod;
    public ImageView ima;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String b = null;
        String c = null;
        String d = null;
        String e = "";
        if( getArguments() != null){
            b= getArguments().getString("nombrema");
            c = getArguments().getString("codigoma");
            d = getArguments().getString("imagenma");
            e += getArguments().getString("idmateria");

        }

        idmateria=e;

        nombre = b;
        codigo = c;
        imagen = d;
        View rootView = inflater.inflate(R.layout.detalle_materia, container, false);

        name = (TextView) rootView.findViewById(R.id.MateriaNom);
        cod = (TextView) rootView.findViewById(R.id.CodigoMateria);
        ima = (ImageView) rootView.findViewById(R.id.ImagenMateria);

        name.setText(nombre);
        cod.setText(codigo);

        byte[] decodedString = Base64.decode(imagen, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        ima.setImageBitmap(decodedByte);
        return rootView;

    }


    /**
     * Adapter to display recycler view.
     */
}
