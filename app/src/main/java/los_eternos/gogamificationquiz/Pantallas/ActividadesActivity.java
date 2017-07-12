package los_eternos.gogamificationquiz.Pantallas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.Modelo.MostrarActividades;
import los_eternos.gogamificationquiz.Modelo.MostrarAlumnos;
import los_eternos.gogamificationquiz.Modelo.MostrarCuestionario;
import los_eternos.gogamificationquiz.Modelo.MostrarMedallas;
import los_eternos.gogamificationquiz.R;

public class ActividadesActivity extends AppCompatActivity {
    Conexion con;
    String idgrupo ="1";
    String idmateria ="1";
    private ArrayList<MostrarAlumnos> alumnos;
    private ArrayList<MostrarMedallas> medallas;
    private ArrayList<MostrarActividades> actividades;
    private ArrayList<MostrarCuestionario> cuestionarios;
    private DrawerLayout mDrawerLayout;
    static  public TabLayout tabs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);
        con=new Conexion();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /*Intent inte = getIntent();
        idmateria += inte.getStringExtra("idmateria");
        idgrupo += inte.getStringExtra("idgrupo");*/

        //Consulta de alumnos por materia y grupo
        alumnos = ControlServicio.obtenerAlumnos(idmateria,idgrupo,ActividadesActivity.this);
        //Termina Consulta de alumnos por materia y grupo

        //Consulta de medallas por materia
        medallas = ControlServicio.obtenerMedallas(idmateria,ActividadesActivity.this);
        //Termina Consulta de medallas por materia

        //Consulta de actividades por materia y grupo
        actividades = ControlServicio.obtenerActividades(idmateria,idgrupo,ActividadesActivity.this);
        //Termina Consulta de actividades por materia y grupo

        //Consulta de actividades por materia y grupo
        cuestionarios = ControlServicio.obtenerCuestionario(idmateria,idgrupo,ActividadesActivity.this);
        //Termina Consulta de actividades por materia y grupo


        /*AGREGANDO TOOLBAR*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

                /*AGREGANDO MOVILIDAD A LA TOOLBAR*/
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager, alumnos, medallas, actividades, cuestionarios);

        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }



    /*public void navegabilidad2(View view){

        Intent intent = new Intent(getApplicationContext(), CuestionarioActivity.class);
        startActivity(intent);

    }*/
    // UI references.
    /*private LlenadoDatos llenado =null;*/





    private void setupViewPager(ViewPager viewPager, ArrayList<MostrarAlumnos> alumnos, ArrayList<MostrarMedallas> medallas, ArrayList<MostrarActividades> actividades, ArrayList<MostrarCuestionario> cuestionarios ) {
        Adapter adapter = new Adapter(getSupportFragmentManager());

        /*CREO LOS FRAGMENTS*/
        ListadoMedallas medals = new ListadoMedallas();
        ListadoAlumnos alums = new ListadoAlumnos();
        Actividades activis = new Actividades();
        ListadoCuestionario cuestios = new ListadoCuestionario();
        /*CREO LOS FRAGMENTS*/


        ArrayList<String> nombre = new ArrayList<>();
        ArrayList<String> carnet = new ArrayList<>();
        ArrayList<String> foto = new ArrayList<>();


        for (MostrarAlumnos m:alumnos){
            nombre.add(m.getNOMALUMNO());
            carnet.add(m.getCARNET());
            foto.add(m.getFOTOPERFIL());
        }

        ArrayList<String> nommedallas = new ArrayList<>();
        ArrayList<String> fotome = new ArrayList<>();
        ArrayList<String> id_medalla= new ArrayList<>();


        for (MostrarMedallas me:medallas){
            nommedallas.add(me.getNommedalla());
            fotome.add(me.getFoto());
            id_medalla.add(me.getId_medalla());
        }

        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<String> puntos = new ArrayList<>();

        for (MostrarActividades act:actividades){
            nombres.add(act.getNombre());
            puntos.add(act.getIdtipo());
        }

        ArrayList<String> nombrec = new ArrayList<>();
        ArrayList<String> duracionc = new ArrayList<>();

        for (MostrarCuestionario act:cuestionarios){
            nombrec.add(act.getNombre());
            duracionc.add(act.getDuracion());
        }

        /*LE MANDO PARAMETROS AL FRAGMENT QUE HE CREADO*/
        Bundle parametro = new Bundle();
        parametro.putStringArrayList("nombre",nombre);
        parametro.putStringArrayList("carnet",carnet);
        parametro.putStringArrayList("foto",foto);
        parametro.putString("idgrupo",idgrupo);
        parametro.putString("idmateria",idmateria);
        alums.setArguments(parametro);

        Bundle parametro2 = new Bundle();
        parametro2.putStringArrayList("nommedalla",nommedallas);
        parametro2.putStringArrayList("fotome",fotome);
        parametro2.putString("idgrupo",idgrupo);
        parametro2.putString("idmateria",idmateria);
        parametro2.putStringArrayList("nombres_alumnos",nombre);
        parametro2.putStringArrayList("carnets_alumno",carnet);
        parametro2.putStringArrayList("ids_medalla",id_medalla);
        medals.setArguments(parametro2);

        Bundle parametro3 = new Bundle();
        parametro3.putStringArrayList("nombre",nombres);
        parametro3.putStringArrayList("puntos",puntos);
        parametro3.putString("idgrupo",idgrupo);
        parametro3.putString("idmateria",idmateria);
        activis.setArguments(parametro3);

        Bundle parametro4 = new Bundle();
        parametro4.putStringArrayList("nombrec",nombrec);
        parametro4.putStringArrayList("duracionc",duracionc);
        parametro4.putString("idgrupo",idgrupo);
        parametro4.putString("idmateria",idmateria);
        cuestios.setArguments(parametro4);

        /*LE MANDO PARAMETROS AL FRAGMENT QUE HE CREADO*/

        /*AGREGO EL NUEVO FRAGMENT COMO UNA TAB*/
        adapter.addFragment(alums, "Alumnos");
        adapter.addFragment(medals,"Medallas");
        adapter.addFragment(activis, "Actividad");
        adapter.addFragment(cuestios,"Quiz");

            /*AJUSTA EL TAMAÑO DE LAS TABS*/
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }//FIN DE CLASE ADAPTER


    /*CREANDO LOS MENUS DE LA BARRA*/
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //MenuItem searchItem = menu.findItem(R.id.action_buscar);

        return true;
    }


    /*MENU SELECCIONADO DE LA BARRA*/
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_buscar) {
            Intent intent = new Intent(this, Busqueda.class);
            startActivity(intent);

            return true;
        }else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }
    */


}
