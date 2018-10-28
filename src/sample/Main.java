package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    Stage window;
    Stage newWindow = new Stage();
    Stage mainstage = new Stage();
    AnchorPane pane;
    //BorderPane pane2;
    Controller controller;



    private double result;
    private double value1;
    private double value2;

    private TextField textfield;
    private String operator;
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
        loginWindow();
    }
    public void loginWindow(){
        //Parent login = FXMLLoader.load(getClass().getResource("sample.fxml"));
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
        BorderPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            newWindow.setScene(new Scene(pane, 451, 227));
            newWindow.initOwner(window);
            newWindow.initModality(Modality.APPLICATION_MODAL);
            opened = true;
        }
        newWindow.showAndWait();
    }
    public void defautScene(String user) throws Exception {
        preverence.connect();
        if (preverence.getTheme(user) == 1){
            changeToOne(user);
        }else{
            changeToTwo(user);
        }
        System.out.println("hallo");

        //window.show();
    }
    public void changeToOne(String user) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("theme1.fxml"));
        preverence.savePreference(user, "theme", 1);
        AnchorPane pane = loader.load();
        System.out.println("Bubu");
        //scene.setRoot(solo);
        Controller controller = loader.getController();
        controller.theme(this, user);
        System.out.println("nachsetRoot");
        switch (preverence.getBgc( user)){
            case 1:
                pane.setStyle("-fx-background-color: blue;");
                break;
            case 2:
                pane.setStyle("-fx-background-color: orange;");
                break;
            case 3:
                pane.setStyle("-fx-background-color: green;");
                break;
            case 4:
                pane.setStyle("-fx-background-color: white;");
                break;
        }
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
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("theme2.fxml"));
        preverence.savePreference(user, "theme", 2);
        AnchorPane pane = loader.load();
        System.out.println("Bubu");
        //scene.setRoot(solo);
        Controller controller = loader.getController();
        controller.theme2(this, user);

        System.out.println("nachsetRoot");
        switch (preverence.getBgc( user)){
            case 1:
                pane.setStyle("-fx-background-color: blue;");
                break;
            case 2:
                pane.setStyle("-fx-background-color: orange;");
                break;
            case 3:
                pane.setStyle("-fx-background-color: green;");
                break;
            case 4:
                pane.setStyle("-fx-background-color: white;");
                break;
        }
        Scene scene = new Scene(pane, 700, 450);
        if (scene == null)
            System.out.println("scene is null");
        window.setScene(scene);

        window.show();
    }
    public void closeRegister(){
        newWindow.close();
    }
    public void  closeMainstage(){
        window.close();
    }
    public void changeColor(String color, String user) throws IOException {
        int theme = 1;
        FXMLLoader loader;
        AnchorPane pane;
        try {
           theme =  preverence.getTheme(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (theme == 1) {
             loader = new FXMLLoader(Main.class.getResource("theme1.fxml"));
             pane = loader.load();
            System.out.println("Bubu");
            //scene.setRoot(solo);
            Controller controller = loader.getController();
            controller.theme(this, user);
        }else{
            loader = new FXMLLoader(Main.class.getResource("theme2.fxml"));
            pane = loader.load();
            System.out.println("Bubu");
            //scene.setRoot(solo);
            Controller controller = loader.getController();
            controller.theme2(this, user);
        }

        switch(color){
            case "Orange":
                System.out.println(pane);
                pane.setStyle("-fx-background-color: orange;");
                try {
                    preverence.savePreference(user, "bgc", 2);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "Blau":
                pane.setStyle("-fx-background-color: blue;");
                try {
                    preverence.savePreference(user, "bgc", 1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "Grün":
                pane.setStyle("-fx-background-color: green;");
                try {
                    preverence.savePreference(user, "bgc", 3);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "Weiss":
                pane.setStyle("-fx-background-color: white;");
                try {
                    preverence.savePreference(user, "bgc", 4);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        Scene scene = new Scene(pane, 700, 450);
        window.setScene(scene);
    }

    public void openCalculator(){
        BorderPane pane2 = new BorderPane();
        Button minus, mal, durch, gleich, clear, wurzel, plusminus, nr1, nr2, nr3, nr4, nr5, nr6, nr7, nr8, nr9, nr0, plus, komma;

        textfield = new TextField();

        textfield.setEditable(false);
        textfield.setMinWidth(300);
        textfield.setMinHeight(40);

        nr1    = new Button("1");
        nr2    = new Button("2");
        nr3    = new Button("3");
        nr4    = new Button("4");
        nr5    = new Button("5");
        nr6    = new Button("6");
        nr7    = new Button("7");
        nr8    = new Button("8");
        nr9    = new Button("9");
        nr0    = new Button("0");

        plus   = new Button("+");
        minus  = new Button("-");
        mal    = new Button("*");
        durch  = new Button("/");
        gleich = new Button("=");
        clear  = new Button("C");

        wurzel    = new Button("√");
        plusminus = new Button("+/-");
        komma     = new Button(".");

        nr1.setMinSize   (40, 40);
        nr2.setMinSize   (40, 40);
        nr3.setMinSize   (40, 40);
        nr4.setMinSize   (40, 40);
        nr5.setMinSize   (40, 40);
        nr6.setMinSize   (40, 40);
        nr7.setMinSize   (40, 40);
        nr8.setMinSize   (40, 40);
        nr9.setMinSize   (40, 40);
        nr0.setMinSize   (90, 40);


        plus.setMinSize    (40, 40);
        minus.setMinSize   (40, 40);
        mal.setMinSize	   (40, 40);
        durch.setMinSize   (40, 40);

        gleich.setMinSize  (100, 40);
        clear.setMinSize   (100, 40);

        wurzel.setMinSize  (100, 40);
        plusminus.setMinSize(100, 40);
        komma.setMinSize   (40, 40);

        nr1.setOnAction(e -> {
            textfield.appendText("1");
        });
        nr2.setOnAction(e -> {
            textfield.appendText("2");
        });
        nr3.setOnAction(e -> {
            textfield.appendText("3");
        });
        nr4.setOnAction(e -> {
            textfield.appendText("4");
        });
        nr5.setOnAction(e -> {
            textfield.appendText("5");
        });
        nr6.setOnAction(e -> {
            textfield.appendText("6");
        });
        nr7.setOnAction(e -> {
            textfield.appendText("7");
        });
        nr8.setOnAction(e -> {
            textfield.appendText("8");
        });
        nr9.setOnAction(e -> {
            textfield.appendText("9");
        });
        nr0.setOnAction(e -> {
            textfield.appendText("0");
        });

        komma.setOnAction(e -> {
            textfield.appendText(".");
            komma.setDisable(true);
        });

        clear.setOnAction(e -> {
            textfield.clear();
            komma.setDisable(false);
            plus.setDisable(false);
            minus.setDisable(false);
            mal.setDisable(false);
            durch.setDisable(false);
            wurzel.setDisable(false);
            plusminus.setDisable(false);
        });

        plus.setOnAction(e -> {
            operator("+");
            komma.setDisable(false);
            plus.setDisable(true);
            minus.setDisable(true);
            mal.setDisable(true);
            durch.setDisable(true);
            wurzel.setDisable(true);
            plusminus.setDisable(true);
        });
        minus.setOnAction(e -> {
            operator("-");
            komma.setDisable(false);
            plus.setDisable(true);
            minus.setDisable(true);
            mal.setDisable(true);
            durch.setDisable(true);
            wurzel.setDisable(true);
            plusminus.setDisable(true);
        });
        mal.setOnAction(e -> {
            operator("*");
            komma.setDisable(false);
            plus.setDisable(true);
            minus.setDisable(true);
            mal.setDisable(true);
            durch.setDisable(true);
            wurzel.setDisable(true);
            plusminus.setDisable(true);
        });
        durch.setOnAction(e -> {
            operator("/");
            komma.setDisable(false);
            plus.setDisable(true);
            minus.setDisable(true);
            mal.setDisable(true);
            durch.setDisable(true);
            wurzel.setDisable(true);
            plusminus.setDisable(true);
        });

        wurzel.setOnAction(e -> {
            operator("√");
            komma.setDisable(false);
            plus.setDisable(false);
            minus.setDisable(false);
            mal.setDisable(false);
            durch.setDisable(false);
            wurzel.setDisable(false);
            plusminus.setDisable(false);
        });
        plusminus.setOnAction(e -> {
            operator("+/-");
            komma.setDisable(false);
            plus.setDisable(false);
            minus.setDisable(false);
            mal.setDisable(false);
            durch.setDisable(false);
            wurzel.setDisable(false);
            plusminus.setDisable(false);
        });

        gleich.setOnAction( e  -> {
            equals();

            plus.setDisable(false);
            minus.setDisable(false);
            mal.setDisable(false);
            durch.setDisable(false);
            wurzel.setDisable(false);
            plusminus.setDisable(false);
        });

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(20);
        hbox.getChildren().add(textfield);

        MenuBar menu = new MenuBar();
        menu.prefWidthProperty().bind(calculator.widthProperty());

        Menu properties     = new Menu("Properties");
        MenuItem background = new MenuItem("Background Color");

        properties.getItems().add(background);
        menu.getMenus().add(properties);
        background.setOnAction( e -> openSettings(pane2));


        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(0,10,0,10));
        flow.setVgap(10);
        flow.setHgap(10);
        flow.getChildren().addAll(nr7, nr8, nr9, plus, clear, nr4, nr5, nr6,
                minus, wurzel, nr1, nr2, nr3, mal, plusminus, nr0, komma, durch, gleich);

        pane2.setPadding(new Insets(0,0,10,0));
        pane2.setBottom(flow);
        pane2.setTop(menu);
        pane2.setCenter(hbox);

        Scene scene = new Scene(pane2, 310, 280);

        calculator.setTitle("Calculator");
        calculator.setScene(scene);
        calculator.setResizable(false);
        calculator.show();
    }

    private void equals(){
        value2 = Double.parseDouble(textfield.getText());

        switch(operator) {
            case "+":
                result = value1 + value2;
                break;
            case "-":
                result = value1 - value2;
                break;
            case "*":
                result = value1 * value2;
                break;
            case "/":
                result = value1 / value2;
                break;
            default:
                textfield.setText("OPERATOR_FALSE_OR_INVALID");
        }
        textfield.setText(Double.toString((result)));
    }

    private void operator(String value){
        value1 = Double.parseDouble(textfield.getText());
        operator = value;

        textfield.clear();

        switch(operator) {
            case "√":
                result = Math.sqrt(value1);
                textfield.setText(Double.toString((result)));
                break;
            case "+/-":
                result = value1 * (-1);
                textfield.setText(Double.toString((result)));
                break;
        }
    }

    private void openSettings(BorderPane pane3) {

        Label label;
        label = new Label("Choose the Preferd Background Color:");
        label.setPadding(new Insets(10, 0 , 50, 45));

        Button rosa, orange, schwarz, blau;
        rosa   = new Button("Pink");
        orange = new Button("Orange");
        schwarz  = new Button("Black");
        blau   = new Button("Blue");

        rosa.setMinSize   (65, 50);
        orange.setMinSize (65, 50);
        schwarz.setMinSize  (65, 50);
        blau.setMinSize   (65, 50);

        rosa.setOnAction(e    ->
                color("pink", pane3));
        orange.setOnAction( e->
                color("orange", pane3));
        schwarz.setOnAction(e   ->
                color("black", pane3));
        blau.setOnAction(e   ->
                color("blue", pane3));

        FlowPane buttons = new FlowPane();
        buttons.setPadding(new Insets(0,10,0,10));
        buttons.setHgap(5);
        buttons.getChildren().addAll(rosa, orange, schwarz, blau);

        BorderPane settingsLayout = new BorderPane();
        settingsLayout.setCenter(buttons);
        settingsLayout.setTop(label);

        Stage newWindow = new Stage();
        newWindow.setTitle("Background Color");
        newWindow.setScene(new Scene(settingsLayout, 295, 200));

        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.show();
    }

    private void color(String backgroundcolor, BorderPane pane4) {
        switch(backgroundcolor) {

            case "pink":
                pane4.setStyle("-fx-background-color: pink;");
                break;
            case "orange":
                pane4.setStyle("-fx-background-color: orange;");
                break;
            case "black":
                pane4.setStyle("-fx-background-color: black;");
                break;
            case "blue":
                pane4.setStyle("-fx-background-color: blue;");
                break;
        }
    }
    public void openPhone(){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("MainWindow.fxml"));
        try {
            AnchorPane pane = loader.load();

            phone.setMinHeight(400.00);
            phone.setMinWidth(350.00);
            phone.setTitle("Java Phone");

            //für die logik in der scene loader macht die verbindung

            Controller controller = loader.getController();
            controller.phone(this);

            Scene scene = new Scene(pane);
            phone.setScene(scene);
            phone.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {

        launch(args);

    }
}