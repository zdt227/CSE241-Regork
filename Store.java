import java.io.*;
import java.util.*;
import java.sql.*;

public class Store {
    private String id;
    private String street;
    private String city;
    private String state;
    private String zip;

    public Store(String id,String street,String city,String state,String zip) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public String getID(){
        return id;
    }
    public String getStreet(){
        return street;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
    public String getZIP(){
        return zip;
    }
    public String toString(){
        return "Store ID: " + id + "\nAddress: " + street + ", " + city +  ", " + state + ' ' + zip;
    }
}