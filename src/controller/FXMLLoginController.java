/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.ControllerUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import view.AppView;

/**
 *
 * A classe <b>FXMLLoginController</b> gerência o arquivo FXMLLogin.
 *
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField textoEntrada;
    @FXML
    private PasswordField textoSenhaEntrada;

    @FXML
    private Label lblErroLogin;
  

    /**
     *Realiza o login do usuário.
     * @param event Click Mouse ou Pressionar <i>Enter</i>.
     * @throws IOException Ler grafo salvo em disco e ler o arquivo FXML.
     */
    @FXML
    public void doLogin(Event event) throws IOException {
        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        String login = textoEntrada.getText();
        String senha = String.valueOf(textoSenhaEntrada.getText());
        if (AppView.getControlUser().checkLogin(login, senha)) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLPerfil.fxml"));
            Scene cenaPerfil = new Scene(root);
            Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
            palco.setScene(cenaPerfil);
        } else {

            lblErroLogin.setText("Nome do usuário ou senha está incorreto");
        }
        try {
            AppView.getControlUser().saveRegisters();
        } catch (Exception ex) {
        }

    }

    /**
     *Registra um novo usuário
     * @param event Click mouse.
     * @throws IOException Ler grafo salvo em disco e ler o arquivo FXML.
     */
    @FXML
    public void registrar(Event event) throws IOException {
        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLRegistro.fxml"));
        Scene cenaPerfil = new Scene(root);

        Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
        palco.setScene(cenaPerfil);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
