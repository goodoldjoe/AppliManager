package sample;


import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {
    private Login login = new Login();
    private Main main = new Main();

    @FXML private TextField userNameInput;
    @FXML private TextField pwInput;
    @FXML private Text errorText;
    @FXML private MenuItem btn_save, btn_close;


    @FXML private void loginProcess() throws Exception {
        String user = userNameInput.getText();
        String pw = pwInput.getText();
        if (!login.connect()) {
            login.connect();
        }
        if(login.checkLogin(user, pw)) {
            System.out.println("Success!");
            main.mainstage(user);
        } else {
            errorText.setText("ERROR: Check yo details!");
            System.out.println("Fail!");
        }
    }

    @FXML private void openRegisterWindow() {
        try {
            main.registerWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML private void  saveChanges(){

    }

    @FXML private void close(){

    }
}