
package controller;

import java.io.FileNotFoundException;
import java.util.Iterator;
import model.Amizade;
import model.User;
import util.Vertex;
import util.Graph;
/**
 * A classe <b>ControllerUser</b> faz o gerenciamento dos usuários do aplicativo.
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
     * Método que retorna o grafo que contém todos os usuários.
     * @return grafo dos usuários.
     */
    public Graph getGrafoUsers() {
        return grafoUsers;
    }

    /**
     * Método que retorna o usuário do perfil corrente do programa.
     * @return usuário do perfil corrente.
     */
    public User getPerfilCorrent() {
        return perfilCorrent;
    }

    /**
     * Método que designa um novo usuário para o perfil corrente.
     * @param perfilCorrent novo Usuário.
     */
    public void setPerfilCorrent(User perfilCorrent) {
        this.perfilCorrent = perfilCorrent;
    }

    /**
     * Método que verifica se o usuário já está registrado.
     * @param novo Usuário a ser conferido.
     * @return true, caso esteja, e false, caso não.
     */
    public boolean checkRegistred(User novo) {
        try {
            return grafoUsers.getVertices().contains(novo);
        } catch (NullPointerException e) {
            return false;
        }
    }
    
    /**
     * Método que retorna o usuário que está utilizando a plataforma.
     * @return usuário que fez login.
     */
    public User getLoginCorrent() {
        return loginCorrent;
    }

    /**
     * Método que designa um novo usuário para o usuário que está "logado".
     * @param loginCorrent Usuário.
     */
    public void setLoginCorrent(User loginCorrent) {
        this.loginCorrent = loginCorrent;
    }

    /**
     * Método que altera a foto do perfil de um usuário.
     * @param local endereço da imagem.
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
