/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author ricky
 */
public class Conexion {

    public static Connection getConexion() {
        String url = "jdbc:sqlserver://127.0.0.1:1433;"
                + "database=Banregio;"
                + "user=sa;"
                + "password=12345678";

        try {
            Connection con = DriverManager.getConnection(url);
            return con;

        } catch (SQLException exception) {
            System.out.println(exception.toString());
            return null;
        }
    }

}
