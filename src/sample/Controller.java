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
            try {
                login.cleanUp();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Fail!");
            try {
                login.cleanUp();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
