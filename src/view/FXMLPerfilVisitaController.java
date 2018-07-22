/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;
import util.Edge;
import util.Vertex;

/**
 * FXML Controller class
 *
 * @author Matheus Nascimento
 */
public class FXMLPerfilVisitaController implements Initializable {

    @FXML
    private Button btnSobre;
    @FXML
    private Button btnArquivos;
    @FXML
    private Label lblLogout;
    @FXML
    private ImageView imvFoto;
    @FXML
    private Label lblNome;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private ListView ltvPostagens;
    @FXML
    private ListView ltvAmigos;
    @FXML
    private ImageView imvUserLogin;
    @FXML
    private Label lblUserLogin;
    @FXML
    private Button btnSolicitacao;
    @FXML
    private Pane pnPesquisa;
    @FXML
    private Pane pnFundo;
    @FXML
    private ListView ltvPesquisa;
    @FXML
    private Button btnVoltar;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        lblNome.setText(AppView.getControlUser().getPerfilCorrent().getLogin());
        lblUserLogin.setText(AppView.getControlUser().getLoginCorrent().getLogin());
        imvUserLogin.setImage(new Image(AppView.getControlUser().getLoginCorrent().getDirFoto()));
        Vertex atual = AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getPerfilCorrent());
        Image image;
        try {
            if (!AppView.getControlUser().getPerfilCorrent().getDirFoto().equals("")) {
                image = new Image(AppView.getControlUser().getPerfilCorrent().getDirFoto());
            } else {
                image = new Image("icon/Person.png");
            }
        } catch (RuntimeException exe) {
            image = new Image("icon/Person.png");
        }
        imvFoto.setImage(image);
        for (int i = 0; i < atual.getArestas().size(); i++) {
            User amigo = (User) ((Edge) atual.getArestas().get(i)).getPre().getValue();
            Label amigos = new Label(amigo.getLogin());
            amigos.setOnMouseClicked((Event event) -> {
                AppView.getControlUser().setPerfilCorrent((User) AppView.getControlUser().getGrafoUsers().getVertex(amigo).getValue());
            });
            ltvAmigos.getItems().add(amigos);

        }
        // TODO
    }
    @FXML
    public void logout(Event evento) {
        AppView.getControlUser().setLoginCorrent(null);
        try {
            AppView.getControlUser().saveRegisters();
        } catch (Exception ex) {
            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene cenaPerfil = new Scene(root);
        Stage palco = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        palco.setScene(cenaPerfil);
        palco.show();
    }
    @FXML
    public void pesquisar(ActionEvent evento) {
        pnFundo.setVisible(false);
        btnVoltar.setVisible(true);
        ltvPesquisa.getItems().clear();
        Iterator it = AppView.getControlUser().getGrafoUsers().vertices();
        while (it.hasNext()) {
            HBox perfilPesquisa = new HBox(5);
            Image foto;
            User user = (User) ((Vertex) it.next()).getValue();
            if (user.getNome().substring(0, txtPesquisa.getText().length()).equalsIgnoreCase(txtPesquisa.getText())) {
                try {
                    if (!user.getDirFoto().equals("")) {
                        foto = new Image(user.getDirFoto());
                    } else {
                        foto = new Image("icon/Person.png");
                    }
                } catch (RuntimeException exe) {
                    foto = new Image("icon/Person.png");
                }
                
                ImageView fotoNode = new ImageView(foto);
                fotoNode.setFitHeight(50);
                fotoNode.setFitWidth(50);
                Label nome = new Label(user.getLogin());
                perfilPesquisa.getChildren().add(fotoNode);
                perfilPesquisa.getChildren().add(nome);
                
                perfilPesquisa.setOnMouseClicked((Event event) -> {
                    AppView.getControlUser().setPerfilCorrent((User)AppView.getControlUser().getGrafoUsers().getVertex(user).getValue());
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("FXMLPerfilVisita.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene cenaPerfil = new Scene(root);
                    Stage palco = (Stage) ((Node) evento.getSource()).getScene().getWindow();
                    palco.setScene(cenaPerfil);
                    palco.show();
                });
                ltvPesquisa.getItems().add(perfilPesquisa);
            }
        }
        pnPesquisa.setVisible(true);

    }

}
