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


    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private UserLoginTask userlogintask =null;
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
                //attemptLogin();
                userlogintask = new UserLoginTask();
                userlogintask.execute();

            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }


    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.


            //mAuthTask = new UserLoginTask(email, password);
            //mAuthTask.execute((Void) null);
        }
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        /*private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }*/
        @Override
        protected void onPreExecute(){

        }

        @Override
        protected Boolean doInBackground(Void... params) {
           List<Perfil> perfil = null;
            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();

            int resultado = ControlServicio.obtenerRespuestaLogin(email, password);

            System.out.println("resultado: " + resultado);
                /*switch(resultado){
                    case 1:
                        //si es estudiante

                   Intent intent = new Intent(LoginActivity.this,MateriasExistentesActivity.class);

                   intent.putExtra("email",emaail);
                   intent.putExtra("resultado",resultado);

                   startActivity(intent);

                        break;
                    case 2:
//otro metodo que traiga los datos del docente,
//buscar el perfil por medio del correo usando el metodo de rodrigo obtener perfil
                        //si es docente
                        System.out.println("soy estudiante docente");

                        break;
                    case 3:
                        //si es administrador
                        break;
                    case 4:
                        //si esta mala la contra
                        break;
                    case 5:
                        //si esta malo el correo
                        break;
                    default:
                        //si es desconocido
                        break;
                }*/





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

