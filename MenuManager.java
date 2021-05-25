package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class MenuManager {


    private Connection connection;


    public MenuManager() {

        connection = SqliteConnection.Connector();


        if(connection == null)
            System.exit(1);


    }


    public ObservableList<FoodItem> buildMenuData() throws SQLException{

        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null ;
        String query ="SELECT * from Fooditems_Details";

        ObservableList<FoodItem> itemData = FXCollections.observableArrayList();

        try {

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){


                FoodItem item = new FoodItem(


                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)


                );


                item.setItemId(resultSet.getInt(1));
                itemData.add(item);

            }




        } catch (Exception e) {
            e.printStackTrace();


        }finally{
            preparedStatement.close();
        }
        return itemData;
    }



    public void removeMenuData(int selectedIndex) throws SQLException{



        PreparedStatement preparedStatement = null;
        String query ="DELETE FROM Fooditems_Details WHERE foodItem_id = ?";

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, selectedIndex);


            preparedStatement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();


        }finally{
            preparedStatement.close();

        }



    }


    public void addMenuData(FoodItem itemToAdd) throws SQLException{

        PreparedStatement preparedStatement = null;
        String query ="INSERT INTO Fooditems_details(Fooditem_description,category,rate) VALUES(?,?,?)";

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, itemToAdd.getFoodItemDescription());
            preparedStatement.setString(2, itemToAdd.getCategory());
            preparedStatement.setDouble(3, itemToAdd.getRate());


            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();


        }finally{
            preparedStatement.close();

        }

    }


    public boolean isFoodItemDescription(String foodItemDescription) throws SQLException{

        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null ;
        String query ="SELECT Fooditem_description from Order_Items where Fooditem_description = ?";
        boolean isFoodItemDescription = false;
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, foodItemDescription);




            resultSet = preparedStatement.executeQuery();


            if(resultSet.next()){
                isFoodItemDescription =  true;
            }else{
                isFoodItemDescription = false;

            }
        } catch (Exception e) {
            e.printStackTrace();


        }finally{
            preparedStatement.close();
            resultSet.close();
        }
        return isFoodItemDescription;
    }

}
