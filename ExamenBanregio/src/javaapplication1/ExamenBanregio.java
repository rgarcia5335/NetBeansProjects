/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package javaapplication1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Date;

/**
 *
 * @author ricky
 */
public class ExamenBanregio {

    public static void main(String[] args) throws IOException {

        try {
            DecimalFormat formato = new DecimalFormat("#.00");
            Statement sql = Conexion.getConexion().createStatement();
            Statement sql2 = Conexion.getConexion().createStatement();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Ingresa el número de cliente");
            int numeroCliente = Integer.parseInt(reader.readLine());

            String consulta = "Select * from prestamos p"
                    + " where cliente = " + numeroCliente + " and estado = 0"
                    + " order by fecha asc";
            ResultSet resultado = sql.executeQuery(consulta);

            Date hoy = new Date(System.currentTimeMillis());
            int diasAnioComercial = 360;
            int tasa_iva = 16;
            double montoPrestamo = 0;

            System.out.println("Cliente\t plazo\t tasa_interes\t monto\t Interes\t IVA\t\t Pago");

            while (resultado.next()) {
                Date fechaPrestamo = resultado.getDate(3);

                int plazo = (int) ((hoy.getTime() - fechaPrestamo.getTime()) / 86400000);

                String obtenerTasa = "Select * from tasas "
                        + "where " + plazo + " >= plazo_min and " + plazo + " <= plazo_max";
                ResultSet tasa = sql2.executeQuery(obtenerTasa);

                tasa.next();

                montoPrestamo = resultado.getDouble(4);
                double interes = (montoPrestamo * plazo) * (tasa.getDouble(3) / diasAnioComercial);
                double iva = interes * tasa_iva;

                double pago = montoPrestamo + interes + iva;
                System.out.println(numeroCliente + "\t " + plazo + "\t " + tasa.getInt(3) + "\t\t " + montoPrestamo + "\t " + formato.format(interes) + "\t " + formato.format(iva) + "\t " + formato.format(pago));
            }
        } catch (SQLException excepcion) {
            System.err.println(excepcion.toString());
        }
    }

}
