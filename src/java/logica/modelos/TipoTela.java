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
public class TipoTela {
    private int codigoTipoTela;
    private String nombre;

    public TipoTela() {
    }

    public TipoTela(int codigoTipoTela, String nombre) {
        this.codigoTipoTela = codigoTipoTela;
        this.nombre = nombre;
    }

    public int getCodigoTipoTela() {
        return codigoTipoTela;
    }

    public void setCodigoTipoTela(String codigoTipoTela) {
        this.codigoTipoTela = Integer.parseInt(codigoTipoTela);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoTela{" + "codigoTipoTela=" + codigoTipoTela + ", nombre=" + nombre + '}';
    }
    
    
    
}
