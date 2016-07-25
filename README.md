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


