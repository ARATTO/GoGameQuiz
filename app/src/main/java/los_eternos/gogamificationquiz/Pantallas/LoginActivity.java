package los_eternos.gogamificationquiz.Pantallas;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.Modelo.Perfil;
import los_eternos.gogamificationquiz.R;

public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private UserLoginTask userlogintask = null;
    private UserLoginTask mAuthTask = null;
    Conexion conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Valores iniciales de los objetos de esta clase

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.

        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        conn=new Conexion();
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                userlogintask = new UserLoginTask();
                userlogintask.execute();

            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }



    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected Boolean doInBackground(Void... params) {

            //Metodo que se activa al ingresar datos al formulario y dar click al boton Registrar
            //Esta tarea se ejecuta en segundo plano al hilo principal

            List<Perfil> perfil = null;
            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();

            int resultado = ControlServicio.obtenerRespuestaLogin(email, password);

            System.out.println("resultado: " + resultado);

            switch(resultado){

                    case 1:

                        //Cuando el que se registra es un estudiante

                        Intent intent = new Intent(LoginActivity.this,MateriasExistentesActivity.class);
                        intent.putExtra("email",email);
                        intent.putExtra("resultado",resultado);
                        startActivity(intent);

                        break;

                    case 2:

                        //Cuando el que se registra es un docente
                        //otro metodo que traiga los datos del docente,
                        //buscar el perfil por medio del correo usando el metodo de rodrigo obtener perfil
                        //si es docente
                        System.out.println("soy estudiante docente");

                        break;

                    case 3:

                        //Cuando el que se registra es un administrador

                        break;

                    case 4:

                        //Cuando la contraseña ingresada no es correcta

                        break;

                    case 5:

                        //Cuando el correo ingresado no es correcto

                        break;

                    default:

                        //Cuando ni el correo ni la contraseña no existe

                        break;

                }





        return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                    Intent NavBar = new Intent(getApplicationContext(), MateriasExistentesActivity.class);
                    startActivity(NavBar);
                    finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;

            try {
                this.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                Log.v("ERROR_ASYNC", throwable.getMessage());
            }
        }
    }
}

