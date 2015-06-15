/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vcliente;
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
public class fcliente {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Rut", "Nombre", "Apaterno", "Amaterno", "Dirección", "Email", "Telefono", "Código", "Deuda", "Observacion"};

        String[] registro = new String[11];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona,p.rut,p.nombre,p.apaterno,p.amaterno,"
                + "p.direccion,p.email,p.telefono,c.codigo,c.deuda,c.observaciones from persona p inner join cliente c "
                + "on p.idpersona=c.idpersona where codigo like'%"
                + buscar + "%' order by idpersona desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("rut");
                registro[2] = rs.getString("nombre");
                registro[3] = rs.getString("apaterno");
                registro[4] = rs.getString("amaterno");
                registro[5] = rs.getString("direccion");
                registro[6] = rs.getString("email");
                registro[7] = rs.getString("telefono");
                registro[8] = rs.getString("codigo");
                registro[9] = rs.getString("deuda");
                registro[10] = rs.getString("observaciones");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(vcliente dts) {
        sSQL = "insert into persona (rut,nombre,apaterno,amaterno,direccion,email,telefono)"
                + "values (?,?,?,?,?,?,?)";
        sSQL2 = "insert into cliente (idpersona,codigo,deuda,observaciones)"
                + "values (?,?,?)";
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

            pst2.setString(1, dts.getCodigo());
            pst2.setInt(2, dts.getDeuda());
            pst2.setString(3, dts.getObservaciones());

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

    public boolean editar(vcliente dts) {
        sSQL = "update persona set rut=?,nombre=?,apaterno=?,amaterno=?,"
                + " direccion=?,email=?,telefono=? where idpersona=?";

        sSQL2 = "update cliente set codigo=?,deuda=?,observaciones=?"
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

            pst2.setString(1, dts.getCodigo());
            pst2.setInt(2, dts.getIdpersona());
            pst2.setInt(3, dts.getDeuda());
            pst2.setString(4, dts.getObservaciones());

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

    public boolean eliminar(vcliente dts) {
        sSQL = "delete from cliente where idpersona=?";
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
}
