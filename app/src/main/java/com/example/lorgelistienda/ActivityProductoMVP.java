package com.example.lorgelistienda;

import java.util.List;

public interface ActivityProductoMVP {

    interface View {
        void successfullUrlImagen(List<GridViewImagenProduct> listURL);
        void Error(String error);
    }

    interface Presenter {
        void getUrlImagen();
    }
}
