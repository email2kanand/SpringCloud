ADD 
for server:
 <dependency>
         <groupId>de.codecentric</groupId>
         <artifactId>spring-boot-admin-starter-server</artifactId>
      </dependency>

@EnableAdminServer

For client:
There are 2 way:
1. Add dependency to client and specify the admin-server url
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>2.4.1</version>
</dependency>

#spring boot admin
spring.boot.admin.client.url=http://localhost:8080
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always

2.Using naming server
If you have a Spring Cloud Discovery (like Eureka) for your application, you don’t need to have Spring Boot Admin Client jar in each of your client application
ADD:
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
@EnableAdminServer
@EnableDiscoveryClient


eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka
eureka.client.register-with-eureka=false
Spring Boor Admin Server will get all client application details from Eureka and poll them for metrics.

# Enable security
ADD to server:
  <dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-server-ui-login</artifactId>
    <version>1.5.7</version>
	</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    <version>2.4.0</version>
</dependency>

and 
Define the security rule ex: WebSecurityConfig

App.prop define uname/pwd

spring.security.user.name=admin
spring.security.user.password=admin

#Clien side change for security
Once security is active the clients have to know about this authentication in order to register themselves to Spring Boot Admin Server. Also, they have to tell Spring Boot Admin Server how it should connect its actuator endpoints, i.e pass its own credentials (passed via metadata). 
#Required for this application to connect to SBA
spring.boot.admin.client.username=admin
spring.boot.admin.client.password=admin

#basic auth creddentials
spring.security.user.name=client
spring.security.user.password=client

#configs to give secured server info to SBA while registering
spring.boot.admin.client.instance.metadata.user.name= ${spring.security.user.name}
spring.boot.admin.client.instance.metadata.user.password=${spring.security.user.password}



### Notification
The following notifiers are available for configuration:

Email
PagerDuty
OpsGenie
Hipchat
Slack
Let's Chat

## Email Notifications
We'll first focus on configuring mail notifications for our admin server. For this to happen, we have to add the mail starter dependency as shown below:

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
    <version>2.4.0</version>
</dependency>
Copy
After this, we must add some mail configuration:

spring.mail.host=smtp.example.com
spring.mail.username=smtp_user
spring.mail.password=smtp_password
spring.boot.admin.notify.mail.to=admin@example.com


Now, whenever our registered client changes his status from UP to OFFLINE or otherwise, an email is sent to the address configured above. For the other notifiers, the configuration is similar.

###
Spring Boot Admin also supports cluster replication using Hazelcast. We just have to add the following Maven dependency and let the autoconfiguration do the rest:

https://www.javadevjournal.com/spring-boot/spring-boot-admin/
https://www.baeldung.com/spring-boot-admin


