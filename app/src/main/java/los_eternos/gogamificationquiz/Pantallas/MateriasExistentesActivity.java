package los_eternos.gogamificationquiz.Pantallas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import los_eternos.gogamificationquiz.R;

public class MateriasExistentesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias_existentes);

        //recibir el correo del login
        // Bundle datos = null;

        //String datos = getIntent().getExtras();
        //int tipoUsuario = datos.getInt("resultado");


        //buscar el perfil
        /*
            List<Perfil> perfill = null;
            Conexion c = new Conexion();
            String url = "";

            url+= c.getURLLocal()+ "perfil/" +datos.getString("email");

             perfil = ControlServicio.obtenerPerfil(url,getActivity());

         */


        //buscar materias inscritas

        //hacer el metodo

    }

    public void navegabilidad(View view){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
