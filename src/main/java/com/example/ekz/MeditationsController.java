package com.example.ekz;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.ekz.DbFunctions.DbFunctions;
import com.example.ekz.DbFunctions.Variables;
import com.example.ekz.Models.Meditations;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MeditationsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView backImage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableColumn<?, ?> tableColumnidmedex;

    @FXML
    private TableColumn<?, ?> tableColumnmname;

    @FXML
    private TableColumn<?, ?> tableColumntimetocomplete;

    @FXML
    private TableColumn<?, ?> tableColumntimetorest;

    @FXML
    private TableView<Meditations> tableView;

    @FXML
    private ImageView updateImage;

    private final DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {

        tableColumnidmedex.setCellValueFactory(new PropertyValueFactory<>("idMed"));
        tableColumntimetocomplete.setCellValueFactory(new PropertyValueFactory<>("timeToComplite"));
        tableColumntimetorest.setCellValueFactory(new PropertyValueFactory<>("timeToReseat"));
        tableColumnmname.setCellValueFactory(new PropertyValueFactory<>("nameMed"));

        tableView.setItems(dbFunctions.getAllMeditations());

        updateImage.setOnMouseClicked(mouseEvent -> {

            tableColumnidmedex.setCellValueFactory(new PropertyValueFactory<>("idMed"));
            tableColumntimetocomplete.setCellValueFactory(new PropertyValueFactory<>("timeToComplite"));
            tableColumntimetorest.setCellValueFactory(new PropertyValueFactory<>("timeToReseat"));
            tableColumnmname.setCellValueFactory(new PropertyValueFactory<>("nameMed"));

            tableView.setItems(dbFunctions.getAllMeditations());
        });

        backImage.setOnMouseClicked(event -> {
            new HelloApplication().openNewScene(rootPane, "/com/example/ekz/auth-screen.fxml", "Главное меню");
            Variables.ROLE_USER = "";
            Variables.ACTIVE_USER = "";
        });


    }



}