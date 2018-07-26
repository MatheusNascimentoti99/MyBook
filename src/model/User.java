/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

/**
     *
     * A classe <b>User</b> é utilizada para gravar os dados de um usuário.
     *
     * @author Matheus Nascimento e Elvis Serafim
     * @since Jul 2018
     * @version 1.0
     */
public class User implements Serializable{
    private  String nome;
    private  String email;
    private  String password;
    private  String telefone;
    private  String dataNasc;
    private  String endereco;
    private  String login;
    private  LinkedList solicitacoes;
    private String dirFoto;
    private LinkedList postagens;

    public String getDirFoto() {
        return dirFoto;
    }

    public void setDirFoto(String dirFoto) {
        this.dirFoto = dirFoto;
    }
  
    
    public User(String nome, String email, String password, String telefone, String dataNasc, 
            String endereco, String login) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.telefone = telefone;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.login = login;
        solicitacoes = new LinkedList();
        postagens = new LinkedList();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LinkedList getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(LinkedList solicitacoes) {
        this.solicitacoes = solicitacoes;
    }
    
    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
        return hashCode() == obj.hashCode();
        }
        return false;
    }

    public LinkedList getPostagens() {
        return postagens;
    }

    public void setPostagens(LinkedList postagens) {
        this.postagens = postagens;
    }
    
    
}
