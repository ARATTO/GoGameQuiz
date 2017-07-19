//Esta clase corresponde a la parte logica de la activity de Preguntas del cuestionario

package los_eternos.gogamificationquiz.Pantallas;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.Modelo.Pregunta;
import los_eternos.gogamificationquiz.Modelo.Respuesta;
import los_eternos.gogamificationquiz.R;

public class PreguntasActivity extends AppCompatActivity {

    //Widgets de la Activity
    Toolbar toolbar;
    TextView pregunta;
    RadioButton respuesta;
    RadioGroup group;
    Button boton;

    //Variables globales
    private int idpregunta = (int)(Math.random()*50+1); //Variable que le manda de parametro al servidor (1-50)
    private int ids_respuesta[] = {
            R.id.respuesta1, R.id.respuesta2, R.id.respuesta3, R.id.respuesta4
    }; //Arreglo con los layouts de respuestas (RadioButton)
    private ArrayList<Pregunta> preguntas; //Aqui se almacena la pregunta que recibe el cliente del servidor
    private ArrayList<String> preguntasString = new ArrayList<>(); //Aqui se guarda la pregunta que se mostrara en el textview
    private ArrayList<Respuesta> respuestas; //Aqui se almacenan las respuestas que recibe el cliente del servidor
    private ArrayList<String> respuestasString = new ArrayList<>(); //Aqui se guarda la respuesta que se muestran en los radiobutton
    private ArrayList<String> correctas = new ArrayList<>(); //Aqui se almacenan el identificador de respuesta correcto o incorrecta
    private int respuestacorrecta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cuestionario");             //Cambia el texto que aparece en la toolbar

        pregunta = (TextView) findViewById(R.id.text_pregunta);     //Inicializa el texto del TextView
        group = (RadioGroup) findViewById(R.id.radio_group);        //Inicializa el RadioGroup

        preguntas = ControlServicio.obtenerPreguntas(idpregunta, PreguntasActivity.this); //Consulta la pregunta segun el id que se mande de parametro
        respuestas = ControlServicio.obtenerRespuestas(idpregunta, PreguntasActivity.this); //Consulta las respuestas segun el id que se mande de parametro

        //Traslada la pregunta del arreglo preguntas al arreglo preguntasStrings
        for(Pregunta pre : preguntas){
            preguntasString.add(pre.getPregunta());
        }

        //Traslada las respuestas del arreglo respuestas al arreglo respuestasString
        for(Respuesta res : respuestas){
            respuestasString.add(res.getAlternativas());
            correctas.add(res.getEsCorrecta());
        }

        //Valor final que mostrara el TextView
        pregunta.setText(preguntasString.get(0));

        //Valor final que mostraran los RadioButton
        for(int i = 0; i < ids_respuesta.length; i++){
            respuesta = (RadioButton) findViewById(ids_respuesta[i]);
            respuesta.setText(respuestasString.get(i));
        }

        for(int i = 0; i < correctas.size(); i++){
            if (correctas.get(i) == "1"){
                respuestacorrecta = i;
            }
        }

        boton = (Button) findViewById(R.id.boton_siguiente);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = group.getCheckedRadioButtonId();
                int index = -1;
                for(int i = 0; i < ids_respuesta.length; i++) {
                    if (ids_respuesta[i] == id) {
                        index = i;
                    }
                }

                if (index == respuestacorrecta){

                    Toast.makeText(getApplicationContext(), "Correcta", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getApplicationContext(), "Incorrecta", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
