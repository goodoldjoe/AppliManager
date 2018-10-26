package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterWindow {
    private Login login = new Login();
    private Main main;




    /*@FXML
    private void registerProcess(){
        String user = userRegister.getText();
        String pw = pwRegister.getText();
        String pwApprove = pwDoubleCheck.getText();
        if (pw.equals(pwApprove)) {
            if (login.register(user, pw)) {
                System.out.println("YAY!");
                main.closeRegister();
            } else {
                System.out.println("Aww!");
            }
        } else {
            errorText.setText("Password mismatch! Try again.");
        }
    }*/
}
