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
import kuhn.daniel.ws.entity.Pedido;
import kuhn.daniel.ws.persistence.Conexao;
import kuhn.daniel.ws.util.Timestamp;

/**
 *
 * @author dani
 */
public class PedidoDAO implements Serializable{

    private static final Logger LOG = Logger.getLogger(PedidoDAO.class.getName());

    public Integer adicionarPedido(Pedido p) {
        
        Connection conn = Conexao.getConnection();
        
        try {
            PreparedStatement pstmt = conn
                .prepareStatement(
                    "INSERT INTO pedido (produto_id, usuario_id, endereco_id, cep, bairro, rua, numero, complemento, cidade, timestamp, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                ,Statement.RETURN_GENERATED_KEYS);
            
            EnderecoDAO edao = new EnderecoDAO();
            
            Endereco end = edao.selecionaEndereco(p.getEndereco().getId());

            pstmt.setInt(1, p.getProduto().getId());
            pstmt.setInt(2, p.getUsuario().getId());
            pstmt.setInt(3, p.getEndereco().getId());
            pstmt.setInt(4, end.getCep());
            pstmt.setString(5, end.getBairro());
            pstmt.setString(6, end.getRua());
            pstmt.setString(7, end.getNumero());
            pstmt.setString(8, end.getComplemento());
            pstmt.setString(9, end.getCidade());
            pstmt.setString(10, Timestamp.timestamp());
            pstmt.setString(11, "0");
            
            pstmt.executeUpdate();
            
            Integer pedido_id = null;
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                pedido_id = rs.getInt(1);
            }
            
            pstmt.close();
            return pedido_id;
            
        } catch(SQLException ex) {
            System.out.println(LOG+" "+ex.getMessage());
            return null;
        }
    }
    
    
    public List<Pedido> selecionaPedidos(Integer id) {
    
        Connection conn = Conexao.getConnection();
        
        String sql = " SELECT p.id, p.produto_id, p.cep, p.bairro, p.rua, p.numero, p.complemento, p.status, p.cidade "
                   + "   FROM pedido p "
                   + "  WHERE p.usuario_id = ? ORDER BY p.id DESC";

            try {
            PreparedStatement pstmt = conn
                .prepareStatement(sql);
            
            pstmt.setInt(1, id);
            
            
            ResultSet rs = pstmt.executeQuery();
            
            ProdutoDAO pdao = new ProdutoDAO();

            List<Pedido> pedidos = new ArrayList<>();
            String endereco;
            while (rs.next()) 
            {    
                Pedido p = new Pedido();
                endereco = "";
                if (rs.getString("rua")!= null) {
                    endereco += rs.getString("rua")+" ";
                }
                if (rs.getString("bairro")!= null) {
                    endereco += " - "+rs.getString("bairro")+" ";
                }
                
                if (rs.getString("complemento")!= null) {
                    endereco += " - "+rs.getString("complemento")+ " ";
                }
                
                if (rs.getString("cidade")!= null) {
                    endereco += " - "+rs.getString("cidade");
                }

                p.setId(rs.getInt("id"));
                p.setStrendereco(endereco);
                p.setProduto(pdao.selecionaProduto(rs.getInt("produto_id")));
                p.setStatus(rs.getString("status"));
                
                pedidos.add(p);
            }
            
            return pedidos;
            
        } catch(SQLException e) {
            System.out.println(LOG+" "+e.getMessage());
            return null;
        }
    }
    
    
    public boolean excluirPedido(Pedido p) 
    {
        Connection conn = Conexao.getConnection();
        
        try {
            PreparedStatement pstmt = conn
                .prepareStatement("DELETE FROM pedido WHERE id = ?");
            
            pstmt.setInt(1, p.getId());
            
            pstmt.executeUpdate();
            
            pstmt.close();
            return true;
            
        } catch(SQLException ex) {
            System.out.println(LOG+" "+ex.getMessage());
            return false;
        }
    }
//    
//    public static void main (String[] args) 
//    {
//        PedidoDAO pdao = new PedidoDAO();
//        
//        Endereco e = new Endereco(38, "Ibirubá", new Usuario(1), 98200000, "Planalto", "Serafim Fagundes", "1544", null, "timestamp");
//
//        Pedido p = new Pedido(null, new Usuario(1), new Produto(2), e, "timestamp", "timestamp");
//        
//       // pdao.adicionarPedido(p);
//        System.out.println(pdao.selecionaPedidos(1));
//    }
}
