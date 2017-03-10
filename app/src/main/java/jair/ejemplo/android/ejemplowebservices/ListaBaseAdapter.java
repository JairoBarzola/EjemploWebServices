package jair.ejemplo.android.ejemplowebservices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jair Barzola on 28-Feb-17.
 */

public class ListaBaseAdapter extends BaseAdapter {
    List<Usuario> usuarioList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public ListaBaseAdapter(Context context,List<Usuario> usuarioList){
        this.context = context;
        this.usuarioList= usuarioList;
        layoutInflater= LayoutInflater.from(this.context);
    }
    @Override
    public int getCount() {
        return usuarioList.size();
    }

    @Override
    public Usuario getItem(int position) {
        return usuarioList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if ( convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_list,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Usuario usuario = getItem(position);
        viewHolder.nombre.setText(usuario.getNombre());
        viewHolder.apellido.setText(usuario.getApellido());
        viewHolder.edad.setText(String.valueOf(usuario.getEdad()));
        //viewHolder.carrera.setText(usuario.getCarrera());
        Picasso.with(context).load(usuario.getCarrera()).into(viewHolder.imageView);

        return convertView;
    }
    // necesario por material desgin
    public class ViewHolder {

        TextView nombre;
        TextView apellido;
        TextView edad;
        TextView carrera;
        ImageView imageView;


        public ViewHolder (View item){
            nombre = (TextView) item.findViewById(R.id.nombre);
            apellido= (TextView) item.findViewById(R.id.apellido);
            edad = (TextView) item.findViewById(R.id.edad);
            imageView = (ImageView) item.findViewById(R.id.imageView);
           // carrera = (TextView) item.findViewById(R.id.carrera);

        }


    }
}
