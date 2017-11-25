/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuhn.daniel.ws.DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import kuhn.daniel.ws.entity.Endereco;
import kuhn.daniel.ws.entity.Usuario;
import kuhn.daniel.ws.persistence.Conexao;
import kuhn.daniel.ws.util.Timestamp;

/**
 *
 * @author dani
 */
public class EnderecoDAO implements Serializable {

    private static final Logger LOG = Logger.getLogger(EnderecoDAO.class.getName());
    
        public Integer adicionarEndereco(Endereco e) {
    
            Connection conn = Conexao.getConnection();
        
            try {
                PreparedStatement pstmt = conn
                    .prepareStatement(
                        "INSERT INTO endereco (usuario_id, cidade, cep, bairro, rua, numero, complemento, timestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                    ,Statement.RETURN_GENERATED_KEYS);

                pstmt.setInt(1, e.getUsuario().getId());
                pstmt.setString(2, e.getCidade());
                pstmt.setInt(3, e.getCep());
                pstmt.setString(4, e.getBairro());
                pstmt.setString(5, e.getRua());
                pstmt.setString(6, e.getNumero());
                pstmt.setString(7, e.getComplemento());
                pstmt.setString(8, Timestamp.timestamp());

                pstmt.executeUpdate();

                Integer endereco_id = null;

                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    endereco_id = rs.getInt(1);
                }

                pstmt.close();
                return endereco_id;

            } catch(SQLException ex) {
                System.out.println(LOG+" "+ex.getMessage());
                return null;
            }
        }
        
    
    public Endereco selecionaEndereco(Integer id) {
    
        Connection conn = Conexao.getConnection();
        
        String sql = "SELECT * FROM endereco e WHERE e.id = ? AND e.excluido IS NULL ";
        
        try 
        {   
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            
            ResultSet rs = pstmt.executeQuery();

            Endereco e = new Endereco();
            
            while (rs.next()) 
            {   
                e.setId(rs.getInt("id"));
                e.setCidade(rs.getString("cidade"));
                e.setCep(rs.getInt("cep"));
                e.setBairro(rs.getString("bairro"));
                e.setRua(rs.getString("rua"));
                e.setNumero(rs.getString("numero"));
                e.setComplemento(rs.getString("complemento"));
                e.setTimestamp(rs.getString("timestamp"));
            }
            
            return e;
            
        } catch(SQLException e) {
            System.out.println(LOG+" "+e.getMessage());
            return null;
        }
    }  

    
    public List<Endereco> selecionaEnderecosUsuario(Integer usuario_id) {
    
        Connection conn = Conexao.getConnection();
        
        String sql = " SELECT * FROM endereco e WHERE e.usuario_id = ? AND excluido IS NULL ";
        
        try 
        {   
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, usuario_id);
            
            ResultSet rs = pstmt.executeQuery();

            List<Endereco> enderecos = new ArrayList<>();
            
            while (rs.next()) 
            {    
                Endereco e = new Endereco();

                e.setId(rs.getInt("id"));
                e.setCidade(rs.getString("cidade"));
                e.setUsuario(new Usuario(rs.getInt("usuario_id")));
                e.setCep(rs.getInt("cep"));
                e.setBairro(rs.getString("bairro"));
                e.setRua(rs.getString("rua"));
                e.setNumero(rs.getString("numero"));
                e.setComplemento(rs.getString("complemento"));
                e.setTimestamp(rs.getString("timestamp"));
                
                enderecos.add(e);
            }
            
            return enderecos;
            
        } catch(SQLException e) {
            System.out.println(LOG+" "+e.getMessage());
            return null;
        }
    }
    
    
    public boolean excluirEndereco(Endereco end) {
        try 
        {
            String sql = " UPDATE endereco "
                       + "    SET excluido = ? "
                       + "  WHERE id = ? ";
            
            PreparedStatement pstmt = Conexao.getConnection().prepareStatement(sql);
            
            pstmt.setInt(1, 1);
            pstmt.setInt(2, end.getId());
            
            if (pstmt.executeUpdate() == 1) {
                return true;
            }
            return false;
            
        } catch(SQLException e) {
            System.out.println(LOG+" "+e.getMessage());
            return false;
        }
    }
    
    public static void main (String[] args) {
        EnderecoDAO edao = new EnderecoDAO();
        System.out.println(edao.selecionaEndereco(1));
    }
}
