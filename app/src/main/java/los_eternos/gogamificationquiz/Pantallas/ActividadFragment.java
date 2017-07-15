package los_eternos.gogamificationquiz.Pantallas;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.R;


/**
 * Created by Rodrigo on 15/10/2016.
 */
public class ActividadFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    CalendarView calendarView;
    ArrayList<String> actividades = null;
    ArrayList<String> idTA = null;
    String idTipAct="";
    Button guardar;
    TextView nomAct;
    TextView puntosAct;
    String fecha;
    String idgrupo;
    String nommateria;
    Conexion con;
    String URL="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.content_formulario_actividad, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        idgrupo= getArguments().getString("idgrupo");
        nommateria=getArguments().getString("nommateria");

        calendarView = (CalendarView) v.findViewById(R.id.calendarioActividades);

        Spinner tipoActividad = (Spinner) v.findViewById(R.id.tipoActividad);

        guardar = (Button) v.findViewById(R.id.button);

        nomAct = (TextView) v.findViewById(R.id.nombreActividad);

        puntosAct = (TextView) v.findViewById(R.id.puntosActividad);

        actividades = tipoActividades();

        Calendar Calendario = new GregorianCalendar();
        int dia,mes,anio;

        anio=Calendario.get(Calendar.YEAR);
        dia=Calendario.get(Calendar.DAY_OF_MONTH);
        mes=Calendario.get(Calendar.MONTH)+1;

        fecha=anio+"-"+mes+"-"+dia;

        con=new Conexion();

        ArrayAdapter<String> adapterAct= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, actividades);


        adapterAct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoActividad.setAdapter(adapterAct);
        tipoActividad.setOnItemSelectedListener(this);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {

                int imes;
                imes=i1+1;
                fecha=i+"-"+imes+"-"+i2;



                Toast.makeText(getContext(), "Selected Date:\n" + "Day = " + i2 + "\n" + "Month = " + i1 + "\n" + "Year = " + i, Toast.LENGTH_LONG).show();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           String nomActividad= nomAct.getText().toString();
                                           String nom = nomActividad.replace(" ", "%20");
                                           int puntosActividad= Integer.parseInt(puntosAct.getText().toString());



                                           //VALIDAR LOS CAMPOS

                                           URL +=con.getURLLocal()+"obtenerTA.php?accion=3&param3="+puntosActividad+"&param4="+idgrupo+"&param5="+idTipAct+"&param6="+nom+"&param7="+fecha;

                                           //ServiciosController.obtenerTipoActividades(URL,getContext());

                                           /*Intent intent = new Intent(getContext(),Principal.class);
                                           intent.putExtra("idgrupo",idgrupo);
                                           intent.putExtra("nommateria",nommateria);
                                           startActivity(intent);*/


                                           Toast.makeText(getContext(), "Guardando", Toast.LENGTH_SHORT).show();
                                       }
                                   }

        );

        return  v;
    }


    public ArrayList<String> tipoActividades(){
        ArrayList<String> TA = null;

        //String URL = Conexion.URLLocal +"obtenerTA.php?accion=1";

        //TA = ServiciosController.obtenerTipoActividades(URL,getContext());

        return TA;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



        //String URL = Conexion.URLLocal +"obtenerTA.php?accion=2&param1="+parent.getItemAtPosition(position).toString();

        /*idTA = ServiciosController.obtenerTipoActividades(URL,getContext());

        for (String ta:idTA){
            idTipAct = ta;
        }*/


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
