/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Matheus Nascimento
 */
public class FXMLRegistroController implements Initializable {

    @FXML
    TextField txtUserName;
    @FXML
    PasswordField passSenha;
    @FXML
    TextField txtEndereco;
    @FXML
    TextField txtTelefone;
    @FXML
    TextField txtDataNascimento;
    @FXML
    TextField txtNomeCompleto;
    @FXML
    TextField txtEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void cancelar(Event evento) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        Scene cenaPerfil = new Scene(root);

        Stage palco = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        palco.setScene(cenaPerfil);

    }

    @FXML
    private boolean checkRegistro(TextField c, int valor, boolean registroOk) {
        if (c.getText().length() < valor) {
            registroOk = false;
            return false;
        }
        return true;
    }

    public boolean checkSenha(PasswordField senha, int valor, boolean registroOk) {
        if (senha.getText().length() < valor) {
            registroOk = false;
            return false;
        }
        return true;
    }

    @FXML
    public void registrar(Event evento) throws IOException {
        /*
        
        Falta pegar dados do campo de texto e criar um novo usuário.
         */
        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        boolean registroOk = true;
        String nome = null;
        String email = null;
        String endereco = null;
        String telefone = null;
        String senha = null;
        String userName = null;
        String dataNasc = null;
        if (checkRegistro(txtUserName, 1, registroOk)) {
            userName = txtUserName.getText();
            txtUserName.setStyle(nome);
        } else {
            registroOk = false;

        }
        if (checkRegistro(txtEmail, 1, registroOk)) {
            email = txtEmail.getText();
        } else {
            registroOk = false;

        }
        if (checkRegistro(txtEndereco, 1, registroOk)) {
            endereco = txtEndereco.getText();
        } else {
            registroOk = false;

        }
        if (checkRegistro(txtTelefone, 8, registroOk)) {
            telefone = txtTelefone.getText();
        } else {
            registroOk = false;

        }
        if (checkSenha(passSenha, 8, registroOk)) {
            senha = passSenha.getText();
        } else {
            registroOk = false;
        }
        if (checkRegistro(txtDataNascimento, 1, registroOk)) {
            dataNasc = txtDataNascimento.getText();
        } else {
            registroOk = false;
        }

        if (checkRegistro(txtNomeCompleto, 1, registroOk)) {
            nome = txtNomeCompleto.getText();
        } else {
            registroOk = false;
        }

        if (registroOk) {
            if (AppView.getControlUser().addUser(nome, email, senha, telefone, dataNasc, endereco, userName)) {
                try {
                    AppView.getControlUser().saveRegisters();
                    Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
                    Scene cenaPerfil = new Scene(root);
                    Stage palco = (Stage) ((Node) evento.getSource()).getScene().getWindow();
                    palco.setScene(cenaPerfil);
                    palco.show();
                } catch (Exception ex) {
                }

            }
        }
    }

}
