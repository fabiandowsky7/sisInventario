/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vcliente;
//import Datos.vproducto;
import Datos.vtrabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fabian
 */
public class ftrabajador {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Rut", "Nombre", "Apaterno", "Amaterno", "Direccion", "Email", "Telefono", "Acceso", "Usuario", "Password", "Estado"};

        String[] registro = new String[12];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona,p.rut,p.nombre,p.apaterno,p.amaterno,"
                + "p.direccion,p.email,p.telefono,t.acceso,t.usuario,t.password,t.estado from persona p inner join Trabajador t "
                + "on p.idpersona=t.idpersona where rut like '%"
                + buscar + "%' order by idpersona desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("rut");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apaterno");
                registro[3] = rs.getString("amaterno");
                registro[6] = rs.getString("direccion");
                registro[7] = rs.getString("email");
                registro[8] = rs.getString("telefono");
                registro[10] = rs.getString("acceso");
                registro[11] = rs.getString("usuario");
                registro[12] = rs.getString("password");
                registro[13] = rs.getString("estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(vtrabajador dts) {
        sSQL = "insert into persona (rut,nombre,apaterno,amaterno,direccion,email,telefono)"
                + "values (?,?,?,?,?,?,?)";
        sSQL2 = "insert into trabajador (idpersona,acceso,usuario,password,estado)"
                + "values ((select idpersona from persona order by idpersona desc limit 1),?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getRut());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getApaterno());
            pst.setString(4, dts.getAmaterno());
            pst.setString(5, dts.getDireccion());
            pst.setString(6, dts.getEmail());
            pst.setInt(7, dts.getTelefono());

            pst2.setString(1, dts.getAcceso());
            pst2.setString(2, dts.getUsuario());
            pst2.setString(3, dts.getPassword());
            pst2.setString(4, dts.getEstado());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(vtrabajador dts) {
        sSQL = "update persona set rut=?,nombre=?,apaterno=?,amaterno=?,"
                + " direccion=?,email=?,telefono=? where idpersona=?";

        sSQL2 = "update trabajador set acceso=?,usuario=?,password=?,estado=?"
                + " where idpersona=?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getRut());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getApaterno());
            pst.setString(4, dts.getAmaterno());
            pst.setString(5, dts.getDireccion());
            pst.setString(6, dts.getEmail());
            pst.setInt(7, dts.getTelefono());
            pst.setInt(8, dts.getIdpersona());

            pst2.setString(1, dts.getAcceso());
            pst2.setString(2, dts.getUsuario());
            pst2.setString(3, dts.getPassword());
            pst2.setString(4, dts.getEstado());
            pst2.setInt(5, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(vtrabajador dts) {
        sSQL = "delete from trabajador where idpersona=?";
        sSQL2 = "delete from persona where idpersona=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setInt(1, dts.getIdpersona());

            pst2.setInt(1, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public DefaultTableModel login(String login, String password) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Rut", "Nombre", "Apaterno", "Amaterno", "Acceso", "Usuario", "Password", "Estado"};

        String[] registro = new String[9];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona,p.rut,p.nombre,p.apaterno,p.amaterno,"
                + "t.acceso,t.usuario,t.password,t.estado from persona p inner join Trabajador t "
                + "on p.idpersona=t.idpersona where t.usuario='"
                + login + "' and t.password='" + password + "' and t.estado='A'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("rut");
                registro[2] = rs.getString("nombre");
                registro[3] = rs.getString("apaterno");
                registro[4] = rs.getString("amaterno");

                registro[5] = rs.getString("acceso");
                registro[6] = rs.getString("usuario");
                registro[7] = rs.getString("password");
                registro[8] = rs.getString("estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public DefaultTableModel usuario(String usuario, String password) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Rut", "Nombre", "Apaterno", "Amaterno","Acceso", "Usuario", "Password", "Estado"};

        String[] registro = new String[9];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona,p.rut,p.nombre,p.apaterno,p.amaterno,"
                + "t.acceso,t.usuario,t.password,t.estado from persona p inner join Trabajador t "
                + "on p.idpersona=t.idpersona where t.usuario'"
                + usuario + "' and t.password'" + password + "'and t.estado='A'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("rut");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apaterno");
                registro[3] = rs.getString("amaterno");
                registro[4] = rs.getString("acceso");
                registro[5] = rs.getString("usuario");
                registro[6] = rs.getString("password");
                registro[7] = rs.getString("estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
}
