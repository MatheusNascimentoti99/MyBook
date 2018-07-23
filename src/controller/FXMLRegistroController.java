/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.stage.Stage;
import view.AppView;

/**
 * FXML Controller class
 *
 * @author Matheus Nascimento
 */
public class FXMLRegistroController implements Initializable {

    private final String styleError = "-fx-background-color: #EEEE00;";
    private final String styleAcept = "-fx-background-color: #FFFFFF;";
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
        //Altera a cena para o login
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLLogin.fxml"));
        Scene cenaPerfil = new Scene(root);

        Stage palco = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        palco.setScene(cenaPerfil);

    }

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

        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        boolean registroOk = true;
        String nome = null;
        String email = null;
        String endereco = null;
        String telefone = null;
        String senha = null;
        String userName = null;
        String dataNasc = null;

        /*
                    implementação futura:
                    Colocar mais tratamentos de erros, como na data, nome e telefone.
                    séria bom também poder alterar a cor dos campos de texto, até agora não sei como fazer isso
         */
        if (checkRegistro(txtUserName, 1, registroOk)) {
            userName = txtUserName.getText();
            txtUserName.setStyle(styleAcept);
        } else {
            registroOk = false;
            txtUserName.setStyle(styleError);

        }
        if (checkRegistro(txtEmail, 5, registroOk)) {
            email = txtEmail.getText();
            txtEmail.setStyle(styleAcept);
        } else {
            registroOk = false;
            txtEmail.setStyle(styleError);

        }
        if (checkRegistro(txtEndereco, 5, registroOk)) {
            endereco = txtEndereco.getText();
            txtEndereco.setStyle(styleAcept);
        } else {
            registroOk = false;
            txtEndereco.setStyle(styleError);

        }
        if (checkRegistro(txtTelefone, 8, registroOk)) {
            telefone = txtTelefone.getText();
            txtTelefone.setStyle(styleAcept);
        } else {
            registroOk = false;
            txtTelefone.setStyle(styleError);

        }
        if (checkSenha(passSenha, 8, registroOk)) {
            senha = passSenha.getText();
            passSenha.setStyle(styleAcept);
        } else {
            registroOk = false;
            passSenha.setStyle(styleError);
        }
        if (checkRegistro(txtDataNascimento, 8, registroOk)) {
            dataNasc = txtDataNascimento.getText();
            txtDataNascimento.setStyle(styleAcept);
        } else {
            registroOk = false;
            txtDataNascimento.setStyle(styleError);
        }

        if (checkRegistro(txtNomeCompleto, 5, registroOk)) {
            nome = txtNomeCompleto.getText();
            txtNomeCompleto.setStyle(styleAcept);
        } else {
            registroOk = false;
            txtNomeCompleto.setStyle(styleError);
        }

        if (registroOk) {
            if (AppView.getControlUser().addUser(nome, email, senha, telefone, dataNasc, endereco, userName)) {
                try {
                    AppView.getControlUser().saveRegisters();
                    Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLLogin.fxml"));
                    Scene cenaPerfil = new Scene(root);
                    Stage palco = (Stage) ((Node) evento.getSource()).getScene().getWindow();
                    palco.setScene(cenaPerfil);
                    palco.show();
                } catch (Exception ex) {
                }

            } else {
                txtUserName.setStyle(styleError);
            }
        }
    }

}
