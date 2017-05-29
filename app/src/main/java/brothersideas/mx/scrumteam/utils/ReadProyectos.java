package brothersideas.mx.scrumteam.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import brothersideas.mx.scrumteam.config.Config;

/**
 * Created by erikzubia on 5/28/17.
 */

public class ReadProyectos {

    public final static String URL_PROYECTOS_SCRUM = String.format("%s/proyectos/findProyectosScrum", Config.DOMAIN);
    public final static String URL_PROYECTOS_MANAGER = String.format("%s/proyectos/findProyectosManager", Config.DOMAIN);
    public final static String URL_PROYECTOS_DEVELOPER = String.format("%s/proyectos/findProyectosDeveloper", Config.DOMAIN);
    public final static String URL_PROYECTOS_ID = String.format("%s/proyectos/findById", Config.DOMAIN);

    public static String findProyectosScrumMaster(String id){
        String json = "{}";
        try {
            URL ruta = new URL(ReadProyectos.URL_PROYECTOS_SCRUM + "/" + id);
            HttpURLConnection con = (HttpURLConnection) ruta.openConnection();
            json = transformBuffer(con.getInputStream()).toString();
        }catch (Exception ex){
            Log.w("Error", "No se puede leer el servicio.");
            json = "null";
        }
        return json;
    }

    public static String findProyectosManager(String id){
        String json = "{}";
        try {
            URL ruta = new URL(ReadProyectos.URL_PROYECTOS_MANAGER + "/" + id);
            HttpURLConnection con = (HttpURLConnection) ruta.openConnection();
            json = transformBuffer(con.getInputStream()).toString();
        }catch (Exception ex){
            Log.w("Error", "No se puede leer el servicio.");
            json = "null";
        }
        return json;
    }

    public static String findProyectosDeveloper(String id){
        String json = "{}";
        try {
            URL ruta = new URL(ReadProyectos.URL_PROYECTOS_DEVELOPER + "/" + id);
            HttpURLConnection con = (HttpURLConnection) ruta.openConnection();
            json = transformBuffer(con.getInputStream()).toString();
        }catch (Exception ex){
            Log.w("Error", "No se puede leer el servicio.");
            json = "null";
        }
        return json;
    }

    public static String findById(String id){
        String json = "{}";
        try {
            URL ruta = new URL(ReadProyectos.URL_PROYECTOS_ID + "/" + id);
            HttpURLConnection con = (HttpURLConnection) ruta.openConnection();
            json = transformBuffer(con.getInputStream()).toString();
        }catch (Exception ex){
            Log.w("Error", "No se puede leer el servicio.");
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
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            //El buffer devuelve -1 cuando ya no hay nada que leer
            int value = -1;
            while ((value = reader.read()) != -1){
                char c = (char) value;
                linea = String.format("%s%s", linea, c);
            }
        }catch (Exception ex){
            Log.e("Error", "No se puede leer el JSON.");
        }finally {
            try {
                if (reader != null){
                    reader.close();
                }
            }catch (IOException e){
                Log.e("Error","No se pudo cerrar el Buffer.");
            }
        }
        return linea;
    }
}
