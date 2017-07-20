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
    TextView titulo;
    TextView pregunta;
    RadioButton respuesta;
    RadioGroup group;
    Button boton;

    //Variables globales
    private int idpregunta;
    private int ids_respuesta[] = {
            R.id.respuesta1, R.id.respuesta2, R.id.respuesta3, R.id.respuesta4
    }; //Arreglo con los layouts de respuestas (RadioButton)
    private ArrayList<Pregunta> preguntas; //Aqui se almacena la pregunta que recibe el cliente del servidor
    private ArrayList<String> preguntasString = new ArrayList<>(); //Aqui se guarda la pregunta que se mostrara en el textview
    private ArrayList<Respuesta> respuestas; //Aqui se almacenan las respuestas que recibe el cliente del servidor
    private ArrayList<String> respuestasString = new ArrayList<>(); //Aqui se guarda la respuesta que se muestran en los radiobutton
    private ArrayList<String> correctas = new ArrayList<>(); //Aqui se almacenan el identificador de respuesta correcto o incorrecta
    private int respuestacorrecta; //Guarda la posicion de la respuesta correcta
    private int numeroPregunta; //Almacena el indice del arreglo preguntasString
    private int numeroRespuesta;    //Almacena el indice del arreglo respuestasString
    private int cantidadPreguntas;  //Numero de preguntas del cuestionario
    private double notaFinal;       //Nota obtenida al resolver el cuestionario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cuestionario");             //Cambia el texto que aparece en la toolbar

        titulo = (TextView) findViewById(R.id.titulo_pregunta);     //Inicializa el texto del titulo de pregunta
        pregunta = (TextView) findViewById(R.id.text_pregunta);     //Inicializa el texto del TextView
        group = (RadioGroup) findViewById(R.id.radio_group);        //Inicializa el RadioGroup
        boton = (Button) findViewById(R.id.boton_siguiente);        //Inicializa el boton

        numeroPregunta = 0;
        numeroRespuesta = 0;
        cantidadPreguntas = 1;
        notaFinal = 0;

        MostrarPregunta();  //Metodo que muestra las preguntas

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = group.getCheckedRadioButtonId();   //Obtiene el valor del indice de los radiobutton
                int index = -1; //Valor del indice cuando no se selecciona ninguna opcion
                //Se comprueba cual de los indices fue seleccionado
                for(int i = 0; i < ids_respuesta.length; i++) {
                    if (ids_respuesta[i] == id) {
                        index = i;
                    }
                }
                //Si se escogio alguna de las opciones (radiobutton)
                if (index != -1){
                    //Si la opcion seleccionada es la respuesta correcta
                    if (index == respuestacorrecta){

                        numeroPregunta++;
                        numeroRespuesta+=4;
                        cantidadPreguntas++;
                        notaFinal++;

                        //Cuando nos pasamos de 10 preguntas
                        if (cantidadPreguntas > 10){

                            Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
                            intent.putExtra("notaFinal", notaFinal); //Manda notaFinal a ResultadoActivity
                            startActivity(intent);
                        }
                        //Cuando no hemos pasado 10 preguntas
                        else {
                            MostrarPregunta();  //Metodo que muestra las preguntas
                        }

                    }
                    //Si la opcion seleccionada no es la respuesta correcta
                    else{

                        numeroPregunta++;
                        numeroRespuesta+=4;
                        cantidadPreguntas++;

                        //Cuando nos pasamos de 10 preguntas
                        if (cantidadPreguntas > 10){

                            Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
                            intent.putExtra("notaFinal", notaFinal); //Manda notaFinal a ResultadoActivity
                            startActivity(intent);
                        }
                        //Cuando no hemos pasado 10 preguntas
                        else {
                            MostrarPregunta();  //Metodo que muestra las preguntas
                        }
                    }
                }
                //Cuando no se selecciona ninguna opcion (radiobutton)
                else{
                    Toast.makeText(getApplicationContext(), "Debe escoger una respuesta", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void MostrarPregunta(){

        idpregunta = (int)(Math.random()*50+1); //Variable que le manda de parametro al servidor (1-50)
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

        //Valor final que mostrara el TextView de titulo
        titulo.setText("PREGUNTA " + (numeroPregunta + 1) + " de 10");

        //Valor final que mostrara el TextView
        pregunta.setText(preguntasString.get(numeroPregunta));

        //Deja limpios los radiobutton
        group.clearCheck();

        //Valor final que mostraran los RadioButton
        for(int i = 0; i < ids_respuesta.length; i++){
            respuesta = (RadioButton) findViewById(ids_respuesta[i]);
            respuesta.setText(respuestasString.get(numeroRespuesta + i));
        }

        //Valor final que mostrara cual es la respuesta correcta
        for(int i = 0; i < ids_respuesta.length; i++){
            if (correctas.get(i + numeroRespuesta) == "1"){
                respuestacorrecta = i;
            }
        }

        //En la ultima pregunta cambia el texto del boton
        if (cantidadPreguntas == 10){
            boton.setText("Comprobar");
        }

    }

}
