package sample;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class EmployeeHomeController {


    @FXML
    private Label welcomeLabel;
    @FXML
    private Label empIdLabel;
    @FXML
    private Label ordersLabel;
    @FXML
    private Label addOrderLabel;
    @FXML
    private Button logOutBtn;
    @FXML
    private Region addOrderRegion;
    @FXML
    private Region ordersRegion;

    private String currentUsername = LoginController.username;
    private String currentPassword = LoginController.password;


    private StaffManager staffManager = new StaffManager();
    private TableManager tableManager = new TableManager();


    public void setEmpID(int empId){

        empIdLabel.setText("EmpID: "+Integer.toString(empId));
        empIdLabel.setFont(new Font("System", 24));

    }


    public void setEmployeeName(int empID) throws SQLException{

        String[] fullName = staffManager.retrieveEmpName(empID);

        String firstname = fullName[0];
        String surname = fullName[1];


        welcomeLabel.setText("Welcome, "+firstname+" "+surname);
        welcomeLabel.setFont(new Font("System", 22));


    }



    @FXML
    private void logOut(ActionEvent click){


        try{

            Parent root1 = FXMLLoader.load(getClass().getResource("Login.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root1);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


            stage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));
            stage.setTitle("Restaurant Billing System");
            stage.setScene(scene);
            stage.show();

            Stage stageToClose = (Stage) logOutBtn.getScene().getWindow();
            stageToClose.close();



        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @FXML
    private void goToAddOrder(MouseEvent click) throws SQLException{

        Stage stageToClose = (Stage) addOrderRegion.getScene().getWindow();
        stageToClose.close();

        int currentEmpId = staffManager.retrieveConnectedEmpId(currentUsername, currentPassword);

        try{

            FXMLLoader loader2 = new FXMLLoader();

            Parent root =  loader2.load(getClass().getResource("Tables.fxml").openStream());
            TablesController tablesController = (TablesController) loader2.getController();

            tablesController.disableBtn(TablesController.getStaffType());
            tablesController.setEmpId(currentEmpId);


            for(int tableNum =1; tableNum<=7; tableNum++ ){

                try {

                    int numberOfPeople = tableManager.getNumberOfPeople(tableNum);

                    Table table = new Table();

                    table.setNumberOfPeople(numberOfPeople);
                    table.setTableNumber(tableNum);


                    tablesController.updateNumberOfPeople(table, numberOfPeople);



                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());



            stage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));
            stage.setTitle("Orders");
            stage.setScene(scene);
            stage.show();




        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    private void goToOrderHistory(MouseEvent click) throws SQLException{

        Stage stageToClose = (Stage) addOrderRegion.getScene().getWindow();
        stageToClose.close();



        try{


            Parent root =  FXMLLoader.load(getClass().getResource("OrderHistory.fxml"));


            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());



            stage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));
            stage.setTitle("Order History");
            stage.setScene(scene);
            stage.show();




        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
