/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuhn.daniel.ws.entity;

import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author dani
 */
public class Usuario {

    private static final Logger LOG = Logger.getLogger(Usuario.class.getName());
 
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private List<Endereco> enderecos;
    private String sessao;

    public Usuario() {
        
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(String nome, String email, String senha, String sessao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.sessao = sessao;
    }

    public Usuario(Integer id, String nome, String email, String senha, List<Endereco> enderecos, String sessao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.enderecos = enderecos;
        this.sessao = sessao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }
    

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", enderecos=" + enderecos + '}';
    }
}
