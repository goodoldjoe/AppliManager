package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    Stage window;
    Stage newWindow = new Stage();
    Stage mainstage = new Stage();
    AnchorPane pane;
    Controller controller;
    //String user;
    //AnchorPane pane = new AnchorPane();
    //Scene scene = new Scene(pane);

    Stage phone = new Stage();
    Stage calculator = new Stage();

    boolean opened = false;
    Login preverence = new Login();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.window = primaryStage;
        //Parent login = FXMLLoader.load(getClass().getResource("sample.fxml"));
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
        BorderPane pane = loader.load();
        window.setTitle("AppliManager");
        Controller controller = loader.getController();
        controller.sample(this);
        System.out.println(controller);
        window.setScene(new Scene(pane, 700, 450));
        //Scene scene = new Scene(pane);
        //window.setScene(scene);
        window.show();
    }

    public void registerWindow() throws Exception{
        if (!opened){
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("registerWindow.fxml"));
            AnchorPane pane = loader.load();
            Controller controller = loader.getController();
            controller.register(this);
            System.out.println(controller);
            newWindow.setScene(new Scene(pane, 700, 450));
            newWindow.initOwner(window);
            newWindow.initModality(Modality.APPLICATION_MODAL);
            opened = true;
        }
        newWindow.showAndWait();
    }
    public void defautScene(String user) throws Exception {
        preverence.connect();
        if (preverence.checkPreverence("theme", user)){
            changeToOne(user);
        }else{
            changeToTwo(user);
        }
        preverence.checkPreverence("backgroundcolor", user);
        System.out.println("hallo");

        //window.show();
    }
    public void changeToOne(String user) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("theme1.fxml"));
        AnchorPane pane = loader.load();
        System.out.println("Bubu");
        //scene.setRoot(solo);
        Controller controller = loader.getController();
        controller.theme1(this, user);
        System.out.println("nachsetRoot");
        Scene scene = new Scene(pane, 700, 450);
        if (scene == null)
            System.out.println("scene is null");
        window.setScene(scene);

        window.show();

        //FXMLLoader loader = new FXMLLoader(Main.class.getResource("theme1.fxml"));
        //System.out.println("Bubu");
      // AnchorPane pane = loader.load();
       // System.out.println("nachloader");
       //Scene scene = new Scene(pane);
       // System.out.println("vorscene");
     //  window.setScene(scene);
     //  System.out.println("nachscene");
     //   window.show();
    }
    public void changeToTwo(String user) throws Exception{
        Parent solo = FXMLLoader.load(getClass().getResource("theme2.fxml"));
        System.out.println("Bubu");
        mainstage.setScene(new Scene(solo, 700, 450));
        mainstage.showAndWait();
    }
    public void closeRegister(){
        newWindow.close();
    }
    public void  closeMainstage(){

        window.close();
    }
    public void changeColor(String color, String user) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("theme1.fxml"));
        AnchorPane pane = loader.load();
        System.out.println("Bubu");
        //scene.setRoot(solo);
        Controller controller = loader.getController();
        controller.theme1(this, user);
        switch(color){
            case "Orange":
                System.out.println(pane);
                pane.setStyle("-fx-background-color: orange;");
                break;
            case "Blau":
                pane.setStyle("-fx-background-color: blue;");
                break;
            case "Grün":
                pane.setStyle("-fx-background-color: green;");
                break;
        }
        Scene scene = new Scene(pane, 700, 450);
        window.setScene(scene);
    }

    public static void main(String[] args) throws SQLException {

        launch(args);

    }
}