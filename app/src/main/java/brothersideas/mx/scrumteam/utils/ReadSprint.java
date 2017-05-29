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

public class ReadSprint {
    public final static String URL_SPRINTS_BY_PROYECTO = String.format("%s/sprint/findByProyecto", Config.DOMAIN);

    public static String findSprintByProyecto(String id){
        String json = "{}";
        try {
            URL ruta = new URL(ReadSprint.URL_SPRINTS_BY_PROYECTO + "/" + id);
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
