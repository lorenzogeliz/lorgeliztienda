package com.example.lorgelistienda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ActivityProducto1 extends AppCompatActivity implements ActivityProductoMVP.View{

    private GridAdapterProduct adaptador;
    private GridView gridView;

    private List<GridViewImagen> gridViewImagenList =new ArrayList<>();

    private GridLayoutManager adapter;

    private ImageView irAtras, imageView;


    private ActivityProductoMVP.Presenter presenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto1);

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

        gridView = (GridView) findViewById(R.id.gridviewProducto1);

        adaptador = new GridAdapterProduct(this, (ArrayList<GridViewImagen>) gridViewImagenList);
        gridView.setAdapter(adaptador);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> item, View vista, int i, long id) {



            }

        });




    }

    public void getImagen(){
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.platm7));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.platm8));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.platm9));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.platm18));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.platm19));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.platm20));
        /*gridViewImagenList.add(new GridViewImagen(" ", R.drawable.san2));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.san3));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.san4));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.san5));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.san6));
        gridViewImagenList.add(new GridViewImagen(" ", R.drawable.san6));*/

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


