package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {
    Stage window;
    Stage newWindow = new Stage();
    boolean opened = false;
    Login preverence = new Login();
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.window = primaryStage;
        Parent login = FXMLLoader.load(getClass().getResource("sample.fxml"));

        window.setTitle("AppliManager");
        window.setScene(new Scene(login, 700, 450));
        window.show();
    }

    public void registerWindow() throws Exception{
        if (!opened){
            Parent register = FXMLLoader.load(getClass().getResource("registerWindow.fxml"));
            newWindow.setScene(new Scene(register, 700, 450));
            newWindow.initOwner(window);
            newWindow.initModality(Modality.APPLICATION_MODAL);
            opened = true;
        }
        newWindow.showAndWait();
    }

    public static void main(String[] args) throws SQLException {

        launch(args);

    }

    public void mainstage(String user) throws Exception {
        preverence.connect();
        preverence.checkPreverence("theme", user);
        preverence.checkPreverence("backgroundcolor", user);
        System.out.println("hallo");
        Parent mainfxml = FXMLLoader.load(getClass().getResource("sample/theme1.fxml"));
        System.out.println("Bubu");
        window.setScene(new Scene(mainfxml, 700, 450));
        //window.showAndWait();
        window.show();
    }
}