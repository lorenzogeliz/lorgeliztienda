package com.example.lorgelistienda;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ActivityProductoPresenter implements ActivityProductoMVP.Presenter {

    ActivityProductoMVP.View vista;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    // Create a storage reference from our app
    StorageReference storageRef = storage.getReference();

    // Create a reference with an initial file path and name
//    StorageReference pathReference = storageRef.child("medianas/10(2).jpg");
    StorageReference listRef = storageRef.child("medianas/");
    // StorageReference pathReference = storageRef.child("medianas/uid");

    public ActivityProductoPresenter(ActivityProductoMVP.View vista) {
        this.vista = vista;
    }

    @Override
    public void getUrlImagen() {

        listRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        List<GridViewImagenProduct> listURL = new ArrayList<>();

                        for (StorageReference item : listResult.getItems()) {
                            // All the items under listRef.
                            item.getDownloadUrl()
                                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Uri> task) {

                                            if (task.isSuccessful()) {

                                                String gotUrl = task.getResult().toString();
                                                listURL.add(new GridViewImagenProduct("", gotUrl));
                                                if (listResult.getItems().size()==1){
                                                    getDatesUrl(listURL);
                                                }
                                                System.out.println("gggggg"+gotUrl);
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
                        errorGetUrl("Error al descargar las imagenes");
                    }
                });

    }


    private void getDatesUrl(List<GridViewImagenProduct> listURL) {
        if (listURL != null && !listURL.isEmpty()) {
            if (vista != null)
                vista.successfullUrlImagen(listURL);
        } else {

        }
    }

    private void errorGetUrl(String s) {

        if (s != null && !s.isEmpty()) {
            if (vista != null)
                vista.Error(s);
        } else {
            vista.Error("Error desconocido,por favor intente mas tarde");
        }
    }
}
