# Dummy-Core-Bank-System-with-Integrator-Service
Back-end developer task (dummy core banking system and rest integrator service), as a SOAP web services and Integrator service as a REST Api.

## Development Methodology

### Core Bank Dummy

#### Tools and Technology Stack for core bank dummy.

JDK 1.8, Intellij , Maven – Development environment</Br>
Spring-boot – Underlying application framework </Br>
maven-jaxb2-plugin plugin – for JAXB stub generation and Mapping </Br>
(H2 In-memory database + Java Persistence API) - for memory management </Br>
SOAP UI and Wizdler browser plugin - to test SOAP end points</Br></Br>

##### Important Development Steps

SOAP Web Service created with the Spring Boot Starter Web Services. When creating the SOAP services use the contract first approach and define the XSD for the request and response.</BR>
/src/main/resources/core-bank-details.xsd

##### Sample get account balance request and response.  
![alt text](https://github.com/RajithAsanka/SSE-Task-Back-end-Developer-/blob/main/images/xsd.png?raw=true)

To do the mapping from XML to Java and Java to XML, JAXB (Java API for XML binding) used.</Br>
To generate the Java objects based on the XSD Maven JAXB Plugin defined in the pom.xml. Also wsdl4j dependency added to the pom.xml to generate the WSDl with spring web services.

##### URL of the WSDL - http://localhost:8082/dummy-core-bank/ws/corebank.wsdl

Wizdler browser plugin and SOAP-UI used to test the SOAP end points during the development time.</BR></BR>
![](images/wsdl.PNG)
</BR>

 H2 In-memory database used for the manage data in memory,  with spring boot + SOAP web services.
 
##### URL of the H2 databse console - http://localhost:8082/dummy-core-bank/h2/login.jsp (username/password - root)

###### SERVER CONFIGURATIONS</BR> 
server.port=8082 </BR>
server.servlet.context-path=/dummy-core-bank </BR>
 
 ### Integrator Service (Spring boot soap client with rest APIs)

#### Tools and Technology Stack for core integrator Service.

JDK 1.8, Intellij , Maven – Development environment</Br>
Spring-boot – Underlying application framework </Br>
maven-jaxb2-plugin plugin – for JAXB stub generation and Mapping </Br>
(mysql + Java Persistence API)  - to save audit events</Br>
swagger-ui - to test rest end points
</Br></Br>

##### Important Development Steps

Spring boot soap client created with the Spring Boot Starter Web Services and to generate client proxy code
use the maven-jaxb2-plugin and generated the JAXB annotated stub classes.
</BR>
resources/wsdl/corebank.wsdl

##### URL of the swagger Ui - http://localhost:8083/integrator-service/swagger-ui.html

## Exposed rest end points.</Br>
![](images/restendpoints.PNG)
</BR>

###### SERVER CONFIGURATIONS </BR> 
server.port=8083 </BR>
server.servlet.context-path=/integrator-service </BR>

## Test Core Bank Dummy System 
#### Build Commands for Application
##### dcbs- dummy core bank system source folder
##### integrator-service - integrator service source folder
##### mvn clean install - to build the Dummy core bank system and Integrator service use maven build commands.
## Run Commands

#####  java -jar dcbs-0.0.1-SNAPSHOT.jar - to run dummy core bank SOAP web services
##### java -jar integrator-service-0.0.1-SNAPSHOT.jar - to run dummy Integrator Service

## Exposed rest end points.</Br>
![](images/restendpoints.PNG)

###### Get account balance of particular account - @GetMapping
###### URL  - http://localhost:8083/integrator-service/rest/api/v1/get/account/balance/by/accountNo
###### Expected parameter name - accountNo
return the account balance of particular account if exists in the dummy core bank database other wise return service messages with error.


###### Sample Success Request
![](images/1.PNG)

###### Get total account balance - @GetMapping
###### URL  - http://localhost:8083/integrator-service/rest/api/v1/get/total/account/balance/by/userId
###### Expected parameter name - userId
return the total account balance of particular user if exists in the dummy core bank database other wise return error messages.

###### Sample Success Request
![](images/2.PNG)

###### Make Fund Transfer to Own Accounts / Make Fund Transfer to other Accounts - @PostMapping
###### URL  - http://localhost:8083/integrator-service/rest/api/v1/make/fundtransfer
###### Expected parameters,
###### fundTransferType - (OWN ,THIRDPARTY)
###### userId 
###### fromAccountNO - source account number
###### toAccountNO - beneficiary account number
###### amount - transfer amount

make the transaction according to fund trasfer type and return the transaction details.
###### OWN - Make Fund Transfer to Own Accounts
###### THIRDPARTY - Make Fund Transfer to other Accounts

###### Sample Success Request
![](images/3.PNG)

###### Sample Falied Request
![](images/4.PNG)


