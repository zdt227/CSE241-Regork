import java.io.*;
import java.util.*;
import java.sql.*;

public class Supplier{
    private String id;
    private String company;
    private String type;
    private String street;
    private String city;
    private String state;
    private String zip;

    public Supplier(String id, String company, String type, String street, String city, String state, String zip){
        this.id = id;
        this.company = company;
        this.type = type;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public String toString(){
        return "Supplier ID: " + id
                + "\nCompany Name: " + company
                + "\nSupplier type: " + type
                + "\nAddress: " + street + ", " + city + ", " + state + ' ' + zip;
    }
}