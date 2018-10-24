package sample;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {
    private Login login = new Login();
    private Main main = new Main();

    @FXML
    private TextField userNameInput;

    @FXML
    private TextField pwInput;

    @FXML
    private Text errorText;

    @FXML
    private void loginProcess() {
        String user = userNameInput.getText();
        String pw = pwInput.getText();
        if (!login.connect()) {
            login.connect();
        }
        if(login.checkLogin(user, pw)) {
            System.out.println("Success!");
        } else {
            errorText.setText("ERROR: Check yo details!");
            System.out.println("Fail!");
        }
    }

    @FXML
    private void openRegisterWindow() {
        try {
            System.out.println("kakspasst");
            main.registerWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
