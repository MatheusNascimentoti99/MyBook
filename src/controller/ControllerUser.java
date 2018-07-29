
package controller;

import java.io.FileNotFoundException;
import java.util.Iterator;
import model.Amizade;
import model.User;
import util.Vertex;
import util.Graph;

/**
 * A classe <b>ControllerUser</b> ger�ncia os usu�rios. Manipulando o grafo e gerenciando o usu�rio que est� conectado.
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
     *Altera o perfil a ser v�sitado.
     * @param perfilCorrent Novo usu�rio a ser visitado.
     */
    public void setPerfilCorrent(User perfilCorrent) {
        this.perfilCorrent = perfilCorrent;
    }

    /**
     *Verifica se o novo usu�rio j� est� registrado no sistema.
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
     *Pega o usu�rio que est� atualmente acessando o MyBook.
     * @return Retorna o usu�rio que est� acessando o MyBook atualmente.
     */
    public User getLoginCorrent() {
        return loginCorrent;
    }

    /**
     *Altera o usu�rio que est� atualmente acessando o MyBook.
     * @param loginCorrent Altera o usu�rio que acessar� o MyBook.
     */
    public void setLoginCorrent(User loginCorrent) {
        this.loginCorrent = loginCorrent;
    }

    /**
     * M�todo que altera foto do perfil de um usu�rio.
     * @param local local da foto.
     */
    public void alterarFoto(String local) {
        loginCorrent.setDirFoto(local);
    }

    /**
     *Verifica se o login e senha do usu�rio est�o corretos, m�todo utilizado para validar acesso do usu�rio ao sistema.
     * @param login O Username do usu�rio.
     * @param senha A senha do usu�rio.
     * @return Retorna <i>true</i> se o usu�rio existir e se a senhar estiver correta, retorna <i>false</i> se o usu�rio n�o existir ou a senha estiver incorreta.
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

}
