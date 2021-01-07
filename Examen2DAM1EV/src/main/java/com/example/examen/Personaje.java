package com.example.examen;

public class Personaje {
    private String nombre;
    private float valoracion;
    private int imagen;

    public Personaje(String nombre, float valoracion, int imagen, boolean favorito, String actorOriginal, int imagenActor, String pelicula) {
        this.nombre = nombre;
        this.valoracion = valoracion;
        this.imagen = imagen;
        this.favorito = favorito;
        this.actorOriginal = actorOriginal;
        this.imagenActor = imagenActor;
        this.pelicula = pelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public String getActorOriginal() {
        return actorOriginal;
    }

    public void setActorOriginal(String actorOriginal) {
        this.actorOriginal = actorOriginal;
    }

    public int getImagenActor() {
        return imagenActor;
    }

    public void setImagenActor(int imagenActor) {
        this.imagenActor = imagenActor;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
       if(pelicula.contains("Hotel Transilvania") && (pelicula.contains("1") || pelicula.contains("2") || pelicula.contains("3"))){
           this.pelicula = pelicula;
       }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private boolean favorito;
    private String actorOriginal;
    private int imagenActor;
    private String pelicula;
    private String descripcion;


}
