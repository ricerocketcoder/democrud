Introduction:

Below is a summary of what are the applications in this repository:

1. A microserverice developed using Spring Boot framework and follows the MVC architectural pattern

- It has an embedded tomcat server and can be run as a standalone app, or it can be deployed in an application containerI
- It uses JPA and Hibernate for database access
- It's a RESTful service and has the following 4 operations:  POST (create), GET (read), PUT (update), DELETE (delete)
- It has a SWAGGER UI that describes the API and can be used to facilitate testing/integration
- The endpoint is protected by oauth2, only the true resource owner can access the resource

2. An Authentication server that implements Oauth2 framework, it acts as the authentication authority and is responsible for issuing access tokens
- server is implemented using Spring Boot Security
- Tokens are generated using JWT standard
- Each token is signed using asymmetric key, where the key pair was generated using SHA 256 Hash Algorithm
	
3. The goal is to demonstrate
- Microservices that can be standup'd and torn down as needed
- The "password" grant flow of Oauth2
- stateless JWT authentication
- authorization based on user roles
- Token expiration
- Using the refresh token
	



How to use it:

1. start the authentication server: java -jar demo-oauth2-1.0-SNAPSHOT.jar
	- you should not see any exception at start up
	- authentication server runs behind port 9081
2. start the democrud app: java -jar democrud-1.0-SNAPSHOT.jar
	- you should not see any exception at start up
	- democrud app runs behind port 9080
	
3. You can either skip to step 5 if you want to use curl/postman/soapui
   or you can test from the swagger-ui: http://localhost:9080/swagger-ui.html

4. To use the swagger UI, first follow step 7 to get a token.  The click on the "Authorize" button on the top right.  Enter "Bear <token>".  -> click authorize -> click done.  Now you have 2 minutes to try out all the APIs before the token needs to be renewed again.

5. Get "person" information without a access token, and receive a HTTP 403 Access Denid (forbidden)
curl -X GET \
  'http://localhost:9080/gary/demo/crud/v1/person?id=1' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 7d9dd910-e7b3-ce89-e35d-6e2ea890cf06'
	
6. Get "person" information with wrong token, and receive a HTTP 401 Unauthorized
curl -X GET \
  'http://localhost:9080/gary/demo/crud/v1/person?id=1' \
  -H 'authorization: Bearer abcd' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 7b589a40-7d16-9f3d-25c2-d74a805a6aa5'

7. To get a token, use the following CURL command:
curl -X POST \
  http://democrud:123password@localhost:9081/oauth/token \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -H 'postman-token: 96a1854d-1489-b14f-d909-03420d88f795' \
  -d 'grant_type=password&username=user&password=password'
  
  
8. Create a "person" in the database (replace the token below with a valid one)
curl -X POST \
  http://localhost:9080/gary/demo/crud/v1/person \
  -H 'authorization: Bearer <access token goes here>' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 9c790573-d256-f2e2-a4b8-54fbf9191ee5' \
  -d '{
  "firstName": "Gary",
  "lastName": "Ma"
}'


9. Get "person" using a valid token (becareful, token expires in 2 minutes)
curl -X GET \
  'http://localhost:9080/gary/demo/crud/v1/person?id=1' \
  -H 'authorization: Bearer <access token goes here>' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: bcaa4b77-8f71-3b83-b011-d6ecf5096fa8'

  
10. wait 2 minutes, then execute the GET again, you should receive a 401
11. Obtain a new access token using the refresh token
curl -X POST \
  http://democrud:123password@localhost:9081/oauth/token \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -H 'postman-token: 2c850596-0c52-5de8-06ee-6245b7224b62' \
  -d 'grant_type=refresh_token&refresh_token=<refresh token goes here>'
  
12. Get person again using the token obtained from step 8

13. go to https://jwt.io/ to decode the token and see what is inside
