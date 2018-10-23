package sample;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class Controller {
    private Login login = new Login();

    @FXML
    private TextField userNameInput;

    @FXML
    private TextField pwInput;

    @FXML
    private void loginProcess() {
        String user = userNameInput.getText();
        String pw = pwInput.getText();
        if (!login.connect()) {
            login.connect();
        }
        if(login.checkLogin(user, pw)) {
            System.out.println("Success!");
            login.cleanUp();
        } else {
            System.out.println("Fail!");
            login.cleanUp();
        }
    }

    @FXML
    private void registerProcess(){
        String user = userNameInput.getText();
        String pw = pwInput.getText();
        boolean connected = false;
        if (!login.connect()) {
            connected = login.connect();
        }

        if (login.register(user, pw)) {
            System.out.println("YAY!");
        } else {
            System.out.println("Aww!");
        }

        if (connected) {
            login.cleanUp();
        }
    }
}
