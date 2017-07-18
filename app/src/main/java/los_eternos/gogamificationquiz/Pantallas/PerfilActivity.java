package los_eternos.gogamificationquiz.Pantallas;

import android.content.Intent;
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
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import los_eternos.gogamificationquiz.Controladores.MedallaAdapter;
import los_eternos.gogamificationquiz.Modelo.Medalla;
import los_eternos.gogamificationquiz.R;

public class PerfilActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        private RecyclerView recycler;
        private RecyclerView.Adapter adapter;
        private RecyclerView.LayoutManager lManager;
        private String correo="";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_perfil);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            //codigo de conexion
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Intent inte = getIntent();
            correo += inte.getStringExtra("email");

            System.out.println("el correo que recibo: "+correo);

            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);

            TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);


        }

        /*  CODIGO DE LAS TAB  */
        private void setupViewPager(ViewPager viewPager) {
            Adapter adapter = new Adapter(getSupportFragmentManager());

            PerfilFragment perfilFragment = new PerfilFragment();
            MedallasFragment medallasFragment = new MedallasFragment();

            Bundle datos = new Bundle();
            datos.putString("correo",correo);

            perfilFragment.setArguments(datos);
            medallasFragment.setArguments(datos);

            adapter.addFragment(perfilFragment, "Perfil");
            adapter.addFragment(medallasFragment, "Medallas");

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
    }
        /*FIN DEL CODIGO DE LAS TAB*/


        @Override
        public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

            if (id == R.id.nav_perfil) {
                // Handle the camera action
                Intent intent1 = new Intent(getApplicationContext(), PerfilActivity.class);
                startActivity(intent1);


            } else if (id == R.id.nav_gallery) {

            } else if (id == R.id.nav_listamateria) {

                Intent intent2 = new Intent(getApplicationContext(), MateriasExistentesActivity.class);
                startActivity(intent2);

            } else if (id == R.id.nav_manage) {

                //Esto es solo para probar el ResultadoActivity
                Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_share) {

                //Esto es solo para probar el PreguntasActivity
            /*
            Intent intent = new Intent(getApplicationContext(), PreguntasActivity.class);
            startActivity(intent);
            */

            } else if (id == R.id.nav_send) {
                //Esto es solo para probar el CuestionarioActivity
                Intent intent = new Intent(getApplicationContext(), CuestionarioActivity.class);
                startActivity(intent);
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

}
