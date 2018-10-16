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
    private void loginProcess() throws SQLException {
        String user = userNameInput.getText();
        String pw = pwInput.getText();

        if(login.checkLogin(user, pw)) {
            System.out.println("Success!");
            login.cleanUp();
        } else {
            System.out.println("Fail!");
            login.cleanUp();
        }
    }
}
