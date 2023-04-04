/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 * 
 */
public class Conectar {
    private static Connection con;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:2974/ponderadounimag";

    public Conectar() {
        con = null;
        try{
            Class.forName(driver);
            con = DriverManager.getConnection("jdbc:mysql://localhost:2974/?user=root&password=rootpassword");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error al conectar " + e);
        }
    }
    //nos devuelve la conexion a la base de datos
    public Connection getConnection(){
        return con;
    }
    //nos desconecta de la base de datos
    public void desconectar(){
        con = null;
    }
        
    public Connection conexion(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e) {
         JOptionPane.showMessageDialog(null, "Algo no anda bien!"+ e);
        }
        return con;
    }

}