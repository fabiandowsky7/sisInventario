/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Datos;

/**
 *
 * @author Fabian
 */
public class vtrabajador extends vpersona{
    private String acceso;
    private String usuario;
    private String password;
    private String estado;

    public vtrabajador() {
    }

    public vtrabajador(String acceso, String usuario, String password, String estado) {
        this.acceso = acceso;
        this.usuario = usuario;
        this.password = password;
        this.estado = estado;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
