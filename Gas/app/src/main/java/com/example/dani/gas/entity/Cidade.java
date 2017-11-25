/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dani.gas.entity;

/**
 *
 * @author dani
 */
public class Cidade {
    
    Integer id;
    Integer estado_id;
    String nome;

    public Cidade() {
    }

    public Cidade(Integer id) {
        this.id = id;
    }
    
    public Cidade(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cidade(Integer id, Integer estado_id, String nome) {
        this.id = id;
        this.estado_id = estado_id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cidade{" + "id=" + id + ", estado_id=" + estado_id + ", nome=" + nome + '}';
    }
    
}
