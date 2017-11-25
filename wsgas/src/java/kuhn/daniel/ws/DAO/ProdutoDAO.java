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
import kuhn.daniel.ws.entity.Produto;
import kuhn.daniel.ws.persistence.Conexao;

/**
 *
 * @author dani
 */
public class ProdutoDAO implements Serializable{

    private static final Logger LOG = Logger.getLogger(ProdutoDAO.class.getName());
 
    public Integer adicionarProduto(Produto p) {
        
        Connection conn = Conexao.getConnection();
        
        try {
            PreparedStatement pstmt = conn
                .prepareStatement(
                    "INSERT INTO produto (titulo, descricao, preco) VALUES (?, ?, ?)"
                ,Statement.RETURN_GENERATED_KEYS);
           
            pstmt.setString(1, p.getTitulo());
            pstmt.setString(2, p.getDescricao());
            pstmt.setString(3, p.getPreco());
            
            pstmt.executeUpdate();
            
            Integer produto_id = null;
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                produto_id = rs.getInt(1);
            }
            
            pstmt.close();
            return produto_id;
            
        } catch(SQLException ex) {
            System.out.println(LOG+" "+ex.getMessage());
            return null;
        }
    }
    
    
    public Produto selecionaProduto(Integer id) {
    
        Connection conn = Conexao.getConnection();
        
        String sql = "SELECT * FROM produto p WHERE p.id = ?";
        
        try 
        {   
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            
            ResultSet rs = pstmt.executeQuery();

            Produto p = new Produto();
            
            while (rs.next()) 
            {    
                p.setTitulo(rs.getString("titulo"));
                p.setPreco(rs.getString("preco"));
                p.setDescricao(rs.getString("descricao"));
            }
            
            return p;
            
        } catch(SQLException e) {
            System.out.println(LOG+" "+e.getMessage());
            return null;
        }
    }
    
    
    
    public Integer produtoExiste(String titulo, Connection conn) 
    {
        String sql = 
                   " SELECT * " +
                   "   FROM produtos_servicos ps " +
                   "  WHERE ps.titulo = ' "+titulo+" '";

        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) 
            {
                return rs.getInt("id");
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    
    public List<Produto> selecionaProdutos() {
    
        Connection conn = Conexao.getConnection();
        
        String sql = "SELECT * FROM produto";
        
        try 
        {   
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            List<Produto> produtos = new ArrayList<>();
            
            while (rs.next()) 
            {    
                Produto p = new Produto();

                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setDescricao(rs.getString("descricao"));
                p.setPreco(rs.getString("preco"));
                
                produtos.add(p);
            }
            
            return produtos;
            
        } catch(SQLException e) {
            System.out.println(LOG+" "+e.getMessage());
            return null;
        }
    }
    
    
    public static void main (String[] args) {
//        ProdutoDAO pdao = new ProdutoDAO();
//        
//        Produto p = new Produto(null, null, "Gás 01", "Gás do bão", "65", "timestamp");
//        
//        pdao.adicionarProduto(p);
    }
}
