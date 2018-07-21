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
     *
     * @author Matheus Nascimento
     */
    public class ControllerUser {
        private User loginCorrent;
        private Graph grafoUsers;
        private User perfilCorrent;
        public ControllerUser() {
            this.grafoUsers = new Graph();
        }

        public Graph getGrafoUsers() {
            return grafoUsers;
        }

        public boolean checkRegistred(User novo) {
            try {
                return grafoUsers.getVertices().contains(novo);
            } catch (NullPointerException e) {
                return false;
            }
        }

    public User getLoginCorrent() {
        return loginCorrent;
    }

    public void setLoginCorrent(User loginCorrent) {
        this.loginCorrent = loginCorrent;
    }
        
        public void alterarFoto(String local){
            loginCorrent.setDirFoto(local);
        }

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

        public void saveRegisters() throws Exception {
            ControllerFile.save(grafoUsers, "resources/registros.txt");
        }

        public void setGrafoUsers(Graph grafoUsers) {
            this.grafoUsers = grafoUsers;
        }

        public Graph readRegisters() {
            Graph temp;
            try {
                temp = (Graph) ControllerFile.readDate("resources/registros.txt");
            } catch (FileNotFoundException e) {
                temp = null;
            }
            if (temp == null) {
                return new Graph();
            }
            return temp;
        }
        
        public boolean addAmizade(User user1, User user2) {
            if (user1 instanceof User && user2 instanceof User) {
                if (grafoUsers.getEdge(grafoUsers.getVertex(user1), grafoUsers.getVertex(user2)) == null) {
                    Amizade nova = new Amizade(99);
                    grafoUsers.addEdge(grafoUsers.getVertex(user1), grafoUsers.getVertex(user2), nova);
                    user1.getSolicitacoes().remove(user2);
                    return true;
                }
            }
            return true;
        }

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

        public Iterator showUsers() {
            return grafoUsers.vertices();
        }
        
        public void solicitacao(User origem, User destino){
               destino.getSolicitacoes().add(origem);
        }
    }
