package los_eternos.gogamificationquiz.Pantallas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Bryan Lobos1 on 06/07/2017.
 */
public class welcome {
    private DrawerLayout mDrawerLayout;

    List<Materia> materias;
    // Variables para el RecyclerView
    private RecyclerView matRecyclerView;
    private RecyclerView.Adapter matAdapter;
    private RecyclerView.LayoutManager matLayoutManager;
    // Variables para el SwipeRefresh
    private SwipeRefreshLayout refreshLayout;

    public SessionManager _SESSION;
    //Variables para preferencias
    private static final String PREF_NAME = "GGPreferences";// Sharedpref file name
    private static final String USUARIO = "usuario";
    private static final String FOTO_PERFIL = "foto_perfil";
    private static final String EMAIL = "email";
    private static final String NOM_PERFIL = "nom_perfil";
    private static final String IS_DOCENTE =   "is_docente";
    SharedPreferences shared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Preferencias
        shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);


        //Recycler View inicializacion
        matRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);//Obtiene el recicler del xml creado
        matLayoutManager = new LinearLayoutManager(this);
        matRecyclerView.setLayoutManager(matLayoutManager);

        _SESSION = new SessionManager(this);





        //SwipreRefres inicializacion
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        //Iniciar la tarea asincrona  luego del swipe
        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //Instancia de la clase asincrona
                        new CargarMateriasTask(shared.getString(USUARIO,""), shared.getBoolean(IS_DOCENTE,false)).execute();
                    }
                }
        );

        //Cambiar colores a la animacion de carga

        refreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorCeleste
        );

        //Cargar las materias por primera vez
        refreshLayout.setRefreshing(true);
        refreshLayout.setSize(SwipeRefreshLayout.LARGE);
        new CargarMateriasTask(shared.getString(USUARIO,""), shared.getBoolean(IS_DOCENTE,false)).execute();


        /*AGREGANDO NAVIGATION DRAWER*/
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        CircleImageView imagen_nav = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.profile_image);
        imagen_nav.setImageBitmap(decodeBase64(shared.getString(FOTO_PERFIL,"")));

        TextView correo= (TextView) navigationView.getHeaderView(0).findViewById(R.id.email);
        correo.setText(shared.getString(EMAIL,""));

        TextView nombre= (TextView) navigationView.getHeaderView(0).findViewById(R.id.username);
        nombre.setText(shared.getString(NOM_PERFIL,""));

        ActionBar supportActionBar = getSupportActionBar();

        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_line_weight_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Set item in checked state
                        menuItem.setChecked(true);
                        //codigo de que selecciono en el drawer
                        int seleccion = menuItem.getItemId();
                        switch (seleccion){
                            case R.id.menu_mi_perfil:
                                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                                intent.putExtra(DetailActivity.EXTRA_POSITION, 1);
                                startActivity(intent);
                                break;
                            case R.id.menu_salir:
                                _SESSION.logOut();
                                break;
                        }
                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
        /*FINALIZANDO NAVIGATION DRAWER*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Se agrego una materia mas", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //Cargar las materias por primera vez
                refreshLayout.setRefreshing(true);
                new CargarMateriasTask(shared.getString(USUARIO,""), shared.getBoolean(IS_DOCENTE,false)).execute();

            }
        });

        fab.setVisibility(View.GONE);
    }

    //Metodo para decodificar imagenes en Base64
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }


    //Inicializar el adaptador

    private void initializeAdapter(List<Materia> materias) {
        matAdapter = new MateriaAdapter(materias,shared.getBoolean(IS_DOCENTE,false));
        matRecyclerView.setAdapter(matAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
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



    private class CargarMateriasTask extends AsyncTask<Void, Void, List<Materia>> {
        String usuario;
        boolean is_docente;

        //Constructor que recibe el carnet o id del usuario que acaba de logearse
        public CargarMateriasTask(String usuario, boolean is_docente) {
            this.usuario = usuario;
            this.is_docente = is_docente;
        }

        //Este metodo se encarga de la conexion y recuperacion de los datos de las materias
        @Override
        protected List<Materia> doInBackground(Void... voids) {
            try {
                String consulta;
                if (is_docente)//Verifica si es docente o es estudiante y asi elije el webservice adecuado
                    consulta = Conexion.URLLocal + String.format("MateriaService.php?accion=2&param1=%s", usuario);
                else
                    consulta = Conexion.URLLocal + String.format("MateriaService.php?accion=1&param1=%s", usuario);

                List<Materia> materias = get_materias(consulta);
                //Retornar la lista de elementos
                return materias;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Materia> materias) {
            super.onPostExecute(materias);
            initializeAdapter(materias);
            refreshLayout.setRefreshing(false);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
