Introduction:

Below is a summary of what are the applications in this repository:

1. A microserverice developed using Spring Boot framework and follows the MVC architectural pattern
	2. It has an embedded tomcat server and can be run as a standalone app, or it can be deployed in an application container
	3. It uses JPA and Hibernate for database access
	4. It's a RESTful service and has the following 4 operations:  POST (create), GET (read), PUT (update), DELETE (delete)
	5. It has a SWAGGER UI that describes the API and can be used to facilitate testing/integration
	6. The endpoint is protected by oauth2, only the true resource owner can access the resource

7. An Authentication server that implements Oauth2 framework, it acts as the authentication authority and is responsible for issuing access tokens
	8. The server is implemented using Spring Boot Security
	9. Tokens are generated using JWT standard
	10. Each token is signed using asymmetric key, where the key pair was generated using SHA 256 Hash Algorithm
	
11. The goal is to demonstrate
	12. Microservices that can be standup'd and torn down as needed
	13. The "password" grant flow of Oauth2
	14. stateless JWT authentication
	15. authorization based on user roles
	16. Token expiration
	17. Using the refresh token
	



How to use it:

1. start the authentication server: java -jar demo-oauth2-1.0-SNAPSHOT.jar
	- you should not see any exception at start up
	- authentication server runs behind port 9081
2. start the democrud app: java -jar democrud-1.0-SNAPSHOT.jar
	- you should see 2 exceptions at start up because the app is dropping table and sequence that doesn't exist in database yet
	- democrud app runs behind port 9080
	
3. start the democrud_not_secure app : java-Dserver.port=9082 -jar democrud_not_secure.jar
	- Since democrud is not protected, you can no longer accsss anything without first authenticating with the oauth2 server therefore a 2nd one that is not secure has to be started to demonstrate swagger-ui and other things

5. Get "person" information without a access token, and receive a HTTP 403 Access Denid (forbidden)
curl -X GET \
  'http://localhost:9080/gary/demo/crud/v1/person?id=1' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 7d9dd910-e7b3-ce89-e35d-6e2ea890cf06'
	
5. Get "person" information with wrong token, and receive a HTTP 401 Unauthorized
curl -X GET \
  'http://localhost:9080/gary/demo/crud/v1/person?id=1' \
  -H 'authorization: Bearer abcd' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 7b589a40-7d16-9f3d-25c2-d74a805a6aa5'

4. To get a token, use the following CURL command:
curl -X POST \
  http://democrud:123password@localhost:9081/oauth/token \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -H 'postman-token: 96a1854d-1489-b14f-d909-03420d88f795' \
  -d 'grant_type=password&username=user&password=password'
  
  
5. Create a "person" in the database (replace the token below with a valid one)
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


6. Get "person" using a valid token (becareful, token expires in 2 minutes)
curl -X GET \
  'http://localhost:9080/gary/demo/crud/v1/person?id=1' \
  -H 'authorization: Bearer <access token goes here>' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: bcaa4b77-8f71-3b83-b011-d6ecf5096fa8'

  
7. wait 2 minutes, then execute the GET again, you should receive a 401

8. Obtain a new access token using the refresh token
curl -X POST \
  http://democrud:123password@localhost:9081/oauth/token \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -H 'postman-token: 2c850596-0c52-5de8-06ee-6245b7224b62' \
  -d 'grant_type=refresh_token&refresh_token=<refresh token goes here>'
  
9. Get person again using the token obtained from step 8

10. go to https://jwt.io/ to decode the token and see what is inside