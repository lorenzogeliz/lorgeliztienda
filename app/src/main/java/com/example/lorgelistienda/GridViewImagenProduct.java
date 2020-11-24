package com.example.lorgelistienda;

public class GridViewImagenProduct {
    private String nameGridView;
    private String imagenGridView;

    public GridViewImagenProduct(String nameGridView, String imagenGridView) {
        this.nameGridView = nameGridView;
        this.imagenGridView = imagenGridView;
    }

    public String getNameGridView() {
        return nameGridView;
    }

    public void setNameGridView(String nameGridView) {
        this.nameGridView = nameGridView;
    }

    public String getImagenGridView() {
        return imagenGridView;
    }

    public void setImagenGridView(String imagenGridView) {
        this.imagenGridView = imagenGridView;
    }
}
