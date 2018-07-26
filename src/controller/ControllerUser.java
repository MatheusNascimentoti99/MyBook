/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.util.Iterator;
import model.Amizade;
import model.User;
import util.Vertex;
import util.Graph;

/**
 * A classe <b>ControllerUser</b> faz o gerenciamento dos usuários. Manipulando o grafo e gerenciando o usuário que está conectado.
 *
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 */
public class ControllerUser {

    private User loginCorrent;
    private Graph grafoUsers;
    private User perfilCorrent;

    /**
     *
     */
    public ControllerUser() {
        this.grafoUsers = new Graph();
    }

    /**
     *
     * @return Retorna o grafo que contém todos os usuários cadastrados no sistema.
     */
    public Graph getGrafoUsers() {
        return grafoUsers;
    }

    /**
     *Pega o usuário que está atualmente com o perfil aberto.
     * @return Retorna o usuário que está sendo visitado.
     * 
     */
    public User getPerfilCorrent() {
        return perfilCorrent;
    }

    /**
     *
     * @param perfilCorrent Novo usuário a ser visitado.
     */
    public void setPerfilCorrent(User perfilCorrent) {
        this.perfilCorrent = perfilCorrent;
    }

    /**
     *<b>checkRegistred</b> Verifica se o novo usuário já está registrado no sistema.
     * @param novo Novo usuário a ser registrado.
     * @return Retorna <i>true</i> se o usuário já está registrado, caso contrário retorna <i>false</i>.
     */
    public boolean checkRegistred(User novo) {
        try {
            return grafoUsers.getVertices().contains(novo);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     *
     * @return Retorna o usuário que está acessando o MyBook atualmente.
     */
    public User getLoginCorrent() {
        return loginCorrent;
    }

    /**
     *
     * @param loginCorrent Altera o usuário que acessará o MyBook.
     */
    public void setLoginCorrent(User loginCorrent) {
        this.loginCorrent = loginCorrent;
    }

    /**
     *Alterar foto do perfil do usuário que está acessando o MyBook.
     * @param local Caminho para acessar a nova foto do usuário.
     */
    public void alterarFoto(String local) {
        loginCorrent.setDirFoto(local);
    }

    /**
     *
     * @param login
     * @param senha
     * @return
     */
    public boolean checkLogin(String login, String senha) {
        try {
            Iterator iterador = grafoUsers.vertices();
            while (iterador.hasNext()) {
                Vertex entry = (Vertex) iterador.next();
                User user = (User) entry.getValue();
                if (user.getLogin().equals(login)) {
                    loginCorrent = user;
                    return user.getPassword().equals(senha);
                }
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    /**
     *
     * @param login
     * @return
     */
    public User verificaLogin(String login) {
        Iterator iterador = grafoUsers.vertices();
        while (iterador.hasNext()) {
            Vertex entry = (Vertex) iterador.next();
            User user = (User) entry.getValue();
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    /**
     *
     * @throws Exception
     */
    public void saveRegisters() throws Exception {
        ControllerFile.save(grafoUsers, "resources/registros.data");
    }

    /**
     *
     * @param grafoUsers
     */
    public void setGrafoUsers(Graph grafoUsers) {
        this.grafoUsers = grafoUsers;
    }

    /**
     *
     * @return
     */
    public Graph readRegisters() {
        Graph temp;
        try {
            temp = (Graph) ControllerFile.readDate("resources/registros.data");
        } catch (FileNotFoundException e) {
            temp = null;
        }
        if (temp == null) {
            return new Graph();
        }
        return temp;
    }

    /**
     *
     * @param user1
     * @param user2
     * @return
     */
    public boolean addAmizade(User user1, User user2) {
        if (user1 instanceof User && user2 instanceof User) {
            if (grafoUsers.getEdge(grafoUsers.getVertex(user1), grafoUsers.getVertex(user2)) == null) {
                Amizade nova = new Amizade(99);
                grafoUsers.addEdge(grafoUsers.getVertex(user1), grafoUsers.getVertex(user2), nova);
                grafoUsers.addEdge(grafoUsers.getVertex(user2), grafoUsers.getVertex(user1), nova);
                user1.getSolicitacoes().remove(user2);
                return true;
            }
        }
        return true;
    }

    /**
     *
     * @param nome
     * @param email
     * @param password
     * @param telefone
     * @param dataNasc
     * @param endereco
     * @param login
     * @return
     */
    public boolean addUser(String nome, String email, String password, String telefone,
            String dataNasc, String endereco, String login) {
        User novo = new User(nome, email, password, telefone, dataNasc, endereco, login);

        if (!checkRegistred(novo)) {
            grafoUsers.addVertex(novo);
            return true;
        } else {
            return false;
        }

    }

    /**
     *
     * @return
     */
    public Iterator showUsers() {
        return grafoUsers.vertices();
    }

    /**
     *
     * @param origem
     * @param destino
     */
    public void solicitacao(User origem, User destino) {
        destino.getSolicitacoes().add(origem);
    }
}
