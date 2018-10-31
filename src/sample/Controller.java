/*
This class handles all gui related funtions, e.g. methods that load different fxml
created by Zurbrügg, Dittrich, Studer
 */

package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;


public class Controller {
    private Login login = new Login();
    private Main main;

    @FXML
    private TextField userNameInput;
    @FXML
    private TextField pwInput;
    @FXML
    private Text errorText, errorTextRegister, user_name, user_name2;
    @FXML
    private MenuItem btn_theme, btn_bgc, btn_close, btn_delete, btn_logout;
    @FXML
    private MenuItem btn_bgc2, btn_theme2, btn_close2, btn_delete2, btn_logout2;
    @FXML
    private Button regiButt, submitButt, registerButton;
    @FXML
    private Button btn_phone, btn_calculator;
    @FXML
    private Button btn_phone2, btn_calculator2;
    @FXML
    private TextField userRegister;
    @FXML
    private TextField pwRegister;
    @FXML
    private TextField pwDoubleCheck;
    @FXML private Text unique; //error field for when we have duplicate username registration

    @FXML private javafx.scene.control.Label status;
    @FXML private javafx.scene.control.TextField textfield, text;
    @FXML private javafx.scene.control.Button nr1, nr2, nr3, nr4, nr5, nr6, nr7, nr8, nr9, nr0 ,nr12, nr11, hook, button;


    //this method opens the login page, it's the first window that opens
    public void sample(Main main) {
        this.main = main;
        submitButt.setOnAction(e -> {
            String user = userNameInput.getText();
            String pw = pwInput.getText();
            if (!login.connect()) {
                login.connect();
            }
            if (login.checkLogin(user, pw)) {
                System.out.println("Success!");
                try {
                    main.defautScene(user);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                errorText.setText("ERROR: Check yo details!");
                System.out.println("Fail!");
            }
        });

        regiButt.setOnAction(e -> {
            try {
                main.registerWindow();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        });
    }

    //this method is used to open the register window and then call the corresponding method in the login class
    public void register(Main main) {
        this.main = main;
        registerButton.setOnAction(e -> {
            String user = userRegister.getText();
            String pw = pwRegister.getText();
            String pwApprove = pwDoubleCheck.getText();
            if (pw.equals(pwApprove)) {
                if (login.register(user, pw)) {
                    System.out.println("YAY!");
                    //errorText.setText("You Registered successfully");
                    main.closeRegister();
                } else {
                    System.out.println("Aww!");
                    unique.setText("Username already exists!");
                }
            } else {
                errorTextRegister.setText("Password mismatch! Try again.");
            }
        });
    }

    //with this method we open the window in the desired layout
    public void theme(Main main, String user) {
        this.main = main;
        user_name.setText("Eingeloggt als " + user);
        btn_phone.setOnAction(e -> {
            main.openPhone();
        });
        btn_delete.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + user_name.getText() + "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setTitle("Delete Account");
            alert.setHeaderText("Benutzerkonto löschen");
            alert.setContentText("Möchten Sie Ihr Benutzerkonto wirklich löschen?");

            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                login.delete(user);
                 main.loginWindow();}
        });

        btn_close.setOnAction(e -> {
            main.closeMainstage();
        });
        btn_theme.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + user_name.getText() + "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setTitle("Theme");
            alert.setHeaderText("Change Theme");
            alert.setContentText("Möchten Sie das Theme wechseln?");

            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    if (login.getTheme(user) == 1){
                        try {
                            main.changeToTwo(user);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }else {
                        try {
                            main.changeToOne(user);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });
        btn_bgc.setOnAction(e -> {
            String java = "Orange";
            String csharp = "Blau";
            String python = "Grün";
            String white = "Weiss";

            String defaultString = csharp;

            ChoiceDialog<String> dialog = new ChoiceDialog<String>(defaultString, java, csharp, python, white);

            dialog.setTitle("Background");
            dialog.setHeaderText("Wählen Sie eine Hintergrundfarbe");
            dialog.setContentText("Auswahl:");

            Optional<String> result = dialog.showAndWait();
            String resultS = result.get();
            try {
                main.changeColor(resultS, user);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // eifach so?
            //text.setText(resultS);
        });
        btn_calculator.setOnAction(e -> {
            main.openCalculator();
        });
        btn_logout.setOnAction(e -> {
            main.loginWindow();
        });
    }

    //with this method we open the window in the desired layout
    public void theme2(Main main, String user){
        this.main = main;
        user_name2.setText("Eingeloggt als " + user);
        btn_calculator2.setOnAction(e -> {
            main.openCalculator();
        });
        btn_phone2.setOnAction(e -> {
            main.openPhone();
        });
        btn_delete2.setOnAction(e -> {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + user_name2.getText() + "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert2.setTitle("Delete Account");
            alert2.setHeaderText("Benutzerkonto löschen");
            alert2.setContentText("Möchten Sie Ihr Benutzerkonto wirklich löschen?");

            alert2.showAndWait();
            if (alert2.getResult() == ButtonType.YES) {
                login.delete(user);
                main.loginWindow();}
        });

        btn_logout2.setOnAction(e -> {
            main.loginWindow();
        });
        btn_bgc2.setOnAction(e -> {
            String java = "Orange";
            String csharp = "Blau";
            String python = "Grün";
            String white = "Weiss";

            String defaultString = csharp;

            ChoiceDialog<String> dialog = new ChoiceDialog<String>(defaultString, java, csharp, python, white);

            dialog.setTitle("Background");
            dialog.setHeaderText("Wählen Sie eine Hintergrundfarbe");
            dialog.setContentText("Auswahl:");

            Optional<String> result = dialog.showAndWait();
            String resultS = result.get();
            try {
                main.changeColor(resultS, user);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        btn_close2.setOnAction(e -> {
            main.closeMainstage();
        });
        btn_theme2.setOnAction(e -> {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + user_name2.getText() + "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert2.setTitle("Theme");
            alert2.setHeaderText("Change Theme");
            alert2.setContentText("Möchten Sie das Theme wechseln?");

            alert2.showAndWait();
            if (alert2.getResult() == ButtonType.YES) {
                try {
                    if (login.getTheme(user) == 1){
                        try {
                            main.changeToTwo(user);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }else {
                        try {
                            main.changeToOne(user);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });
    }

    //this method defines the java phone application and
    public void phone(Main main){
        this.main = main;
        hook.setText("hook On");
        nr1.setText("1");
        nr2.setText("2");
        nr3.setText("3");
        nr4.setText("4");
        nr5.setText("5");
        nr6.setText("6");
        nr7.setText("7");
        nr8.setText("8");
        nr9.setText("9");
        nr0.setText("0");
        nr11.setText("#");
        nr12.setText("NewWindow");
        hook.setText("hook On");
        status.setText("ready");



        nr1.setOnAction(e ->
                {
                    String text = textfield.getText();
                    textfield.setText(text +"1");

                }
        );


        textfield.appendText("1");

        nr2.setOnAction(e -> {
            String text = textfield.getText();
            textfield.setText(text +"2");});

        nr3.setOnAction(e -> {
            String text = textfield.getText();
            textfield.setText(text +"3");});

        nr4.setOnAction(e -> {
            String text = textfield.getText();
            textfield.setText(text +"4");});

        nr5.setOnAction(e -> {
            String text = textfield.getText();
            textfield.setText(text +"5");});

        nr6.setOnAction(e -> {
            String text = textfield.getText();
            textfield.setText(text +"6");});

        nr7.setOnAction(e -> {
            String text = textfield.getText();
            textfield.setText(text +"7");});

        nr8.setOnAction(e -> {
            String text = textfield.getText();
            textfield.setText(text +"8");});

        nr9.setOnAction(e -> {
            String text = textfield.getText();
            textfield.setText(text +"9");});

        nr0.setOnAction(e -> {
            String text = textfield.getText();
            textfield.setText(text +"0");});

        nr11.setOnAction(e -> {
            String text = textfield.getText();
            textfield.setText(text +"#");});

        //nr12.setOnAction((event) -> { main.newWindow();});


        hook.setOnAction(e -> {
            if ("hook On".equals(hook.getText())) {
                status.setText("connected..");
                hook.setText("hook Off");

            }
            else {
                status.setText("ready..");
                hook.setText("hook On");
                textfield.clear();}});
    }
    }

    /* das ist im setOnAction für den submitButt
    @FXML private void loginProcess(Main main) throws Exception { //

        this.main = main;
        String user = userNameInput.getText();
        String pw = pwInput.getText();
        if (!login.connect()) {
            login.connect();
        }
        if(login.checkLogin(user, pw)) {
            System.out.println("Success!");
            main.defautScene(user);
        } else {
            errorText.setText("ERROR: Check yo details!");
            System.out.println("Fail!");
        }
    }

    @FXML private void openRegisterWindow() {
        this.main = main;
        try {
            main.registerWindow();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/
