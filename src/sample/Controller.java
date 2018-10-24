package sample;

import javafx.fxml.FXML;
import java.io.IOException;
import java.util.Optional;

import com.sun.javafx.logging.Logger;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Controller {
    @FXML
    private javafx.scene.control.Label status, label;
    @FXML private javafx.scene.control.TextField textfield, text;
    @FXML private javafx.scene.control.Button btn1, btn2;

    public Main main;

    //das erlaubt uns auf alle methoden aus der Main kalsse zugreifen zu können
    public void setMain(Main main) {
        this.main = main;

        btn1.setOnAction(event -> {
            main.newWindow();
        });

        btn2.setOnAction(event -> {
            System.out.println("ey");
        });
    }

    public void setNewWindow(Main main) {
        this.main = main;


    }
}
