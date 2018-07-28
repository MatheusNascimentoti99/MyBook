/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
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
import javafx.event.Event;
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
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import model.Postagem;
import model.User;
import util.Edge;
import util.Vertex;
import view.AppView;

/**
 *
 * A classe <b>FXMLPerfilVisitaController</b> gerência o arquivo
 * FXMLPerfilVisita.
 *
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 */
public class FXMLPerfilVisitaController implements Initializable {

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

    @FXML
    private Button btnArquivos;

    @FXML
    private Pane pnArquivos;

    @FXML
    private TilePane tpArquivos;

    @FXML
    private Button btnVoltarArquivos;

    /**
     * Insere um imagem qualquer em uma ImageView.
     *
     * @param imagem Instancia do tipo Image que gravará a imagem que está salva
     * no <i>dir</i>.
     * @param dir Caminho da imagem.
     * @param imageView O objeto da interface que mostrará a foto do perfil.
     */
    public void carregarFoto(Image imagem, String dir, ImageView imageView) {
        try {
            imagem = new Image(dir);
            if (imagem.isError()) {
                imagem = new Image("/icon/Person.png");
            }

        } catch (RuntimeException ex) {
            imagem = new Image("/icon/Person.png");
        }
        imageView.setImage(imagem);

    }

    /**
     * Insere um imagem qualquer em uma ImageView.
     *
     * @param imagem Instancia do tipo Image que gravará a imagem que está salva
     * no <i>dir</i>.
     * @param dir Caminho da imagem.
     * @param imageView O objeto da interface que mostrará a imagem.
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
        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        lblNome.setText(AppView.getControlUser().getPerfilCorrent().getLogin());
        lblUserLogin.setText(AppView.getControlUser().getLoginCorrent().getLogin());
        Vertex atual = AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getPerfilCorrent());

        Image imageUserLogin = null;

        if (AppView.getControlUser().getGrafoUsers().getEdge(
                AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getLoginCorrent()),
                AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getPerfilCorrent()))
                != null) {
            btnSolicitacao.setVisible(false);

        }
        carregarFoto(imageUserLogin, AppView.getControlUser().getLoginCorrent().getDirFoto(), imvUserLogin);
        Image imagemPerfilVisit = null;

        carregarFoto(imagemPerfilVisit, AppView.getControlUser().getPerfilCorrent().getDirFoto(), imvFoto);

        Set<Integer> chaves = atual.getArestas().keySet();

        chaves.stream().filter((chave) -> (chave != null)).map((chave) -> (User) ((Edge) atual.getArestas().get(chave)).getPre().getValue()).map((amigo) -> {
            HBox perfilamigo = new HBox(5);
            Image imageAmigo = null;
            Label nomeAmigo = new Label(amigo.getLogin());

            ImageView fotoAmigo = new ImageView();
            carregarFoto(imageAmigo, amigo.getDirFoto(), fotoAmigo);

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
            return perfilamigo;
        }).map((perfilamigo) -> {
            ltvAmigos.getItems().add(perfilamigo);
            return perfilamigo;
        }).forEachOrdered((_item) -> {
            try {
                AppView.getControlUser().saveRegisters();
            } catch (Exception ex) {
                Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
                    Image imagePost = null;

                    String urlImagem = (String) it.next();

                    ImageView imageView = new ImageView();
                    carregarFotoPostagem(imagePost, urlImagem, imageView);

                    imageView.setFitHeight(150);
                    imageView.setFitWidth(150);
                    campoPostagem.getChildren().add(imageView);
                }
            }
            if (!postagem.getUrlVideo().isEmpty()) {
                Iterator it = postagem.getUrlVideo().iterator();
                while (it.hasNext()) {
                    String urlVideo = (String) it.next();
                    try {
                        Media media = new Media(urlVideo);
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
            Button compartilhar = new Button("Compartilhar");
            compartilhar(compartilhar, postagem);
            campoPostagem.getChildren().add(compartilhar);
            ltvPostagens.getItems().add(0, campoPostagem);

        }
        // TODO
    }

    /**
     * A postagem do Perfil que está sendo visitado pode ser adicionado as postagens do usuário que está acessando.
     * @param compartilhar Botão que recebera o evento.
     * @param post Postagem que será compartilhada.
     */
    private void compartilhar(Button compartilhar, Postagem post) {
        compartilhar.setOnMouseClicked((Event event) -> {
            Postagem postCompar = new Postagem();
            postCompar.setUrlVideo(post.getUrlVideo());
            postCompar.setUrlImagem(post.getUrlImagem());
            postCompar.setHoraPostagem(new Date());
            postCompar.setDataPostagem(new Date());
            postCompar.setTextoPostagem(post.getTextoPostagem());
            ((User) AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getLoginCorrent()).getValue()).getPostagens().add(0, postCompar);
            AppView.getControlUser().getLoginCorrent().getPostagens().add(postCompar);
            try {
                AppView.getControlUser().saveRegisters();
            } catch (Exception ex) {
                Logger.getLogger(FXMLPerfilVisitaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    /**
     *Abrir galeria de imagens e vídeos.
     * @param evento Click mouse.
     */
    @FXML
    public void verArquivos(Event evento) {
        pnPesquisa.setVisible(false);
        pnFundo.setVisible(false);
        tpArquivos.getChildren().clear();
        tpArquivos.setHgap(10);
        tpArquivos.setVgap(10);
        Iterator iterador = ((User) AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getPerfilCorrent()).getValue()).getPostagens().iterator();
        while (iterador.hasNext()) {
            Postagem postagem = (Postagem) iterador.next();

            if (!postagem.getUrlImagem().isEmpty()) {
                for (Object o : postagem.getUrlImagem()) {
                    Image imagePost = null;
                    ImageView imageView = new ImageView();
                    String caminho = (String) o;
                    carregarFotoPostagem(imagePost, caminho, imageView);
                    imageView.setFitHeight(150);
                    imageView.setFitWidth(150);
                    tpArquivos.getChildren().add(imageView);
                }
            }
            if (!postagem.getUrlVideo().isEmpty()) {
                Iterator it = postagem.getUrlVideo().iterator();
                while (it.hasNext()) {
                    Media media;
                    try {

                        media = new Media((String) it.next());

                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        MediaView mediaView = new MediaView(mediaPlayer);
                        mediaView.setFitHeight(150);
                        mediaView.setFitWidth(150);
                        mediaView.setOnMouseEntered((Event event) -> {
                            mediaPlayer.play();
                        });
                        mediaView.setOnMouseExited((Event event) -> {
                            mediaPlayer.pause();
                            mediaView.setFitHeight(150);
                            mediaView.setFitWidth(150);
                        });
                        tpArquivos.getChildren().add(mediaView);
                    } catch (RuntimeException ex) {

                    }
                }
            }

        }
        pnArquivos.setVisible(true);
    }

    /**
     *Lista as informações básicas do usuário. 
     * @param evento Mouse click
     */
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
        Image image = null;

        carregarFoto(image, AppView.getControlUser().getPerfilCorrent().getDirFoto(), desFoto);

    }

    /**
     *Adiciona o usuário na lista de solicitações de amizades do outro usuário dono do perfil que está sendo visitado.
     * @param evento Mouse click
     */
    @FXML
    public void enviarSolicitacao(Event evento) {
        if (!((User) AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getPerfilCorrent()).getValue()).getSolicitacoes().contains(AppView.getControlUser().getLoginCorrent())) {
            ((User) AppView.getControlUser().getGrafoUsers().getVertex(AppView.getControlUser().getPerfilCorrent()).getValue()).getSolicitacoes().add(AppView.getControlUser().getLoginCorrent());
        }

        btnSolicitacao.setStyle("-fx-opacity: 0.5");
        try {
            AppView.getControlUser().saveRegisters();
        } catch (Exception ex) {
            Logger.getLogger(FXMLPerfilVisitaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *Volta para o perfil inicial. 
     * @param evento Mouse click
     */
    @FXML
    public void home(Event evento) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FXMLPerfil.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene cenaPerfil = new Scene(root);
        Stage palco = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        palco.setScene(cenaPerfil);
        palco.show();
    }

    /**
     *Sai do MyBook.
     * @param evento Click mouse.
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
     *Volta para o perfil inicial.
     * @param evento Mouse click
     */
    @FXML
    public void voltar(Event evento) {
        pnArquivos.setVisible(false);

        pnPesquisa.setVisible(false);
        pnSobre.setVisible(false);
        pnFundo.setVisible(true);
    }

    /**
     *Apaga a palavra "Pesquisar" quando o usuário clicar no campo de texto de pesquisa.
     * @param evento Mouse click
     */
    @FXML
    public void clickPesquisar(Event evento) {
        if (txtPesquisa.getText().equals("Pesquisar"));
        txtPesquisa.setText("");
    }

    /**
     *Opção de pesquisa para ver os resultados por nível de proximidade do usuário. 
     * @param event Mouse click
     */
    @FXML
    public void percorrerLargura(Event event) {

        ltvPesquisa.getItems().clear();
        LinkedList visitados = AppView.getControlUser().getGrafoUsers().percorrerLargura(AppView.getControlUser().getLoginCorrent());
        Iterator it = visitados.iterator();
        while (it.hasNext()) {
            HBox perfilPesquisa = new HBox(5);
            Image fotoP = null;
            User user = (User) ((Vertex) it.next()).getValue();
            int tamanho = txtPesquisa.getText().length() < user.getNome().length() ? txtPesquisa.getText().length() : user.getNome().length();
            if (user.getNome().substring(0, tamanho).equalsIgnoreCase(txtPesquisa.getText())) {

                ImageView fotoNode = new ImageView();
                carregarFoto(fotoP, user.getDirFoto(), fotoNode);
                fotoNode.setFitHeight(50);
                fotoNode.setFitWidth(50);
                Label nome = new Label(user.getLogin());
                perfilPesquisa.getChildren().add(fotoNode);
                perfilPesquisa.getChildren().add(nome);
                abrirPerfil(perfilPesquisa, user);

                ltvPesquisa.getItems().add(perfilPesquisa);

            }
        }

    }

    /**
     *Busca por nomes de usuários que iniciam com o mesmo dado passado pelo usuário no campo de texto de pesquisa.
     * @param evento Pressionar Enter
     */
    @FXML
    public void pesquisar(Event evento) {
        pnArquivos.setVisible(false);
        pnFundo.setVisible(false);
        btnVoltar.setVisible(true);
        ltvPesquisa.getItems().clear();
        Iterator it = AppView.getControlUser().getGrafoUsers().vertices();
        while (it.hasNext()) {
            HBox perfilPesquisa = new HBox(5);
            Image foto = null;
            User user = (User) ((Vertex) it.next()).getValue();
            if (user.getNome().substring(0, txtPesquisa.getText().length()).equalsIgnoreCase(txtPesquisa.getText())) {

                ImageView fotoNode = new ImageView();
                carregarFoto(foto, user.getDirFoto(), fotoNode);
                fotoNode.setFitHeight(50);
                fotoNode.setFitWidth(50);
                Label nome = new Label(user.getLogin());
                perfilPesquisa.getChildren().add(fotoNode);
                perfilPesquisa.getChildren().add(nome);
                abrirPerfil(perfilPesquisa, user);
                ltvPesquisa.getItems().add(perfilPesquisa);
            }
        }
        pnPesquisa.setVisible(true);

    }

    /**
     *Opção de abrir perfil de outros usuários.
     * @param perfilPesquisa Opção de perfil a ser visidado.
     * @param user Usuário a ser visidado.
     */
    public void abrirPerfil(HBox perfilPesquisa, User user) {
        perfilPesquisa.setOnMouseClicked((Event event) -> {
            if (!user.equals(AppView.getControlUser().getLoginCorrent())) {
                AppView.getControlUser().setPerfilCorrent((User) AppView.getControlUser().getGrafoUsers().getVertex(user).getValue());
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
    }

}
