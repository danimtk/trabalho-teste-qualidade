/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuhn.daniel.ws.entity;

/**
 *
 * @author dani
 */
public class Pedido {
    public Integer id;
    public Usuario usuario;
    public Produto produto;
    public Endereco endereco;
    public String strendereco;
    public String dt_pedido;
    public String dt_entrega;
    public String status;

    public Pedido() {
    }

    public Pedido(Integer id) {
        this.id = id;
    }

    public Pedido(Integer id, Usuario usuario, Produto produto, Endereco endereco, String dt_pedido, String dt_entrega) {
        this.id = id;
        this.usuario = usuario;
        this.produto = produto;
        this.endereco = endereco;
        this.dt_pedido = dt_pedido;
        this.dt_entrega = dt_entrega;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getStrendereco() {
        return strendereco;
    }

    public void setStrendereco(String strendereco) {
        this.strendereco = strendereco;
    }

    public String getDt_pedido() {
        return dt_pedido;
    }

    public void setDt_pedido(String dt_pedido) {
        this.dt_pedido = dt_pedido;
    }

    public String getDt_entrega() {
        return dt_entrega;
    }

    public void setDt_entrega(String dt_entrega) {
        this.dt_entrega = dt_entrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
   
    

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", produto=" + produto + ", endereco=" + endereco + ", dt_pedido=" + dt_pedido + ", dt_entrega=" + dt_entrega + '}';
    }
}
