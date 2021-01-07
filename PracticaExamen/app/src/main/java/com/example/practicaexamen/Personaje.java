package com.example.practicaexamen;

public class Personaje {
    private int valoracion;
    private int apariciones;
    private String nombre;
    private String tipo;
    private int imagen;

    public Personaje(int valoracion, int apariciones, String nombre, String tipo, int imagen, String texto) {
        this.valoracion = valoracion;
        this.apariciones = apariciones;
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagen = imagen;
        this.texto = texto;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getApariciones() {
        return apariciones;
    }

    public void setApariciones(int apariciones) {
        this.apariciones = apariciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    private String texto;
}
