package los_eternos.gogamificationquiz.Pantallas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.Modelo.MostrarActividades;
import los_eternos.gogamificationquiz.Modelo.MostrarAlumnos;
import los_eternos.gogamificationquiz.Modelo.MostrarMedallas;
import los_eternos.gogamificationquiz.R;

public class DocenteActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Conexion con;
    String idgrupo ="";
    String idmateria ="";
    private ArrayList<MostrarAlumnos> alumnos;
    private ArrayList<MostrarMedallas> medallas;
    private ArrayList<MostrarActividades> actividades;
    private DrawerLayout mDrawerLayout;
    static  public TabLayout tabs;
    public FloatingActionButton flot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente);
        con=new Conexion();

        Intent inte = getIntent();
        idmateria += inte.getStringExtra("idmateria");
        idgrupo += inte.getStringExtra("idgrupo");

        flot = (FloatingActionButton) findViewById(R.id.fab);

        flot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Informacion");
                builder.setIcon(R.drawable.gogame);
                builder.setView(R.layout.informacion_docente);
                builder.setNegativeButton(R.string.btn_cancel,null);
                builder.setPositiveButton(R.string.btn_ok,null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Consulta de alumnos por materia y grupo
        alumnos = ControlServicio.obtenerAlumnos(idmateria,idgrupo,DocenteActivity.this);
        //Termina Consulta de alumnos por materia y grupo

        //Consulta de medallas por materia
        medallas = ControlServicio.obtenerMedallas(idmateria,DocenteActivity.this);
        //Termina Consulta de medallas por materia

        //Consulta de actividades por materia y grupo
        actividades = ControlServicio.obtenerActividades(idmateria,idgrupo,DocenteActivity.this);
        //Termina Consulta de actividades por materia y grupo

        /*AGREGANDO TOOLBAR*/


                /*AGREGANDO MOVILIDAD A LA TOOLBAR*/
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager, alumnos, medallas, actividades);

        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


    }



    /*public void navegabilidad2(View view){

        Intent intent = new Intent(getApplicationContext(), CuestionarioActivity.class);
        startActivity(intent);

    }*/
    // UI references.
    /*private LlenadoDatos llenado =null;*/





    private void setupViewPager(ViewPager viewPager, ArrayList<MostrarAlumnos> alumnos, ArrayList<MostrarMedallas> medallas, ArrayList<MostrarActividades> actividades ) {
        Adapter adapter = new Adapter(getSupportFragmentManager());

        /*CREO LOS FRAGMENTS*/
        ListadoMedallas medals = new ListadoMedallas();
        ListadoAlumnos alums = new ListadoAlumnos();
        Actividades activis = new Actividades();
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
        ArrayList<String> pumi = new ArrayList<>();


        for (MostrarMedallas me:medallas){
            nommedallas.add(me.getNommedalla());
            fotome.add(me.getFoto());
            pumi.add(me.getPuntosminimos());
        }

        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<String> puntos = new ArrayList<>();
        ArrayList<String> idact = new ArrayList<>();


        for (MostrarActividades act:actividades){
            nombres.add(act.getNombre());
            puntos.add(act.getIdtipo());
            idact.add(act.getIdact());
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
        parametro2.putStringArrayList("puntosmi",pumi);
        parametro2.putString("idgrupo",idgrupo);
        parametro2.putString("idmateria",idmateria);
        medals.setArguments(parametro2);

        Bundle parametro3 = new Bundle();
        parametro3.putStringArrayList("nombre",nombres);
        parametro3.putStringArrayList("puntos",puntos);
        parametro3.putStringArrayList("nombres_alumnos",nombre);
        parametro3.putStringArrayList("carnets_alumno",carnet);
        parametro3.putStringArrayList("idactividades",idact);
        parametro3.putString("idgrupo",idgrupo);
        parametro3.putString("idmateria",idmateria);
        activis.setArguments(parametro3);


        /*LE MANDO PARAMETROS AL FRAGMENT QUE HE CREADO*/

        /*AGREGO EL NUEVO FRAGMENT COMO UNA TAB*/
        adapter.addFragment(alums, "Alumnos");
        adapter.addFragment(medals,"Medallas");
        adapter.addFragment(activis, "Actividades");

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout){
            LoginActivity LOGOUT = new LoginActivity();
            LOGOUT.logOut(DocenteActivity.this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}