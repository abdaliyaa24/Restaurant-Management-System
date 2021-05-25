package sample;

public class Employee {


    protected int empNumber = 0;


    protected String firstname = "";
    protected String surname ="";
    protected String username ="";
    protected String password = "";
    protected String type = "";
    protected String lastLogin="";



    public Employee(String firstname, String surname, String username,String password){

        this.firstname = firstname;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.type = this.getClass().getSimpleName();



    }



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(int empNumber) {
        this.empNumber = empNumber;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getLastLogin() {
        return lastLogin;
    }


    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }


}