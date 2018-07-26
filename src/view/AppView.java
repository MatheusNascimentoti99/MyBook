/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class AppView extends Application {

    private static ControllerUser controlUser;

    public static ControllerUser getControlUser() {
        return controlUser;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //A primeira cena é o ceneLogin
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        
        Scene cena = new Scene(root);
        primaryStage.setScene(cena);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public AppView() {
        controlUser = new ControllerUser();
        
    }

}
