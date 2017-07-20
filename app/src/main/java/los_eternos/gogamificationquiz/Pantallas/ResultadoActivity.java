//Esta clase corresponde a la parte logica de la activity Resultado que muestra el resultado del cuestionario


package los_eternos.gogamificationquiz.Pantallas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.R;

public class ResultadoActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView informacionResultado;
    Button button;
    public String idcuestionario;
    public String idperfil;

    private double notaFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Intent inte = getIntent();
        idcuestionario = inte.getStringExtra("idcuestionario");
        idperfil += inte.getStringExtra("idperfil");


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cuestionario");

        informacionResultado = (TextView) findViewById(R.id.informacion_resultado);
        notaFinal = getIntent().getExtras().getDouble("notaFinal");
        informacionResultado.setText("Cuestionario finalizado con nota: " + notaFinal);


    }

    public void salirCuestionario(View view){
        Intent intent = new Intent(getApplicationContext(), EstudianteActivity.class);
        ControlServicio.mandarNota(idperfil, idcuestionario, notaFinal);
        startActivity(intent);
    }
}
