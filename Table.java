package sample;

public class Table {


    private int tableNumber;
    //true - free; false - busy
    private boolean status = true;
    private int numberOfPeople;
    private int currentOrderId;


    /**
     * <b>Constructor:</b>
     *
     *
     * <p> Generates a Table object but does not initialize
     * any of its fields (they have default values).</p>
     */
    public Table(){

    }


    /**
     * <b>Constructor:</b>
     *
     *
     * <p> Generates a Table object with
     * a specified table number, status and number of people.</p>
     *
     * @param tableNumber - the current number of the table.
     * @param status - the current status of the table.
     * @param numberOfPeople - the current number of people at the table.
     */
    public Table(int tableNumber, boolean status, int numberOfPeople){
        this.tableNumber = tableNumber;
        this.status = status;
        this.numberOfPeople = numberOfPeople;
    }


    //getters and setters

    public int getTableNumber() {
        return tableNumber;
    }


    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }


    public boolean isStatus() {
        return status;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }


    public int getNumberOfPeople() {
        return numberOfPeople;
    }


    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getCurrentOrderId() {
        return currentOrderId;
    }


    public void setCurrentOrderId(int currentOrderId) {
        this.currentOrderId = currentOrderId;
    }

}