Initial

mvn clean install -DskipTests

Note : to use below urls comment out the prop spring.cloud.gateway.discovery.locator.enabled=true

- http://localhost:8765/CURRENCY-EXCHANGE-SERVICE/currency-exchange/from/USD/to/INR
- http://localhost:8765/CURRENCY-CONVERSION-SERVICE/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/CURRENCY-CONVERSION-SERVICE/currency-conversion-feign/from/USD/to/INR/quantity/10

lowercase:
- http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR
- http://localhost:8765/currency-conversion-service/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-conversion-service/currency-conversion-feign/from/USD/to/INR/quantity/10
- http://localhost:8765/identity-service/auth/validate?token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwaW50dSIsImlhdCI6MTY4NzI4OTkwMiwiZXhwIjoxNjg3MjkxNzAyfQ.IQ-bEzdAOkVCZwWBOLG3Qfc2aDRhzIsaQBaZFaATz5Y



Custom Routes
 Note : to use below urls comment out the prop spring.cloud.gateway.discovery.locator.enabled=true

- http://localhost:8765/currency-exchange/from/USD/to/INR
  while calling above api pass bearer token  in request header
  to generate token for already registered user using below api with username and pass
  
  http://localhost:9000/auth/token  -- POST 
  {
  "username":"pintu",
  "password":"pwd"
  }

- http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10

- http://localhost:8765/auth/validate?token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwaW50dSIsImlhdCI6MTY4NzM1ODk2MSwiZXhwIjoxNjg3MzYwNzYxfQ.YhAuImAFewFAAovC-lSJzR_XTTcPlbrlMkyDCL9p2WU


VAULT
----------
 Vault is a tool for securely accessing secrets. A secret can be anything that needs to be provided 
 controlled access. For e.g., API keys, passwords, database credentials, certificates, and more.  
 All the secure details are stored in Vault to avoid hard coding of them anywhere. All applications
  access Vault during initialization and while processing. Since Vault is the heart of the System,
  it should support highly availability and Fault Tolerance. 
  
 Rest-Assured and TestNG
 -----------------------
 https://www.techgeeknext.com/spring-boot/spring-boot-rest-assured
 https://www.youtube.com/watch?v=JJ7Tp7_fX4c
 
 We can override configuration parameters in spring boot in code, command-line arguments, ServletConfig init parameters, ServletContext init parameters, Java system properties, operating system variables, and application properties file.
 
An important thing to keep in mind is that these application properties files have the lowest precedence compared to other forms of overriding application context properties.
 
 In contrast to a Spring Boot application, a Spring Cloud application features a bootstrap context that is the parent of the application context. Although both of them share the same Environment, they have different conventions for locating the external configuration files.
  
  
  
  
  https://www.baeldung.com/spring-cloud-custom-gateway-filters
  https://www.youtube.com/watch?v=MWvnmyLRUik&t=2251s
  
 Request  flow
 --------------------- 
UM/signup
apiGateqay/UM/signup
apigateway->routelocator->forward UM module
signup done.

/login
same like above
uname/pwd succsess-> 
forward to UM .

success /failure

if success ->
response will have token
tokengenrate(){
//have secret key,jwt jar
//use lib pass some unique userinfo and secret key , pass expire info
//expire info and other deatils(ex:Roles,etc) will be part of token only so no need to store any where
//

}


/dashboard

req(apikey,token)->apigateway->
as login/signup is withut validation but others will go thru validation
filter-> 

validatetoken(){
        1.way to hit the common other module pass the token and validate it.
        2.same module validate it.

        valdation(){
            //u shoud have secretkey,jwt
            //use jwt lib pass token and secretkey it will validate

        }
}
gateway will redirect to dashboard.



/logout

need to check , how to invalidate before expire

check how to define load balancer strategy with gateway.
  


