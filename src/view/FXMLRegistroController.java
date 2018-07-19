/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Matheus Nascimento
 */
public class FXMLRegistroController implements Initializable {

    /**
     * Initializes the controller class.
     */
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

}
