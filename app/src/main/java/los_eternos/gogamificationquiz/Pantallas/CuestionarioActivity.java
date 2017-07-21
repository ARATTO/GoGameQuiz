//Esta clase corresponde a la parte logica de la activity de Cuestionario (Indicaciones de cuestionario)


package los_eternos.gogamificationquiz.Pantallas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import los_eternos.gogamificationquiz.R;

public class CuestionarioActivity extends AppCompatActivity {

    Toolbar toolbar;
    private String idcuestionario;
    private String idperfil;
    private String idgrupo;
    private String idmateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);

        //Recibe los parametros de EstudianteActivity
        idcuestionario = getIntent().getExtras().getString("idcuestionario");
        idperfil = getIntent().getExtras().getString("idperfil");
        idgrupo = getIntent().getExtras().getString("idgrupo");
        idmateria = getIntent().getExtras().getString("idmateria");

        //Inicializa la toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cuestionario");         //Cambia el texto que aparece en la toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(true); //Mostrar el texto de la toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //Mostrar la flecha de "atras" en la toolbar. La navegabilidad se establece en AndroidManifest.xml
    }

    //Metodo que se activa al pulsar el boton COMENZAR
    public void empezarCuestionario(View view){

        Intent intent = new Intent(getApplicationContext(), PreguntasActivity.class);
        //Manda parametros a PreguntasActivity
        intent.putExtra("idgrupo", idgrupo);
        intent.putExtra("idmateria", idmateria);
        intent.putExtra("idcuestionario",idcuestionario);
        intent.putExtra("idperfil",idperfil);
        startActivity(intent);

    }
}
