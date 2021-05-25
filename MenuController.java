package sample;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class MenuController implements Initializable {


    @FXML
    private AnchorPane menuDataPane;
    @FXML
    private Label titleLabel;
    @FXML
    private Label specifyLabel;
    @FXML
    private Button addItemBtn;
    @FXML
    private Button remItemBtn;
    @FXML
    private Button backBtn;
    @FXML
    private TableView<FoodItem> menuDataTable;
    @FXML
    private TableColumn<FoodItem, Integer> itemColumn1;
    @FXML
    private TableColumn<FoodItem, String> itemColumn2;
    @FXML
    private TableColumn<FoodItem, String> itemColumn3;
    @FXML
    private TableColumn<FoodItem, Double> itemColumn4;
    @FXML
    private TextField foodItemDescriptionText;
    @FXML
    private TextField categoryText;
    @FXML
    private TextField rateText;



    private MenuManager menuManager = new MenuManager();

    public ObservableList<FoodItem> data;


    @Override
    public void initialize(URL url, ResourceBundle rb){


        try {
            data = menuManager.buildMenuData();
        } catch (SQLException e) {

            e.printStackTrace();
        }


        itemColumn1.setCellValueFactory(new PropertyValueFactory<FoodItem, Integer>("itemId"));
        itemColumn2.setCellValueFactory(new PropertyValueFactory<FoodItem, String>("foodItemDescription"));
        itemColumn3.setCellValueFactory(new PropertyValueFactory<FoodItem, String>("category"));
        itemColumn4.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("rate"));


        menuDataTable.setItems(data);

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
    private void remItem(ActionEvent event) throws SQLException, MalformedURLException {

        FoodItem itemToRemove = menuDataTable.getSelectionModel().getSelectedItem();

        if(itemToRemove != null){
            int selectedIndex  = itemToRemove.getItemId();


            if(selectedIndex>= 0){

                menuManager.removeMenuData(selectedIndex);

                //rebuild table after removing item
                data = menuManager.buildMenuData();
                menuDataTable.setItems(data);

            }


        }else{
            //No selection

            Alert alert = new Alert(AlertType.WARNING);


            Stage tempStage = (Stage) alert.getDialogPane().getScene().getWindow();
            tempStage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));

            alert.setTitle("Restaurant Billing System - WARNING");
            alert.setHeaderText("No Menu Item Selected");
            alert.setContentText("Please select a Menu Item to remove.");

            alert.showAndWait();
        }

    }


    @FXML
    private void addItem(ActionEvent event) throws SQLException, MalformedURLException {


        String foodItemDescription = foodItemDescriptionText.getText();
        String category = categoryText.getText();
        String rateString = rateText.getText();




        if(foodItemDescription.equalsIgnoreCase("")|| category.equalsIgnoreCase("") || rateString.equalsIgnoreCase("")){

            Alert alert = new Alert(AlertType.ERROR);


            Stage tempStage = (Stage) alert.getDialogPane().getScene().getWindow();
            tempStage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));

            alert.setTitle("Restaurant Billing System - ERROR");
            alert.setHeaderText("Invalid menu item");
            alert.setContentText("Please enter a valid menu item to add.");

            alert.showAndWait();

        }else if(menuManager.isFoodItemDescription(foodItemDescription)==true){
            Alert alert = new Alert(AlertType.ERROR);


            Stage tempStage = (Stage) alert.getDialogPane().getScene().getWindow();
            tempStage.getIcons().add(new Image((getClass().getClassLoader().getResource("restaurant-icon.png").toExternalForm())));

            alert.setTitle("Restaurant Billing System - ERROR");
            alert.setHeaderText("Food Item already exists");
            alert.setContentText("Please enter a valid menu item to add.");

            alert.showAndWait();
        }
        else{

            double rate = Double.valueOf(rateString);


            FoodItem itemToAdd = new FoodItem(foodItemDescription,category,rate);
            menuManager.addMenuData(itemToAdd);
            data = menuManager.buildMenuData();
            menuDataTable.setItems(data);
        }

    }


}