This project explains how to use Spring boot+jersey+DynamoDB together

Step 1: Create "ShoppingCart" table in AWS dynamodb 
Table Name: ShoppingCart
Primary Key or Hash Key : name

Step 2: Add Dependencies in the pom for jersey & dynamo db - refer pom.xml

Step 3: Create JerseyConfig.java 

Step 4: Create DynamoDBConfig.java  

Step 5: Add properties in application.properties

amazon.aws.accesskey=
amazon.aws.secretkey=
amazon.dynamodb.endpoint=

for endpoint for specific region refer -> http://docs.aws.amazon.com/general/latest/gr/rande.html#ddb_region 

Step 6:Create Entity Class table refer ShoppingCart.java

Step 7: Create repository interface ShoppingCartRepository.java

Step 8:In main application create rest resource using jersey Application.java

Available Rest Services:

1.Resource to fetch shopingcart by a name
Method: GET
URL:http://{{ip}}:{{port}}/rest/cart/fetch?name={{name}}
Response MIME media type: JSON

2.Resource to add item to shopping cart
METHOD:PUT 
URL:http://{{ip}}:{{port}}/rest/cart/additem
Content-Type: application/x-www-form-urlencoded
@FormParam:name  - > name of the cart
@FormParam:items - > comma seperated string values
Response MIME media type: JSON

3.Resource to delete cart
METHOD:DELETE
URL:http://{{ip}}:{{port}}/rest/cart/delete?name={{name}}
Response MIME media type: "text/plain"

4.Resouce to fetch all carts
METHOD:GET
URL:http://{{ip}}:{{port}}/rest/cart/fetchall
Response MIME media type:JSON
