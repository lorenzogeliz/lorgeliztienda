package com.example.lorgelistienda;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class ActivityProducto4 extends AppCompatActivity implements ActivityProductoMVP.View{

    private GridAdapterProduct adaptador;
    private GridView gridView;

    private List<GridViewImagen> gridViewImagenList =new ArrayList<>();

    private GridLayoutManager adapter;

    private ImageView irAtras, imageView;


    private ActivityProductoMVP.Presenter presenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto4);

        /*presenter=new ActivityProductoPresenter(this);

        if (presenter!=null)presenter.getUrlImagen();*/
        getImagen();

        imageView = findViewById(R.id.imv);
        irAtras = findViewById(R.id.btn_atras);
        irAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        gridView = (GridView) findViewById(R.id.gridviewProducto4);

        adaptador = new GridAdapterProduct(this, (ArrayList<GridViewImagen>) gridViewImagenList);
        gridView.setAdapter(adaptador);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> item, View vista, int i, long id) {
               /* if (i == 0) {
                    Toast.makeText(ActivityProducto1.this, "este es el producto Plataformas Medianas", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(vista.getContext(), ActivityProducto1.class));
                }
                if (i == 1) {
                    Toast.makeText(ActivityProducto1.this, "este es el producto Plataformas Altas", Toast.LENGTH_SHORT).show();

                }
                if (i == 2) {
                    Toast.makeText(ActivityProducto1.this, "este es el producto Baletas", Toast.LENGTH_SHORT).show();

                }
                if (i == 3) {
                    Toast.makeText(ActivityProducto1.this, "este es el producto Sandalias", Toast.LENGTH_SHORT).show();

                }
                if (i == 4) {
                    Toast.makeText(ActivityProducto1.this, "este es el producto Tacones", Toast.LENGTH_SHORT).show();
                }*/
            }

        });


    }

    public void getImagen(){

        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.san2));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.san3));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.san4));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.san5));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.san6));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.san6));

    }


    @Override
    public void successfullUrlImagen(List<GridViewImagenProduct> listURL) {
        /*
        for (int i = 0; i < listURL.size(); i++) {
            gridViewImagenList.add(new GridViewImagenProduct("",listURL.get(i).getImagenGridView() ));
        }*/
    }

    @Override
    public void Error(String error) {

    }
}


