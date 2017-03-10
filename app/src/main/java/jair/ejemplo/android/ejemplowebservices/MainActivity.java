package jair.ejemplo.android.ejemplowebservices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;
//import static jair.ejemplo.android.ejemplowebservices.R.id.progressBar;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ListaAdapater adapter;
    //ListaBaseAdapter adapter2;
    RecyclerView recyclerView;
    RecyclerAdapter adapter3;

    ProgressBar progressBar;
    Button button;
    //List<MyAsyncTask> taskList;
    List<Usuario> userList;
    ListView listView;
    private static ConnectivityManager manager;

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*progressBar=(ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.texto);
        button =(Button)findViewById(R.id.boton);*/
        //listView = (ListView) findViewById(R.id.list);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        //textView.setMovementMethod(new ScrollingMovementMethod()); //habilitar el scroll

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnectedMobile(getApplicationContext()) == true || isConnectedWifi(getApplicationContext()) == true) {
                  //  pedirDatos("http://jairbarzola.esy.es/jairws.xml"); //ejemplo xml
                    pedirDatos("http://jairbarzola.esy.es/jairwsji.json");  //ejemplo json
                   // pedirDatos("http://maloschistes.com/maloschistes.com/jose/s/webservice.php");

                }else{
                    Toast.makeText(getApplication(),"No hay conexion a internet",Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        if(isOnline(getApplicationContext())){
        //if (isConnectedMobile(getApplicationContext()) == true || isConnectedWifi(getApplicationContext()) == true) {
            //  pedirDatos("http://jairbarzola.esy.es/jairws.xml"); //ejemplo xml
            pedirDatos("http://jairbarzola.esy.es/jairwsji.json");  //ejemplo json
            // pedirDatos("http://maloschistes.com/maloschistes.com/jose/s/webservice.php");

        }else{
            Toast.makeText(getApplication(),"No hay conexion a internet",Toast.LENGTH_SHORT).show();
        }
    }

    private void pedirDatos(String uri) {
        MyAsyncTask at = new MyAsyncTask();
        at.execute(uri); // por defecto hace que la ejecucion sea serial
        //at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR); // forma paralela la ejecucion del hilo
    }


    private void cargarDatos() {
       //textView.append(cad +"\n");
       // adapter2 = new ListaBaseAdapter(getApplicationContext(),userList);
       // listView.setAdapter(adapter2);
        if(userList == null){
            Toast.makeText(getApplication(),"No se pueden cargar los datos 2",Toast.LENGTH_SHORT).show();
        }else{
        adapter3 = new RecyclerAdapter(getApplicationContext(),userList);
        recyclerView.setAdapter(adapter3);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
       //if(userList != null){

        }
            /*for(Usuario usuario:userList){
            textView.append(usuario.getNombre()+" "+usuario.getApellido()+ "\n");
            }*/
        //}
        //button.setVisibility(View.GONE);
    }
    private class MyAsyncTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //cargarDatos("Inicio de Carga");
            //progressBar.setVisibility(View.VISIBLE);
            /*if(taskList.size()==0){
            progressBar.setVisibility(View.VISIBLE);}
            taskList.add(this);*/
        }

        @Override
        protected String doInBackground(String... params) {
              /*  for (int i=0;i<10;i++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(String.valueOf(i));

                }*/
            String content = HttpManager.getData(params[0]); //sin contraseña
            //String content = HttpManager.getData(params[0],"pepito","pepito"); //sin contraseña
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //textView.append(values[0]+"\n");
            //cargarDatos(values[0]);

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

           // userList = UsuarioXMLParser.paser(result);// parser xml
            if(result == null){
                Toast.makeText(getApplicationContext(),"No se pudo conectar",Toast.LENGTH_SHORT).show();
                //progressBar.setVisibility(View.GONE);

            }else {

                try {
                    userList = UsuarioJSONParser.parse(result); //parser json
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //textView.append(result);
                cargarDatos();
                //progressBar.setVisibility(View.GONE);
           /* taskList.remove(this);
            if(taskList.size()==0){
                progressBar.setVisibility(View.GONE);
            }*/
            }
        }
    }
    public static boolean isConnectedWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isConnectedMobile(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }


}
