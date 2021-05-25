package sample;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class StaffController implements Initializable{


    @FXML
    private AnchorPane empDataPane;
    @FXML
    private Button addEmpBtn;
    @FXML
    private Button remEmpBtn;
    @FXML
    private Button backBtn;
    @FXML
    private TableView<Employee> empDataTable;
    @FXML
    private TableColumn<Employee, Integer> column1;
    @FXML
    private TableColumn<Employee, String> column2;
    @FXML
    private TableColumn<Employee, String> column3;
    @FXML
    private TableColumn<Employee, String> column4;
    @FXML
    private TableColumn<Employee, String> column5;
    @FXML
    private TableColumn<Employee, String> column6;
    @FXML
    private TableColumn<Employee, String> column7;
    @FXML
    private TextField firstnameText;
    @FXML
    private TextField surnameText;
    @FXML
    private TextField usernameText;
    @FXML
    private TextField passwordText;
    @FXML
    private Label titleLabel;




    public StaffManager staffManager = new StaffManager();

    public ObservableList<Employee> data;


    @Override
    public void initialize(URL url, ResourceBundle rb){



        try {
            data = staffManager.buildStaffData();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        column1.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("empNumber"));
        column2.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstname"));
        column3.setCellValueFactory(new PropertyValueFactory<Employee, String>("surname"));
        column4.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
        column5.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));
        column6.setCellValueFactory(new PropertyValueFactory<Employee, String>("type"));
        column7.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastLogin"));

        empDataTable.setItems(data);

    }


    @FXML
    private void goBack(ActionEvent click){
        try{

            Parent root1 = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root1);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


            stage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));
            stage.setTitle("Restaurant Billing System");
            stage.setScene(scene);
            stage.show();

            Stage stageToClose = (Stage) backBtn.getScene().getWindow();
            stageToClose.close();



        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @FXML
    private void remEmployee(ActionEvent event) throws SQLException, MalformedURLException {


        Employee empToRemove = empDataTable.getSelectionModel().getSelectedItem();

        if(empToRemove !=null){
            int selectedIndex  = empToRemove.getEmpNumber();




            //cannot remove admin user
            if(empToRemove.getUsername().equals("admin")){
                Alert alert = new Alert(AlertType.WARNING);

                Stage tempStage = (Stage) alert.getDialogPane().getScene().getWindow();
                tempStage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));

                alert.setTitle("Restaurant Billing System - WARNING");
                alert.setHeaderText("Root admin Removal Error");
                alert.setContentText("Cannot remove root admin.");

                alert.showAndWait();

            }
            else if (!(empToRemove.getUsername().equals("admin"))){

                Alert alert = new Alert(AlertType.CONFIRMATION);
                Stage tempStage = (Stage) alert.getDialogPane().getScene().getWindow();
                tempStage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));
                alert.setTitle("Restaurant Billing System - Confirmation Dialog");
                alert.setHeaderText("You are about to remove an employee:");
                alert.setContentText("Are you ok with this? - empID: "+selectedIndex+" will be deleted");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    staffManager.removeStaffData(selectedIndex);

                    data = staffManager.buildStaffData();
                    empDataTable.setItems(data);
                }

            }


        }else{


            //No selection

            Alert alert = new Alert(AlertType.WARNING);

            Stage tempStage = (Stage) alert.getDialogPane().getScene().getWindow();
            tempStage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));

            alert.setTitle("Restaurant Billing System - WARNING");
            alert.setHeaderText("No Employee Selected:");
            alert.setContentText("Please select an employee to remove.");

            alert.showAndWait();

        }

    }


    @FXML
    private void addEmployee(ActionEvent event) throws SQLException, MalformedURLException {


        String firstname = firstnameText.getText();
        String surname = surnameText.getText();
        String username = usernameText.getText();
        String password = passwordText.getText();

        Employee empToAdd = new Employee(firstname,surname,username,password);

        ArrayList<String> usernames = staffManager.getUsernames();

        if(firstname.equalsIgnoreCase("") || surname.equalsIgnoreCase("") || username.equalsIgnoreCase("")|| password.equalsIgnoreCase("")){

            Alert alert = new Alert(AlertType.ERROR);


            Stage tempStage = (Stage) alert.getDialogPane().getScene().getWindow();
            tempStage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));

            alert.setTitle("Restaurant Billing System - ERROR");
            alert.setHeaderText("Invalid employee data");
            alert.setContentText("Please enter employee data to add.");

            firstnameText.clear();
            surnameText.clear();
            usernameText.clear();
            passwordText.clear();

            alert.showAndWait();

        }else if(usernames.contains(username)){
            Alert alert = new Alert(AlertType.ERROR);


            Stage tempStage = (Stage) alert.getDialogPane().getScene().getWindow();
            tempStage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));

            alert.setTitle("Restaurant Billing System - ERROR");
            alert.setHeaderText("Username already Exists");
            alert.setContentText("Please enter a different username.");

            firstnameText.clear();
            surnameText.clear();
            usernameText.clear();
            passwordText.clear();

            alert.showAndWait();
        }else{

            firstnameText.clear();
            surnameText.clear();
            usernameText.clear();
            passwordText.clear();


            staffManager.addStaffData(empToAdd);
            data = staffManager.buildStaffData();
            empDataTable.setItems(data);
        }
    }


}
