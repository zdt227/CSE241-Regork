import java.io.*;
import java.util.*;
import java.sql.*;

/*
General tech debt:
Add a way to exit all options if there was a misinput (can probably do this by setting the id to -1 and returning)

Supplier tech debt:
Add a created shipment for every supplier

Store tech debt:
Add a received shipment for every store
*/

public class Regork{
    public static String username = "";
    public static String password = "";
    public static int interfaceMode = 0;
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Hello. Welcome to Regork's supply chain interface. Please enter a valid password: ");
        while(!handleUserInput(scan, 1));
        System.out.print("Please select an interface option. For information about the capabilities of each interface and suggested test case"
                        +" please consult the README document for this program."
                        +"\n[1] - Store manager mode"
                        +"\n[2] - Supplier relations mode"
                        +"\n[3] - Recall mode"
                        +"\n[4] - Exit the program"
                        +"\nPlease make your selection: ");
        while(!handleUserInput(scan, 2));
        while(!handleInterfaceSelection(scan, interfaceMode)){
            System.out.print("\n\nPlease select an interface option. For information about the capabilities of each interface and suggested test case"
                        +" please consult the README document for this program."
                        +"\n[1] - Store manager mode"
                        +"\n[2] - Supplier relations mode"
                        +"\n[3] - Recall mode"
                        +"\n[4] - Exit the program"
                        +"\nPlease make your selection: ");
            while(!handleUserInput(scan, 2));
        };
        scan.close();
    }
    /**
     * The handleUserInput is used to handle the user when they are logging into the program, and selecting a view.
     * Case mode = 1: Deal with the user entering their password and make sure its valid
     * Case mode = 2: Deal with the user selecting a view and make sure its valid
     * Case mode = 3: Deal with the user selecting a manager mode
     * Case mode = 4: Deal with the user selecting a specific store option
     */
    public static boolean handleUserInput(Scanner scan, int mode){
        //Handle the user's password
        if (mode == 1){
            password = scan.nextLine();
            try(
                Connection c = DriverManager.getConnection("", username, password);
                Statement s = c.createStatement();
            ){
                System.out.println("Login successful!");
                c.close();
                s.close();
                return true;
            }
            catch(SQLException e){
                System.out.print("Invalid password. Please try again: ");
                return false;
            }
        }
        //Handle the user's view selection
        else if (mode == 2){
            try{
                interfaceMode = Integer.parseInt(scan.nextLine());
            }
            catch(Exception e){
                interfaceMode = -1;
            }
            if(interfaceMode <= 4 && interfaceMode > 0){
                return true;
            }
            else{
                System.out.print("Enter a valid integer (1-4) to select your mode: ");
                return false;
            }
        }
        //Handle the user's store manager selection
        else if (mode == 3){
            try{
                interfaceMode = Integer.parseInt(scan.nextLine());
            }
            catch(Exception e){
                interfaceMode = -1;
            }
            if(interfaceMode <= 5 && interfaceMode > 0){
                return true;
            }
            else{
                System.out.print("Enter a valid integer (1-5) to select your mode: ");
                return false;
            }
        }
        //Handle the user's selection when they are viewing a specific store
        else if (mode == 4){
            try{
                interfaceMode = Integer.parseInt(scan.nextLine());
            }
            catch(Exception e){
                interfaceMode = -1;
            }
            if(interfaceMode <= 6 && interfaceMode > 0){
                return true;
            }
            else{
                System.out.print("Enter a valid integer (1-6) to select your mode: ");
                return false;
            }
        }
        //Handle the user's selection while in supplier relation mode
        else if (mode == 5){
            try{
                interfaceMode = Integer.parseInt(scan.nextLine());
            }
            catch(Exception e){
                interfaceMode = -1;
            }
            if(interfaceMode <= 6 && interfaceMode > 0){
                return true;
            }
            else{
                System.out.print("Enter a valid integer (1-6) to select your mode: ");
                return false;
            }
        }
        //Handle the user's selection while looking at a specific supplier
        else if (mode == 6){
            try{
                interfaceMode = Integer.parseInt(scan.nextLine());
            }
            catch(Exception e){
                interfaceMode = -1;
            }
            if(interfaceMode <= 4 && interfaceMode > 0){
                return true;
            }
            else{
                System.out.print("Enter a valid integer (1-4) to select your mode: ");
                return false;
            }
        }
        //Handle the users recall mode selection
        else if (mode == 7){
            try{
                interfaceMode = Integer.parseInt(scan.nextLine());
            }
            catch(Exception e){
                interfaceMode = -1;
            }
            if(interfaceMode <= 4 && interfaceMode > 0){
                return true;
            }
            else{
                System.out.print("Enter a valid integer (1-4) to select your mode: ");
                return false;
            }
        }
        else{
            return false;
        }
    }
    /**
     * The handleInterfaceSelection method is used to call methods for each view.
     * Case 1: Store manager mode
     * 
     * Case 2: Supplier/Manufacturer mode
     * 
     * Case 3: Issue a recall
     * 
     * Case 3: Exit the program
     *      <> Exits the program and outputs a message to the user
     */
    public static boolean handleInterfaceSelection(Scanner scan, int view){
        switch(view){
            case 1:
                storeManagerMode(scan);
                return false;
            case 2:
                supplierRelationsMode(scan);
                return false;
            case 3:
                recallMode(scan);
                return false;
            case 4:
                System.out.println("Exiting program. Have a nice day.");
                return true;
        }
        return true;
    }
    /////////////////////////////////////////////////////////// START OF RECALL MODE \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public static void recallMode(Scanner scan){
        System.out.print("\n\nRecall mode selected. For suggested test cases and clarification, view the README."
                        +"\n[1] - View all products"
                        +"\n[2] - View all the components in a product"
                        +"\n[3] - Issue a product recall"
                        +"\n[4] - Exit Recall mode"
                        +"\nPlease make your selection: ");
        while(!handleUserInput(scan, 7));
        switch(interfaceMode){
            case 1:
                viewEveryProduct();
                recallMode(scan);
                break;
            case 2:
                viewAllComponents(scan);
                recallMode(scan);
                break;
            case 3:
                issueRecall(scan);
                recallMode(scan);
                break;
            case 4:
                break;
        }
    }
    public static void viewEveryProduct(){
        String serial = "";
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getStoreIDs = "SELECT * FROM PRODUCT_INFO";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getStoreIDs);
            ){
                ResultSet productList = stmt1.executeQuery();
                while(productList.next()){
                    String product = 
                        "Product ID: " + productList.getString("PRODUCT_ID") 
                        + "\nProduct Name: " + productList.getString("PRODUCT_NAME")
                        + "\nMSRP: $" + productList.getString("MSRP");
                    serial = productList.getString("SERIAL_NUMBER");
                    if(serial == null){
                        product = product + "\nSerial Number: N/A";
                    }
                    else{
                        product = product + "\nSerial Number: " + serial;
                    }
                    System.out.println(product);
                    System.out.println();
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
    }
    public static void viewAllComponents(Scanner scan){
        ArrayList<String> allProducts = new ArrayList<>();
        String productID = "";
        boolean invalidInput = false;
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getStoreIDs = "SELECT * FROM PRODUCT_INFO";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getStoreIDs);
            ){
                ResultSet productList = stmt1.executeQuery();
                while(productList.next()){
                    productID = productList.getString("PRODUCT_ID");
                    allProducts.add(productID);

                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        System.out.print("Select a product ID to view its ingredients (");
        for(int i = 0; i < allProducts.size(); i++){
            if(i != allProducts.size() -1){
                System.out.print(allProducts.get(i) + ", ");
            }
            else{
                System.out.print(allProducts.get(i) + "): ");
            }
        }
        do{
            try{ 
                productID = scan.nextLine();
                int i = Integer.parseInt(productID);
            }
            catch(Exception e){
                System.out.print("Enter a valid ID (5 digits, no characters and/or special symbols): ");
                invalidInput = true;
                continue;
            }
            if(!allProducts.contains(productID)){
                System.out.print("Enter an ID that corresponds to a product: ");
                invalidInput = true;
            }
            else{
                invalidInput = false;
            }
        }
        while(invalidInput);
        boolean hasOutput = false;
        System.out.println("Ingredients:");
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getAllIngredients = 
                "WITH INGREDIENTS (COMPONENT_ID) AS ( "
                + "SELECT CONTAINS.COMPONENT_ID " 
                + "FROM CONTAINS JOIN PRODUCT_INFO ON (CONTAINS.PRODUCT_ID = PRODUCT_INFO.PRODUCT_ID) " 
                + "WHERE PRODUCT_INFO.PRODUCT_ID = '" + productID + "' " 
                + "UNION ALL " 
                + "SELECT C.COMPONENT_ID " 
                + "FROM CONTAINS C " 
                + "JOIN INGREDIENTS I ON C.PRODUCT_ID = I.COMPONENT_ID) " 
                + "SELECT * " 
                + "FROM PRODUCT_INFO " 
                + "WHERE PRODUCT_ID IN (SELECT COMPONENT_ID FROM INGREDIENTS)";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getAllIngredients);
            ){
                ResultSet ingredientList = stmt1.executeQuery();
                while(ingredientList.next()){
                    String ingredient = 
                        "Product ID: " + ingredientList.getString("PRODUCT_ID") 
                        + "\nProduct Name: " + ingredientList.getString("PRODUCT_NAME")
                        + "\nMSRP: $" + ingredientList.getString("MSRP");
                    String serial = ingredientList.getString("SERIAL_NUMBER");
                    if(serial == null){
                        ingredient = ingredient + "\nSerial Number: N/A";
                    }
                    else{
                        ingredient = ingredient + "\nSerial Number: " + serial;
                    }
                    System.out.println(ingredient);
                    System.out.println();
                    hasOutput = true;
                }
                if(!hasOutput){
                    System.out.println("There are no components in this product.");
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }


    }
    public static void issueRecall(Scanner scan){
        ArrayList<String> allProducts = new ArrayList<>();
        String productID = "";
        boolean invalidInput = false;
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getStoreIDs = "SELECT * FROM PRODUCT_INFO";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getStoreIDs);
            ){
                ResultSet productList = stmt1.executeQuery();
                while(productList.next()){
                    productID = productList.getString("PRODUCT_ID");
                    allProducts.add(productID);

                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        System.out.print("Select a product ID to recall (");
        for(int i = 0; i < allProducts.size(); i++){
            if(i != allProducts.size() -1){
                System.out.print(allProducts.get(i) + ", ");
            }
            else{
                System.out.print(allProducts.get(i) + "): ");
            }
        }
        do{
            try{ 
                productID = scan.nextLine();
                int i = Integer.parseInt(productID);
            }
            catch(Exception e){
                System.out.print("Enter a valid ID (5 digits, no characters and/or special symbols): ");
                invalidInput = true;
                continue;
            }
            if(!allProducts.contains(productID)){
                System.out.print("Enter an ID that corresponds to a product: ");
                invalidInput = true;
            }
            else{
                invalidInput = false;
            }
        }
        while(invalidInput);
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getAllRecalls = 
                "SELECT * FROM PRODUCT_INFO "
                + "WHERE PRODUCT_ID = ? "
                + "OR PRODUCT_ID IN (SELECT PRODUCT_ID FROM CONTAINS WHERE COMPONENT_ID = ?) "
                + "OR PRODUCT_ID IN (SELECT C1.PRODUCT_ID FROM CONTAINS C1 JOIN CONTAINS C2 ON C1.COMPONENT_ID = C2.PRODUCT_ID WHERE C2.COMPONENT_ID = ?)";
            String recallProducts = 
                "DELETE FROM PRODUCT_INFO "
                + "WHERE PRODUCT_ID = ? "
                + "OR PRODUCT_ID IN (SELECT PRODUCT_ID FROM CONTAINS WHERE COMPONENT_ID = ?) "
                + "OR PRODUCT_ID IN (SELECT C1.PRODUCT_ID FROM CONTAINS C1 JOIN CONTAINS C2 ON C1.COMPONENT_ID = C2.PRODUCT_ID WHERE C2.COMPONENT_ID = ?)";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getAllRecalls);
                PreparedStatement stmt2 = c.prepareStatement(recallProducts);
            ){
                stmt1.setString(1, productID);
                stmt1.setString(2, productID);
                stmt1.setString(3, productID);
                stmt2.setString(1, productID);
                stmt2.setString(2, productID);
                stmt2.setString(3, productID);

                ResultSet recallIDs = stmt1.executeQuery();
                System.out.println("Recalled Products: ");
                while(recallIDs.next()){
                    String output = "Product ID: " + recallIDs.getString("PRODUCT_ID");
                    output += "\nProduct Name: " + recallIDs.getString("PRODUCT_NAME");
                    System.out.println(output);
                    System.out.println();
                }

                stmt2.executeUpdate();
                c.close();
                s.close();
            }
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
    }

    /////////////////////////////////////////////////////////// END OF RECALL MODE \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    
    /////////////////////////////////////////////////////////// START OF SUPPLIER MANAGER MODE \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public static void supplierRelationsMode(Scanner scan){
        System.out.print("\n\nSupplier relations mode selected. For suggested test cases and clarification, view the README."
                        +"\n[1] - View information about a specific supplier" //view products made, location info, current stores the supplier ships to, outgoing shipments, received shipments (Not really a part of the story I've conceived for Regork, but its a grading requirement), etc.
                        +"\n[2] - View a list of all shipments"
                        +"\n[3] - View the contents of an existing shipment" 
                        +"\n[4] - Make a new shipment" 
                        +"\n[5] - Change a shipment's destination"
                        +"\n[6] - Exit supplier relations mode"
                        +"\nPlease make your selection: ");
        while(!handleUserInput(scan, 5));
        switch(interfaceMode){
            case 1:
                supplierMode1A(scan);
                supplierRelationsMode(scan);
                break;
            case 2:
                viewEveryShipment(scan);
                supplierRelationsMode(scan);
                break;
            case 3:
                viewAllCrates(scan);
                supplierRelationsMode(scan);
                break;
            case 4:
                makeNewShipment(scan);
                supplierRelationsMode(scan);
                break;
            case 5:
                changeShipmentDestination(scan);
                supplierRelationsMode(scan);
                break;
            case 6:
                break;
        }
    }
    public static void supplierMode1A(Scanner scan){
        ArrayList<String> suppliers = new ArrayList<>();
        String supplierID = "";
        boolean invalidInput = false;
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getSupplierIDs = "SELECT SUPPLIER_ID FROM SUPPLIER";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getSupplierIDs);
            ){
                ResultSet supplierIDs = stmt1.executeQuery();
                while(supplierIDs.next()){
                    suppliers.add(supplierIDs.getString("SUPPLIER_ID"));
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        System.out.print("Enter a valid supplier ID (");
        for(int i = 0; i < suppliers.size(); i++){
            if(i != suppliers.size() -1){
                System.out.print(suppliers.get(i) + ", ");
            }
            else{
                System.out.print(suppliers.get(i) + "): ");
            }
        }
        do{
            try{ 
                supplierID = scan.nextLine();
                int i = Integer.parseInt(supplierID);
            }
            catch(Exception e){
                System.out.print("Enter a valid ID (5 digits, no characters and/or special symbols): ");
                invalidInput = true;
                continue;
            }
            if(!suppliers.contains(supplierID)){
                System.out.print("Enter an ID that corresponds to a supplier: ");
                invalidInput = true;
            }
            else{
                invalidInput = false;
            }
        }
        while(invalidInput);
        System.out.println("Downloading supplier information...");
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getStoreInfo = "SELECT * FROM SUPPLIER WHERE SUPPLIER_ID = '" + supplierID + "'";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getStoreInfo);
            ){
                ResultSet supplierInfo = stmt1.executeQuery();
                supplierInfo.next();
                Supplier s1 = new Supplier(supplierInfo.getString("SUPPLIER_ID"), supplierInfo.getString("COMPANY_NAME"), supplierInfo.getString("SUPPLIER_TYPE"), supplierInfo.getString("STREET"), supplierInfo.getString("CITY"), supplierInfo.getString("SUPPLIER_STATE"), supplierInfo.getString("ZIP"));
                System.out.println(s1.toString());
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }        
        supplierMode1B(scan, supplierID);
    }
    public static void supplierMode1B(Scanner scan, String supplierID){
        System.out.print("\n\nManagement options for supplier " 
                        + supplierID 
                        + ". For suggested test cases and clarification, view the README."
                        +"\n[1] - View all outgoing shipments"
                        +"\n[2] - View all incoming shipments"
                        +"\n[3] - View all products made by this supplier"
                        +"\n[4] - Exit management mode for store " 
                        + supplierID
                        +"\nPlease make your selection: ");
        while(!handleUserInput(scan, 6));
        switch(interfaceMode){
            case 1:
                supplierMode1_viewOutgoing(scan, supplierID);
                supplierMode1B(scan, supplierID);
                break;
            case 2:
                supplierMode1_viewIncoming(scan, supplierID);
                supplierMode1B(scan, supplierID);
                break;
            case 3:
                supplierMode1_viewAllProducts(scan, supplierID);
                supplierMode1B(scan, supplierID);
                break;
            case 6:
                supplierRelationsMode(scan);
                break;
        }
    }
    public static void supplierMode1_viewOutgoing(Scanner scan, String supplierID){
        boolean output = false;
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getOutgoingShipments1 = "SELECT * FROM SUPPLIER_SHIPMENTS WHERE SENDER = '" + supplierID + "'"; 
            try(
                PreparedStatement stmt1 = c.prepareStatement(getOutgoingShipments1);
            ){
                ResultSet outgoingShipments1 = stmt1.executeQuery();
                System.out.println("Supplier bound shipments:");
                while(outgoingShipments1.next()){
                    System.out.println("Shipment ID: " + outgoingShipments1.getString("SHIPMENT_ID"));
                    System.out.println("Shipment Date: " + outgoingShipments1.getString("SHIPMENT_DATE"));
                    System.out.println("Destination ID (Supplier): " + outgoingShipments1.getString("RECIPIENT"));
                    System.out.println();
                    output = true;
                }
                if(!output){
                    System.out.println("No supplier bound shipments have been sent by this supplier.");
                }
                System.out.println();
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        output = false;
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getOutgoingShipments2 = "SELECT * FROM STORE_SHIPMENTS WHERE SUPPLIER_ID = '" + supplierID + "'"; 
            try(
                PreparedStatement stmt1 = c.prepareStatement(getOutgoingShipments2);
            ){
                ResultSet outgoingShipments2 = stmt1.executeQuery();
                System.out.println("Store bound shipments:");
                while(outgoingShipments2.next()){
                    System.out.println("Shipment ID: " + outgoingShipments2.getString("SHIPMENT_ID"));
                    System.out.println("Shipment Date: " + outgoingShipments2.getString("SHIPMENT_DATE"));
                    System.out.println("Destination ID (Store): " + outgoingShipments2.getString("STORE_ID"));
                    System.out.println();
                    output = true;
                }
                if(!output){
                    System.out.println("No store bound shipments have been sent by this supplier.");
                }
                System.out.println();
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
    }
    public static void supplierMode1_viewIncoming(Scanner scan, String supplierID){
        boolean output = false;
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getOutgoingShipments1 = "SELECT * FROM SUPPLIER_SHIPMENTS WHERE RECIPIENT = '" + supplierID + "'";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getOutgoingShipments1);
            ){
                ResultSet incomingShipments = stmt1.executeQuery();
                while(incomingShipments.next()){
                    System.out.println("Shipment ID: " + incomingShipments.getString("SHIPMENT_ID"));
                    System.out.println("Supplier ID: " + incomingShipments.getString("SENDER"));
                    System.out.println("Shipment Date: " + incomingShipments.getString("SHIPMENT_DATE"));
                    System.out.println();
                    output = true;
                }
                if(!output){
                    System.out.println("No shipments have been sent by this supplier.");
                }
                System.out.println();
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
    }
    public static void supplierMode1_viewAllProducts(Scanner scan, String supplierID){
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getProducts =
                "SELECT * "
                + "FROM SUPPLIER, MAKES, PRODUCT_INFO "
                + "WHERE SUPPLIER.SUPPLIER_ID = MAKES.SUPPLIER_ID "
                + "  AND PRODUCT_INFO.PRODUCT_ID = MAKES.PRODUCT_ID "
                + "  AND SUPPLIER.SUPPLIER_ID = '"
                + supplierID
                + "'";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getProducts);
            ){
                ResultSet products = stmt1.executeQuery();
                boolean hasoutput3 = false;
                while(products.next()){
                    String product = 
                        "Product ID: " + products.getString("PRODUCT_ID") 
                        + "\nProduct Name: " + products.getString("PRODUCT_NAME")
                        + "\nMSRP: $" + products.getString("MSRP");
                    String serial = products.getString("SERIAL_NUMBER");
                    if(serial == null){
                        product = product + "\nSerial Number: N/A";
                    }
                    else{
                        product = product + "\nSerial Number: " + serial;
                    }
                    System.out.println(product);
                    System.out.println();
                    hasoutput3 = true;
                }
                if(!hasoutput3){
                    System.out.println("There are no products sold at this store.");
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
    }
    public static void viewEveryShipment(Scanner scan){
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getEveryShipment1 = "SELECT * FROM SUPPLIER_SHIPMENTS"; 
            try(
                PreparedStatement stmt1 = c.prepareStatement(getEveryShipment1);
            ){
                ResultSet everyShipment1 = stmt1.executeQuery();
                System.out.println("Supplier bound shipments:");
                while(everyShipment1.next()){
                    System.out.println("Shipment ID: " + everyShipment1.getString("SHIPMENT_ID"));
                    System.out.println("Shipment Date: " + everyShipment1.getString("SHIPMENT_DATE"));
                    System.out.println("Supplier ID: " + everyShipment1.getString("SENDER"));
                    System.out.println("Destination ID (Supplier): " + everyShipment1.getString("RECIPIENT"));
                    System.out.println();
                }
                System.out.println();
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getOutgoingShipments2 = "SELECT * FROM STORE_SHIPMENTS"; 
            try(
                PreparedStatement stmt1 = c.prepareStatement(getOutgoingShipments2);
            ){
                ResultSet outgoingShipments2 = stmt1.executeQuery();
                System.out.println("Store bound shipments:");
                while(outgoingShipments2.next()){
                    System.out.println("Shipment ID: " + outgoingShipments2.getString("SHIPMENT_ID"));
                    System.out.println("Shipment Date: " + outgoingShipments2.getString("SHIPMENT_DATE"));
                    System.out.println("Supplier ID: " + outgoingShipments2.getString("SUPPLIER_ID"));
                    System.out.println("Destination ID (Store): " + outgoingShipments2.getString("STORE_ID"));
                    System.out.println();
                }
                System.out.println();
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
    }
    public static void viewAllCrates(Scanner scan){
        ArrayList<String> shipments = new ArrayList<>();
        boolean invalidInput = false;
        String shipmentID = "";
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getShipmentIDs = "SELECT SHIPMENT_ID FROM SHIPMENT";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getShipmentIDs);
            ){
                ResultSet shipmentIDs = stmt1.executeQuery();
                while(shipmentIDs.next()){
                    shipments.add(shipmentIDs.getString("SHIPMENT_ID"));
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        System.out.print("Enter a valid shipment ID (");
        for(int i = 0; i < shipments.size(); i++){
            if(i != shipments.size() -1){
                System.out.print(shipments.get(i) + ", ");
            }
            else{
                System.out.print(shipments.get(i) + "): ");
            }
        }
        do{
            try{ 
                shipmentID = scan.nextLine();
                int i = Integer.parseInt(shipmentID);
            }
            catch(Exception e){
                System.out.print("Enter a valid ID (5 digits, no characters and/or special symbols): ");
                invalidInput = true;
                continue;
            }
            if(!shipments.contains(shipmentID)){
                System.out.print("Enter an ID that corresponds to a shipment: ");
                invalidInput = true;
            }
            else{
                invalidInput = false;
            }
        }
        while(invalidInput);
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getCrates = "SELECT * FROM SHIPMENT, CRATE WHERE SHIPMENT.SHIPMENT_ID = CRATE.SHIPMENT_ID and SHIPMENT.SHIPMENT_ID = '" + shipmentID + "'";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getCrates);
            ){
                ResultSet crates = stmt1.executeQuery();
                int i = 1;
                while(crates.next()){
                    System.out.println("Crate " + i + ":");
                    String output = "Batch ID: " + crates.getString("BATCH_ID");
                    output += "\nProduct ID: " + crates.getString("PRODUCT_ID");
                    output += "\nNumber of Units: " + crates.getString("UNITS");
                    output += "\nPrice Paid per unit: " + crates.getString("PRICE_PAID");
                    System.out.println(output + '\n');
                    i++;
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
    }
    public static void changeShipmentDestination(Scanner scan){
        ArrayList<String> shipments = new ArrayList<>();
        ArrayList<String> validStoreDestinations = new ArrayList<>();
        ArrayList<String> validSupplierDestinations = new ArrayList<>();
        String shipmentID = "";
        String destinationID = "";
        boolean invalidInput = false;
        boolean hasDestination = false;
        String currentDestination = "";
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getShipmentIDs = "SELECT SHIPMENT_ID FROM SHIPMENT";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getShipmentIDs);
            ){
                ResultSet shipmentIDs = stmt1.executeQuery();
                while(shipmentIDs.next()){
                    shipments.add(shipmentIDs.getString("SHIPMENT_ID"));
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        System.out.print("Enter a valid shipment ID (");
        for(int i = 0; i < shipments.size(); i++){
            if(i != shipments.size() -1){
                System.out.print(shipments.get(i) + ", ");
            }
            else{
                System.out.print(shipments.get(i) + "): ");
            }
        }
        do{
            try{ 
                shipmentID = scan.nextLine();
                int i = Integer.parseInt(shipmentID);
            }
            catch(Exception e){
                System.out.print("Enter a valid ID (5 digits, no characters and/or special symbols): ");
                invalidInput = true;
                continue;
            }
            if(!shipments.contains(shipmentID)){
                System.out.print("Enter an ID that corresponds to a shipment: ");
                invalidInput = true;
            }
            else{
                invalidInput = false;
            }
        }
        while(invalidInput);
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getDestination1 = "SELECT * FROM STORE_SHIPMENTS WHERE SHIPMENT_ID = '" + shipmentID + "'";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getDestination1);
            ){
                ResultSet checkIfStoreShipment = stmt1.executeQuery();
                if (checkIfStoreShipment.next()){
                    currentDestination = checkIfStoreShipment.getString("STORE_ID");
                    if(currentDestination != null){
                        System.out.println("Current destination ID (Store): " + currentDestination);
                        hasDestination = true;
                    }
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        if(!hasDestination){
            try(
                Connection c = DriverManager.getConnection("", username, password);
                Statement s = c.createStatement();
            ){
                String getDestination2 = "SELECT * FROM SUPPLIER_SHIPMENTS WHERE SHIPMENT_ID = '" + shipmentID + "'";
                try(
                    PreparedStatement stmt2 = c.prepareStatement(getDestination2);
                ){
                    ResultSet checkIfSupplierShipment = stmt2.executeQuery();
                    if (checkIfSupplierShipment.next()){
                        currentDestination = checkIfSupplierShipment.getString("RECIPIENT");
                        System.out.println("Current destination ID (Supplier): " + currentDestination);
                        hasDestination = true;
                    }
                }
                c.close();
                s.close();
            }
            catch(SQLException e){
                System.out.print("SQL error - " + e.getMessage());
            }
        }
        if(!hasDestination){
            System.out.println("The shipment you selected currently has no destination.");
            currentDestination = "N/A";
        }
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getDestinations1 = "SELECT * FROM STORE_LOCATION";
            String getDestinations2 = "SELECT * FROM SUPPLIER";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getDestinations1);
                PreparedStatement stmt2 = c.prepareStatement(getDestinations2);
            ){
                ResultSet IDs_1 = stmt1.executeQuery();
                ResultSet IDs_2 = stmt2.executeQuery();
                boolean valid = false;
                while(IDs_1.next()){
                   validStoreDestinations.add(IDs_1.getString("STORE_ID"));
                }
                while(IDs_2.next()){
                    validSupplierDestinations.add(IDs_2.getString("SUPPLIER_ID"));
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        System.out.print("Valid store destinations (");
        for(int i = 0; i < validStoreDestinations.size(); i++){
            if(i != validStoreDestinations.size() -1){
                System.out.print(validStoreDestinations.get(i) + ", ");
            }
            else{
                System.out.print(validStoreDestinations.get(i) + ")\n");
            }
        }
        System.out.print("Valid supplier destinations (");
        for(int i = 0; i < validSupplierDestinations.size(); i++){
            if(i != validSupplierDestinations.size() -1){
                System.out.print(validSupplierDestinations.get(i) + ", ");
            }
            else{
                System.out.print(validSupplierDestinations.get(i) + ")\n");
            }
        }
        boolean storeOrSupplier = false;
        System.out.print("Enter a new Destination ID: ");
        do{
            try{ 
                destinationID = scan.nextLine();
                int i = Integer.parseInt(destinationID);
            }
            catch(Exception e){
                System.out.print("Enter a valid ID (5 digits, no characters and/or special symbols): ");
                invalidInput = true;
                continue;
            }
            if(destinationID.length() > 5){
                System.out.print("Enter a valid ID (5 digits, no characters and/or special symbols): ");
                invalidInput = true;
                continue;
            }
            if(!validStoreDestinations.contains(destinationID) && !validSupplierDestinations.contains(destinationID)){
                System.out.print("Enter an ID that corresponds to a shipment: ");
                invalidInput = true;
            }
            if(destinationID.equals(currentDestination)){
                System.out.print("New destination ID must be different than the old one: ");
                invalidInput = true;
            }
            else{
                invalidInput = false;
                if(validStoreDestinations.contains(destinationID)){
                    storeOrSupplier = false;
                }
                else if (validSupplierDestinations.contains(destinationID)){
                    storeOrSupplier = true;
                }
            }
        }
        while(invalidInput);
        if(!storeOrSupplier){
            try(
                Connection c = DriverManager.getConnection("", username, password);
                Statement s = c.createStatement();
            ){
                String updateDestination = "DELETE FROM SUPPLIER_RECEIVES WHERE SHIPMENT_ID = '" + shipmentID + "'";
                s.executeUpdate(updateDestination);
                updateDestination = "UPDATE SHIPMENT SET STORE_ID = '" + destinationID + "' WHERE SHIPMENT_ID = '" + shipmentID + "'";
                s.executeUpdate(updateDestination);
                System.out.println("Destination updated to " + destinationID + " for shipment " + shipmentID);
                c.close();
                s.close();
            }
            catch(SQLException e){
                System.out.print("SQL error - " + e.getMessage());
            }
        }
        else{
            try(
                Connection c = DriverManager.getConnection("", username, password);
                Statement s = c.createStatement();
            ){
                String updateDestination = "";
                if(validSupplierDestinations.contains(destinationID) && validSupplierDestinations.contains(currentDestination)){
                    updateDestination = "UPDATE SUPPLIER_RECEIVES SET SUPPLIER_ID = '" + destinationID + "' WHERE SHIPMENT_ID = '" + shipmentID + "'";
                }
                else{
                    updateDestination = "INSERT INTO SUPPLIER_RECEIVES (SHIPMENT_ID, SUPPLIER_ID) VALUES ('" + shipmentID + "' , '" + destinationID + "')";
                }
                s.executeUpdate(updateDestination);
                updateDestination = "UPDATE SHIPMENT SET STORE_ID = NULL WHERE SHIPMENT_ID = '" + shipmentID + "'";
                s.executeUpdate(updateDestination);
                System.out.println("Destination updated to " + destinationID + " for shipment " + shipmentID);
                c.close();
                s.close();
            }
            catch(SQLException e){
                System.out.print("SQL error - " + e.getMessage());
            }
        }

    }
    public static void makeNewShipment(Scanner scan){
        ArrayList<String> existingShipments = new ArrayList<>();
        ArrayList<String> existingBatchIDs = new ArrayList<>();
        ArrayList<String> validSuppliers = new ArrayList<>();
        ArrayList<String> allProducts = new ArrayList<>();
        String newShipmentID = "";
        String newShipmentSupplier = "";
        boolean invalidInput = false;
        String serial = "";
        String productID = "";
        int units = 0;
        double price = 0.0;
        String batchID = "";
        String addCrate = "";
        String temp = "";
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getShipments = "SELECT * FROM SHIPMENT";
            String getBatches = "SELECT * FROM CRATE";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getShipments);
                PreparedStatement stmt2 = c.prepareStatement(getBatches);
            ){
                ResultSet shipment_list = stmt1.executeQuery();
                while(shipment_list.next()){
                    String sID = shipment_list.getString("SHIPMENT_ID");
                    existingShipments.add(sID);
                }
                ResultSet batch_list = stmt2.executeQuery();
                while(shipment_list.next()){
                    String bID = shipment_list.getString("BATCH_ID");
                    existingShipments.add(bID);
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getShipments = "SELECT * FROM SUPPLIER";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getShipments);
            ){
                ResultSet supplier_list = stmt1.executeQuery();
                while(supplier_list.next()){
                    String sID = supplier_list.getString("SUPPLIER_ID");
                    validSuppliers.add(sID);
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }

        do{
            int rand = (int)(Math.random() * 90000) + 10000;
            newShipmentID = String.valueOf(rand);
        }
        while(existingShipments.contains(newShipmentID));
        System.out.print("Select a supplier for this shipment (");
        for(int i = 0; i < validSuppliers.size(); i++){
            if(i != validSuppliers.size() -1){
                System.out.print(validSuppliers.get(i) + ", ");
            }
            else{
                System.out.print(validSuppliers.get(i) + "): ");
            }
        }
        do{
            try{ 
                newShipmentSupplier = scan.nextLine();
                int i = Integer.parseInt(newShipmentSupplier);
            }
            catch(Exception e){
                System.out.print("Enter a valid ID (5 digits, no characters and/or special symbols): ");
                invalidInput = true;
                continue;
            }
            if(!validSuppliers.contains(newShipmentSupplier)){
                System.out.print("Enter an ID that corresponds to a shipment: ");
                invalidInput = true;
            }
            else{
                invalidInput = false;
            }
        }
        while(invalidInput);
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String createShipmentPT1 = "Insert into Shipment(SHIPMENT_ID, STORE_ID, SUPPLIER_ID) VALUES ('" + newShipmentID + "', null, '" + newShipmentSupplier + "')";
            String createShipmentPT2 = "Insert into Supplier_ships(SHIPMENT_ID, SUPPLIER_ID, SHIPMENT_DATE) values ('" + newShipmentID + "', '" + newShipmentSupplier + "', DATE '2025-05-02')";
            s.executeUpdate(createShipmentPT1);
            s.executeUpdate(createShipmentPT2);
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getStoreIDs = "SELECT * FROM PRODUCT_INFO";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getStoreIDs);
            ){
                ResultSet productList = stmt1.executeQuery();
                while(productList.next()){
                    String product = 
                        "Product ID: " + productList.getString("PRODUCT_ID") 
                        + "\nProduct Name: " + productList.getString("PRODUCT_NAME")
                        + "\nMSRP: $" + productList.getString("MSRP");
                    serial = productList.getString("SERIAL_NUMBER");
                    if(serial == null){
                        product = product + "\nSerial Number: N/A";
                    }
                    else{
                        product = product + "\nSerial Number: " + serial;
                    }
                    System.out.println(product);
                    System.out.println();

                    productID = productList.getString("PRODUCT_ID");
                    allProducts.add(productID);

                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        do{
            System.out.print("Select a product to add to the shipment: ");
            do{
                try{ 
                    productID = scan.nextLine();
                    int i = Integer.parseInt(productID);
                }
                catch(Exception e){
                    System.out.print("Enter a valid ID (5 digits, no characters and/or special symbols): ");
                    invalidInput = true;
                    continue;
                }
                if(!allProducts.contains(productID)){
                    System.out.print("Enter an ID that corresponds to a product: ");
                    invalidInput = true;
                }
                else{
                    invalidInput = false;
                }
            }
            while(invalidInput);
            System.out.print("Enter the number of units: ");
            do{
                try{ 
                    temp = scan.nextLine();
                    units = Integer.parseInt(temp);
                }
                catch(Exception e){
                    System.out.print("Enter a valid quantity: ");
                    invalidInput = true;
                    continue;
                }
                if(units < 1 || temp.isEmpty()){
                    System.out.print("Cannot have an empty crate (select a value greater than 0): ");
                    invalidInput = true;
                }
                if(units > 1000){
                    System.out.print("Crate capacity exceeded (select a value less than 1000): ");
                    invalidInput = true;
                }
                else{
                    invalidInput = false;
                }
            }
            while(invalidInput);
            System.out.print("Enter the price paid per unit: ");
            do{
                try{ 
                    temp = scan.nextLine();
                    price = Double.parseDouble(temp);
                }
                catch(Exception e){
                    System.out.print("Enter a price (No dollar sign): ");
                    invalidInput = true;
                    continue;
                }
                if(price < 0 || temp.isEmpty()){
                    System.out.print("Cannot have a price less than 0.00: ");
                    invalidInput = true;
                }
                if(price > 10000){
                    System.out.print("Cannot have a price greater than 10000: ");
                    invalidInput = true;
                }
                else{
                    invalidInput = false;
                }
            }
            while(invalidInput);
            do{
                int rand = (int)(Math.random() * 90000) + 10000;
                batchID = String.valueOf(rand);
            }
            while(existingBatchIDs.contains(batchID));
            try(
                Connection c = DriverManager.getConnection("", username, password);
                Statement s = c.createStatement();
            ){
                String createCrate = "INSERT INTO Crate(BATCH_ID, SHIPMENT_ID, PRODUCT_ID, UNITS, PRICE_PAID) " + "VALUES ('" + batchID + "', '" + newShipmentID + "', '" + productID + "', '" + units + "', '" + price + "')";
                s.executeUpdate(createCrate);
                c.close();
                s.close();
            }
            catch(SQLException e){
                System.out.print("SQL error - " + e.getMessage());
            }
            System.out.print("Add another crate [y] or [n]? ");
            addCrate = scan.nextLine();
        }
        while(addCrate.equals("y"));
        System.out.println("\nCreated shipment where Shipment ID = " + newShipmentID + ", Supplier ID = " + newShipmentSupplier + ", Shipment Date = 2025-05-02");
        System.out.println("NOTE: This shipment currently does not have a destination. Select option [5] to add a destination.\n");
    }

    /////////////////////////////////////////////////////////// END OF SUPPLIER MANAGER MODE \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    
    /////////////////////////////////////////////////////////// START OF STORE MANAGER MODE \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public static void storeManagerMode(Scanner scan){
        System.out.print("\n\nStore management mode selected. For suggested test cases and clarification, view the README."
                        +"\n[1] - Manage a specific Regork location" //view location info, products sold, shipments received, suppliers, etc.
                        +"\n[2] - View a list of all stores"
                        +"\n[3] - Open a new store location"
                        +"\n[4] - Close an existing store location"
                        +"\n[5] - Exit store management mode"
                        +"\nPlease make your selection: ");
        while(!handleUserInput(scan, 3));
        switch(interfaceMode){
            case 1:
                managerMode1A(scan);
                break;
            case 2:
                viewAllStores(scan);
                storeManagerMode(scan);
                break;
            case 3:
                addNewStore(scan);
                storeManagerMode(scan);
                break;
            case 4:
                removeStore(scan);
                storeManagerMode(scan);
                break;
            case 5:
                break;
        }
    }
    public static void managerMode1A(Scanner scan){
        ArrayList<String> stores = new ArrayList<>();
        String storeID = "";
        boolean invalidInput = false;
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getStoreIDs = "SELECT STORE_ID FROM STORE_LOCATION";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getStoreIDs);
            ){
                ResultSet storeIDs = stmt1.executeQuery();
                while(storeIDs.next()){
                    stores.add(storeIDs.getString("STORE_ID"));
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        System.out.print("Enter a valid store ID (");
        for(int i = 0; i < stores.size(); i++){
            if(i != stores.size() -1){
                System.out.print(stores.get(i) + ", ");
            }
            else{
                System.out.print(stores.get(i) + "): ");
            }
        }
        do{
            try{ 
                storeID = scan.nextLine();
                int i = Integer.parseInt(storeID);
            }
            catch(Exception e){
                System.out.print("Enter a valid ID (5 digits, no characters and/or special symbols): ");
                invalidInput = true;
                continue;
            }
            if(!stores.contains(storeID)){
                System.out.print("Enter an ID that corresponds to a store: ");
                invalidInput = true;
            }
            else{
                invalidInput = false;
            }
        }
        while(invalidInput);
        System.out.println("Downloading store information...");
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getStoreInfo = "SELECT * FROM STORE_LOCATION WHERE STORE_ID = '" + storeID + "'";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getStoreInfo);
            ){
                ResultSet storeInfo = stmt1.executeQuery();
                storeInfo.next();
                Store s1 = new Store(storeInfo.getString("STORE_ID"), storeInfo.getString("STREET"), storeInfo.getString("CITY"), storeInfo.getString("STORE_STATE"), storeInfo.getString("ZIP"));
                System.out.println(s1.toString());
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }        
        managerMode1B(scan, storeID);
    }
    public static void managerMode1B(Scanner scan, String storeID){
        System.out.print("\n\nManagement options for store " 
                        + storeID 
                        + ". For suggested test cases and clarification, view the README."
                        +"\n[1] - View all shipments received"
                        +"\n[2] - View all suppliers we have received a shipment from"
                        +"\n[3] - View all products sold"
                        +"\n[4] - Add a new product"
                        +"\n[5] - Remove an existing product"
                        +"\n[6] - Exit management mode for store " 
                        + storeID
                        +"\nPlease make your selection: ");
        while(!handleUserInput(scan, 4));
        switch(interfaceMode){
            case 1:
                managerMode1_viewAllShipments(scan, storeID);
                managerMode1B(scan, storeID);
                break;
            case 2:
                managerMode1_viewAllSuppliers(scan, storeID);
                managerMode1B(scan, storeID);
                break;
            case 3:
                managerMode1_viewAllProducts(scan, storeID);
                managerMode1B(scan, storeID);
                break;
            case 4:
                managerMode1_addNewProduct(scan, storeID);
                managerMode1B(scan, storeID);
                break;
            case 5:
                managerMode1_viewAllProducts(scan, storeID);
                managerMode1_removeProduct(scan, storeID);
                managerMode1B(scan, storeID);
                break;
            case 6:
                storeManagerMode(scan);
                break;
        }
    }
    public static void managerMode1_viewAllSuppliers(Scanner scan, String storeID){
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getSuppliers =
                "select distinct SUPPLIER.COMPANY_NAME, SUPPLIER.SUPPLIER_ID "
                + "from SHIPMENT, STORE_LOCATION, SUPPLIER_SHIPS, SUPPLIER "
                + "where SHIPMENT.STORE_ID = STORE_LOCATION.STORE_ID "
                + "and SHIPMENT.SHIPMENT_ID = SUPPLIER_SHIPS.SHIPMENT_ID "
                + "and SUPPLIER_SHIPS.SUPPLIER_ID = SUPPLIER.SUPPLIER_ID "
                + "and STORE_LOCATION.STORE_ID = '"
                + storeID
                + "'";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getSuppliers);
            ){
                ResultSet suppliers = stmt1.executeQuery();
                boolean hasoutput1 = false;
                while(suppliers.next()){
                    String supplier = "Supplier ID: " + suppliers.getString("SUPPLIER_ID") + "\nSupplier Name: " + suppliers.getString("COMPANY_NAME");
                    System.out.println(supplier);
                    hasoutput1 = true;
                    System.out.println();
                }
                if(!hasoutput1){
                    System.out.println("There are no suppliers that ship to this store.");
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }   
    }
    public static void managerMode1_viewAllShipments(Scanner scan, String storeID){
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getShipments =
                "select * "
                + "from SHIPMENT, STORE_LOCATION, SUPPLIER_SHIPS, SUPPLIER "
                + "where SHIPMENT.STORE_ID = STORE_LOCATION.STORE_ID "
                + "and SHIPMENT.SHIPMENT_ID = SUPPLIER_SHIPS.SHIPMENT_ID "
                + "and SUPPLIER_SHIPS.SUPPLIER_ID = SUPPLIER.SUPPLIER_ID "
                + "and STORE_LOCATION.STORE_ID = '"
                + storeID
                + "'";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getShipments);
            ){
                ResultSet shipments = stmt1.executeQuery();
                boolean hasoutput2 = false;
                while(shipments.next()){
                    Shipment s1 = new Shipment(shipments.getString("SHIPMENT_ID"), shipments.getString("SHIPMENT_DATE"), shipments.getString("COMPANY_NAME"));
                    System.out.println(s1.toString());
                    System.out.println();
                    hasoutput2 = true;
                }
                if(!hasoutput2){
                    System.out.println("There are no shipments that go to this store.");
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }   
    }
    public static void managerMode1_viewAllProducts(Scanner scan, String storeID){
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getProducts =
                "SELECT * "
                + "FROM STORE_LOCATION, SELLS, PRODUCT_INFO "
                + "WHERE STORE_LOCATION.STORE_ID = SELLS.STORE_ID "
                + "  AND SELLS.PRODUCT_ID = PRODUCT_INFO.PRODUCT_ID "
                + "  AND STORE_LOCATION.STORE_ID = '"
                + storeID
                + "'";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getProducts);
            ){
                ResultSet products = stmt1.executeQuery();
                boolean hasoutput3 = false;
                while(products.next()){
                    String product = 
                        "Product ID: " + products.getString("PRODUCT_ID") 
                        + "\nProduct Name: " + products.getString("PRODUCT_NAME")
                        + "\nMSRP: $" + products.getString("MSRP");
                    String serial = products.getString("SERIAL_NUMBER");
                    if(serial == null){
                        product = product + "\nSerial Number: N/A";
                    }
                    else{
                        product = product + "\nSerial Number: " + serial;
                    }
                    System.out.println(product);
                    System.out.println();
                    hasoutput3 = true;
                }
                if(!hasoutput3){
                    System.out.println("There are no products sold at this store.");
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }   
    }
    public static void managerMode1_addNewProduct(Scanner scan, String storeID){
        ArrayList<String> allProducts = new ArrayList<>();
        ArrayList<String> productsSold = new ArrayList<>();
        String productID = "";
        String serial = "";
        boolean invalidInput = false;
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getStoreIDs = "SELECT * FROM PRODUCT_INFO";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getStoreIDs);
            ){
                ResultSet productList = stmt1.executeQuery();
                while(productList.next()){
                    String product = 
                        "Product ID: " + productList.getString("PRODUCT_ID") 
                        + "\nProduct Name: " + productList.getString("PRODUCT_NAME")
                        + "\nMSRP: $" + productList.getString("MSRP");
                    serial = productList.getString("SERIAL_NUMBER");
                    if(serial == null){
                        product = product + "\nSerial Number: N/A";
                    }
                    else{
                        product = product + "\nSerial Number: " + serial;
                    }
                    System.out.println(product);
                    System.out.println();

                    productID = productList.getString("PRODUCT_ID");
                    allProducts.add(productID);

                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }

        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getProducts =
                "SELECT * "
                + "FROM STORE_LOCATION, SELLS, PRODUCT_INFO "
                + "WHERE STORE_LOCATION.STORE_ID = SELLS.STORE_ID "
                + "  AND SELLS.PRODUCT_ID = PRODUCT_INFO.PRODUCT_ID "
                + "  AND STORE_LOCATION.STORE_ID = '"
                + storeID
                + "'";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getProducts);
            ){
                ResultSet product_ids = stmt1.executeQuery();
                while(product_ids.next()){
                    productID = product_ids.getString("PRODUCT_ID");
                    productsSold.add(productID);
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        System.out.print("Select a product to add to your store using the product ID: ");
        do{
            try{
                productID = scan.nextLine();
                int i = Integer.parseInt(productID);
            }
            catch(Exception e){
                System.out.print("Enter a valid ID (5 digits, no characters and/or special symbols): ");
                invalidInput = true;
                continue;
            }
            if(!allProducts.contains(productID)){
                System.out.print("Enter an ID that corresponds to a product: ");
                invalidInput = true;
                continue;
            }
            if(productsSold.contains(productID)){
                System.out.print("Enter a new product ID. A store cannot list multiple items with the same ID: ");
                invalidInput = true;
                continue;
            }
            invalidInput = false;
        }
        while(invalidInput);
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String addProduct = "INSERT INTO SELLS(STORE_ID, PRODUCT_ID) values (" + storeID + ", " + productID + ')';
            s.executeUpdate(addProduct);
            System.out.println("Inserted product ID, " + productID + ", into store ID, " + storeID + '.');
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }

    }
    public static void managerMode1_removeProduct(Scanner scan, String storeID){
        ArrayList<String> productsSold2 = new ArrayList<>();
        String productID = "";
        Boolean invalidInput = false;
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getProducts =
                "SELECT * "
                + "FROM STORE_LOCATION, SELLS, PRODUCT_INFO "
                + "WHERE STORE_LOCATION.STORE_ID = SELLS.STORE_ID "
                + "  AND SELLS.PRODUCT_ID = PRODUCT_INFO.PRODUCT_ID "
                + "  AND STORE_LOCATION.STORE_ID = '"
                + storeID
                + "'";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getProducts);
            ){
                ResultSet product_ids = stmt1.executeQuery();
                boolean hasProducts = false;
                while(product_ids.next()){
                    productID = product_ids.getString("PRODUCT_ID");
                    productsSold2.add(productID);
                    hasProducts = true;
                }
                if(!hasProducts){
                    return;
                }
                
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        System.out.print("Select a product to remove: ");
        do{
            try{
                productID = scan.nextLine();
                int i = Integer.parseInt(productID);
            }
            catch(Exception e){
                System.out.print("Enter a valid ID (5 digits, no characters and/or special symbols): ");
                invalidInput = true;
                continue;
            }
            if(!productsSold2.contains(productID)){
                System.out.print("Enter the ID of a product this store currently sells: ");
                invalidInput = true;
                continue;
            }
            invalidInput = false;
        }
        while(invalidInput);
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String addProduct = "DELETE FROM SELLS WHERE STORE_ID = '" + storeID + "' AND PRODUCT_ID = '" + productID + "'";
            s.executeUpdate(addProduct);
            System.out.println("Deleted product ID, " + productID + ", from store ID, " + storeID + '.');
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
    }
    public static void viewAllStores(Scanner scan){
        ArrayList<String> stores2 = new ArrayList<>();
        String storeID = "";
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getStoreInfo = "SELECT * FROM STORE_LOCATION";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getStoreInfo);
            ){
                ResultSet storeInfo = stmt1.executeQuery();
                boolean valid = false;
                while(storeInfo.next()){
                    Store s1 = new Store(storeInfo.getString("STORE_ID"), storeInfo.getString("STREET"), storeInfo.getString("CITY"), storeInfo.getString("STORE_STATE"), storeInfo.getString("ZIP"));
                    System.out.println(s1.toString());
                    System.out.println();
                    valid = true;
                }
                if(!valid){
                    return;
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
    }
    public static void addNewStore(Scanner scan){
        ArrayList<String> existingAddresses = new ArrayList<>();
        ArrayList<String> existingStoreIds = new ArrayList<>();
        boolean invalidInput = false;
        String address = "";
        String city = "";
        String state = "";
        String zip = "";
        String store_id = "";
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getAddresses = "SELECT * FROM STORE_LOCATION";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getAddresses);
            ){
                ResultSet addressList = stmt1.executeQuery();
                while(addressList.next()){
                    existingAddresses.add(addressList.getString("STREET"));
                    existingStoreIds.add(addressList.getString("STORE_ID"));
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        System.out.print("Enter an address for the store: ");
        do{
            address = scan.nextLine();
            if(existingAddresses.contains(address)){
                System.out.print("Two stores cannot share the same location: ");
                invalidInput = true;
                continue;
            }
            if(address.isEmpty()){
                System.out.print("Address cannot be empty: ");
                invalidInput = true;
                continue;
            }
            if(address.length() > 250){
                System.out.print("Character limit exceeded for an address. Try again: ");
                invalidInput = true;
                continue;
            }
            invalidInput = false;
        }
        while(invalidInput);
        System.out.print("Enter an city for the store: ");
        do{
            city = scan.nextLine();
            if(city.isEmpty()){
                System.out.print("City cannot be empty: ");
                invalidInput = true;
                continue;
            }
            if(city.length() > 250){
                System.out.print("Character limit exceeded for a city. Try again: ");
                invalidInput = true;
                continue;
            }
            invalidInput = false;
        }
        while(invalidInput);
        System.out.print("Enter a state for the store: ");
        int i = 0;
        do{
            state = scan.nextLine();
            if(state.isEmpty()){
                System.out.print("State cannot be empty: ");
                invalidInput = true;
                continue;
            }
            if(state.length() != 2){
                System.out.print("Use the state's abbreviation (Ex: NY for New York): ");
                invalidInput = true;
                continue;
            }
            else{
                invalidInput = false;
            }
        }
        while(invalidInput);
        System.out.print("Enter a ZIP code for the store: ");
        do{
            try{
                zip = scan.nextLine();
                i = Integer.parseInt(zip);
            }
            catch(Exception e){
                System.out.print("Enter a valid ZIP (5 digits, no characters and/or special symbols): ");
                invalidInput = true;
                continue;
            }
            if(zip.length() > 5){
                System.out.print("Enter a valid ZIP (Maximum 5 digits): ");
                invalidInput = true;
                continue;
            }
            invalidInput = false;
        }
        while(invalidInput);
        do{
            int rand = (int)(Math.random() * 90000) + 10000;
            store_id = String.valueOf(rand);
        }
        while(existingStoreIds.contains(store_id));

        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String insertStore = "INSERT INTO STORE_LOCATION(STORE_ID, STREET, CITY, STORE_STATE, ZIP) VALUES (?, ?, ?, ?, ?)";
            try(
                PreparedStatement stmt1 = c.prepareStatement(insertStore);
            ){
                stmt1.setString(1, store_id);
                stmt1.setString(2, address);
                stmt1.setString(3, city);
                stmt1.setString(4, state);
                stmt1.setString(5, zip);

                stmt1.executeUpdate();
                String temp = "Inserted store with attributes: id = " + store_id + ", address = "+ address + ", " + city +  ", " + state + ' ' + zip;
                System.out.println(temp);
                c.close();
                s.close();
            }
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
    }
    public static void removeStore(Scanner scan){
        ArrayList<String> stores3 = new ArrayList<>();
        String storeID = "";
        boolean invalidInput = false;
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String getStoreInfo = "SELECT * FROM STORE_LOCATION";
            try(
                PreparedStatement stmt1 = c.prepareStatement(getStoreInfo);
            ){
                ResultSet storeInfo = stmt1.executeQuery();
                boolean valid2 = false;
                while(storeInfo.next()){
                    String s1 = storeInfo.getString("STORE_ID");
                    stores3.add(s1);
                    valid2 = true;
                }
                if(!valid2){
                    System.out.println("There are currently no store locations.");
                    return;
                }
            }
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }
        System.out.print("Select a store to close (");
        for (int i = 0; i < stores3.size(); i++){
            if(i != stores3.size() -1){
                System.out.print(stores3.get(i) + ", ");
            }
            else{
                System.out.print(stores3.get(i) + "): ");
            }
        }
        do{
            try{ 
                storeID = scan.nextLine();
                int i = Integer.parseInt(storeID);
            }
            catch(Exception e){
                System.out.print("Enter a valid ID (5 digits, no characters and/or special symbols): ");
                invalidInput = true;
                continue;
            }
            if(!stores3.contains(storeID)){
                System.out.print("Enter an ID that corresponds to a store: ");
                invalidInput = true;
            }
            else{
                invalidInput = false;
            }
        }
        while(invalidInput);
        try(
            Connection c = DriverManager.getConnection("", username, password);
            Statement s = c.createStatement();
        ){
            String addProduct = "DELETE STORE_LOCATION WHERE STORE_ID = '" + storeID + "'";
            s.executeUpdate(addProduct);
            System.out.println("Deleted store location with store ID = " + storeID);
            c.close();
            s.close();
        }
        catch(SQLException e){
            System.out.print("SQL error - " + e.getMessage());
        }

    }

    /////////////////////////////////////////////////////////// END OF STORE MANAGER MODE \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
}
