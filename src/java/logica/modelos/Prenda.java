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
public class Prenda {
    private int codigoPrenda;
    private int codigoCliente;
    private int codigoTipoPrenda;
    private int codigoTipoTela;
    private String especificaciones;

    public Prenda() {
    }

    public Prenda(int codigoPrenda, int codigoCliente, int codigoTipoPrenda, int codigoTipoTela, String especificaciones) {
        this.codigoPrenda = codigoPrenda;
        this.codigoCliente = codigoCliente;
        this.codigoTipoPrenda = codigoTipoPrenda;
        this.codigoTipoTela = codigoTipoTela;
        this.especificaciones = especificaciones;
    }

    public int getCodigoPrenda() {
        return codigoPrenda;
    }

    public void setCodigoPrenda(String codigoPrenda) {
        this.codigoPrenda = Integer.parseInt(codigoPrenda);
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoTipoPrenda() {
        return codigoTipoPrenda;
    }

    public void setCodigoTipoPrenda(int codigoTipoPrenda) {
        this.codigoTipoPrenda = codigoTipoPrenda;
    }

    public int getCodigoTipoTela() {
        return codigoTipoTela;
    }

    public void setCodigoTipoTela(int codigoTipoTela) {
        this.codigoTipoTela = codigoTipoTela;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    @Override
    public String toString() {
        return "Prenda{" + "codigoPrenda=" + codigoPrenda + ", codigoCliente=" + codigoCliente + ", codigoTipoPrenda=" + codigoTipoPrenda + ", codigoTipoTela=" + codigoTipoTela + ", especificaciones=" + especificaciones + '}';
    }
    
}
