Author: Zachary Treichler

Table of Contents:
1 - Key Assumptions Made
2 - Description of Interfaces
3 - "Special" Features
4 - Triggers Created
5 - Views Created
6 - Recommended Testing procedure
7 - Sources used

------------------------------------------------------------------------------------------------------------------------------------------------------------

1 - Key Assumtions Made:

Key assumption 1: I assumed that Regork does not own its suppliers, and that the current list of suppliers is exhaustive of the entire food industry. 
This is why there is no option for Regork to open or close a supplier. However, Regork can request an existing supplier to make a new shipment for them, 
which is why it's an available option.

Key assumption 2: I assumed that a shipment leaves the supplier as soon as its created in the database. This is why a Regork employee cannot delete an 
existing shipment while in supplier relations mode or add any more crates to a shipment it once it has been created. In the event a store is deleted, 
however, an employee can choose to redirect a shipment.

Key assumption 3: Whenever a shipment and its crates are made, I am assuming they are made the day this assignment is due (5/2/2025). This is done to
streamline the creation of shipments.

Key assumption 4: Whenever we do a recall, we are assuming that Regork and its suppliers can instantaneously remove those products from any shipments 
currently in transportation. In my mind, I imagined this as Regork removing recalled crates from a shipment as the product left the supplier facilty.

Key assumption 5: We don't take into account the size of goods being transported inside of crates. For database administration purposes, I arbitrarily
set the maximum capacity for a crate to anything less than 1000 units

------------------------------------------------------------------------------------------------------------------------------------------------------------

2 - Description of Interfaces

Login Screen: At the beginning of the program, the user is prompted for a password. The user should enter their Edgar1/Oracle password to start the program.

Store Manager Mode: The store manager mode is used to manage all of the stores operated by Regork. In the main interface, users can view a list of all of 
Regork's stores, open a new store, or close an existing location. The user can use the ID of a Regork location to manage the day-to-day operations of that 
particular store location. While managing a specific store location, features include: Viewing a list of all received shipments, viewing a list of all 
suppliers that have delivered a shipment to the selected store location, viewing a list of all products sold at the selected Regork location, and 
adding/removing a product to the list of products sold by the store.  

Supplier Relations Mode: Supplier relations mode focuses more specifically on Regork's supply chain with its suppliers. In the main interface, the user can
view a list of all shipments along with the contents of those shipments, make a new shipment, and change a current shipment's destination, which is also used
to assign a destination to a new shipment after it is created. I assumed that Regork's suppliers are independent entities, which is why the interface for a 
specific supplier is more limited in comparison to that of a store. After selecting a particular supplier, the user can view all outgoing and incoming 
shipments related to that supplier, and view a list of all the products they make. The ability to see a suppliers shipments could be helpful in the event of
a product recall, and could also help Regork determine the suppliers they are most relient on in particular product categories. 

Recall Mode: Recall mode is a much more limited interface, but provides the user with a powerful set of features. When the user issues a product recall, it 
impacts the entire database because the program has to update the products sold by each store, the products made by each supplier, the crates included inside 
shipments, and the components inside of other products. When a product is recalled, any product that contained that product is also recalled to ensure the safety 
and health of Regork customers. Through the view all products and view all components features, the user can see how a potential recall could impact the products 
sold by the company.

------------------------------------------------------------------------------------------------------------------------------------------------------------

3 - Special Features:

Something special I added to my supplier relations mode was the ability to redirect a shipment. I assumed a shipment is made the moment it's created by the 
database, but that it hasn't yet arrived at the Regork Location at the time of creation. When the user closes a store, this feature can be used to redirect 
a shipment that was going to the store location that was closed. In the context of a business, this would be a valuable feature because they wouldn't want 
their shipments to go to waste and get deleted whenever a store closed.

------------------------------------------------------------------------------------------------------------------------------------------------------------

4 - Triggers Created:

A: Triggers which are activated after the deletion of a store

This trigger updates the shipment table whenever a store is deleted by setting that shipment's store_id to null. The reason I chose to update the ID to null, 
rather than delete it, is because there is functionality to alter a shipments destination. If a shipment had its destination store closed, the user can give
it a new one through the supplier relations mode, rather than having to redo an entire shipment. 

CREATE TRIGGER UPDATE_SHIPMENT_AFTER_STORE_CLOSING AFTER DELETE ON STORE_LOCATION
REFERENCING OLD AS OROW
FOR EACH ROW
BEGIN 
    UPDATE SHIPMENT
    SET STORE_ID = NULL
    WHERE STORE_ID = :OROW.STORE_ID;
END;

When a store is deleted, this trigger activates to elimate any tuples containing that store's ID from the sells table. This ensures that the database is 
properly updated, because a closed store shouldn't be able to sell any products.

CREATE TRIGGER UPDATE_SELLS_AFTER_STORE_CLOSING AFTER DELETE ON STORE_LOCATION
REFERENCING OLD AS OROW
FOR EACH ROW
BEGIN 
    DELETE FROM SELLS
    WHERE STORE_ID = :OROW.STORE_ID;
END;


B: Triggers which are activated after the recall of a particular product

When a product is deleted, this trigger deletes any tuple in the sells relation containing that product's ID; a store cannot sell a non-existant procuct.

CREATE TRIGGER UPDATE_SELLS_AFTER_PRODUCT_IS_DELETED AFTER DELETE ON PRODUCT_INFO
REFERENCING OLD AS OROW
FOR EACH ROW
BEGIN 
    DELETE FROM SELLS
    WHERE PRODUCT_ID = :OROW.PRODUCT_ID;
END;

In a similar manner to the previous trigger, this trigger deletes any tuple in the makes relation containing that product's ID; a supplier cannot manufacture
a non-existant procuct.

CREATE TRIGGER UPDATE_MAKES_AFTER_PRODUCT_IS_DELETED AFTER DELETE ON PRODUCT_INFO
REFERENCING OLD AS OROW
FOR EACH ROW
BEGIN 
    DELETE FROM MAKES
    WHERE PRODUCT_ID = :OROW.PRODUCT_ID;
END;

This triger deletes any crate that contains the deleted product ID. Any shipment containing the deleted crate is still preserved.

CREATE TRIGGER UPDATE_CRATE_AFTER_PRODUCT_IS_DELETED AFTER DELETE ON PRODUCT_INFO
REFERENCING OLD AS OROW
FOR EACH ROW
BEGIN 
    DELETE FROM CRATE
    WHERE PRODUCT_ID = :OROW.PRODUCT_ID;
END;

This trigger is especially important for the implementation of recalls. Whenever a product is deleted, any tuple in the contains relation that has a product ID
or a component ID matching the recalled product is also deleted. This ensures consistency in the database. 

CREATE TRIGGER UPDATE_CONTAINS_AFTER_PRODUCT_IS_DELETED AFTER DELETE ON PRODUCT_INFO
REFERENCING OLD AS OROW
FOR EACH ROW
BEGIN 
    DELETE FROM CONTAINS
    WHERE PRODUCT_ID = :OROW.PRODUCT_ID
    OR COMPONENT_ID = :OROW.PRODUCT_ID;
END;

------------------------------------------------------------------------------------------------------------------------------------------------------------

5 - Views Created:

This view is used to get the set of all shipments that are shipped from one supplier to another supplier. This view was helpful when implementing the supplier
relations interface, becuase I had to track the shipments received by suppliers.

CREATE VIEW SUPPLIER_SHIPMENTS AS 
SELECT SHIPMENT.SHIPMENT_ID, SUPPLIER.SUPPLIER_ID AS SENDER, SUPPLIER_SHIPS.SHIPMENT_DATE, SUPPLIER_RECEIVES.SUPPLIER_ID AS RECIPIENT
FROM SUPPLIER, SUPPLIER_SHIPS, SHIPMENT, SUPPLIER_RECEIVES
WHERE SUPPLIER.SUPPLIER_ID = SUPPLIER_SHIPS.SUPPLIER_ID
AND SHIPMENT.SHIPMENT_ID = SUPPLIER_SHIPS.SHIPMENT_ID
AND SHIPMENT.SHIPMENT_ID = SUPPLIER_RECEIVES.SHIPMENT_ID;

There view is the opposite of the SUPPLIER_SHIPMENTS view. The reason why I couldn't just select from the shipments not in SUPPLIER_SHIPMENTS is because I wanted
to access other information like the shipment date. This was also helpful when I developed the supplier relations interface.

CREATE VIEW STORE_SHIPMENTS AS
SELECT SHIPMENT.SHIPMENT_ID, SHIPMENT.SUPPLIER_ID, SHIPMENT.STORE_ID, SUPPLIER_SHIPS.SHIPMENT_DATE
FROM SHIPMENT JOIN SUPPLIER_SHIPS ON SHIPMENT.SHIPMENT_ID = SUPPLIER_SHIPS.SHIPMENT_ID
WHERE SHIPMENT.SHIPMENT_ID
NOT IN (SELECT SHIPMENT_ID FROM SUPPLIER_SHIPMENTS);

------------------------------------------------------------------------------------------------------------------------------------------------------------

6 - Recommended Testing Procedure (Do these in order for optimal demonstration of program features)

How to start the program: 
1. Go into the top-level directory which contains ojdbc8.jar, zdt227.jar, and README.txt, DataGeneration/ and zdt227/
    You can check that you're in the right spot using the command: ls -l
2. Run the command: java -jar zdt227.jar

Procedure 1: Test login Functionality
1: Test exception handling by entering in an incorrect password.
2: Enter in the correct password (Oracle/Edgar1 password) so the interfaces can be accessed

Procedure 2: Test handling of user input in the main interface (same process can be applied to specific interfaces as well)
1: Enter a string
2: Enter a a number greater than 4 or less than 1

Procedure 3: Test Store Mode Functionality
1. Select option [1] in the main interface
2. Repeat procedure 2, but this time with numbers greater than 5 or less than 1
3. Select option [1] to manage a specific regork location
4. To verify proper handling of input, enter a string, a number greater than 5 digits, and a number not in the list of store IDs
5. Select a store ID to manage (14555 to see a store with no shipments, 79601 to see a store with shipments)
6. Repeat procedure 2, but this time with numbers greater than 7 or less than 1
7. Select option [1] to view all shipments received (this should have data for store 79601 and be empty for store 14555)
8. Select option [2] to view all suppliers connected to this store (Suppliers should be the same as the ones for each shipment)
9. Select option [3] to view all products sold (Take note of the fact that store 14555 sells pizza for when we do a recall later)
10. Select option [4] to add a new product (any product ID should suffice, but first test to make sure an already existing product can't be added again)
11. Select option [3] to check the product was added
12. Select option [4] to remove a product (I recommend removing the product you just added, but first check to make sure the store can't remove an item they don't sell)
12. Select option [3] to check the product was removed
13. Select option [6] to exit the interface mode for the store and repeat steps 7-9 with the other store's ID as input
14. Select option [2] to view all store locations
15. Select option [3] to create a new store
16. Follow the prompts while testing to make sure:
    1. Two stores can't share the same address (Ex: 7 Ridge Oak Circle)
    2. A store can't have a empty attribute or an attribute that exceeds SQL's character limit
    3. A state is always in its abbreviated form 
    4. A ZIP can only be 5 digits
17. Select option [2] to verify the store was opened and added to the store list
18. Select option [4] to close the store (I recommend using the ID of the store you created)
19. Select option [2] to verify the store was closed
20. Select option [5] to exit store manager mode

Procedure 4: Test Supplier Relations Mode
1. Select option [2] in the main interface
2. Repeat procedure 2, but this time with numbers greater than 5 or less than 1
3. Select option [1] to view information about a specific supplier
4. To verify proper handling of input, enter a string, a number greater than 5 digits, and a number not in the list of store IDs
5. Select a supplier ID to view information about (I recommend 64523 or 69411 for suppliers with shipments, and 81420 for a supplier without any existing
incoming or outgoing shipments. Take note of the fact that 81420 currently makes pizza)
6. Select options [1], [2], and [3] to verfiy their functionality. You'll notice Store ID 79601 appears for both suppliers, which is consistent with store 
testing. You can also verify that suppliers 64523 and 69411 have sent and received shipments to/from the other.
7. Select option [2] to view all shipments
8. Select option [3] to view the contents of a shipment (I recommend shipment 10000 or 10003. Take note of the fact that a crate in 10003 contains pizza (ID = 26418))
9. Select option [4] to make a new shipment
    1. Any supplier suffices for the shipment
    2. Select a product ID to add to the shipment (I recommend at least doing 26418, 31538, or 32957 because those will come into play with recalls)
    3. Test to make sure a crates capacity is realistic (0 < number of units < 1000)
    3. Test to make sure a price cannot lead to an exception (price to high or <= 0)
    4. Repeat 2-3 for as many crates as you want to add to the shipment
10. Select option [2] to verify the shipment was made (it should have a null destination)
11. Select option [5] to enter a destination for the shipment you just made (I recommend using Store ID = 14555 since we know it's already empty and can 
easily verfiy the shipment was added). Also test that a shipment can't be re-routed to the destination it's already going to
12. Select option [2] to verify the new shipment now has a destination
12. Select option [5] to change the destination of an existing shipment (I recommend using shipment 10000 and using a supplier destination other than 64524 or 69411)
13. Select option [6] to exit supplier relations mode

Procedure 5: Test recall mode
1. Select option [3] in the main interface
2. Repeat procedure 2, but this time with numbers greater than 4 or less than 1
3. Select option [1] to view all products 
4. Select option [2] to view all components in a product (I recommend either 52634 (chocolate cake), 26418 (pizza), or 32957 (tomato sauce), 35138 (tomato))
    Note the recursive nature of the ingredients in pizza i.e. tomato sauce is an ingredient, so its ingredient is also listed
5. Select option [3] to issue a recall (I recommend 35138 (tomato) to demonstrate recursion in recalls, 94198 for a product that isn't a component)
6. Select option [1] to verify the recalled product and any product that contained the recalled product, were removed from the list of all products
7. Select option [4] to exit recall mode

Procedure 6: Integration testing
1. Go into store manager mode and select option [1] with the Store ID = 14555
    You will be able to verify that the store no longer sells pizza since tomatos were recalled
    You will also be able to see the store now has a shipment and a list of suppliers (assuming you directed a shipment to store 14555)
    You will also be unable to add any of the recalled products
2. Select option [6] and option [5] to exit store management mode
3. Go into supplier relations mode and select option [3] with shipment ID = 10003
    You will be able to see that the shipment no longer contains a crate with pizza (ID = 26418)
4. Select option [1] with supplier ID = 81420
    You will be able to see that they no longer produce pizza
5. Select option [4] and option [6] to exit supplier relations mode
6. Select option [4] to exit the program

------------------------------------------------------------------------------------------------------------------------------------------------------------

7 - Sources used: 

I used Mockaroo to assist with data generation in the file GenerateData.sql. Mockaroo can be accessed at the following link: https://www.mockaroo.com/
