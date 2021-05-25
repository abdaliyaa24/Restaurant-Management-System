package sample;

public class OrderItems {


    private String foodItem;
    private int quantity;
    private double rate;
    private String specialRequests;
    private String comments;


    public OrderItems(String foodItem, int quantity, double rate, String specialRequests, String comments){

        this.foodItem = foodItem;
        this.quantity = quantity;
        this.rate = rate;
        this.specialRequests = specialRequests;
        this.comments = comments;
    }


    //getters and setters

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}