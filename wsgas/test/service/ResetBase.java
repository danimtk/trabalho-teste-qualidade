/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import kuhn.daniel.ws.persistence.Conexao;

/**
 *
 * @author Daniel M. Kuhn <www.cybercidades.com.br>
 */
public class ResetBase {

    private static final Logger LOG = Logger.getLogger(ResetBase.class.getName());
    
    public boolean resetBase() {
        try 
        {
            String sql = " DELETE FROM endereco; DELETE FROM pedido; DELETE FROM produto; DELETE FROM usuario;";

            Statement stmt  = Conexao.getConnection().createStatement();

            if (stmt.execute(sql)) {
                return true;
            }
            return false;

        } catch(SQLException e) {
            System.out.println(LOG+" "+e.getMessage());
            return false;
        }
    }

    public boolean resetSequence() {
        try 
        {
            String sql = " ALTER SEQUENCE endereco_id_seq RESTART WITH 1; "
                       + " ALTER SEQUENCE pedido_id_seq RESTART WITH 1; "
                       + " ALTER SEQUENCE produto_id_seq RESTART WITH 1; "
                       + " ALTER SEQUENCE usuario_id_seq RESTART WITH 1; ";

            Statement stmt  = Conexao.getConnection().createStatement();

            if (stmt.execute(sql)) {
                return true;
            }
            return false;

        } catch(SQLException e) {
            System.out.println(LOG+" "+e.getMessage());
            return false;
        }
    }
    
    public static void main (String[] args) {
        ResetBase rb = new ResetBase();
        rb.resetBase();
        rb.resetSequence();
    }
}
