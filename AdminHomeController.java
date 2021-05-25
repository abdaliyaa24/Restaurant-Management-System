package sample;


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
import javafx.stage.Stage;


public class AdminHomeController {

    @FXML
    private Region ordersRegion;
    @FXML
    private Region staffRegion;
    @FXML
    private Region menuManagerRegion;
    @FXML
    private Region addOrderRegion;
    @FXML
    private Button logOutBtn;
    @FXML
    private Label adminHomeTitleLabel;

    private String currentUsername = LoginController.username;
    private String currentPassword = LoginController.password;


    private StaffManager staffManager = new StaffManager();

    @FXML
    private void logOut(ActionEvent event){


        try{
            Parent root =  FXMLLoader.load(getClass().getResource("Login.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


            stage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));
            stage.setTitle("Restaurant Management System");
            stage.setScene(scene);
            stage.show();

            Stage stageToClose = (Stage) logOutBtn.getScene().getWindow();
            stageToClose.close();


        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void goToStaff(MouseEvent click){


        try{
            Parent root =  FXMLLoader.load(getClass().getResource("Staff.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


            stage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));
            stage.setTitle("Staff Manager");
            stage.setScene(scene);
            stage.show();

            Stage stageToClose = (Stage) staffRegion.getScene().getWindow();
            stageToClose.close();


        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    private void goToAddOrder(MouseEvent click) throws SQLException{

        int currentEmpId = staffManager.retrieveConnectedEmpId(currentUsername, currentPassword);

        try{

            FXMLLoader loader2 = new FXMLLoader();
            Parent root =  loader2.load(getClass().getResource("Tables.fxml").openStream());
            TablesController tablesController = (TablesController) loader2.getController();

            tablesController.disableBtn(TablesController.getStaffType());
            tablesController.setEmpId(currentEmpId);




            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            stage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));
            stage.setTitle("Orders");
            stage.setScene(scene);
            stage.show();

            Stage stageToClose = (Stage) addOrderRegion.getScene().getWindow();
            stageToClose.close();


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



    @FXML
    private void editMenu(MouseEvent click){


        try{
            Parent root =  FXMLLoader.load(getClass().getResource("Menu.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


            stage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));
            stage.setTitle("Menu Editor");
            stage.setScene(scene);
            stage.show();

            Stage stageToClose = (Stage) menuManagerRegion.getScene().getWindow();
            stageToClose.close();


        }catch(Exception e){
            e.printStackTrace();
        }
    }




}
