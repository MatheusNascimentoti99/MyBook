
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
     *Construtor da classe  <b>ControllerUser</b> .
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
     * Método que designa o nome usuário que está acessando o Mybook.
     * @param loginCorrent usuário.
     */
    public void setLoginCorrent(User loginCorrent) {
        this.loginCorrent = loginCorrent;
    }

    /**
     *
     * @param local Altera o usuário que acessará o MyBook.
     */
    public void alterarFoto(String local) {
        loginCorrent.setDirFoto(local);
    }

    /**
     * Método que verifica o login e senha de um usuário, ou seja, se ele está cadastradp no sistema, para adentrar ao aplicativo.
     * @param login login do usuário.
     * @param senha senha do usuário.
     * @return true, caso ele esteja cadastrado, e false, caso não.
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
     * Método que verifica apenas o login de usuário.
     * @param login login do usuário.
     * @return usuário a qual pertence o login.
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
     * Método que salva os registros dos usuários.
     * @throws Exception
     */
    public void saveRegisters() throws Exception {
        ControllerFile.save(grafoUsers, "resources/registros.data");
    }

    /**
     * Método que designa um novo grafo para o grafo de usuários.
     * @param grafoUsers novo grafo.
     */
    public void setGrafoUsers(Graph grafoUsers) {
        this.grafoUsers = grafoUsers;
    }

    /**
     * Método que lê os registros dos usuários.
     * @return grafo dos usuários.
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
     * Método que adiciona uma amizade entre dois usuários.
     * @param user1 Usuário.
     * @param user2 Usuário.
     * @return true.
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
     * Método que adiciona um novo usuário ao sistema e ao grafo.
     * @param nome nome do usuário.
     * @param email email do usuário.
     * @param password senha do usuário.
     * @param telefone telefone do usuário.
     * @param dataNasc data de nascimento do usuário.
     * @param endereco endereço do usuário.
     * @param login login do usuário.
     * @return true, caso o usuário foi registrado, e false, caso não tenha sido.
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
     * Método que retorna o iterator contendo todos os usuários registrados do grafo.
     * @return iterator de todos os vertices.
     */
    public Iterator showUsers() {
        return grafoUsers.vertices();
    }

    /**
     * Método que adiciona uma solicitação de amizade entre dois usuários.
     * @param origem usuário que enviou a solicitação.
     * @param destino usuário que receberá a solicitação.
     */
    public void solicitacao(User origem, User destino) {
        destino.getSolicitacoes().add(origem);
    }
}