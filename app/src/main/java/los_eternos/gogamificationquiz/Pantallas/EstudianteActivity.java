package los_eternos.gogamificationquiz.Pantallas;

import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.Modelo.MostrarActividades;
import los_eternos.gogamificationquiz.Modelo.MostrarAlumnos;
import los_eternos.gogamificationquiz.Modelo.MostrarCuestionario;
import los_eternos.gogamificationquiz.Modelo.MostrarMedallas;
import los_eternos.gogamificationquiz.R;


public class EstudianteActivity extends AppCompatActivity {
    Conexion con;
    String idgrupo ="1";
    String idmateria ="1";
    private ArrayList<MostrarCuestionario> cuestionarios;
    private DrawerLayout mDrawerLayout;
    static  public TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante);
        con=new Conexion();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /*Intent inte = getIntent();
        idmateria += inte.getStringExtra("idmateria");
        idgrupo += inte.getStringExtra("idgrupo");*/

        //Consulta de actividades por materia y grupo
        cuestionarios = ControlServicio.obtenerCuestionario(idmateria,idgrupo,EstudianteActivity.this);
        //Termina Consulta de actividades por materia y grupo

        /*AGREGANDO TOOLBAR*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

                /*AGREGANDO MOVILIDAD A LA TOOLBAR*/
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager, cuestionarios);

        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }



    /*public void navegabilidad2(View view){

        Intent intent = new Intent(getApplicationContext(), CuestionarioActivity.class);
        startActivity(intent);

    }*/
    // UI references.
    /*private LlenadoDatos llenado =null;*/





    private void setupViewPager(ViewPager viewPager, ArrayList<MostrarCuestionario> cuestionarios ) {
        Adapter adapter = new Adapter(getSupportFragmentManager());

        ListadoCuestionario cuestios = new ListadoCuestionario();
        /*CREO LOS FRAGMENTS*/


        ArrayList<String> nombrec = new ArrayList<>();
        ArrayList<String> duracionc = new ArrayList<>();


        for (MostrarCuestionario m:cuestionarios){
            nombrec.add(m.getNombre());
            duracionc.add(m.getDuracion());
        }

        Bundle parametro = new Bundle();
        parametro.putStringArrayList("nombrec",nombrec);
        parametro.putStringArrayList("duracionc",duracionc);
        parametro.putString("idgrupo",idgrupo);
        parametro.putString("idmateria",idmateria);
        cuestios.setArguments(parametro);

        /*LE MANDO PARAMETROS AL FRAGMENT QUE HE CREADO*/

        /*AGREGO EL NUEVO FRAGMENT COMO UNA TAB*/
        adapter.addFragment(cuestios,"Cuestionario");

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
