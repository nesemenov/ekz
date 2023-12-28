package com.example.ekz;


import com.example.ekz.DbFunctions.DbFunctions;
import com.example.ekz.DbFunctions.Variables;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AuthController  {
    @FXML
    private Label ErrorText;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView closeImage;

    @FXML
    private Button btnAuth;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Label RegLabel;


    private final DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void Exit(MouseEvent event) {
        Stage stage = (Stage) closeImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        btnAuth.setOnAction(e -> validation());

        RegLabel.setOnMouseClicked(mouseEvent -> {
            new HelloApplication().openNewScene(rootPane, "/com/example/ekz/reg-screen.fxml", "Регистрация");
        });
    }


    private void validation() {
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();
        int codeError = dbFunctions.signIn(login, password);
        if (login.isEmpty()) {
            ErrorText.setText("Логин пустой");
        } else if(password.isEmpty()) {
            ErrorText.setText("Пароль пустой");
        } else if(codeError == 0) {
            ErrorText.setText("Не найден аккаунт");
        } else if(codeError == 404) {
            ErrorText.setText("Какая-то ошибка");
        }
        else {
            ErrorText.setText("");
            new HelloApplication().openNewScene(rootPane, "/com/example/ekz/Med-screen.fxml", "Главное меню");

        }

    }
}
