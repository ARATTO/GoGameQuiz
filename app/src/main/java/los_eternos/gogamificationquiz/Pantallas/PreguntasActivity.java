//Esta clase corresponde a la parte logica de la activity de Preguntas del cuestionario

package los_eternos.gogamificationquiz.Pantallas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import los_eternos.gogamificationquiz.R;

public class PreguntasActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cuestionario");             //Cambia el texto que aparece en la toolbar
    }
}
