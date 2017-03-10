package jair.ejemplo.android.ejemplowebservices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jair Barzola on 27-Feb-17.
 */

public class UsuarioJSONParser {
    public static List<Usuario>parse(String content) throws JSONException {

        JSONArray jsonArray = new JSONArray(content);
        List<Usuario> usuarioList = new ArrayList<>();
        for(int i=0;i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Usuario usuario = new Usuario();
             usuario.setNombre(jsonObject.getString("nombre"));
            usuario.setApellido(jsonObject.getString("apellido"));
            usuario.setEdad(jsonObject.getInt("edad"));
            usuario.setCarrera(jsonObject.getString("carrera"));
            usuarioList.add(usuario);
        }
        return usuarioList;
    }
}
