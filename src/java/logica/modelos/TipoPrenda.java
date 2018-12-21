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
public class TipoPrenda {
    private int codigoTipoPrenda;
    private String nombre;

    public TipoPrenda() {
    }

    public TipoPrenda(int codigoTipoPrenda, String nombre) {
        this.codigoTipoPrenda = codigoTipoPrenda;
        this.nombre = nombre;
    }

    public int getCodigoTipoPrenda() {
        return codigoTipoPrenda;
    }

    public void setCodigoTipoPrenda(String codigoTipoPrenda) {
        this.codigoTipoPrenda = Integer.parseInt(codigoTipoPrenda);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoPrenda{" + "codigoTipoPrenda=" + codigoTipoPrenda + ", nombre=" + nombre + '}';
    }    
}
