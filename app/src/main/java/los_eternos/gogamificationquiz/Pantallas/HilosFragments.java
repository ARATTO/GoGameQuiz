package los_eternos.gogamificationquiz.Pantallas;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
/**
 * Created by Bryan Lobos1 on 21/02/2017.
 */
public class HilosFragments extends Fragment{

    /*
   Interfaz para la comunicación con la actividad ABTest.
    */
    static interface TaskCallbacks {
        void onPreExecute();
        void onProgressUpdate(int progress);
        void onCancelled();
        void onPostExecute();
    }

    /*
    new Thread(
            new Runnable() {
        @Override
        public void run() {
            //metodo
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //mensaje
                }
            });
        }
    }
    ).start();
    */

    private TaskCallbacks mCallbacks;
    ProgressBarTask progressBarTask;

    public HilosFragments() {}

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        //Obtener la instancia de ABTest
        mCallbacks = (TaskCallbacks) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Retener el fragmento creado
        setRetainInstance(true);

        //Una vez creado el fragmento se inicia la tarea asincrona
        progressBarTask = new ProgressBarTask();
        progressBarTask.execute();
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mCallbacks = null;
    }

    //*****************************CLASE PARA USAR HILOS******************************************//
    public class ProgressBarTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            if (mCallbacks != null) {
                mCallbacks.onPreExecute();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            if (mCallbacks != null) {
                mCallbacks.onProgressUpdate(values[0]);
            }
        }

        @Override
            protected void onPostExecute(Void aLong) {
            if (mCallbacks != null) {
                mCallbacks.onPostExecute();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            mCallbacks.onCancelled();
        }


    }

    //********************************FIN CLASE PARA HILOS****************************************//



}
