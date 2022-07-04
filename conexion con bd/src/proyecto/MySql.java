package proyecto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Reaper
 */
public class MySql {
    Connection conexion=null;
    Statement comando=null;
    ResultSet registro;
    
    public Connection MySqlConnect(){
        try{
            //Driver Mysql
            Class.forName("com.mysql.jdbc.Driver");
            //Nombre y puerto del servidor
           // String servidor="jdbc:mysql://127.0.0.1:3306/bancos";
            String servidor="jdbc:mysql://192.168.43.156:3306/la_palma";
            //Nombre de usuario y contraseña
            String usuario="root";
            String pwd="sistemas";
            //Iniciamos la conexion
            conexion=DriverManager.getConnection(servidor, usuario, pwd);            
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e,"Error en la BD "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e,"Error en la conexion a la BD"+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e,"Error en conexion "+e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }finally{
            return conexion;
        }
    }
}
