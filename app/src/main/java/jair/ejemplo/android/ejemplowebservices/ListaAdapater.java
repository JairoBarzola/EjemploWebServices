package jair.ejemplo.android.ejemplowebservices;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.resource;

/**
 * Created by Jair Barzola on 27-Feb-17.
 */

public class ListaAdapater extends ArrayAdapter<Usuario>{
    public Usuario usuario;

    public ListaAdapater(Context context, List<Usuario> users) {
        super(context,0,users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }
        usuario = getItem(position);
        TextView nombre = (TextView) listItemView.findViewById(R.id.nombre);
        TextView apellido = (TextView) listItemView.findViewById(R.id.apellido);
        TextView edad = (TextView) listItemView.findViewById(R.id.edad);
        TextView carrera = (TextView) listItemView.findViewById(R.id.carrera);

        nombre.setText(usuario.getNombre());
        apellido.setText(usuario.getApellido());
        edad.setText(String.valueOf(usuario.getEdad()));
        carrera.setText(usuario.getCarrera());

        return listItemView;
    }
}
