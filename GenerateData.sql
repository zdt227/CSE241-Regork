-- NOTE: Data generation was done with the help of mockaroo

-- -- Generate Store Location Data
insert into STORE_LOCATION (STORE_ID, STREET, CITY, STORE_STATE, ZIP) values (79601, '8 Bartillon Terrace', 'Miami', 'FL', 16190);
insert into STORE_LOCATION (STORE_ID, STREET, CITY, STORE_STATE, ZIP) values (27385, '1 Westend Lane', 'Denver', 'CO', 72028);
insert into STORE_LOCATION (STORE_ID, STREET, CITY, STORE_STATE, ZIP) values (14555, '62 Center Street', 'Springfield', 'MA', 19777);
insert into STORE_LOCATION (STORE_ID, STREET, CITY, STORE_STATE, ZIP) values (63754, '4484 Bay Center', 'Flushing', 'NY', 32313);
insert into STORE_LOCATION (STORE_ID, STREET, CITY, STORE_STATE, ZIP) values (52633, '873 Porter Point', 'Toledo', 'OH', 47865);
insert into STORE_LOCATION (STORE_ID, STREET, CITY, STORE_STATE, ZIP) values (78057, '78 Schmedeman Park', 'Washington', 'DC', 62192);
insert into STORE_LOCATION (STORE_ID, STREET, CITY, STORE_STATE, ZIP) values (19095, '6 Forster Circle', 'Clearwater', 'FL', 12651);
insert into STORE_LOCATION (STORE_ID, STREET, CITY, STORE_STATE, ZIP) values (39709, '3 Lakewood Gardens Circle', 'Nashville', 'TN', 54250);
insert into STORE_LOCATION (STORE_ID, STREET, CITY, STORE_STATE, ZIP) values (54007, '7 Hagan Place', 'Fresno', 'CA', 58333);
insert into STORE_LOCATION (STORE_ID, STREET, CITY, STORE_STATE, ZIP) values (33619, '7 Ridge Oak Circle', 'Syracuse', 'NY', 92469);

-- -- Generate Supplier Data
insert into SUPPLIER (SUPPLIER_ID, COMPANY_NAME, SUPPLIER_TYPE, STREET, CITY, SUPPLIER_STATE, ZIP) values (69411, 'Rippin, Hilpert and Hauck', 'Meat packing plant', '34 South Circle', 'New Orleans', 'LA', 38096);
insert into SUPPLIER (SUPPLIER_ID, COMPANY_NAME, SUPPLIER_TYPE, STREET, CITY, SUPPLIER_STATE, ZIP) values (46830, 'Miller Inc', 'Brewery', '5 Blue Bill Park Road', 'Fredericksburg', 'VA', 95597);
insert into SUPPLIER (SUPPLIER_ID, COMPANY_NAME, SUPPLIER_TYPE, STREET, CITY, SUPPLIER_STATE, ZIP) values (64437, 'Stiedemann, Robel and Bayer', 'Warehouse', '375 Gale Circle', 'Memphis', 'TN', 34657);
insert into SUPPLIER (SUPPLIER_ID, COMPANY_NAME, SUPPLIER_TYPE, STREET, CITY, SUPPLIER_STATE, ZIP) values (77824, 'Rice-Dicki', 'Manufacturing Plant', '9158 Melby Parkway', 'Dayton', 'OH', 16682);
insert into SUPPLIER (SUPPLIER_ID, COMPANY_NAME, SUPPLIER_TYPE, STREET, CITY, SUPPLIER_STATE, ZIP) values (81420, 'Weimann Inc', 'Distillery', '2060 Golden Leaf Park', 'Shawnee Mission', 'KS', 77823);
insert into SUPPLIER (SUPPLIER_ID, COMPANY_NAME, SUPPLIER_TYPE, STREET, CITY, SUPPLIER_STATE, ZIP) values (48608, 'Ernser, Denesik and Runolfsson', 'Distillery', '020 Jackson Lane', 'Fort Myers', 'FL', 80944);
insert into SUPPLIER (SUPPLIER_ID, COMPANY_NAME, SUPPLIER_TYPE, STREET, CITY, SUPPLIER_STATE, ZIP) values (72623, 'Hermiston LLC', 'Farm', '5663 2nd Street', 'Yakima', 'WA', 66839);
insert into SUPPLIER (SUPPLIER_ID, COMPANY_NAME, SUPPLIER_TYPE, STREET, CITY, SUPPLIER_STATE, ZIP) values (53067, 'O''Kon and Sons', 'Farm', '329 Ohio Way', 'Rochester', 'NY', 23875);
insert into SUPPLIER (SUPPLIER_ID, COMPANY_NAME, SUPPLIER_TYPE, STREET, CITY, SUPPLIER_STATE, ZIP) values (74442, 'Beier and Sons', 'Fruit Orchard', '5 Towne Plaza', 'Savannah', 'GA', 49117);
insert into SUPPLIER (SUPPLIER_ID, COMPANY_NAME, SUPPLIER_TYPE, STREET, CITY, SUPPLIER_STATE, ZIP) values (64523, 'Barton Muller and Harris', 'Fruit Orchard', '84 Hooker Pass', 'Houston', 'TX', 58116);

-- -- Generate Product Info Data
-- Non-serialized products
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (57254, 'Cocoa powder', null, 19.61);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (82637, 'Pepper - Gypsy Pepper', null, 10.37);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (94198, 'Toothpick Frilled', null, 21.98);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (77813, 'Sugar', null, 23.1);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (51897, 'Chicken - White Meat With Tender', null, 20.76);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (84264, 'Wine - Cotes Du Rhone Parallele', null, 2.49);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (59909, 'Scallops - U - 10', null, 1.23);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (20036, 'Poppy Seed', null, 16.44);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (36148, 'Gherkin', null, 21.14);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (39960, 'Scallops - 10/20', null, 14.18);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (14729, 'Hummus - Spread', null, 6.25);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (15732, 'Pizza Pizza Dough', null, 15.5);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (87018, 'Chicken - Livers', null, 21.45);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (84280, 'Sorrel - Fresh', null, 14.93);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (50931, 'Bagel - Everything Presliced', null, 2.53);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (52634, 'Cake - Chocolate', null, 10.77);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (18036, 'Grenadine', null, 15.18);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (25957, 'Pastry - Plain Baked Croissant', null, 14.22);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (63201, 'Gingerale - Diet - Schweppes', null, 19.2);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (88789, 'Orange - Canned, Mandarin', null, 3.09);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (81241, 'Cheese - Feta', null, 22.3);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (44281, 'Eggs', null, 18.71);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (59947, 'Vinegar - Balsamic, White', null, 5.22);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (36177, 'Apple - Royal Gala', null, 11.21);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (82941, 'Flour', null, 23.33);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (92334, 'Wiberg Cure', null, 8.76);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (55321, 'Veal - Brisket, Provimi,bnls', null, 6.63);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (52127, 'Melon - Honey Dew', null, 7.18);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (27853, 'Lid - 3oz Med Rec', null, 23.27);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (35138, 'Tomato', null, 5.29);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (32957, 'Tomato sauce', null, 13.44);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (93587, 'Pepperoni Slices', null, 20.5);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (64727, 'Madeira', null, 16.71);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (50580, 'Lemonade - Mandarin, 591 Ml', null, 6.19);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (44340, 'Versatainer Nc - 888', null, 7.02);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (15221, 'Plasticspoonblack', null, 11.2);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (69170, 'Tarts Assorted', null, 3.6);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (26418, 'Pepperoni Pizza', null, 24.86);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (99315, 'Truffle Shells - White Chocolate', null, 13.44);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (52487, 'Oil - Olive, Extra Virgin', null, 19.72);
-- Serialized products
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (63396, 'Toaster', 311, 77.49);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (58885, 'Toaster', 820, 24.98);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (47433, 'Toaster', 656, 32.51);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (81506, 'Waffle Iron', 224, 24.53);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (83491, 'Waffle Iron', 257, 23.41);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (47955, 'Gas Oven', 339, 12.4);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (31306, 'Gas Oven', 251, 24.97);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (45681, 'Refrigerator', 666, 1.82);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (53177, 'Refrigerator', 142, 14.29);
insert into PRODUCT_INFO (PRODUCT_ID, PRODUCT_NAME, SERIAL_NUMBER, MSRP) values (49662, 'Refrigerator', 207, 17.82);

-- -- Generate contains data
-- Pepperoni pizza ingredients
-- dough
insert into CONTAINS (PRODUCT_ID, COMPONENT_ID) values (26418, 15732); 
-- cheese 
insert into CONTAINS (PRODUCT_ID, COMPONENT_ID) values (26418, 81241); 
-- olive oil
insert into CONTAINS (PRODUCT_ID, COMPONENT_ID) values (26418, 52487); 
-- pepperoni slices
insert into CONTAINS (PRODUCT_ID, COMPONENT_ID) values (26418, 93587); 
-- tomato sauce
insert into CONTAINS (PRODUCT_ID, COMPONENT_ID) values (26418, 32957); 
-- Chocolate cake ingredients
-- Flour
insert into CONTAINS (PRODUCT_ID, COMPONENT_ID) values (52634, 82941); 
-- Eggs
insert into CONTAINS (PRODUCT_ID, COMPONENT_ID) values (52634, 44281);
-- Sugar
insert into CONTAINS (PRODUCT_ID, COMPONENT_ID) values (52634, 77813); 
-- Olive oil
insert into CONTAINS (PRODUCT_ID, COMPONENT_ID) values (52634, 52487); 
-- Cocoa powder
insert into CONTAINS (PRODUCT_ID, COMPONENT_ID) values (52634, 57254);
--Tomato Sauce Ingredients (To test recursive ingredients)
insert into CONTAINS (PRODUCT_ID, COMPONENT_ID) values (32957, 35138); 

-- -- Generate sells data
--Store 1
Insert into SELLS (STORE_ID, PRODUCT_ID) values (79601, 63396);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (79601, 20036);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (79601, 82637);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (79601, 45681);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (79601, 25957);
--Store 2
Insert into SELLS (STORE_ID, PRODUCT_ID) values (27385, 52634);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (27385, 50931);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (27385, 36148);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (27385, 45681);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (27385, 59909);
--Store 3
Insert into SELLS (STORE_ID, PRODUCT_ID) values (14555, 81506);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (14555, 45681);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (14555, 44340);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (14555, 84264);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (14555, 26418);
--Store 4
Insert into SELLS (STORE_ID, PRODUCT_ID) values (63754, 52127);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (63754, 94198);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (63754, 82637);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (63754, 84264);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (63754, 84280);
--Store 5
Insert into SELLS (STORE_ID, PRODUCT_ID) values (52633, 50931);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (52633, 64727);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (52633, 99315);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (52633, 82637);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (52633, 15221);
--Store 6
Insert into SELLS (STORE_ID, PRODUCT_ID) values (78057, 87018);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (78057, 47955);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (78057, 58885);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (78057, 83491);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (78057, 44340);
--Store 7
Insert into SELLS (STORE_ID, PRODUCT_ID) values (19095, 55321);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (19095, 50931);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (19095, 27853);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (19095, 26418);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (19095, 25957);
--Store 8
Insert into SELLS (STORE_ID, PRODUCT_ID) values (39709, 57254);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (39709, 53177);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (39709, 14729);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (39709, 83491);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (39709, 52127);
--Store 9
Insert into SELLS (STORE_ID, PRODUCT_ID) values (54007, 44281);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (54007, 25957);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (54007, 39960);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (54007, 84280);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (54007, 53177);
--Store 10
Insert into SELLS (STORE_ID, PRODUCT_ID) values (33619, 44281);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (33619, 59909);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (33619, 84264);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (33619, 82941);
Insert into SELLS (STORE_ID, PRODUCT_ID) values (33619, 59947);

-- -- Generate makes data
--Supplier 1
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (69411, 92334);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (69411, 82941);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (69411, 87018);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (69411, 99315);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (69411, 15732);
--Supplier 2
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (46830, 50931);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (46830, 52634);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (46830, 36148);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (46830, 53177);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (46830, 84264);
--Supplier 3
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (64437, 18036);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (64437, 31306);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (64437, 82941);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (64437, 50580);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (64437, 81506);
--Supplier 4
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (77824, 83491);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (77824, 44340);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (77824, 84264);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (77824, 52487);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (77824, 93587);
--Supplier 5
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (81420, 87018);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (81420, 55321);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (81420, 31306);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (81420, 52487);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (81420, 26418);
--Supplier 6
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (48608, 84280);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (48608, 36177);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (48608, 82941);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (48608, 81506);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (48608, 39960);
--Supplier 7
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (72623, 35138);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (72623, 47433);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (72623, 15732);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (72623, 69170);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (72623, 44281);
--Supplier 8
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (53067, 84264);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (53067, 47955);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (53067, 50580);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (53067, 81506);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (53067, 49662);
--Supplier 9
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (74442, 84264);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (74442, 32957);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (74442, 81241);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (74442, 92334);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (74442, 94198);
--Supplier 10
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (64523, 25957);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (64523, 15221);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (64523, 57254);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (64523, 52127);
Insert into Makes (SUPPLIER_ID, PRODUCT_ID) values (64523, 84280);

-- -- Generate shipment data
-- Shipments sent to a store
Insert into Shipment (SHIPMENT_ID, STORE_ID, SUPPLIER_ID) values (10000, 79601, 69411);
Insert into Shipment (SHIPMENT_ID, STORE_ID, SUPPLIER_ID) values (10001, 79601, 69411);
Insert into Shipment (SHIPMENT_ID, STORE_ID, SUPPLIER_ID) values (10002, 79601, 64523);
-- Shipments sent to a supplier
Insert into Shipment (SHIPMENT_ID, STORE_ID, SUPPLIER_ID) values (10003, null, 69411);
Insert into Shipment (SHIPMENT_ID, STORE_ID, SUPPLIER_ID) values (10004, null, 69411);
Insert into Shipment (SHIPMENT_ID, STORE_ID, SUPPLIER_ID) values (10005, null, 64523);

-- Generate supplier ships data
Insert into Supplier_ships (SHIPMENT_ID, SUPPLIER_ID, SHIPMENT_DATE) values (10000, 69411, DATE '2025-04-19');
Insert into Supplier_ships (SHIPMENT_ID, SUPPLIER_ID, SHIPMENT_DATE) values (10001, 69411, DATE '2025-04-20');
Insert into Supplier_ships (SHIPMENT_ID, SUPPLIER_ID, SHIPMENT_DATE) values (10002, 64523, DATE '2025-04-21');
Insert into Supplier_ships (SHIPMENT_ID, SUPPLIER_ID, SHIPMENT_DATE) values (10003, 69411, DATE '2025-04-22');
Insert into Supplier_ships (SHIPMENT_ID, SUPPLIER_ID, SHIPMENT_DATE) values (10004, 69411, DATE '2025-04-23');
Insert into Supplier_ships (SHIPMENT_ID, SUPPLIER_ID, SHIPMENT_DATE) values (10005, 64523, DATE '2025-04-24');

-- Generate supplier recieves data
Insert into Supplier_receives (SHIPMENT_ID, SUPPLIER_ID) values (10003, 64523);
Insert into Supplier_receives (SHIPMENT_ID, SUPPLIER_ID) values (10004, 64523);
Insert into Supplier_receives (SHIPMENT_ID, SUPPLIER_ID) values (10005, 69411);

-- Generate crate data
-- Shipment 10000:
-- Crate containing eggs
Insert into Crate (BATCH_ID, SHIPMENT_ID, PRODUCT_ID, UNITS, PRICE_PAID) VALUES (99999, 10000, 44281, 25, 25.00);
-- Crate containing eggs (Showing the same unit can have a different price paid in the same shipment)
Insert into Crate (BATCH_ID, SHIPMENT_ID, PRODUCT_ID, UNITS, PRICE_PAID) VALUES (99998, 10000, 44281, 25, 27.50);

-- Shipment 10003:
-- Crate containing toasters
Insert into Crate (BATCH_ID, SHIPMENT_ID, PRODUCT_ID, UNITS, PRICE_PAID) VALUES (99997, 10003, 63396, 10, 50);
-- Crate containing pepperoni pizza (will be used for recalls)
Insert into Crate (BATCH_ID, SHIPMENT_ID, PRODUCT_ID, UNITS, PRICE_PAID) VALUES (99996, 10003, 26418, 10, 30);