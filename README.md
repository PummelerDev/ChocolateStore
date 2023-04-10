# ChocolateStore
___
Project for TeachMeSkills

# Descriptions:


This is the server side of the store. Allows you to optimize the processes of creating and processing orders. as well as providing product information.


# Database
The server works with PostgreSQL. The database has 6 tables. Buyers - stores data about buyers. Orders - stores data about orders. Manufacturers - stores data about manufacturers. Products - stores data about products. Roles - stores data about which of the buyers is also an administrator. Also provided is the Flayway schema history table, which is responsible for checking database versions.
At the time of creation, there are test values in the database.

# Registration
To register a customer, go to "POST http://localhost:8080/registration". It is also necessary to send json:( 
{
  "firstName": "testName",
  "lastName": "surname",
  "address": "testAddress",
  "phone": "testPhone",
  "email": "test@gmail.com",
  "login": "testLogin",
  "password": "testPassword"
} ).

# Authentication
Get access to endpoints (except: "http://localhost:8080/registration", "http://localhost:8080/auth", "http://localhost:8080/product/all", "http://localhost:8080/manufacturer/all" ) we can only pass authentication. After passing the authentication, the user has the role USER or ADMIN.


# User capabilities
___
Available endpoints:

"GET http://localhost:8080/customer/current" – gets current customer.

"PUT http://localhost:8080/customer/update" – update customer's info.
You need to send json:( 
{
  "firstName": "testName",
  "lastName": "surname",
  "address": "testAddress",
  "phone": "testPhone",
  "email": "test@gmail.com"
} ).

"DELETE http://localhost:8080/customer/current/delete" - marks customer as deleted.

"GET http://localhost:8080/customer/login" – gets login.

"PUT http://localhost:8080/customer/update/login" – get data about company from ticker.
You need to send json:( 
{
  "login": "testLogin"
} ).

"GET http://localhost:8080/order/all" – gets all orders for current customer.

"POST http://localhost:8080/order/create" – creates an order.
You need to send json:( 
{
  "productId": long_id,
  "customerId": "id",
  "quantity": "quantityTest"
} ).

"POST http://localhost:8080/order/add/{orderNumer}" – adds a product to order.
You need to send json:( 
{
  "customerId": long_id,
  "quantity": "quantityTest"
} ).

"POST http://localhost:8080/order/update/{key}" – updates a product to order. Key it's an unique value from order. You can find it to go to ""GET http://localhost:8080/order/number/{orderNumber}"
You need to send json:( 
{
  "customerId": long_id,
  "quantity": "quantityTest"
} ).

"POST http://localhost:8080/order/cancel/{orderNumer}" – cancels an order by number.
You need to send json:( 
{
  "customerId": long_id,
  "quantity": "quantityTest"
} ).

"POST http://localhost:8080/order/cancel/{orderNumber}/{id}" – cancels a product from the order by number and key. You can find it to go to ""GET http://localhost:8080/order/number/{orderNumber}"
You need to send json:( 
{
  "customerId": long_id,
  "quantity": "quantityTest"
} ).

"POST http://localhost:8080/order/collect/{orderNumer}" – collects an order.

"GET http://localhost:8080/order/number/{orderNumer}" – gets an order by order number if it belongs to current customer.

"GET http://localhost:8080/order/number/{orderNumer}" – gets an pdf for order by order number if it belongs to current customer.


# Admin capabilities
___
Because the administrator is also a buyer, then all user endpoints are available to him.
Available endpoints:

"GET http://localhost:8080/customer/get/all" - gets all customers.

"GET http://localhost:8080/customer/get/{id}" - gets a customer by id.

"POST http://localhost:8080/customer/{id}/restore" - restores a deleted customer.

"DELETE http://localhost:8080/customer/{id}/remove" - removes a customer from DB.

"GET http://localhost:8080/order/all/admin" – gets all orders.

"GET http://localhost:8080/order/get/{id}" - gets an order by id.

"DELETE http://localhost:8080/order/get/{id}/remove" – remove an order by id.

"POST http://localhost:8080/order/finish/{orderNumber}" – marks order as finished.

"GET http://localhost:8080/order/number/{orderNumer}/admin" – gets an order by order number.

"GET http://localhost:8080/order/number/{orderNumer}" – gets an pdf for order by order number.

"GET http://localhost:8080/manufacturer/all/admin" – gets all manufacturers with addictional info.

"GET http://localhost:8080/manufacturer/get/{id}?manufacturerName=testName" - gets a manufacturer by id.

"POST http://localhost:8080/manufacturer/create?manufacturerName=testName" - creates a manufacturer.

"PUT http://localhost:8080/manufacturer/update/{id}" - updates a manufacturer by id.

"DELETE http://localhost:8080/manufacturer/get/{id}/remove"" - removes a manufacturer from DB.

"GET http://localhost:8080/product/all/admin" – gets all products with addictional info.

"GET http://localhost:8080/product/get/{id}" - gets a product by id.

"POST http://localhost:8080/product/create" - creates a product.
You need to send json:( 
{
  "kind": "ENUM_KIND",
  "topping": "ENUM_TOPPING",
  "manufacturerId": testId,
  "name": "testName",
  "description": "testDescription",
  "weight": int_weight,
  "price": double_price
} ).

"PUT http://localhost:8080/product/update/{id}" - updates a product by id.
You need to send json:( 
{
  "kind": "ENUM_KIND",
  "topping": "ENUM_TOPPING",
  "manufacturerId": testId,
  "name": "testName",
  "description": "testDescription",
  "weight": int_weight,
  "price": double_price
} ).

"DELETE http://localhost:8080/product/get/{id}/remove"" - removes a product from DB.
