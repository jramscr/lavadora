/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelos;

/**
 *
 * @author jramos
 */
public class EstadoServicio {
    private int codigoEstadoServicio;
    private String nombre;

    public EstadoServicio() {
    }

    public EstadoServicio(int codigoEstadoServicio, String nombre) {
        this.codigoEstadoServicio = codigoEstadoServicio;
        this.nombre = nombre;
    }

    public int getCodigoEstadoServicio() {
        return codigoEstadoServicio;
    }

    public void setCodigoEstadoServicio(String codigoEstadoServicio) {
        this.codigoEstadoServicio = Integer.parseInt(codigoEstadoServicio);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EstadoServicio{" + "codigoEstadoServicio=" + codigoEstadoServicio + ", nombre=" + nombre + '}';
    }
}
