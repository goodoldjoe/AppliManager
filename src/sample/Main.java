package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent login = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(login, 700, 450));
        primaryStage.show();


    }


    public static void main(String[] args) throws SQLException {
        Login login = new Login();
        login.connect();
        /*login.query();
        login.extract();*/
        login.prepQuery();
        login.cleanUp();
        launch(args);
    }
}
