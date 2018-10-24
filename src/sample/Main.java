package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;

public class    Main extends Application {
    Stage window;
    Stage window2 = new Stage();
    @FXML private javafx.scene.control.Button  button;


    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        mainWindow();
    }
    public void mainWindow(){
        try{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
        AnchorPane pane = loader.load();
        window.setTitle("Hello Sir");

        Controller controller = loader.getController();
        controller.setMain(this);

        window.setScene(new Scene(pane, 600, 400));
        window.show();

    }catch(IOException e) {
            e.printStackTrace();
        }
        }
    public void newWindow(){
        FXMLLoader root2 = new FXMLLoader(Main.class.getResource("sample2.fxml"));
        try {
            AnchorPane pane = root2.load();
            window.setTitle("Hello Sir");

            Controller controller = root2.getController();
            controller.setNewWindow(this);
            window.setScene(new Scene(pane, 600, 400));
            //nur für die zweiten fenster
            //window2.show();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
