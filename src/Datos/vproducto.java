/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Datos;

/**
 *
 * @author Fernando
 */
public class vproducto {
    private int idproducto;
    private int codigop;
    private String nombrep;
    private String marcap;
    private String descripcionp;
    private String medidap;
    private String preciop;
    private String cantidadp;
    private String calendariop;
    
    public vproducto (int idproducto, int codigop, String nombrep, String descripcionp, String medidap, String preciop, String cantidadp, String calendariop){
    this.idproducto = idproducto;
    this.codigop = codigop;
    this.nombrep = nombrep;
    this.descripcionp = descripcionp;
    this.medidap = medidap;
    this.preciop = preciop;
    this.calendariop = calendariop;
    };

    public vproducto() {
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCodigop() {
        return codigop;
    }

    public void setCodigop(int codigop) {
        this.codigop = codigop;
    }

    public String getNombrep() {
        return nombrep;
    }

    public void setNombrep(String nombrep) {
        this.nombrep = nombrep;
    }

    public String getMarcap() {
        return marcap;
    }

    public void setMarcap(String marcap) {
        this.marcap = marcap;
    }

    public String getDescripcionp() {
        return descripcionp;
    }

    public void setDescripcionp(String descripcionp) {
        this.descripcionp = descripcionp;
    }

    public String getMedidap() {
        return medidap;
    }

    public void setMedidap(String medidap) {
        this.medidap = medidap;
    }

    public String getPreciop() {
        return preciop;
    }

    public void setPreciop(String preciop) {
        this.preciop = preciop;
    }

    public String getCantidadp() {
        return cantidadp;
    }

    public void setCantidadp(String cantidadp) {
        this.cantidadp = cantidadp;
    }

    public String getCalendariop() {
        return calendariop;
    }

    public void setCalendariop(String calendariop) {
        this.calendariop = calendariop;
    }

  
}
