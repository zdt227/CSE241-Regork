import java.io.*;
import java.util.*;
import java.sql.*;

public class Shipment{
    private String id;
    private String date;
    private String supplier;

    public Shipment(String id, String date, String supplier){
        this.id = id;
        this.date = date;
        this.supplier = supplier;
    }
    public String getID(){
        return id;
    }
    public String getDate(){
        return date;
    }
    public String getSupplier(){
        return supplier;
    }
    public String toString(){
        return "Shipment id: " + id + "\nShipment date: " + date + "\nShipment supplier: " + supplier;
    }
}