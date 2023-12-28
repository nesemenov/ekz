package com.example.ekz;

import com.example.ekz.DbFunctions.DbFunctions;
import com.example.ekz.DbFunctions.Variables;
import com.example.ekz.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainController {

    @FXML
    private ImageView updateImage;

    @FXML
    private ImageView backImage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableColumn<?, ?> tableColumnId;

    @FXML
    private TableColumn<?, ?> tableColumnLogin;

    @FXML
    private TableColumn<?, ?> tableColumnPassword;

    @FXML
    private TableColumn<?, ?> tableColumnRole;

    @FXML
    private TableColumn<?, ?> tableColumnStatus;

    @FXML
    private TableView<User> tableView;

    private final DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        tableColumnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tableColumnRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.setItems(dbFunctions.getAllUsers());

        if (Variables.ROLE_USER.equals("Администратор")) {
            tableView.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2) {
                    User user = tableView.getSelectionModel().getSelectedItem();

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ekz/update_user-screen.fxml"));
                        Parent parent = loader.load();
                        UpdateUserController updateUserController = loader.getController();
                        updateUserController.setData(user.getLogin(), user.getPassword(), user.getRole(), user.getId(), user.getStatus());

                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.setScene(new Scene(parent));
                        stage.setTitle("Редактирование пользователя");
                        stage.showAndWait();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }


        backImage.setOnMouseClicked(event -> {
            new HelloApplication().openNewScene(rootPane, "/com/example/ekz/auth-screen.fxml", "Главное меню");
            Variables.ROLE_USER = "";
            Variables.ACTIVE_USER = "";
        });
        updateImage.setOnMouseClicked(event -> {
            tableView.setItems(dbFunctions.getAllUsers());
        });
}
}
