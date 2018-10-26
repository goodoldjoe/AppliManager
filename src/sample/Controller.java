package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    private Login login = new Login();
    private Main main;

    @FXML private TextField userNameInput;
    @FXML private TextField pwInput;
    @FXML private Text errorText, errorTextRegister,user_name;
    @FXML private MenuItem btn_theme, btn_bgc, btn_close;
    @FXML private Button regiButt, submitButt, registerButton;
    @FXML private TextField userRegister;
    @FXML private TextField pwRegister;
    @FXML private TextField pwDoubleCheck;


    public void sample(Main main){
        this.main = main;
        submitButt.setOnAction(e -> {
            String user = userNameInput.getText();
            String pw = pwInput.getText();
            if (!login.connect()) {
                login.connect();
            }
            if(login.checkLogin(user, pw)) {
                System.out.println("Success!");
                try {
                    main.defautScene(user);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                errorText.setText("ERROR: Check yo details!");
                System.out.println("Fail!");
            }
        });

        regiButt.setOnAction(e -> {
            try {
                main.registerWindow();
            }catch (Exception e2) {
                e2.printStackTrace();
            }
        });
    }
    public void register(Main main){
        this.main = main;
        registerButton.setOnAction(e -> {
            String user = userRegister.getText();
            String pw = pwRegister.getText();
            String pwApprove = pwDoubleCheck.getText();
            if (pw.equals(pwApprove)) {
                if (login.register(user, pw)) {
                    System.out.println("YAY!");
                    //errorText.setText("You Registered successfully");
                    main.closeRegister();
                } else {
                    System.out.println("Aww!");
                }
            } else {
                errorTextRegister.setText("Password mismatch! Try again.");
            }
        });
    }
    public void theme1(Main main, String user){
        this.main = main;
        user_name.setText("Eingeloggt als " +user);

        btn_close.setOnAction(e -> {
            main.closeMainstage();
        });
        btn_theme.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + user_name.getText()+ "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setTitle("Theme");
            alert.setHeaderText("Change Theme");
            alert.setContentText("Möchten Sie das Theme wechseln?");

            alert.showAndWait();
        });
        btn_bgc.setOnAction(e -> {
            String java = "Orange";
            String csharp = "Blau";
            String python = "Grün";

            String defaultString = csharp;

            ChoiceDialog<String> dialog = new ChoiceDialog<String>(defaultString, java, csharp, python);

            dialog.setTitle("Background");
            dialog.setHeaderText("Wählen Sie eine Hintergrundfarbe");
            dialog.setContentText("Auswahl:");

            Optional<String> result = dialog.showAndWait();
            String resultS = result.get();
            try {
                main.changeColor(resultS, user);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // eifach so?
            //text.setText(resultS);
        });
    }
    /* das ist im setOnAction für den submitButt
    @FXML private void loginProcess(Main main) throws Exception { //

        this.main = main;
        String user = userNameInput.getText();
        String pw = pwInput.getText();
        if (!login.connect()) {
            login.connect();
        }
        if(login.checkLogin(user, pw)) {
            System.out.println("Success!");
            main.defautScene(user);
        } else {
            errorText.setText("ERROR: Check yo details!");
            System.out.println("Fail!");
        }
    }

    @FXML private void openRegisterWindow() {
        this.main = main;
        try {
            main.registerWindow();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    @FXML private void  saveChanges(){

    }

    @FXML private void close(){
        main.closeMainstage();
    }
}