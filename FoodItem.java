package sample;

public class FoodItem {


    private int itemId;
    private String foodItemDescription="";
    private String category ="";
    private double rate = 0.0;



    public FoodItem(String foodItemDescription, String category, double rate ){

        this.foodItemDescription = foodItemDescription;
        this.category =category;
        this.rate =rate;
    }


    //getters and setters

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getFoodItemDescription() {
        return foodItemDescription;
    }

    public void setFoodItemDescription(String foodItemDescription) {
        this.foodItemDescription = foodItemDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}