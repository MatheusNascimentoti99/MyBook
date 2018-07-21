/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerUser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Iterator;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
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
    private ImageView fotoPerfil;
    @FXML
    private Label lblMudaFoto;
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
    private Button btnVoltarPesquisa;

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
    @FXML
    private Pane pnPesquisa;
    @FXML
    private ListView listPesquisa;
    @FXML
    private TextField txtPesquisar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        lblNome.setText(AppView.getControlUser().loginCorrent().getLogin());
        Vertex atual = AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().loginCorrent());
        Image image;
            System.out.println(AppView.getControlUser().loginCorrent().getDirFoto());
            if (AppView.getControlUser().loginCorrent().getDirFoto() != null) {
                image = new Image(AppView.getControlUser().loginCorrent().getDirFoto());
            } else {
                image = new Image("icon/Person.png");
            }
        fotoPerfil.setImage(image);
        for (int i = 0; i < atual.getArestas().size(); i++) {
            User amigo = (User) ((Edge) atual.getArestas().get(i)).getPre().getValue();
            Label amigos = new Label(amigo.getLogin());
            listAmigos.getItems().add(amigos);
            
        }

    }

    @FXML
    public void clickPesquisar(Event evento) {
        if (txtPesquisar.getText().equals("Pesquisar"));
        txtPesquisar.setText("");
    }

    
    @FXML
    public void pesquisar(ActionEvent evento) {
        btnVoltar.setVisible(true);
        listPesquisa.getItems().clear();
        Iterator it = AppView.getControlUser().getGrafoUsers().vertices();
        while (it.hasNext()) {
            HBox perfilPesquisa = new HBox(5);
            Image foto;
            User user = (User) ((Vertex) it.next()).getValue();
            System.out.println(user.getDirFoto());
            if (user.getDirFoto() != null) {
                foto = new Image(user.getDirFoto());
            } else {
                foto = new Image("icon/Person.png");
            }
            ImageView fotoNode = new ImageView(foto);
            fotoNode.setFitHeight(50);
            fotoNode.setFitWidth(50);
            Label nome = new Label(user.getLogin());
            perfilPesquisa.getChildren().add(fotoNode);
            perfilPesquisa.getChildren().add(nome);
            listPesquisa.getItems().add(perfilPesquisa);
        }
        pnPesquisa.setVisible(true);

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
        pnPesquisa.setVisible(false);

        pnFundo.setVisible(true);
    }

    @FXML
    public void abrir(Event evento) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        try {
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                fotoPerfil.setImage(image);
                ((User) AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().loginCorrent()).getValue()).setDirFoto(file.toURI().toURL().toString());
                AppView.getControlUser().loginCorrent().setDirFoto(file.getAbsolutePath());
            }
        } catch (IOException | RuntimeException ex) {
            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            AppView.getControlUser().saveRegisters();
        } catch (Exception ex) {
            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
