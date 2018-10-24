package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterWindow {
    private Login login = new Login();

    @FXML
    private Button registerButton;

    @FXML
    private TextField userRegister;

    @FXML
    private TextField pwRegister;

    @FXML
    private TextField pwDoubleCheck;

    @FXML
    private Text errorText;

    @FXML
    private void registerProcess(){
        String user = userRegister.getText();
        String pw = pwRegister.getText();
        String pwApprove = pwDoubleCheck.getText();
        if (pw.equals(pwApprove)) {
            if (login.register(user, pw)) {
                System.out.println("YAY!");
            } else {
                System.out.println("Aww!");
            }
        } else {
            errorText.setText("Password mismatch! Try again.");
        }
    }
}
