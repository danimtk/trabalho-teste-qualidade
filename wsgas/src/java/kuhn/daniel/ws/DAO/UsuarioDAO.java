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
import java.util.logging.Logger;
import kuhn.daniel.ws.entity.Usuario;
import kuhn.daniel.ws.persistence.Conexao;
import kuhn.daniel.ws.util.Md5;
import kuhn.daniel.ws.util.Timestamp;

/**
 *
 * @author dani
 */
public class UsuarioDAO implements Serializable{

    private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());
    
    public Integer adicionarUsuario(Usuario u) 
    {
        Connection conn = Conexao.getConnection();
        
        try {
            PreparedStatement pstmt = conn
                .prepareStatement(
                    "INSERT INTO usuario (nome, email, senha, timestamp) VALUES (?, ?, ?, ?)"
                ,Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setString(1, u.getNome());
            pstmt.setString(2, u.getEmail());
            pstmt.setString(3, u.getSenha());
            pstmt.setString(4, Timestamp.timestamp());
            
            pstmt.executeUpdate();
            
            Integer produto_id = null;
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                produto_id = rs.getInt(1);
            }
            
            pstmt.close();
            return produto_id;
            
        } catch(SQLException e) {
            System.out.println(LOG+" "+e.getMessage());
            return null;
        }
    }
    
    
    public Usuario selecionaUsuario(Integer id) {
    
        Connection conn = Conexao.getConnection();
        
        String sql = "SELECT * FROM usuario u WHERE u.id = ?";
        
        try 
        {   
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            
            ResultSet rs = pstmt.executeQuery();

            Usuario u = new Usuario();
            
            while (rs.next()) 
            {    
                u.setEmail(rs.getString("email"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
            }
            
            return u;
            
        } catch(SQLException e) {
            System.out.println(LOG+" "+e.getMessage());
            return null;
        }
    }
    
    
    public boolean emailDisponivel(String email) 
    {
        try{
            PreparedStatement pstmt = Conexao.getConnection()
                .prepareStatement("SELECT u.email FROM usuario u WHERE email = ?");

            pstmt.setString(1, email);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                return false;              
            }
            return true;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    public Usuario logar(String email, String senha)
    {
        try {
            PreparedStatement pstmt = Conexao.getConnection()
                .prepareStatement("SELECT * FROM usuario u WHERE u.email = ? AND u.senha = ?");

            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            
            ResultSet rs = pstmt.executeQuery();
            
            Usuario u = new Usuario();
            
            while(rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setSessao(criarSessao());
                return u;
            }

            return null;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Usuario validarSessao(String email, String senha)
    {
        try {
            PreparedStatement pstmt = Conexao.getConnection()
                .prepareStatement("SELECT * FROM usuario u WHERE u.sessao = ?");

            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            
            ResultSet rs = pstmt.executeQuery();
            
            Usuario u = new Usuario();
            
            while(rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setSessao(rs.getString("sessao"));
                return u;
            }

            return null;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    public String criarSessao() {
        Connection conn = Conexao.getConnection();
        
        String sessao = Md5.hash(Timestamp.timestamp());
        
        try {
            PreparedStatement pstmt = conn
                .prepareStatement(
                    "UPDATE usuario SET sessao= ?");
            
            pstmt.setString(1, Md5.hash(Timestamp.timestamp()));
            
            pstmt.executeUpdate();
            
            return sessao;
            
        } catch(SQLException e) {
            System.out.println(LOG+" "+e.getMessage());
            return null;
        }
    }
    
    
    public static void main (String[] args) {
        
        UsuarioDAO udao = new UsuarioDAO();
        Usuario u = udao.logar("danielmatheuskuhn@gmail.com", "123");
        
        System.out.println("res: "+u);
//        udao.adicionarUsuario(new Usuario("Daniel", "danielmatheuskuhn@gmail.com", "123"));
    }
}


