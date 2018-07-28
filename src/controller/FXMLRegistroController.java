/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view.AppView;

/**
 *
 * A classe <b>FXMLRegistroController</b> gerência o arquivo FXMLRegistro.
 *
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 */
public class FXMLRegistroController implements Initializable {

    private final String styleError = "-fx-background-color: #EEEE00;";
    private final String styleAcept = "-fx-background-color: #FFFFFF;";
    @FXML
    TextField txtUserName;
    @FXML
    PasswordField passSenha;
    @FXML
    TextField txtEndereco;
    @FXML
    TextField txtTelefone;
    @FXML
    TextField txtDataNascimento;
    @FXML
    TextField txtNomeCompleto;
    @FXML
    TextField txtEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mascaraData(txtDataNascimento);
        mascaraTelefone(txtTelefone);
        // TODO

    }

    /**
     *
     * Código retirado da página: https://pastebin.com/HNzmC2tu. Utilizado para
     * criar campo de texto adequado para o preenchimento do telefone.
     *
     * @author Paulo Henrique Luvisoto - paulobitfranca@gmail.com
     * @param textField Campo de texto a ser formatado
     */
    public static void mascaraTelefone(TextField textField) {

        textField.setOnKeyTyped((KeyEvent event) -> {
            if ("0123456789".contains(event.getCharacter()) == false) {
                event.consume();
            }

            if (event.getCharacter().trim().length() == 0) { // apagando

                if (textField.getText().length() == 10 && textField.getText().substring(9, 10).equals("-")) {
                    textField.setText(textField.getText().substring(0, 9));
                    textField.positionCaret(textField.getText().length());
                }
                if (textField.getText().length() == 9 && textField.getText().substring(8, 9).equals("-")) {
                    textField.setText(textField.getText().substring(0, 8));
                    textField.positionCaret(textField.getText().length());
                }
                if (textField.getText().length() == 4) {
                    textField.setText(textField.getText().substring(0, 3));
                    textField.positionCaret(textField.getText().length());
                }
                if (textField.getText().length() == 1) {
                    textField.setText("");
                }

            } else { //escrevendo

                if (textField.getText().length() == 14) {
                    event.consume();
                }

                if (textField.getText().length() == 0) {
                    textField.setText("(" + event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }
                if (textField.getText().length() == 3) {
                    textField.setText(textField.getText() + ")" + event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }
                if (textField.getText().length() == 8) {
                    textField.setText(textField.getText() + "-" + event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }
                if (textField.getText().length() == 9 && !"-".equals(textField.getText().substring(8, 9))) {
                    textField.setText(textField.getText() + "-" + event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }
                if (textField.getText().length() == 13 && textField.getText().substring(8, 9).equals("-")) {
                    textField.setText(textField.getText().substring(0, 8) + textField.getText().substring(9, 10) + "-" + textField.getText().substring(10, 13) + event.getCharacter());
                    textField.positionCaret(textField.getText().length());
                    event.consume();
                }

            }

        });

        textField.setOnKeyReleased((KeyEvent evt) -> {

            if (!textField.getText().matches("\\d()-*")) {
                textField.setText(textField.getText().replaceAll("[^\\d()-]", ""));
                textField.positionCaret(textField.getText().length());
            }
        });

    }

    /**
     *
     * Código retirado da página: https://pastebin.com/HNzmC2tu. Utilizado para
     * criar campo de texto adequado para o preenchimento da data de nascimento.
     *
     * @author Paulo Henrique Luvisoto - paulobitfranca@gmail.com
     * @param textField
     */
    public static void mascaraData(TextField textField) {

        textField.setOnKeyTyped((KeyEvent event) -> {
            if ("0123456789".contains(event.getCharacter()) == false) {
                event.consume();
            }

            if (event.getCharacter().trim().length() == 0) { // apagando

                if (textField.getText().length() == 3) {
                    textField.setText(textField.getText().substring(0, 2));
                    textField.positionCaret(textField.getText().length());
                }
                if (textField.getText().length() == 6) {
                    textField.setText(textField.getText().substring(0, 5));
                    textField.positionCaret(textField.getText().length());
                }

            } else { // escrevendo

                if (textField.getText().length() == 10) {
                    event.consume();
                }

                if (textField.getText().length() == 2) {
                    textField.setText(textField.getText() + "/");
                    textField.positionCaret(textField.getText().length());
                }
                if (textField.getText().length() == 5) {
                    textField.setText(textField.getText() + "/");
                    textField.positionCaret(textField.getText().length());
                }

            }
        });

        textField.setOnKeyReleased((KeyEvent evt) -> {

            if (!textField.getText().matches("\\d/*")) {
                textField.setText(textField.getText().replaceAll("[^\\d/]", ""));
                textField.positionCaret(textField.getText().length());
            }
        });

    }

    /**
     * Cancela realização do cadastro e volta para a tela de login.
     *
     * @param evento Mouse click
     * @throws IOException Ler arquivo FXML.
     */
    @FXML
    public void cancelar(Event evento) throws IOException {
        //Altera a cena para o login
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLLogin.fxml"));
        Scene cenaPerfil = new Scene(root);

        Stage palco = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        palco.setScene(cenaPerfil);

    }

    /**
     *
     * @param c Campo de texto a ser analisado.
     * @param valor Quantidade de caracteres necessários para o campo preenchido
     * ser válido.
     * @param registroOk flag para ferificar se os dados do registro estão
     * corretos.
     * @return Retorna <i>true</i> se o campo for preenchido corretamente e
     * <i>false</i> caso contrário.
     */
    private boolean checkRegistro(TextField c, int valor, boolean registroOk) {
        if (c.getText().length() < valor) {
            registroOk = false;
            return false;
        }
        return true;
    }

    /**
     *
     * @param senha Campo a ser analisado.
     * @param valor Quantidade de caracteres necessários para o campo preenchido
     * ser válido.
     * @param registroOk flag para ferificar se os dados do registro estão
     * corretos.
     * @return Retorna <i>true</i> se o campo for preenchido corretamente e
     * <i>false</i> caso contrário.
     */
    private boolean checkSenha(PasswordField senha, int valor, boolean registroOk) {
        if (senha.getText().length() < valor) {
            registroOk = false;
            return false;
        }
        return true;
    }

    /**
     *Realiza o cadastro de um novo usuário pegando todas as informações preenchidas nos campos.
     * @param evento Mouse click
     * @throws IOException Ler grafo salvo em disco e ler o arquivo FXML.
     */
    @FXML
    public void registrar(Event evento) throws IOException {

        AppView.getControlUser().setGrafoUsers(AppView.getControlUser().readRegisters());
        boolean registroOk = true;
        String nome = null;
        String email = null;
        String endereco = null;
        String telefone = null;
        String senha = null;
        String userName = null;
        String dataNasc = null;

        /*
                    implementação futura:
                    Colocar mais tratamentos de erros, como na data, nome e telefone.
                    séria bom também poder alterar a cor dos campos de texto, até agora não sei como fazer isso
         */
        if (checkRegistro(txtUserName, 1, registroOk)) {
            userName = txtUserName.getText();
            txtUserName.setStyle(styleAcept);
        } else {
            registroOk = false;
            txtUserName.setStyle(styleError);

        }
        if (checkRegistro(txtEmail, 5, registroOk)) {
            email = txtEmail.getText();
            txtEmail.setStyle(styleAcept);
        } else {
            registroOk = false;
            txtEmail.setStyle(styleError);

        }
        if (checkRegistro(txtEndereco, 5, registroOk)) {
            endereco = txtEndereco.getText();
            txtEndereco.setStyle(styleAcept);
        } else {
            registroOk = false;
            txtEndereco.setStyle(styleError);

        }
        if (checkRegistro(txtTelefone, 8, registroOk)) {
            telefone = txtTelefone.getText();
            txtTelefone.setStyle(styleAcept);
        } else {
            registroOk = false;
            txtTelefone.setStyle(styleError);

        }
        if (checkSenha(passSenha, 8, registroOk)) {
            senha = passSenha.getText();
            passSenha.setStyle(styleAcept);
        } else {
            registroOk = false;
            passSenha.setStyle(styleError);
        }
        if (checkRegistro(txtDataNascimento, 10, registroOk)) {
            dataNasc = txtDataNascimento.getText();
            txtDataNascimento.setStyle(styleAcept);
        } else {
            registroOk = false;
            txtDataNascimento.setStyle(styleError);
        }

        if (checkRegistro(txtNomeCompleto, 5, registroOk)) {
            nome = txtNomeCompleto.getText();
            txtNomeCompleto.setStyle(styleAcept);
        } else {
            registroOk = false;
            txtNomeCompleto.setStyle(styleError);
        }

        if (registroOk) {
            if (AppView.getControlUser().addUser(nome, email, senha, telefone, dataNasc, endereco, userName)) {
                try {
                    AppView.getControlUser().saveRegisters();
                    Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLLogin.fxml"));
                    Scene cenaPerfil = new Scene(root);
                    Stage palco = (Stage) ((Node) evento.getSource()).getScene().getWindow();
                    palco.setScene(cenaPerfil);
                    palco.show();
                } catch (Exception ex) {
                }

            } else {
                txtUserName.setStyle(styleError);
            }
        }
    }

}
