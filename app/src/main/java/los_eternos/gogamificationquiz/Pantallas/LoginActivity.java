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
import android.widget.Toast;
import los_eternos.gogamificationquiz.Controladores.ControlServicio;
import los_eternos.gogamificationquiz.Controladores.Conexion;
import los_eternos.gogamificationquiz.R;

public class LoginActivity extends AppCompatActivity {

    // Variables globales de la clase
    private UserLoginTask mAuthTask = null;
    private EditText mEmailView;
    private EditText mPasswordView;
    private UserLoginTask userlogintask =null;
    Conexion conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Valores iniciales de los objetos de esta clase

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Obteniendo datos del formulario.

        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        conn=new Conexion();
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);

        //Cuando damos click al boton ingresar
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();//No se que hace, pero hace algo y es util jajajaja xDD
                //Clase asincrona
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

        // Mensajes de error
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Almacena los valores que tienen los textview del login
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Checa que hay un email en el formulario.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        }
        else {

        }
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        //Variables globales de la subclase
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        int resultado;

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected Boolean doInBackground(Void... params) {

            //Metodo que verifica el email y la contraseña del usuario
            resultado = ControlServicio.obtenerRespuestaLogin(email, password);

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            //Si lo acontecido en el metodo doInBackGround tiene exito
            if (success) {

                System.out.println(resultado);

                switch(resultado){

                    case 1:

                        //Cuando el usuario sea un estudiante
                        Intent intent = new Intent(getApplicationContext(), MateriasExistentesActivity.class);
                        intent.putExtra("email", email);
                        intent.putExtra("resultado", resultado);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Estudiante", Toast.LENGTH_SHORT).show();
                        break;

                    case 2:

                        //Cuando el usuario sea un docente
                        Intent intent2 = new Intent(getApplicationContext(), MateriasExistentesActivity.class);
                        intent2.putExtra("email", email);
                        intent2.putExtra("resultado", resultado);
                        startActivity(intent2);
                        Toast.makeText(getApplicationContext(), "Docente", Toast.LENGTH_SHORT).show();
                        break;

                    case 3:

                        //Cuando el usuario sea un administrador
                        Toast.makeText(getApplicationContext(), "Administrador", Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent3);
                        break;

                    case 4:

                        //Cuando la contraseña es incorrecta
                        Toast.makeText(getApplicationContext(), "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                        Intent intent4 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent4);
                        break;

                    default:

                        //Cuando el usuario no existe
                        System.out.println("No sirve");
                        Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                        Intent intent5 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent5);
                }

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