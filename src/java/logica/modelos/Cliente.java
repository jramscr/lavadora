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
public class Cliente {
    private int codigoCliente;
    private String cedula;
    private String nombre;
    private String celular;
    private String email;
    private String direccion;

    public Cliente() {
    }

    public Cliente(int codigoCliente, String cedula, String nombre, String celular, String email, String direccion) {
        this.codigoCliente = codigoCliente;
        this.cedula = cedula;
        this.nombre = nombre;
        this.celular = celular;
        this.email = email;
        this.direccion = direccion;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = Integer.parseInt(codigoCliente);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return Integer.toString(codigoCliente);
    }
    
}
