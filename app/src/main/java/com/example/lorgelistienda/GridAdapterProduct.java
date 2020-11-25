package com.example.lorgelistienda;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * {@link BaseAdapter} personalizado para el gridview
 */
public class GridAdapterProduct extends BaseAdapter {

    private Context contexto;
    private ArrayList<GridViewImagen> vectorDatos;
    private TextView nombreGridView;
    private ImageView imageView;

    public GridAdapterProduct(Context contexto, ArrayList<GridViewImagen> datos) {
        //Se asignan los valores a los atributos de la clase AdaptadorGridView.
        this.contexto = contexto;
        this.vectorDatos = datos;
    }

    @Override
    public int getCount() {
        return vectorDatos.size();
    }

    @Override
    public Object getItem(int posicion) {
        return vectorDatos.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater layout_inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
            convertView = layout_inflater.inflate(R.layout.gridview_card_productos, null);
        }
        imageView=convertView.findViewById(R.id.imagenLogo);
        imageView.setImageResource(vectorDatos.get(i).getImagenGridView());


       /* Picasso.with(contexto)
                .load(vectorDatos.get(i).getImagenGridView())
                .placeholder(R.drawable.img1)
                .fit()
                .centerCrop().into(imageView);*/


        /*Picasso.get().load(vectorDatos.get(i).getImagenGridView())
                .resize(100, 100)
                .memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE)
                .error(contexto.getResources().getDrawable(R.drawable.img1))
                .centerCrop().into(imageView);*/

        return convertView;
    }
}

