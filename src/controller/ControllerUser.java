
package controller;

import java.io.FileNotFoundException;
import java.util.Iterator;
import model.Amizade;
import model.User;
import util.Vertex;
import util.Graph;

/**
 * A classe <b>ControllerUser</b> faz o gerenciamento dos usu�rios. Manipulando o grafo e gerenciando o usu�rio que est� conectado.
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
     * @return Retorna o grafo que cont�m todos os usu�rios cadastrados no sistema.
     */
    public Graph getGrafoUsers() {
        return grafoUsers;
    }
    /**
     *Pega o usu�rio que est� atualmente com o perfil aberto.
     * @return Retorna o usu�rio que est� sendo visitado.
     * 
     */
    public User getPerfilCorrent() {
        return perfilCorrent;
    }

     /**
     *
     * @param perfilCorrent Novo usu�rio a ser visitado.
     */
    public void setPerfilCorrent(User perfilCorrent) {
        this.perfilCorrent = perfilCorrent;
    }
 /**
     *<b>checkRegistred</b> Verifica se o novo usu�rio j� est� registrado no sistema.
     * @param novo Novo usu�rio a ser registrado.
     * @return Retorna <i>true</i> se o usu�rio j� est� registrado, caso contr�rio retorna <i>false</i>.
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
     * @return Retorna o usu�rio que est� acessando o MyBook atualmente.
     */
    public User getLoginCorrent() {
        return loginCorrent;
    }

    /**
     * M�todo que designa o nome usu�rio que est� acessando o Mybook.
     * @param loginCorrent usu�rio.
     */
    public void setLoginCorrent(User loginCorrent) {
        this.loginCorrent = loginCorrent;
    }

    /**
     *
     * @param local Altera o usu�rio que acessar� o MyBook.
     */
    public void alterarFoto(String local) {
        loginCorrent.setDirFoto(local);
    }

    /**
     * M�todo que verifica o login e senha de um usu�rio, ou seja, se ele est� cadastradp no sistema, para adentrar ao aplicativo.
     * @param login login do usu�rio.
     * @param senha senha do usu�rio.
     * @return true, caso ele esteja cadastrado, e false, caso n�o.
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
     * M�todo que verifica apenas o login de usu�rio.
     * @param login login do usu�rio.
     * @return usu�rio a qual pertence o login.
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
     * M�todo que salva os registros dos usu�rios.
     * @throws Exception
     */
    public void saveRegisters() throws Exception {
        ControllerFile.save(grafoUsers, "resources/registros.data");
    }

    /**
     * M�todo que designa um novo grafo para o grafo de usu�rios.
     * @param grafoUsers novo grafo.
     */
    public void setGrafoUsers(Graph grafoUsers) {
        this.grafoUsers = grafoUsers;
    }

    /**
     * M�todo que l� os registros dos usu�rios.
     * @return grafo dos usu�rios.
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
     * M�todo que adiciona uma amizade entre dois usu�rios.
     * @param user1 Usu�rio.
     * @param user2 Usu�rio.
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
     * M�todo que adiciona um novo usu�rio ao sistema e ao grafo.
     * @param nome nome do usu�rio.
     * @param email email do usu�rio.
     * @param password senha do usu�rio.
     * @param telefone telefone do usu�rio.
     * @param dataNasc data de nascimento do usu�rio.
     * @param endereco endere�o do usu�rio.
     * @param login login do usu�rio.
     * @return true, caso o usu�rio foi registrado, e false, caso n�o tenha sido.
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
     * M�todo que retorna o iterator contendo todos os usu�rios registrados do grafo.
     * @return iterator de todos os vertices.
     */
    public Iterator showUsers() {
        return grafoUsers.vertices();
    }

    /**
     * M�todo que adiciona uma solicita��o de amizade entre dois usu�rios.
     * @param origem usu�rio que enviou a solicita��o.
     * @param destino usu�rio que receber� a solicita��o.
     */
    public void solicitacao(User origem, User destino) {
        destino.getSolicitacoes().add(origem);
    }
}