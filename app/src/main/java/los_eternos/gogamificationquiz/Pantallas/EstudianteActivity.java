package los_eternos.gogamificationquiz.Pantallas;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;
import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.Modelo.Materia;
import los_eternos.gogamificationquiz.Modelo.MostrarCuestionario;
import los_eternos.gogamificationquiz.Modelo.MostrarLideres;
import los_eternos.gogamificationquiz.R;

public class EstudianteActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Conexion con;
    public String idgrupo ="";
    public String idmateria ="";
    public String idperfil ="";
    private ArrayList<MostrarCuestionario> cuestionarios;
    private Materia materias;
    private ArrayList<MostrarLideres> lideres;
    private DrawerLayout mDrawerLayout;
    static  public TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante);
        con=new Conexion();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent inte = getIntent();
        idmateria += inte.getStringExtra("idmateria");
        idgrupo += inte.getStringExtra("idgrupo");
        idperfil += inte.getStringExtra("idperfil");

        //Consulta de actividades por materia y grupo
        cuestionarios = ControlServicio.obtenerCuestionario(idmateria,idgrupo,EstudianteActivity.this);
        //Termina Consulta de actividades por materia y grupo

        //Consulta de actividades por materia y grupo
        materias = ControlServicio.obtenerDescripcionMateria(idmateria,EstudianteActivity.this);
        //Termina Consulta de actividades por materia y grupo

        //Consulta de actividades por materia y grupo
        lideres = ControlServicio.obtenerLideres(idmateria,idgrupo,EstudianteActivity.this);
        //Termina Consulta de actividades por materia y grupo



                /*AGREGANDO MOVILIDAD A LA TOOLBAR*/
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager, cuestionarios, materias,lideres);

        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void setupViewPager(ViewPager viewPager, ArrayList<MostrarCuestionario> cuestionarios , Materia materias, ArrayList<MostrarLideres> lideres) {
        Adapter adapter = new Adapter(getSupportFragmentManager());

        ListadoCuestionario cuestios = new ListadoCuestionario();
        ListadoMaterias materi = new ListadoMaterias();
        ListadoLideres lide = new ListadoLideres();
        /*CREO LOS FRAGMENTS*/


        ArrayList<String> nombrec = new ArrayList<>();
        ArrayList<String> duracionc = new ArrayList<>();
        ArrayList<String> idc = new ArrayList<>();


        for (MostrarCuestionario m:cuestionarios){
            nombrec.add(m.getNombre());
            duracionc.add(m.getDuracion());
            idc.add(m.getIdC());
        }

        Bundle parametro = new Bundle();
        parametro.putStringArrayList("nombrec",nombrec);
        parametro.putStringArrayList("duracionc",duracionc);
        parametro.putStringArrayList("ids",idc);
        parametro.putString("idgrupo",idgrupo);
        parametro.putString("idmateria",idmateria);
        parametro.putString("idperfil",idperfil);
        cuestios.setArguments(parametro);

        String nombrema;
        String codigoma;
        String imagenma;


            nombrema=materias.getNombreMateria();
            codigoma=materias.getCodigoMateria();
            imagenma=materias.getImagenMateria();


        Bundle parametro2 = new Bundle();
        parametro2.putString("nombrema",nombrema);
        parametro2.putString("codigoma",codigoma);
        parametro2.putString("imagenma",imagenma);
        parametro2.putString("idmateria",idmateria);
        materi.setArguments(parametro2);

        ArrayList<String> carnet = new ArrayList<>();
        ArrayList<String> puntaje = new ArrayList<>();
        ArrayList<String> concatenacion = new ArrayList<>();


        for (MostrarLideres m:lideres){
            carnet.add(m.getCarnet());
            puntaje.add(m.getPuntaje());
            concatenacion.add(m.getCarnet()+"                                               "+m.getPuntaje());

        }


        Bundle parametro3 = new Bundle();
        parametro3.putStringArrayList("carnets",concatenacion);
        parametro3.putString("idgrupo",idgrupo);
        parametro3.putString("idmateria",idmateria);
        lide.setArguments(parametro3);




        /*LE MANDO PARAMETROS AL FRAGMENT QUE HE CREADO*/

        /*AGREGO EL NUEVO FRAGMENT COMO UNA TAB*/
        adapter.addFragment(cuestios,"Cuestionario");
        adapter.addFragment(materi,"Descripcion de Materia");
        adapter.addFragment(lide,"Lideres");

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

        //noinspection SimplifiableIfStatement
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
            LOGOUT.logOut(EstudianteActivity.this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
