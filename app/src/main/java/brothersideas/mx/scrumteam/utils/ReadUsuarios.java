package brothersideas.mx.scrumteam.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import brothersideas.mx.scrumteam.config.Config;


public class ReadUsuarios {

//    private final static String DOMAIN = "https://task-master-web.herokuapp.com";
    public final static String URL_LOGIN = String.format("%s/webservice", Config.DOMAIN);

    public static String logIn(String correo, String pass){
        String json = "{}";
        try {
            URL ruta = new URL(ReadUsuarios.URL_LOGIN + "/" + correo + "/" + pass);
            System.out.println("ruta = " + ruta);
            HttpURLConnection con = (HttpURLConnection) ruta.openConnection();
            json = transformBuffer(con.getInputStream()).toString();
            System.out.println("json = " + json);
        }catch (Exception ex){
            ex.printStackTrace();
            Log.w("Error", "No se puede leer el servicio log in.");
            json = "null";
        }
        return json;
    }


    public static String read(String url){
        String json = "{}";
        try {
            URL ruta = new URL(url);
            HttpURLConnection con = (HttpURLConnection) ruta.openConnection();
            json = transformBuffer(con.getInputStream()).toString();
        }catch (Exception ex){
            Log.w("Error", "No se puede leer el servicio.");
            json = "null";
        }
        return json;
    }

    public static String transformBuffer(InputStream in){
        String linea = "";
        System.out.println("linea = " + linea);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            System.out.println("reader = " + reader);
            int value = -1;
            while ((value = reader.read()) != -1){
                char c = (char) value;
                linea = String.format("%s%s", linea, c);
            }
        }catch (Exception ex){
            Log.e("Error", "No se puede leer el JSON.");
        } finally {
            try {
                if (reader != null){
                    reader.close();
                }
            }catch (IOException e){
                Log.e("Error","No se pudo cerrar el Buffer.");
            }
        }
        System.out.println("linea = " + linea);
        return linea;
    }
}
