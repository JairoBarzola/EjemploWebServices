package jair.ejemplo.android.ejemplowebservices;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static jair.ejemplo.android.ejemplowebservices.R.id.imageView;

/**
 * Created by Jair Barzola on 28-Feb-17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {

    public Context context;
    static   int lastPosition=-1;
    public List<Usuario> usuarioList = new ArrayList<>();
    public  RecyclerAdapter(Context context,List<Usuario> usuarioList){
        this.usuarioList= usuarioList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_horizontal,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.nombre.setText(usuarioList.get(position).getNombre());
            //holder.apellido.setText(usuarioList.get(position).getApellido());
            //holder.edad.setText(String.valueOf(usuarioList.get(position).getEdad()));
            Picasso.with(context).load(usuarioList.get(position).getCarrera()).into(holder.imageView);
            //setAnimation(holder.cardView,position);
    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView nombre;
        TextView apellido;
        TextView edad;
        TextView carrera;
        ImageView imageView;
        CardView cardView;


        public ViewHolder (View item){
            super(item);
            //cardView =(CardView) item.findViewById(R.id.cardView);
            nombre = (TextView) item.findViewById(R.id.nombre);
           // apellido= (TextView) item.findViewById(R.id.apellido);
           // edad = (TextView) item.findViewById(R.id.edad);
            imageView = (ImageView) item.findViewById(R.id.imageView);
            // carrera = (TextView) item.findViewById(R.id.carrera);

        }
    }
    private void setAnimation(View viewToAnimate,int position){
        if(position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.left);
            viewToAnimate.startAnimation(animation);
            lastPosition=position;
        }

    }
}
