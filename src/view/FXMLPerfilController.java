/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerUser;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import model.User;
import util.Edge;
import util.Vertex;

/**
 * FXML Controller class
 *
 * @author Matheus Nascimento
 */
public class FXMLPerfilController implements Initializable {

    ControllerUser controlUser;
    /**
     * Initializes the controller class.
     */

    @FXML
    private Label lblNome;
    @FXML
    private Label lblFoto;
    @FXML
    private ListView listAmigos;
    @FXML
    private Pane pnFundo;
    @FXML
    private Button btnSobre;
    @FXML
    private Pane pnSobre;
    @FXML
    private Button btnVoltar;

    @FXML
    private Label txtNome;
    @FXML
    private Label txtUsername;
    @FXML
    private Label txtEmail;
    @FXML
    private Label txtDataNascimento;
    @FXML
    private Label txtTelefone;
    @FXML
    private Label txtEndereco;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        lblNome.setText(AppView.getControlUser().loginCorrent().getLogin());
        Vertex atual = AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().loginCorrent());

        for (int i = 0; i < atual.getArestas().size(); i++) {
            User amigo = (User) ((Edge) atual.getArestas().get(i)).getPre().getValue();
            Label amigos = new Label(amigo.getLogin());
            listAmigos.getItems().add(amigos);
        }

    }

    @FXML
    public void sobre(Event evento) {
        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        pnFundo.setVisible(false);
        txtNome.setText(AppView.getControlUser().loginCorrent().getNome());
        txtUsername.setText(AppView.getControlUser().loginCorrent().getLogin());
        txtEmail.setText(AppView.getControlUser().loginCorrent().getEmail());
        txtTelefone.setText(AppView.getControlUser().loginCorrent().getTelefone());
        txtEndereco.setText(AppView.getControlUser().loginCorrent().getEndereco());
        txtDataNascimento.setText(AppView.getControlUser().loginCorrent().getDataNasc());
        pnSobre.setVisible(true);

    }

    @FXML
    public void voltar(Event evento) {
        pnSobre.setVisible(false);
        pnFundo.setVisible(true);
    }

    @FXML
    public void abrir(ActionEvent evento) {

    }

}
