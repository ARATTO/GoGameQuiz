package los_eternos.gogamificationquiz.Pantallas;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import los_eternos.gogamificationquiz.Modelo.MostrarLideres;
import los_eternos.gogamificationquiz.R;

/**
 * Created by Bryan Lobos1 on 14/07/2017.
 */
public class ListadoLideres extends ListFragment {
    ArrayList<String> carnets=null;
    ArrayList<String> puntajes=null;
    String idgrupo="";
    String idmateria="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v=  inflater.inflate(R.layout.item_lideres, container, false);

        ArrayList<String> b = null;
        ArrayList<String> c = null;
        String d=null;
        String e=null;

        if( getArguments() != null){
            b= getArguments().getStringArrayList("carnets");
            c= getArguments().getStringArrayList("puntajes");
            d += getArguments().getString("idgrupo");
            e += getArguments().getString("idmateria");

        }

        idmateria=d;
        idgrupo=e;
        carnets=b;
        puntajes=c;

        String conjunto="";
        conjunto+=""+carnets+"="+puntajes+"";

        ArrayAdapter<String> car = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,carnets);

        setListAdapter(car);

        return  v;
    }
}
