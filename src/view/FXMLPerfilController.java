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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import model.Postagem;
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
    private VBox VboxPosts;
    @FXML
    private ListView listPostagem;
    @FXML
    private Label fotoPostagem;
    @FXML
    private Label videoPostagem;
    @FXML
    private ListView listPosts;
    @FXML
    private TextArea post;
    @FXML
    private ImageView fotoPerfil;
    @FXML
    private Label lblMudaFoto;
    @FXML
    private Label lblNome;
    @FXML
    private Label lblFoto;
    @FXML
    private Label labelPostFoto;
    @FXML
    private Label labelPostVideo;
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
    private Label lblSair;
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
    private ImageView imvFotoSobre;
    @FXML
    private Button botaoPostar;
    @FXML
    private Pane pnPesquisa;
    @FXML
    private ListView listPesquisa;
    @FXML
    private TextField txtPesquisar;
    private boolean foto, video;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        lblNome.setText(AppView.getControlUser().getLoginCorrent().getLogin());
        Vertex atual = AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getLoginCorrent());
        Image image;
        try {
            if (!AppView.getControlUser().getLoginCorrent().getDirFoto().equals("")) {
                image = new Image(AppView.getControlUser().getLoginCorrent().getDirFoto());
            } else {
                image = new Image("icon/Person.png");
            }
        } catch (RuntimeException exe) {
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
        pnSobre.setVisible(false);
        btnVoltar.setVisible(true);
        listPesquisa.getItems().clear();
        Iterator it = AppView.getControlUser().getGrafoUsers().vertices();
        while (it.hasNext()) {
            HBox perfilPesquisa = new HBox(5);
            Image foto;
            User user = (User) ((Vertex) it.next()).getValue();
            if (user.getNome().substring(0, txtPesquisar.getText().length()).equalsIgnoreCase(txtPesquisar.getText())) {
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
                listPesquisa.getItems().add(perfilPesquisa);
            }
        }
        pnPesquisa.setVisible(true);

    }

    @FXML
    public void sobre(Event evento) {
        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        pnFundo.setVisible(false);
        pnPesquisa.setVisible(false);
        txtNome.setText(AppView.getControlUser().getLoginCorrent().getNome());
        txtUsername.setText(AppView.getControlUser().getLoginCorrent().getLogin());
        txtEmail.setText(AppView.getControlUser().getLoginCorrent().getEmail());
        txtTelefone.setText(AppView.getControlUser().getLoginCorrent().getTelefone());
        txtEndereco.setText(AppView.getControlUser().getLoginCorrent().getEndereco());
        txtDataNascimento.setText(AppView.getControlUser().getLoginCorrent().getDataNasc());
        pnSobre.setVisible(true);
        Image image;
        if (AppView.getControlUser().getLoginCorrent().getDirFoto() != null) {
            image = new Image(AppView.getControlUser().getLoginCorrent().getDirFoto());
        } else {
            image = new Image("icon/Person.png");
        }
        imvFotoSobre.setImage(image);

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
                if (bufferedImage != null) {
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    fotoPerfil.setImage(image);
                    ((User) AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getLoginCorrent()).getValue()).setDirFoto(file.toURI().toURL().toString());
                    AppView.getControlUser().getLoginCorrent().setDirFoto(file.getAbsolutePath());
                }

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
    
    public void postagem(Event evento){
        if (!listPostagem.getItems().isEmpty()){
            listPosts.getItems().add(listPostagem);
        }
    }
    
    public void postagemTexto(){
        if(post.getText()!= null){
            Label label = new Label(post.getText());
            listPostagem.getItems().add(label);
        }
    }
    public void postagemFoto(Event evento){
        foto = true;
        FileChooser filechooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        filechooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        
          try {
            File file = filechooser.showOpenDialog(null);
            if (file != null) {
                BufferedImage bufferedImage = ImageIO.read(file);
                if (bufferedImage != null) {
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    ImageView imageView = new ImageView(image);
                    listPostagem.getItems().add(imageView);
                }
            }
        } catch (IOException | RuntimeException ex) {
            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void postagemVideo(Event evento) throws MalformedURLException{
        video = true;
        FileChooser filechooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterAVI = new FileChooser.ExtensionFilter("AVI files (*.avi)", "*.AVI");
        FileChooser.ExtensionFilter extFilterMPG = new FileChooser.ExtensionFilter("MPEG files (*.mpg)", "*.MPEG");
        FileChooser.ExtensionFilter extFilterMP4 = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
        filechooser.getExtensionFilters().addAll(extFilterAVI, extFilterMPG, extFilterMP4);
        
        try{
            File file = filechooser.showOpenDialog(null);
            if(file!= null){
                Media media = new Media(file.toURI().toURL().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                MediaView mediaView = new MediaView(mediaPlayer);
                listPostagem.getItems().add(mediaView);
            }
        } catch (IOException | RuntimeException ex) {
            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
    

