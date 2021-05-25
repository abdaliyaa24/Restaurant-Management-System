package sample;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;





public class LoginController  {



    @FXML
    private AnchorPane logInPane;
    @FXML
    private Button logInButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button adminLoginBtn;
    @FXML
    private Label lblStatus;
    @FXML
    private Label isConnected;
    @FXML
    private Label timeLabel;
    @FXML
    private Label title;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private DigitalClock digitalClock;


    public static String username;
    public static String password;
    public static int empId;
    public static String loginTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());


    private StaffManager staffManager = new StaffManager();




    @FXML
    private void exitApp(ActionEvent exit){
        System.exit(0);
    }



    private void login(ActionEvent event){

        try {
            String username = usernameField.getText();
            String password = passwordField.getText();

            int empID = staffManager.retrieveConnectedEmpId(username, password);

            //updating static variables
            LoginController.username = username;
            LoginController.password = password;
            LoginController.empId= empID;

            staffManager.updateLastLogin();


            if(staffManager.isAdminLogin(username, password)){




                try{


                    AnchorPane root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));

                    Stage tablesStage = new Stage();
                    Scene scene = new Scene(root);

                    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


                    tablesStage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));
                    tablesStage.setTitle("Restaurant Billing System");
                    tablesStage.setScene(scene);
                    tablesStage.show();




                    Stage stageToClose = (Stage) logInButton.getScene().getWindow();
                    stageToClose.close();


                }catch(Exception e){
                    e.printStackTrace();
                }


            }else if(staffManager.isLogin(username, password)){





                try{

                    FXMLLoader loader2 = new FXMLLoader();

                    Parent root = loader2.load(getClass().getResource("EmployeeHome.fxml").openStream());
                    EmployeeHomeController employeeHomeController = (EmployeeHomeController) loader2.getController();

                    employeeHomeController.setEmpID(empID);
                    employeeHomeController.setEmployeeName(empID);

                    Stage Stage = new Stage();
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


                    Stage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));
                    Stage.setTitle("Restaurant Billing System - Employee Home Menu");
                    Stage.setScene(scene);
                    Stage.show();

                    Stage stageToClose = (Stage) logInButton.getScene().getWindow();
                    stageToClose.close();

                }catch(IOException e){
                    e.printStackTrace();
                }

                //if login not in the database
            } else{


                Alert alert = new Alert(AlertType.WARNING);


                Stage tempStage = (Stage) alert.getDialogPane().getScene().getWindow();
                tempStage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));

                alert.setTitle("Restaurant Billing System - WARNING");
                alert.setHeaderText("Invalid Login Credentials - Login Failed");
                alert.setContentText("Please enter valid credentials or contact administrator for support!");
                alert.showAndWait();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}