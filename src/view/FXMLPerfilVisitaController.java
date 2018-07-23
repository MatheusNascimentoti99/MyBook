/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import model.Postagem;
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
    @FXML
    private Label lblHome;

    @FXML
    private Label desNome;
    @FXML
    private Label desEmail;
    @FXML
    private Label desDataNascimento;
    @FXML
    private Label desTelefone;
    @FXML
    private Label desUserName;
    @FXML
    private Label desEndereco;
    @FXML
    private ImageView desFoto;
    @FXML
    private Pane pnSobre;
    @FXML
    private Button desVoltar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        lblNome.setText(AppView.getControlUser().getPerfilCorrent().getLogin());
        lblUserLogin.setText(AppView.getControlUser().getLoginCorrent().getLogin());
        Vertex atual = AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getPerfilCorrent());

        Image imageUserLogin;
        try {
            if (!AppView.getControlUser().getLoginCorrent().getDirFoto().equals("")) {
                imageUserLogin = new Image(AppView.getControlUser().getLoginCorrent().getDirFoto());
            } else {
                imageUserLogin = new Image("icon/Person.png");
            }
        } catch (RuntimeException exe) {
            imageUserLogin = new Image("icon/Person.png");
        }
        imvUserLogin.setImage(imageUserLogin);
        Image imagemPerfilVisit;
        try {
            if (!AppView.getControlUser().getPerfilCorrent().getDirFoto().equals("")) {
                imagemPerfilVisit = new Image(AppView.getControlUser().getPerfilCorrent().getDirFoto());
            } else {
                imagemPerfilVisit = new Image("icon/Person.png");
            }
        } catch (RuntimeException exe) {
            imagemPerfilVisit = new Image("icon/Person.png");
        }
        imvFoto.setImage(imagemPerfilVisit);
        Set<Integer> chaves = atual.getArestas().keySet();

        for (Integer chave : chaves) {
            if (chave != null) {
                User amigo = (User) ((Edge) atual.getArestas().get(chave)).getPre().getValue();
                HBox perfilamigo = new HBox(5);
                Image imageAmigo;
                Label nomeAmigo = new Label(amigo.getLogin());
                try {
                    if (!amigo.getDirFoto().equals("")) {
                        imageAmigo = new Image(amigo.getDirFoto());
                    } else {
                        imageAmigo = new Image("icon/Person.png");
                    }
                } catch (RuntimeException exe) {
                    imageAmigo = new Image("icon/Person.png");
                }
                ImageView fotoAmigo = new ImageView(imageAmigo);
                fotoAmigo.setFitHeight(50);
                fotoAmigo.setFitWidth(50);
                perfilamigo.getChildren().add(fotoAmigo);
                perfilamigo.getChildren().add(nomeAmigo);
                perfilamigo.setOnMouseClicked((Event event) -> {
                    if (!amigo.equals(AppView.getControlUser().getLoginCorrent())) {
                        AppView.getControlUser().setPerfilCorrent((User) AppView.getControlUser().getGrafoUsers().getVertex(amigo).getValue());
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("FXMLPerfilVisita.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Scene cenaPerfil = new Scene(root);
                        Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        palco.setScene(cenaPerfil);
                        palco.show();
                    } else {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("FXMLPerfil.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Scene cenaPerfil = new Scene(root);
                        Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        palco.setScene(cenaPerfil);
                        palco.show();
                    }
                });
                ltvAmigos.getItems().add(perfilamigo);

                try {
                    AppView.getControlUser().saveRegisters();
                } catch (Exception ex) {
                    Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        Iterator iterador = AppView.getControlUser().getPerfilCorrent().getPostagens().iterator();
        while (iterador.hasNext()) {
            Postagem postagem = (Postagem) iterador.next();
            VBox campoPostagem = new VBox(5);
            Label txtPost = new Label(postagem.getTextoPostagem());
            txtPost.alignmentProperty().setValue(Pos.TOP_LEFT);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = postagem.getDataPostagem();

            DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
            Date date2 = postagem.getHoraPostagem();
            Label nome = new Label(AppView.getControlUser().getPerfilCorrent().getLogin());
            Label data = new Label("----- Postagem do Dia: "
                    + dateFormat.format(date) + " às: " + dateFormat2.format(date2) + " -----\n");
            data.setStyle("-fx-font-family: \"Segoe UI\", Helvetica, Arial, sans-serif;\n"
                    + "    -fx-font-size: 9pt;");
            nome.setStyle("-fx-font-family: \"Segoe UI\", Helvetica, Arial, sans-serif;\n"
                    + "    -fx-font-size: 12pt;");
            txtPost.setStyle("-fx-font-family: \"Segoe UI\", Helvetica, Arial, sans-serif;\n"
                    + "    -fx-font-size: 12pt;");
            campoPostagem.getChildren().add(nome);
            campoPostagem.getChildren().add(data);
            campoPostagem.getChildren().add(txtPost);
            campoPostagem.setSpacing(10);
            campoPostagem.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

            if (!postagem.getUrlImagem().isEmpty()) {
                Iterator it = postagem.getUrlImagem().iterator();
                while (it.hasNext()) {
                    Image imagePost;

                    String urlImagem = (String) it.next();
                    System.out.println(urlImagem);
                    try {
                        if (urlImagem != null) {
                            imagePost = new Image(urlImagem);
                        } else {
                            imagePost = new Image("icon/Empty.png");
                        }
                    } catch (RuntimeException exe) {
                        imagePost = new Image("icon/Empty.png");
                    }
                    System.out.println(imagePost);
                    ImageView imageView = new ImageView(imagePost);
                    imageView.setFitHeight(150);
                    imageView.setFitWidth(150);
                    campoPostagem.getChildren().add(imageView);
                }
            }
            if (!postagem.getUrlVideo().isEmpty()) {
                Iterator it = postagem.getUrlVideo().iterator();
                while (it.hasNext()) {
                    String urlVideo = (String) it.next();
                    Media media = new Media(urlVideo);
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    MediaView mediaView = new MediaView(mediaPlayer);
                    mediaView.setFitHeight(150);
                    mediaView.setFitWidth(150);
                    campoPostagem.getChildren().add(mediaView);
                }
            }
            ltvPostagens.getItems().add(0, campoPostagem);
        }
        // TODO
    }

    @FXML
    public void sobre(Event evento) {
        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        pnFundo.setVisible(false);
        pnPesquisa.setVisible(false);
        desNome.setText(AppView.getControlUser().getPerfilCorrent().getNome());
        desUserName.setText(AppView.getControlUser().getPerfilCorrent().getLogin());
        desEmail.setText(AppView.getControlUser().getPerfilCorrent().getEmail());
        desTelefone.setText(AppView.getControlUser().getPerfilCorrent().getTelefone());
        desEndereco.setText(AppView.getControlUser().getPerfilCorrent().getEndereco());
        desDataNascimento.setText(AppView.getControlUser().getPerfilCorrent().getDataNasc());
        pnSobre.setVisible(true);
        Image image;
        if (AppView.getControlUser().getPerfilCorrent().getDirFoto() != null) {
            image = new Image(AppView.getControlUser().getPerfilCorrent().getDirFoto());
        } else {
            image = new Image("icon/Person.png");
        }
        desFoto.setImage(image);

    }

    @FXML
    private void enviarSolicitacao(Event evento) {
        if (!((User) AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getPerfilCorrent()).
                getValue()).getSolicitacoes().
                contains(AppView.getControlUser().getLoginCorrent())) {
            if (!AppView.getControlUser().getLoginCorrent().equals(AppView.getControlUser().getPerfilCorrent())) {
                ((User) AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getPerfilCorrent()).getValue()).getSolicitacoes().add(AppView.getControlUser().getLoginCorrent());
            }

        }
        btnSolicitacao.setStyle("-fx-opacity: 0.5");
        try {
            AppView.getControlUser().saveRegisters();
        } catch (Exception ex) {
            Logger.getLogger(FXMLPerfilVisitaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void home(Event evento) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLPerfil.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene cenaPerfil = new Scene(root);
        Stage palco = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        palco.setScene(cenaPerfil);
        palco.show();
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
    public void voltar(Event evento) {
        pnPesquisa.setVisible(false);
        pnSobre.setVisible(false);
        pnFundo.setVisible(true);
    }

    @FXML
    public void clickPesquisar(Event evento) {
        if (txtPesquisa.getText().equals("Pesquisar"));
        txtPesquisa.setText("");
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
                    if (!user.equals(AppView.getControlUser().getLoginCorrent())) {
                        AppView.getControlUser().setPerfilCorrent((User) AppView.getControlUser().getGrafoUsers().getVertex(user).getValue());
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
                    } else {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("FXMLPerfil.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Scene cenaPerfil = new Scene(root);
                        Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        palco.setScene(cenaPerfil);
                        palco.show();
                    }
                });
                ltvPesquisa.getItems().add(perfilPesquisa);
            }
        }
        pnPesquisa.setVisible(true);

    }

}
