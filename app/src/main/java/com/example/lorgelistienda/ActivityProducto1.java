package com.example.lorgelistienda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityProducto1 extends AppCompatActivity {

    private GridAdapterProductos adaptador;
    private GridView gridView;

    private List<GridViewImagen> gridViewImagenList = new ArrayList<>();

    private GridLayoutManager adapter;

    private ImageView irAtras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto1);

        addcarviewiten();

        irAtras = findViewById(R.id.btn_atras);
        irAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        gridView = (GridView) findViewById(R.id.gridviewProducto1);

        adaptador = new GridAdapterProductos(this, (ArrayList<GridViewImagen>) gridViewImagenList);
        gridView.setAdapter(adaptador);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> item, View vista, int i, long id) {
                if (i==0) {
                    //plataformas medianas.
                    startActivity(new Intent(vista.getContext(), ActivityProducto1.class));
                }
                if (i==1) {
                    Toast.makeText(ActivityProducto1.this, "este es el producto Plataformas Altas", Toast.LENGTH_SHORT).show();

                }if (i==2) {
                    Toast.makeText(ActivityProducto1.this, "este es el producto Baletas", Toast.LENGTH_SHORT).show();

                }if (i==3) {
                    Toast.makeText(ActivityProducto1.this, "este es el producto Sandalias", Toast.LENGTH_SHORT).show();

                }if (i==4) {
                    Toast.makeText(ActivityProducto1.this, "este es el producto Tacones", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }


    private void addcarviewiten() {
        gridViewImagenList.add(new GridViewImagen("Plataformas Medianas", R.drawable.img1));
        gridViewImagenList.add(new GridViewImagen("Plataformas Altas", R.drawable.img5));
        gridViewImagenList.add(new GridViewImagen("Baletas", R.drawable.img2));
        gridViewImagenList.add(new GridViewImagen("Sandalias", R.drawable.img3));
        gridViewImagenList.add(new GridViewImagen("Tacones", R.drawable.img4));

    }

}