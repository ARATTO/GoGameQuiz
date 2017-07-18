package los_eternos.gogamificationquiz.Pantallas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.Modelo.Materia;
import los_eternos.gogamificationquiz.R;

public class FormularioActividad extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Conexion con;
    private ArrayList<Materia> materia;
    static  public TabLayout tabs;
    TabLayout copia;
    String idmateria="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_actividad);
        String codigo ="";
        String URL ="";
        /*Intent id = getIntent();
        idgrupo = id.getStringExtra("idgrupo");
        idmateria = id.getStringExtra("nommateria");*/

        con=new Conexion();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


          /*AGREGANDO TOOLBAR*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*AGREGANDO MOVILIDAD A LA TOOLBAR*/
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager,materia);

        tabs = (TabLayout) findViewById(R.id.tab);
        tabs.setupWithViewPager(viewPager);


        ActionBar supportActionBar = getSupportActionBar();

        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_line_weight_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }//Fin metodo onCreate



    /*AGREGANDO METODO DE INSERCION DE TABS*/
    private void setupViewPager(ViewPager viewPager, ArrayList<Materia> mat ) {
        Adapter adapter = new Adapter(getSupportFragmentManager());

        /*CREO LOS FRAGMENTS*/

       ActividadFragment AF = new ActividadFragment();

        Bundle parametro2 = new Bundle();
        parametro2.putString("idmateria",idmateria);
        AF.setArguments(parametro2);

        adapter.addFragment(AF, "Crear Actividad");

            /*AJUSTA EL TAMAÑO DE LAS TABS*/
        viewPager.setAdapter(adapter);


    }



    /*CLASE ADAPTER PARA VER LAS TAB*/
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


}
