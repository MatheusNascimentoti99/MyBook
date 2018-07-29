
package controller;

import java.io.FileNotFoundException;
import java.util.Iterator;
import model.Amizade;
import model.User;
import util.Vertex;
import util.Graph;

/**
 * A classe <b>ControllerUser</b> gerência os usuários. Manipulando o grafo e gerenciando o usuário que está conectado.
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
     *Altera o perfil a ser vísitado.
     * @param perfilCorrent Novo usuário a ser visitado.
     */
    public void setPerfilCorrent(User perfilCorrent) {
        this.perfilCorrent = perfilCorrent;
    }

    /**
     *Verifica se o novo usuário já está registrado no sistema.
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
     *Pega o usuário que está atualmente acessando o MyBook.
     * @return Retorna o usuário que está acessando o MyBook atualmente.
     */
    public User getLoginCorrent() {
        return loginCorrent;
    }

    /**
     *Altera o usuário que está atualmente acessando o MyBook.
     * @param loginCorrent Altera o usuário que acessará o MyBook.
     */
    public void setLoginCorrent(User loginCorrent) {
        this.loginCorrent = loginCorrent;
    }

    /**
     * Método que altera foto do perfil de um usuário.
     * @param local local da foto.
     */
    public void alterarFoto(String local) {
        loginCorrent.setDirFoto(local);
    }

    /**
     *Verifica se o login e senha do usuário estão corretos, método utilizado para validar acesso do usuário ao sistema.
     * @param login O Username do usuário.
     * @param senha A senha do usuário.
     * @return Retorna <i>true</i> se o usuário existir e se a senhar estiver correta, retorna <i>false</i> se o usuário não existir ou a senha estiver incorreta.
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
     *Salva o <i>Grafo de registros</i> em disco.
     * @throws Exception Objeto corrompido, problemas na leitura do arquivo, etc. 
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
     */
    public void addAmizade(User user1, User user2) {
        if (user1 instanceof User && user2 instanceof User) {
            if (grafoUsers.getEdge(grafoUsers.getVertex(user1), grafoUsers.getVertex(user2)) == null) {
                Amizade nova = new Amizade(99);
                grafoUsers.addEdge(grafoUsers.getVertex(user1), grafoUsers.getVertex(user2), nova);
                grafoUsers.addEdge(grafoUsers.getVertex(user2), grafoUsers.getVertex(user1), nova);
                user1.getSolicitacoes().remove(user2);
            }
        }
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

}
