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

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

/**
 * FXML Controller class
 *
 * @author Matheus Nascimento
 */
public class FXMLLoginController implements Initializable {

    ControllerUser controlUser;
    @FXML
    TextField textoEntrada;
    @FXML
    PasswordField textoSenhaEntrada;
    @FXML
    Button entrar;
    @FXML
    Label registrar;

    @FXML
    public void doLogin(ActionEvent event) throws IOException {
        controlUser.setGrafoUsers(controlUser.readRegisters());
                String login = textoEntrada.getText();
                String senha = String.valueOf(textoSenhaEntrada.getText());
                if (controlUser.checkLogin(login, senha)) {
                    User user = controlUser.verificaLogin(login);
                    Parent root = FXMLLoader.load(getClass().getResource("FXMLPerfil.fxml"));
                    Scene cenaPerfil = new Scene(root);
                    Stage palco = new Stage();
                    palco.setScene(cenaPerfil);
                } else {
                    System.out.println("Usuário ou senha incorretos, tente novamente");
                }
                try {
                    controlUser.saveRegisters();
                } catch (Exception ex) {
                }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlUser = new ControllerUser();
    }

}
