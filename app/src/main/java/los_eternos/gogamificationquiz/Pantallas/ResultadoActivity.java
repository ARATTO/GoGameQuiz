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

    //Widgets de la Activity
    Toolbar toolbar;
    TextView informacionResultado;
    Button button;

    //Variables globales
    private String idcuestionario;
    private String idperfil;
    private String idmateria;
    private String idgrupo;
    private double notaFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //Recibe parametros de PreguntaActivity
        idcuestionario = getIntent().getExtras().getString("idcuestionario");
        idperfil = getIntent().getExtras().getString("idperfil");
        idgrupo = getIntent().getExtras().getString("idgrupo");
        idmateria = getIntent().getExtras().getString("idmateria");
        notaFinal = getIntent().getExtras().getDouble("notaFinal");

        //Inicializa la toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cuestionario");

        //Valor final del TextView
        informacionResultado = (TextView) findViewById(R.id.informacion_resultado);
        informacionResultado.setText("Cuestionario finalizado con nota: " + notaFinal);


    }

    //Metodo que se activa al dar click en guardar y continuar
    public void salirCuestionario(View view){

        ControlServicio.mandarNota(idperfil, idcuestionario, notaFinal);                    //Metodo que manda la nota obtenida al servidor
        Intent intent = new Intent(getApplicationContext(), EstudianteActivity.class);
        //Manda parametros a EstudiantesActivity
        intent.putExtra("idmateria", idmateria);
        intent.putExtra("idgrupo", idgrupo);
        intent.putExtra("idperfil", idperfil);
        startActivity(intent);

    }
}
