//Esta clase corresponde a la parte logica de la activity Resultado que muestra el resultado del cuestionario


package los_eternos.gogamificationquiz.Pantallas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import los_eternos.gogamificationquiz.R;

public class ResultadoActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Cuestionario");
    }
}
