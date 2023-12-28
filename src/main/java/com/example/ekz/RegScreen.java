package com.example.ekz;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.ekz.DbFunctions.DbFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegScreen {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label ErrorText;

    @FXML
    private Button btnCreate;

    @FXML
    private ImageView closeImage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private TextField textFieldPasswordRepeat;

    @FXML
    private Label AuthLabel;


    @FXML
    void Exit(MouseEvent event) {
        Stage stage = (Stage) closeImage.getScene().getWindow();
        stage.close();
    }

    private final DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {

        btnCreate.setOnMouseClicked(mouseEvent -> {


            String login = textFieldLogin.getText();
            String password = textFieldPassword.getText();
            String repeatpassword = textFieldPasswordRepeat.getText();

            int codeError = dbFunctions.check_login(login);

            if (login.isEmpty()) {
                ErrorText.setText("Заполните логин");
            } else if (password.isEmpty()) {
                ErrorText.setText("Заполните пароль");
            } else if (!password.equals(repeatpassword)) {
                ErrorText.setText("Пароли не совпадают");
            }  else if (codeError == 0) {
                ErrorText.setText("Такой логин уже существует");
            } else if (codeError == 404) {
                ErrorText.setText("Какая-то ошибка");
            }
            else {
                ErrorText.setText("");
                dbFunctions.createUser(login, password);
                new HelloApplication().openNewScene(rootPane, "/com/example/ekz/auth-screen.fxml", "Авторизация");
            }




        });


        AuthLabel.setOnMouseClicked(mouseEvent -> {
            new HelloApplication().openNewScene(rootPane, "/com/example/ekz/auth-screen.fxml", "Авторизация");
        });


    }

}