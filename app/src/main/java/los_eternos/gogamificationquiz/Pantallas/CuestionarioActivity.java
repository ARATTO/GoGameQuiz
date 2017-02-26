//Esta clase corresponde a la parte logica de la activity de Cuestionario (Indicaciones de cuestionario)


package los_eternos.gogamificationquiz.Pantallas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import los_eternos.gogamificationquiz.R;

public class CuestionarioActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cuestionario");         //Cambia el texto que aparece en la toolbar

        getSupportActionBar().setDisplayShowTitleEnabled(true); //Mostrar el texto de la toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //Mostrar la flecha de "atras" en la toolbar. La navegabilidad se establece en AndroidManifest.xml
    }
}
