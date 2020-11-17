package com.example.ejentregar;

import android.media.Image;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pelicula {
    private String titulo;
    private String director;
    private double duracion;
    private int sala;
    private String sinopsis;
    private Date fechaDeEstreno;
    private Image portada;
    private Image pegi;
    private DateFormat format = new SimpleDateFormat("dd/MM/yyyyy");


    public Pelicula(String titulo, String director, double duracion, int sala, String sinopsis, String fechaDeEstreno, Image portada, Image pegi) {
        this.setTitulo(titulo);
        this.setDirector(director);
        this.setDuracion(duracion);
        this.setSala(sala);
        this.setSinopsis(sinopsis);
        this.setFechaDeEstreno(fechaDeEstreno);
        this.setPortada(portada);
        this.setPegi(pegi);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Image getPortada() {
        return portada;
    }

    public void setPortada(Image portada) {
        this.portada = portada;
    }

    public Image getPegi() {
        return pegi;
    }

    public void setPegi(Image pegi) {
        this.pegi = pegi;
    }

    public Date getFecha(String fecha){
        Date date = null;
        try {
            date = format.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public void setFechaDeEstreno(String fechaDeEstreno) {
        Date date = null;
        try {
            date = format.parse(fechaDeEstreno);
            this.fechaDeEstreno = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
