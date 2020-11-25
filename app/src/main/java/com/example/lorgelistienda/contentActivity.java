package com.example.lorgelistienda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;


public class contentActivity extends AppCompatActivity {


    private GridAdapter adaptador;
    private GridView gridView;
    private List<GridViewImagen> gridViewImagenList = new ArrayList<>();
private ImageView btn_atras;
    private GridLayoutManager adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        btn_atras=findViewById(R.id.btn_atras);

        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        addcarviewiten();

        gridView = (GridView) findViewById(R.id.gridview);

        adaptador = new GridAdapter(this, (ArrayList<GridViewImagen>) gridViewImagenList);
        gridView.setAdapter(adaptador);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> item, View vista, int i, long id) {
                if (i==0) {
                    //plataformas medianas.
                    startActivity(new Intent(vista.getContext(), ActivityProducto1.class));
                }
                if (i==1) {
                    startActivity(new Intent(vista.getContext(), ActivityProducto2.class));


                }if (i==2) {
                    startActivity(new Intent(vista.getContext(), ActivityProducto3.class));


                }if (i==3) {
                    startActivity(new Intent(vista.getContext(), ActivityProducto4.class));

                }if (i==4) {
                    startActivity(new Intent(vista.getContext(), ActivityProducto5.class));

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