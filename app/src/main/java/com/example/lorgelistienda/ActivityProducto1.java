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

import java.util.ArrayList;
import java.util.List;

public class ActivityProducto1 extends AppCompatActivity {

    private GridAdapterProduct adaptador;
    private GridView gridView;

    private List<GridViewImagenProduct> gridViewImagenList = new ArrayList<>();

    private GridLayoutManager adapter;

    private ImageView irAtras;

    private ArrayList<String> listURL = new ArrayList<>();

    FirebaseStorage storage = FirebaseStorage.getInstance();

    // Create a storage reference from our app
    StorageReference storageRef = storage.getReference();

    // Create a reference with an initial file path and name
//    StorageReference pathReference = storageRef.child("medianas/10(2).jpg");
    StorageReference listRef = storageRef.child("medianas/");
    // StorageReference pathReference = storageRef.child("medianas/uid");


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

        adaptador = new GridAdapterProduct(this, (ArrayList<GridViewImagenProduct>) gridViewImagenList);
        gridView.setAdapter(adaptador);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> item, View vista, int i, long id) {
                if (i == 0) {
                    Toast.makeText(ActivityProducto1.this, "este es el producto Plataformas Medianas", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(vista.getContext(), ActivityProducto1.class));
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
                }
            }

        });


    }

    public void getDatesUrl(String listURL){

        /*for (int i = 0; i <listURL.size() ; i++) {
            gridViewImagenList.add(new GridViewImagenProduct("", listURL.get(i)));
            System.out.println("urli"+listURL.get(i));
        }*/
        gridViewImagenList.add(new GridViewImagenProduct("", listURL));
        System.out.println("urli"+listURL);

    }


    public void addcarviewiten() {

       ArrayList<String> listURL = new ArrayList<>();

        listRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference prefix : listResult.getPrefixes()) {
                            // All the prefixes under listRef.
                            // You may call listAll() recursively on them.
                            System.out.println("item de las storas" + prefix);
                        }

                        for (StorageReference item : listResult.getItems()) {
                            // All the items under listRef.
                            item.getDownloadUrl()
                                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Uri> task) {

                                            if (task.isSuccessful()) {
                                                String gotUrl = task.getResult().toString();
                                                getDatesUrl(gotUrl);
                                                //listURL.add(gotUrl);
                                                //Toast.makeText(ActivityProducto1.this, "url"+gotUrl, Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Uh-oh, an error occurred!

                        System.out.println("Problemas de conexion");
                    }
                });

    }


}