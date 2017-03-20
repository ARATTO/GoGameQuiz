package los_eternos.gogamificationquiz.Pantallas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import los_eternos.gogamificationquiz.R;

public class ListadoCuestionarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_cuestionario);
    }

    public void indicacionCuestionario(){

        Intent intent = new Intent(getApplicationContext(), CuestionarioActivity.class);
        startActivity(intent);
    }
}
