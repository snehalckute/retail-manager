# retail-manager

Retail Manager Application
Retail manager application mantains Shop details with In memory DB using Spring Boot and ReST operations 

Endpoint - http://localhost:8080/retail/manager/v1/shops


Operations :

A. Add Shop :

1. Request URL - http://localhost:8080/retail/manager/v1/shops

2. HTTP Method - POST

3. Request Header - 
Content-Type	=	application/json

4. Request Body-
{
	"name": "PizzaHut",
	"shopAddress": {
		"number": "333",
		"postcode": "411045",
"addressLine":"Baner"
	}
}

5. Response Body : 

Sample response 1 -
{
  "message": "New shop added"
}

Sample response 2 -
{
  "CurrentAddress": {
    "shop": {
      "shopName": "PizzaHut",
      "shopAddress": "Shop No 111 , Pune",
      "shopPostCode": "411001",
      "latitude": "18.5296029",
      "longitude": "73.87601529999999"
    }
  },
  "PreviousAddress": {
    "shop": {
      "shopName": "PizzaHut",
      "shopAddress": "Shop No 333 , Baner",
      "shopPostCode": "411045",
      "latitude": "18.5642382",
      "longitude": "73.77694319999999"
    }
  }
}



B. Retrieve Nearest Shop :

1. Request URL - http://localhost:8080/retail/manager/v1/shops?lat=20.719194&lng=78.315712

2. HTTP Method - GET

3. Request Parameters-
lat=20.719194
lng=78.315712

4. Response Body : 
{
  "shopName": "McD",
  "shopAddress": "Shop No 999 , Wardha",
  "shopPostCode": "442001",
  "latitude": "20.7329104",
  "longitude": "78.54337009999999"
}


What this application Offers :

1. Operation to add Shop
2. Update existing Shop
3. Maintain these shop details in Memory
4. With request parameters of user location (Latitude and Longitude) Nearest shop from the Memory will be identified and shop details will be provided in response.


Pre-requisites :

1. Gradle
2. Java 1.8
3. Apache tomcat 1.7 or above ( Or any other application server )

How to run this application :

1. Clone code from repository ( You can change Google API Key as per requirement in application.properties)
2. Open command prompt in admin mode
3. cd {..}\MyShop
4. Run following commands
	a. gradlew cleanEclipse clean eclipse clean build
	b. gradlew bootrun --debug(As per required Logging level)
5. Import SampleRequests.json in ReST Client add on of Firefox ( You can use chrome with Postman plugin )
6. Hit all 3 Add shop requests and cross verify by hitting Retrieve Nearest shop request
