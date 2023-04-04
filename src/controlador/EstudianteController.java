/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class EstudianteController {
    
    public void insertar(String nombre, String usuario, String password, String codigo){
        Conectar c = new Conectar();
        Connection cn = c.conexion();
        String sql= "INSERT INTO estudiante (codigo, nombre, usuario, password) VALUES (?,?,?,?)";
        try{
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, codigo);
            pst.setString(2, nombre);
            pst.setString(3, usuario);
            pst.setString(4, password);
            int n = pst.executeUpdate();
            if(n > 0){
                JOptionPane.showMessageDialog(null, "Estudiante ingresado con exito...");
            }
            cn.close();
        }catch (SQLException ex) {
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String buscarNombre(String usuario){
        Conectar c= new Conectar();
        Connection cn=c.conexion();
        String nom = new String();
        String sql="SELECT nombre FROM estudiante WHERE usuario = ?";
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                nom = rs.getString("nombre");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error buscar Nombre");
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }
    public boolean existeUsuario(String usuario){
        Conectar c = new Conectar();
        Connection cn = c.conexion();
        String sql = "SELECT * FROM estudiante WHERE usuario = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            int n = ps.executeUpdate();
            if(n>0){
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error existe usuario");
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean existePassword(String usuario, String password){
        Conectar c = new Conectar();
        Connection cn = c.conexion();
        String sql="SELECT * FROM estudiante WHERE usuario = ? && password = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error existe password");
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean existeCodigo(String codigo){
        Conectar c = new Conectar();
        Connection cn = c.conexion();
        String sql="SELECT usuario FROM estudiante WHERE codigo = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error existe codigo");
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public String[] buscarDatosEstudiante(String usuario){
        Conectar c = new Conectar();
        Connection cn = c.conexion();
        String filas[] = new String[4];
        String buscar = ("SELECT nombre, codigo, usuario, password FROM estudiante WHERE usuario = ?");
        try {
            PreparedStatement ps = cn.prepareStatement(buscar);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                filas[0]=rs.getString("nombre");
                filas[1]=rs.getString("codigo");
                filas[2]=rs.getString("usuario");
                filas[3]=rs.getString("password");
            }
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filas;
    }
    public void cambiarPassword(String usuario, String password){
        Conectar c = new Conectar();
        Connection cn = c.conexion();
        String sql=("UPDATE estudiante SET password = ? WHERE usuario = ?");
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, usuario);
            ps.executeUpdate();
            cn.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error password");
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
}
