package com.example.ekz;

import com.example.ekz.DbFunctions.DbFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UpdateUserController {
    @FXML
    private ImageView closeImage;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private Label ErrorLabel;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonSave;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;



    DbFunctions dbFunctions = new DbFunctions();
    String idUser = "";

    @FXML
    void initialize() {

        roleComboBox.getItems().add("Администратор");
        roleComboBox.getItems().add("Пользователь");
        statusComboBox.getItems().add("true");
        statusComboBox.getItems().add("false");

        buttonSave.setOnAction(e -> {
                updateDataUser();
        });
        buttonDelete.setOnAction(e -> deleteDataUser());

    }



    private void deleteDataUser() {
        if (idUser.equals("")) {
            ErrorLabel.setText("Повторите попытку позже");
        } else {
            dbFunctions.deleteDataUser(idUser);
            buttonDelete.getScene().getWindow().hide();

        }

    }

    private void updateDataUser(){

            String login = loginTextField.getText();
            String password = passwordTextField.getText();
            String role = roleComboBox.getSelectionModel().getSelectedItem();
            String status = statusComboBox.getSelectionModel().getSelectedItem();
            int codeError = dbFunctions.check_login(login);
            if (login.isEmpty()) {
            ErrorLabel.setText("Логин пустой");
            } else if (password.isEmpty()) {
            ErrorLabel.setText("Пароль пустой");
            } else if (role.isEmpty()) {
            ErrorLabel.setText("Роль пустая");
            } else if (idUser.equals("")) {
            ErrorLabel.setText("Повторите попытку позже");
            }
             else if (codeError == 404) {
            ErrorLabel.setText("Какая-то ошибка");
            } else {
            dbFunctions.updateDataUser(login, password, role, status, idUser);
            buttonSave.getScene().getWindow().hide();

            }


    }

    public void setData(String login, String password, String role, String id, String status) {
        loginTextField.setText(login);
        roleComboBox.setValue(role);
        statusComboBox.setValue(status);
        idUser = id;
    }

    @FXML
    public void Exit(MouseEvent event) {
        Stage stage = (Stage) closeImage.getScene().getWindow();
        stage.close();
    }


}
