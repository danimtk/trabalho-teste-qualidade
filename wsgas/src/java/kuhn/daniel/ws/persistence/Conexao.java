/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuhn.daniel.ws.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dani
 */
public class Conexao {

    private static String HOST     = "localhost";
    private static String BASE     = "gas";
    private static String USER     = "postgres";
    private static String PASSWORD = "postgres";
    
    public static Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());
        }

        try {
            return DriverManager.getConnection("jdbc:postgresql://"+HOST+":5432/"+BASE+"",USER, PASSWORD);	

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static void main (String[] args) {
        
        Connection conn = Conexao.getConnection();       
    }
}
