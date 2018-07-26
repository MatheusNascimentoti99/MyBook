/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
import view.AppView;

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
    private Label quantSolisitacao;
    @FXML
    private ListView listPostagem;

    @FXML
    private ListView listPosts;
    @FXML
    private TextArea post;
    @FXML
    private Pane pnSolicitacao;
    @FXML
    private ListView ltvSolicitacao;
    @FXML
    private ImageView fotoPerfil;
    @FXML
    private Label lblMudaFoto;
    @FXML
    private Label lblNome;
    @FXML
    private ListView ltvAmigos;
    @FXML
    private Pane pnFundo;

    @FXML
    private Pane pnSobre;
    @FXML
    private Button btnVoltar;

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
    private Pane pnPesquisa;
    @FXML
    private ListView listPesquisa;
    @FXML
    private TextField txtPesquisar;

    @FXML
    private Button btnArquivos;

    @FXML
    private Pane pnArquivos;

    @FXML
    private ListView ltvArquivos;

    @FXML
    private Button btnVoltarArquivos;

    private ControllerPostagem controlPost;

    /**
     *
     * @param imagem
     * @param dir
     * @param imageView
     */
    public void carregarFoto(Image imagem, String dir, ImageView imageView) {
        try {
            imagem = new Image(dir);
            if (imagem.isError() || dir == null) {
                imagem = new Image("/icon/Person.png");
            }

        } catch (RuntimeException ex) {
            imagem = new Image("/icon/Person.png");
        }
        imageView.setImage(imagem);

    }

    /**
     *
     * @param imagem
     * @param dir
     * @param imageView
     */
    public void carregarFotoPostagem(Image imagem, String dir, ImageView imageView) {
        try {
            imagem = new Image(dir);
            if (imagem.isError()) {
                imagem = new Image("/icon/Empty.png");
            }

        } catch (RuntimeException ex) {
            imagem = new Image("/icon/Empty.png");
        }
        imageView.setImage(imagem);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlPost = new ControllerPostagem();
        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        lblNome.setText(AppView.getControlUser().getLoginCorrent().getLogin());
        Vertex atual = AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getLoginCorrent());
        Image imageUser = null;

        carregarFoto(imageUser, AppView.getControlUser().getLoginCorrent().getDirFoto(), fotoPerfil);
        Set<Integer> chaves = atual.getArestas().keySet();
        for (Integer chave : chaves) {
            if (chave != null) {
                User amigo = (User) ((Edge) atual.getArestas().get(chave)).getPre().getValue();
                HBox perfilamigo = new HBox(5);
                Label nomeAmigo = new Label(amigo.getLogin());
                try {

                    imageUser = new Image(amigo.getDirFoto());

                } catch (RuntimeException exe) {
                    imageUser = new Image("/icon/Person.png");
                }
                if (imageUser.isError()) {
                    imageUser = new Image("/icon/Person.png");
                }
                ImageView fotoAmigo = new ImageView(imageUser);
                fotoAmigo.setFitHeight(50);
                fotoAmigo.setFitWidth(50);
                perfilamigo.getChildren().add(fotoAmigo);
                perfilamigo.getChildren().add(nomeAmigo);
                perfilamigo.setOnMouseClicked((Event event) -> {
                    if (!amigo.equals(AppView.getControlUser().getLoginCorrent())) {
                        AppView.getControlUser().setPerfilCorrent((User) AppView.getControlUser().getGrafoUsers().getVertex(amigo).getValue());
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/view/FXMLPerfilVisita.fxml"));
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
                            root = FXMLLoader.load(getClass().getResource("/view/FXMLPerfil.fxml"));
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
                ltvSolicitacao.getItems().remove(perfilamigo);

                try {
                    AppView.getControlUser().saveRegisters();
                } catch (Exception ex) {
                    Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        quantSolisitacao.setText("" + AppView.getControlUser().getLoginCorrent().getSolicitacoes().size());
        Iterator iterador = AppView.getControlUser().getLoginCorrent().getPostagens().iterator();
        while (iterador.hasNext()) {

            Postagem postagem = (Postagem) iterador.next();
            VBox campoPostagem = new VBox(5);
            Button btnExcluir = new Button("Apagar");
            btnExcluir.setAlignment(Pos.TOP_RIGHT);
            campoPostagem.getChildren().add(btnExcluir);
            Label txtPost = new Label(postagem.getTextoPostagem());
            txtPost.alignmentProperty().setValue(Pos.TOP_LEFT);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = postagem.getDataPostagem();

            DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
            Date date2 = postagem.getHoraPostagem();
            Label nome = new Label(AppView.getControlUser().getLoginCorrent().getLogin());
            Label data = new Label("----- Postagem do Dia: "
                    + dateFormat.format(date) + " �s: " + dateFormat2.format(date2) + " -----\n");
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
                    Image imagePost = null;
                    ImageView imageView = new ImageView();
                    carregarFotoPostagem(imagePost, (String) it.next(), imageView);
                    imageView.setFitHeight(150);
                    imageView.setFitWidth(150);
                    campoPostagem.getChildren().add(imageView);
                }
            }
            if (!postagem.getUrlVideo().isEmpty()) {
                postagem.getUrlVideo().forEach((urlVideo) -> {
                    Media media;
                    try {
                        media = new Media((String) urlVideo);

                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        MediaView mediaView = new MediaView(mediaPlayer);
                        mediaView.setFitHeight(150);
                        mediaView.setFitWidth(150);
                        mediaView.setOnMouseEntered((Event event) -> {
                            mediaPlayer.play();
                            mediaView.setFitHeight(300);
                            mediaView.setFitWidth(300);
                        });
                        mediaView.setOnMouseExited((Event event) -> {
                            mediaPlayer.pause();
                            mediaView.setFitHeight(150);
                            mediaView.setFitWidth(150);
                        });
                        campoPostagem.getChildren().add(mediaView);
                    } catch (RuntimeException ex) {

                    }
                });
            }
            excluirPostagem(campoPostagem, btnExcluir, postagem, AppView.getControlUser().getLoginCorrent(), listPosts);

            listPosts.getItems().add(0, campoPostagem);
        }

    }

    /**
     *
     * @param evento
     */
    @FXML
    public void verArquivos(Event evento) {
        pnPesquisa.setVisible(false);
        pnFundo.setVisible(false);
        ltvArquivos.getItems().clear();
        Iterator iterador = AppView.getControlUser().getLoginCorrent().getPostagens().iterator();
        while (iterador.hasNext()) {
            Postagem postagem = (Postagem) iterador.next();
            HBox campoPostagem = new HBox(20);

            if (!postagem.getUrlImagem().isEmpty()) {
                Iterator it = postagem.getUrlImagem().iterator();
                while (it.hasNext() && campoPostagem.getChildren().size() < 3) {
                    Image imagePost = null;
                    ImageView imageView = new ImageView();
                    String caminho = (String) it.next();
                    carregarFotoPostagem(imagePost, caminho, imageView);
                    imageView.setFitHeight(150);
                    imageView.setFitWidth(150);
                    campoPostagem.getChildren().add(imageView);
                }
            }
            if (!postagem.getUrlVideo().isEmpty()) {
                Iterator it = postagem.getUrlVideo().iterator();
                while (it.hasNext() && campoPostagem.getChildren().size() < 3) {
                    Media media;
                    try {
                        media = new Media((String) it.next());

                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        MediaView mediaView = new MediaView(mediaPlayer);
                        mediaView.setFitHeight(150);
                        mediaView.setFitWidth(150);
                        mediaView.setOnMouseEntered((Event event) -> {
                            mediaPlayer.play();
                            mediaView.setFitHeight(300);
                            mediaView.setFitWidth(300);
                        });
                        mediaView.setOnMouseExited((Event event) -> {
                            mediaPlayer.pause();
                            mediaView.setFitHeight(150);
                            mediaView.setFitWidth(150);
                        });
                        campoPostagem.getChildren().add(mediaView);
                    } catch (RuntimeException ex) {

                    }
                }
            }
            ltvArquivos.getItems().add(0, campoPostagem);
        }
        pnArquivos.setVisible(true);
    }

    /**
     *
     * @param evento
     */
    @FXML
    public void mousecimaFoto(Event evento) {
        fotoPerfil.setStyle("-fx-opacity: 0.5");
        lblMudaFoto.visibleProperty().set(true);

    }

    /**
     *
     * @param evento
     */
    @FXML
    public void mouseForaFoto(Event evento) {
        fotoPerfil.setStyle("-fx-opacity: 1");
        lblMudaFoto.visibleProperty().set(false);
    }

    /**
     *
     * @param evento
     */
    @FXML
    public void clickPesquisar(Event evento) {
        if (txtPesquisar.getText().equals("Pesquisar"));
        txtPesquisar.setText("");
    }

    /**
     *
     * @param evento
     */
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
            root = FXMLLoader.load(getClass().getResource("/view/FXMLLogin.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene cenaPerfil = new Scene(root);
        Stage palco = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        palco.setScene(cenaPerfil);
        palco.show();
    }

    /**
     *
     * @param evento
     */
    @FXML
    public void pesquisar(ActionEvent evento) {
        pnFundo.setVisible(false);
        pnSobre.setVisible(false);
        btnVoltar.setVisible(true);
        listPesquisa.getItems().clear();
        Iterator it = AppView.getControlUser().getGrafoUsers().vertices();
        while (it.hasNext()) {
            HBox perfilPesquisa = new HBox(5);
            Image fotoP = null;
            User user = (User) ((Vertex) it.next()).getValue();
            int tamanho = txtPesquisar.getText().length() < user.getNome().length() ? txtPesquisar.getText().length() : user.getNome().length();
            if (user.getNome().substring(0, tamanho).equalsIgnoreCase(txtPesquisar.getText())) {

                ImageView fotoNode = new ImageView();
                carregarFoto(fotoP, user.getDirFoto(), fotoNode);
                fotoNode.setFitHeight(50);
                fotoNode.setFitWidth(50);
                Label nome = new Label(user.getLogin());
                perfilPesquisa.getChildren().add(fotoNode);
                perfilPesquisa.getChildren().add(nome);
                abrirPerfil(perfilPesquisa, evento, user);

                listPesquisa.getItems().add(perfilPesquisa);
            }
        }
        pnPesquisa.setVisible(true);

    }

    /**
     *
     * @param evento
     */
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
        Image image = null;

        carregarFoto(image, AppView.getControlUser().getLoginCorrent().getDirFoto(), imvFotoSobre);

    }

    /**
     *
     * @param evento
     */
    @FXML
    public void voltar(Event evento) {
        pnArquivos.setVisible(false);
        pnSobre.setVisible(false);
        pnPesquisa.setVisible(false);
        pnFundo.setVisible(true);
    }

    @FXML
    private void aceitarSolicitacao(Event evento) {
        ltvSolicitacao.getItems().clear();
        if (pnSolicitacao.visibleProperty().getValue() == false) {

            Iterator it = AppView.getControlUser().getLoginCorrent().getSolicitacoes().iterator();
            if (!it.hasNext()) {
                Label aviso = new Label("Sem solicita��es de amizade");
                aviso.alignmentProperty().setValue(Pos.TOP_LEFT);
                aviso.setStyle("-fx-font-family: \"Segoe UI\", Helvetica, Arial, sans-serif;\n"
                        + "    -fx-font-size: 8pt;");
                ltvSolicitacao.getItems().add(aviso);
            }
            while (it.hasNext()) {
                HBox perfilSolicita = new HBox(5);
                Image fotoS = null;
                User user = (User) it.next();

                ImageView fotoNode = new ImageView();
                carregarFoto(fotoS, user.getDirFoto(), fotoNode);

                fotoNode.setFitHeight(50);
                fotoNode.setFitWidth(50);
                Label nome = new Label(user.getLogin());
                Button aceitar = new Button();
                aceitar.setPrefSize(24, 24);
                aceitar.setStyle("-fx-background-color: #FFFFFF;");
                aceitar.setTooltip(new Tooltip("Aceitar solicita��o"));
                aceitar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/view/solicitacao.png"))));
                perfilSolicita.getChildren().add(fotoNode);
                perfilSolicita.getChildren().add(nome);
                perfilSolicita.getChildren().add(aceitar);

                aceitar.setOnMouseClicked((Event event) -> {
                    AppView.getControlUser().addAmizade(user, AppView.getControlUser().getLoginCorrent());
                    AppView.getControlUser().getLoginCorrent().getSolicitacoes().remove(user);
                    HBox novoamigo = new HBox(5);
                    Image image = null;
                    Label nomeAmigo = new Label(user.getLogin());

                    ImageView fotoAmigo = new ImageView();
                    carregarFoto(image, user.getDirFoto(), fotoAmigo);
                    fotoAmigo.setFitHeight(50);
                    fotoAmigo.setFitWidth(50);
                    novoamigo.getChildren().add(fotoAmigo);
                    novoamigo.getChildren().add(nome);
                    ltvAmigos.getItems().add(novoamigo);
                    ((User) AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getLoginCorrent()).getValue()).getSolicitacoes().remove(user);
                    ltvSolicitacao.getItems().remove(perfilSolicita);
                    abrirPerfil(novoamigo, evento, user);
                    try {
                        AppView.getControlUser().saveRegisters();
                    } catch (Exception ex) {
                        Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
                ltvSolicitacao.getItems().add(perfilSolicita);

            }
            pnSolicitacao.setVisible(true);
        } else {
            pnSolicitacao.setVisible(false);
        }

    }

    /**
     *
     * @param novoamigo
     * @param evento
     * @param user
     */
    public void abrirPerfil(HBox novoamigo, Event evento, User user) {
        novoamigo.setOnMouseClicked((Event event) -> {
            if (!user.equals(AppView.getControlUser().getLoginCorrent())) {
                AppView.getControlUser().setPerfilCorrent((User) AppView.getControlUser().getGrafoUsers().getVertex(user).getValue());
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/view/FXMLPerfilVisita.fxml"));
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
                    root = FXMLLoader.load(getClass().getResource("/view/FXMLPerfil.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene cenaPerfil = new Scene(root);
                Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
                palco.setScene(cenaPerfil);
                palco.show();
            }
        });
    }

    /**
     *
     * @param evento
     */
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
                    AppView.getControlUser().getLoginCorrent().setDirFoto(file.toURI().toURL().toString());
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

    /**
     *
     * @param postagemView
     * @param excluir
     * @param postagem
     * @param user
     * @param postagensView
     */
    public void excluirPostagem(VBox postagemView, Button excluir, Postagem postagem, User user, ListView postagensView) {
        excluir.setOnMouseClicked((Event event) -> {
            ((User)AppView.getControlUser().getGrafoUsers().getVertex(user).getValue()).getPostagens().remove(postagem);
            user.getPostagens().remove(postagem);
            listPosts.getItems().remove(postagemView);
            try {
                AppView.getControlUser().saveRegisters();
            } catch (Exception ex) {
                Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     *
     * @param evento
     */
    @FXML
    public void postagem(Event evento) {
        if (!listPostagem.getItems().isEmpty() || post.getText().length() > 0) {
            Postagem postagem = new Postagem();
            postagem.setTextoPostagem(post.getText());
            postagem.getUrlImagem().addAll(controlPost.getUrlsFoto());
            postagem.getUrlVideo().addAll(controlPost.getUrlsVideo());

            VBox campoPostagem = new VBox(5);
            Button btnExcluir = new Button("Apagar");
            btnExcluir.setAlignment(Pos.TOP_RIGHT);
            campoPostagem.getChildren().add(btnExcluir);
            Label txtPost = new Label(post.getText());
            txtPost.alignmentProperty().setValue(Pos.TOP_LEFT);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateDia = new Date();
            postagem.setDataPostagem(dateDia);

            DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
            Date dataHora = new Date();
            postagem.setHoraPostagem(dataHora);
            Label nome = new Label(AppView.getControlUser().getLoginCorrent().getLogin());
            Label data = new Label("----- Postagem do Dia: "
                    + dateFormat.format(dateDia) + " �s: " + dateFormat2.format(dataHora) + " -----\n");
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

            Iterator it = listPostagem.getItems().iterator();
            while (it.hasNext()) {
                Object o = it.next();
                if (o instanceof ImageView) {
                    ImageView imagemPost = (ImageView) o;
                    imagemPost.setFitHeight(150);
                    imagemPost.setFitWidth(150);
                    campoPostagem.getChildren().add(imagemPost);
                } else if (o instanceof MediaView) {
                    MediaView videoPost = (MediaView) o;
                    videoPost.setFitHeight(150);
                    videoPost.setFitWidth(150);
                    videoPost.setOnMouseEntered((Event event) -> {
                        videoPost.getMediaPlayer().play();
                        videoPost.setFitHeight(300);
                        videoPost.setFitWidth(300);
                    });
                    videoPost.setOnMouseExited((Event event) -> {
                        videoPost.getMediaPlayer().pause();
                        videoPost.setFitHeight(150);
                        videoPost.setFitWidth(150);
                    });
                    campoPostagem.getChildren().add(videoPost);

                }

            }
            AppView.getControlUser().getLoginCorrent().getPostagens().add(postagem);
            ((User) AppView.getControlUser().getGrafoUsers().
                    getVertex(AppView.getControlUser().getLoginCorrent()).getValue()).getPostagens().add(postagem);
            listPosts.getItems().add(0, campoPostagem);
            excluirPostagem(campoPostagem, btnExcluir, postagem, AppView.getControlUser().getLoginCorrent(), listPosts);

        } else {
            System.out.println("Sem nada para se postar");
        }

        try {
            AppView.getControlUser().saveRegisters();
        } catch (Exception ex) {
            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        post.setText("");
        listPostagem.getItems().clear();

    }

    /**
     *
     * @param evento
     */
    public void postagemFoto(Event evento) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        try {
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                BufferedImage bufferedImage = ImageIO.read(file);
                if (bufferedImage != null) {
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(25);
                    imageView.setFitWidth(25);
                    listPostagem.getItems().add(imageView);
                    controlPost.getUrlsFoto().add(file.toURI().toURL().toString());
                }
            }
        } catch (IOException | RuntimeException ex) {
            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param evento
     * @throws MalformedURLException
     */
    public void postagemVideo(Event evento) throws MalformedURLException {
        FileChooser filechooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterAVI = new FileChooser.ExtensionFilter("AVI files (*.avi)", "*.AVI");
        FileChooser.ExtensionFilter extFilterMPG = new FileChooser.ExtensionFilter("MPEG files (*.mpg)", "*.MPEG");
        FileChooser.ExtensionFilter extFilterMP4 = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
        filechooser.getExtensionFilters().addAll(extFilterAVI, extFilterMPG, extFilterMP4);

        try {
            File file = filechooser.showOpenDialog(null);
            if (file != null) {
                Media media = new Media(file.toURI().toURL().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                MediaView mediaView = new MediaView(mediaPlayer);
                mediaView.setFitHeight(25);
                mediaView.setFitWidth(25);
                mediaView.setOnMouseEntered((Event event) -> {
                    mediaPlayer.play();
                });
                mediaView.setOnMouseExited((Event event) -> {
                    mediaPlayer.pause();
                    mediaView.setFitHeight(25);
                    mediaView.setFitWidth(25);
                });
                listPostagem.getItems().add(mediaView);
                controlPost.getUrlsVideo().add(file.toURI().toURL().toString());

            }
        } catch (IOException | RuntimeException ex) {
            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
