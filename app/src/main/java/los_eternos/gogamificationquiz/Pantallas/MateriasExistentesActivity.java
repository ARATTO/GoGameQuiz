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
import android.view.View;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.Modelo.Materia;
import los_eternos.gogamificationquiz.R;

public class MateriasExistentesActivity extends AppCompatActivity {

    Conexion con;
    String email = "rodrigo_bazan@hotmail.com";
    String resultado = "2";
    private ArrayList<Materia> materias;
    static  public TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);
        con = new Conexion();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        materias = ControlServicio.obtenerMaterias(email, resultado, MateriasExistentesActivity.this);
        System.out.print(materias);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager, materias);

        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    public void setupViewPager(ViewPager viewPager, ArrayList<Materia> materias){
        Adapter adapter = new Adapter(getSupportFragmentManager());


        MateriasExistentesFragment materia = new MateriasExistentesFragment();

        ArrayList<String> nombreMateria = new ArrayList<>();
        ArrayList<String> codigoMateria = new ArrayList<>();
        ArrayList<String> nombreGrupo = new ArrayList<>();
        ArrayList<String> imagenMateria = new ArrayList<>();

        for (Materia mef: materias){
            nombreMateria.add(mef.getNombreMateria());
            codigoMateria.add(mef.getCodigoMateria());
            nombreGrupo.add(mef.getNombreGrupo());
            imagenMateria.add(mef.getImagenMateria());
        }

        Bundle parametro = new Bundle();
        parametro.putStringArrayList("nombrem",nombreMateria);
        parametro.putStringArrayList("codma",codigoMateria);
        parametro.putStringArrayList("foto",imagenMateria);
        parametro.putStringArrayList("nomgru",nombreGrupo);
        //parametro.putString("idgrupo",idgrupo);
        //parametro.putString("idmateria",idmateria);
        materia.setArguments(parametro);

        adapter.addFragment(materia, "Materias");

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

}
