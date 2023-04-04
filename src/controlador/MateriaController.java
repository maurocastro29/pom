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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 
 */
public class MateriaController {
    public void insertar(int creditos, String nombre, float nota, String codigo){
        Conectar c = new Conectar();
        Connection cn = c.conexion();
        String sql= "INSERT INTO materia (nombre, creditos, nota, codigo) VALUES (?,?,?,?)";
        try{
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setInt(2, creditos);
            pst.setFloat(3, nota);
            pst.setString(4, codigo);
            int n = pst.executeUpdate();
            if(n > 0){
                JOptionPane.showMessageDialog(null, "Materia ingresada con exito...");
            }
            cn.close();
        }catch (SQLException ex) {
            Logger.getLogger(MateriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean buscarMateriaPorCodigo(String materia, String codigo){
        Conectar c=new Conectar();
        Connection cn = c.conexion();
        String sql = "SELECT nombre FROM materia WHERE codigo = ? && nombre = ?";
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, codigo);
            ps.setString(2, materia);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(SQLException ex){
            Logger.getLogger(MateriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public void editarNota(String nombre, int nota){
        Conectar c= new Conectar();
        Connection cn = c.conexion();
        String sql="UPDATE materia SET nota = ? WHERE nombre = ?";
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, nota);
            ps.setString(2, nombre);
            ps.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(MateriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editarCreditos(String nombre, int creditos){
        Conectar c= new Conectar();
        Connection cn = c.conexion();
        String sql="UPDATE materia SET creditos = ? WHERE nombre = ?";
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, creditos);
            ps.setString(2, nombre);
            ps.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(MateriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminar(String nombre){
        Conectar c= new Conectar();
        Connection cn = c.conexion();
        String sql="DELETE FROM materia WHERE nombre = ?";
        try{
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Materia eliminada correctamente..");
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public DefaultTableModel mostrarListaMaterias(String codigo){
        Conectar c= new Conectar();
        Connection cn = c.conexion();
        DefaultTableModel modelo = null;
        String sql = "SELECT nombre, creditos, nota FROM materia WHERE codigo = "+codigo+"";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rstb = ps.executeQuery(sql);
            ResultSetMetaData rsmd = rstb.getMetaData();
            int col = rsmd.getColumnCount();
            modelo = new DefaultTableModel();
            for (int i = 0; i < col; i++) {
                modelo.addColumn(rsmd.getColumnLabel(i+1));
            }
            while(rstb.next()){
                String filas[] = new String[col];
                for(int j=0;j<col;j++){
                    filas[j]=rstb.getString(j+1);
                }
                modelo.addRow(filas);
            }
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
    }
}
