package jair.ejemplo.android.ejemplowebservices;

import android.util.Base64;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Jair Barzola on 26-Feb-17.
 */

public class HttpManager {

    //sirve para traer los datos , haciendo una peticion
    public static String getData(String uri){

        BufferedReader reader = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
             while ( (line= reader.readLine()) != null){
                 stringBuilder.append(line+ "\n");
             }
            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        } finally {
            if( reader != null){

                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }
    public static String getData(String uri,String username,String password){

        BufferedReader reader = null;
        byte[]loginBytes=(username + ":" +password).getBytes();
        StringBuffer loginBuilder = new StringBuffer()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes,Base64.DEFAULT));
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization",loginBuilder.toString()); // se tiene que agregar
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ( (line= reader.readLine()) != null){
                stringBuilder.append(line+ "\n");
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        } finally {
            if( reader != null){

                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }


}
