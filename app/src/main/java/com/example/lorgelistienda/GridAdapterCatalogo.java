package com.example.lorgelistienda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link BaseAdapter} personalizado para el gridview
 */
public class GridAdapterCatalogo extends BaseAdapter {

    private Context contexto;
    private ArrayList<GridViewImagen> vectorDatos;
    private TextView nombreGridView;
    private ImageView imageView;
    private ImageView recortar;

    public GridAdapterCatalogo(Context contexto, ArrayList<GridViewImagen> datos) {
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
        nombreGridView = (TextView) convertView.findViewById(R.id.informacion);
        nombreGridView.setText(vectorDatos.get(i).getNameGridView());
        imageView=convertView.findViewById(R.id.imagenLogo);
        imageView.setImageResource(vectorDatos.get(i).getImagenGridView());
        recortar=convertView.findViewById(R.id.recorte);

        recortar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return convertView;
    }
}

