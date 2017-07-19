package los_eternos.gogamificationquiz.Pantallas;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.Modelo.Materia;
import los_eternos.gogamificationquiz.R;

public class MateriasExistentesActivity extends AppCompatActivity {

    //DECLARACION DE VARIABLES GLOBALES

    private Toolbar toolbar;
    private Conexion con;
    private String email;
    private int resultado;
    private ArrayList<Materia> materias;

    //FIN DE DECLARACION DE VARIABLES GLOBALES

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Invoca al layout de materias existentes
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias_existentes);


        con = new Conexion(); //Instancia de la clase conexion
        resultado = getIntent().getExtras().getInt("resultado"); //Recibe el parametro resultado de LoginActivity
        email = getIntent().getExtras().getString("email"); //Recibe el parametro email de LoginActivity

        if(resultado == 1){

            //Cuando ingresa a la app un estudiante

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Materias inscritas"); //Texto que va en la Toolbar

            //Recibe las materias y grupos que el estudiante ha inscrito
            materias = ControlServicio.obtenerMateriasEstudiante(email, resultado, MateriasExistentesActivity.this);

            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager, materias);
        }

        else{

            //Cuando ingresa a la app un docente

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Materias impartidas"); //Texto que va en la toolbar

            //Recibe las materias y grupos que el docente imparte
            materias = ControlServicio.obtenerMateriasDocente(email, resultado, MateriasExistentesActivity.this);

            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager, materias);
        }

    }

    public void setupViewPager(ViewPager viewPager, ArrayList<Materia> materias){

        //Aqui se almacenan los valores que recibe del servidor
        Adapter adapter = new Adapter(getSupportFragmentManager());

        MateriasExistentesFragment materia = new MateriasExistentesFragment();

        ArrayList<String> nombreMateria = new ArrayList<>();
        ArrayList<String> codigoMateria = new ArrayList<>();
        ArrayList<String> nombreGrupo = new ArrayList<>();
        ArrayList<String> codigoCiclo = new ArrayList<>();
        ArrayList<String> imagenMateria = new ArrayList<>();
        ArrayList<String> idMateria = new ArrayList<>();
        ArrayList<String> idGrupo = new ArrayList<>();
        ArrayList<String> idPerfil = new ArrayList<>();

        for (Materia mef: materias){

            nombreMateria.add(mef.getNombreMateria());
            codigoMateria.add(mef.getCodigoMateria());
            nombreGrupo.add(mef.getNombreGrupo());
            codigoCiclo.add(mef.getCodigoCiclo());
            imagenMateria.add(mef.getImagenMateria());
            idMateria.add(mef.getIdMateria());
            idGrupo.add(mef.getIdGrupo());
            idPerfil.add(mef.getIdPerfil());

        }

        //Paso de parametros de la Activity al Fragment
        Bundle parametro = new Bundle();

        parametro.putStringArrayList("nombrem",nombreMateria);
        parametro.putStringArrayList("codma",codigoMateria);
        parametro.putStringArrayList("foto",imagenMateria);
        parametro.putStringArrayList("nomgru",nombreGrupo);
        parametro.putStringArrayList("codci", codigoCiclo);
        parametro.putStringArrayList("idmate", idMateria);
        parametro.putStringArrayList("idgru", idGrupo);
        parametro.putStringArrayList("idper", idPerfil);
        parametro.putInt("resultado",resultado);
        parametro.putString("email",email);
        materia.setArguments(parametro);

        adapter.addFragment(materia, "Materias");

        viewPager.setAdapter(adapter);

    }

    //CLASE ADAPTER
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
    }
    //FIN DE CLASE ADAPTER

}