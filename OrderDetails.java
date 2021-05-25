package sample;

import java.text.SimpleDateFormat;
import java.util.Date;




public class OrderDetails {



    //help track employees who make orders
    private int empId;

    private int tableNumber;
    private int orderId = 0;
    private String orderTime="";
    private String orderDate="";
    private String specialRequests ="";
    private String comments = "";



    public OrderDetails(){

        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String dateStamp = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        this.orderTime = timeStamp;
        this.orderDate = dateStamp;
        this.empId= LoginController.empId;


        //getters and setters

    }
    public int getTableNumber() {
        return tableNumber;
    }
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
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
    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }



}
