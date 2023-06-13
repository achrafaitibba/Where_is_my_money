# Where Is My Money
This is a sample application that provides a comprehensive demonstration of tracking life transactions to help users understand or remember where their money is spent and where it comes from. The app comes with intuitive visualization(You will find the front end in my github repo) tools to enable users to get a clear picture of their expenses/incomes and make informed decisions.

## About the Application

This is a simple web application that exposes a REST API. This application uses Maven as the build tool and the current
LTS version of Java, 17. I hope to add more functionality to this application in the future but
for now this project uses the following dependencies:

- Spring Web
- Spring Data JDBC
- Spring Security
- Spring Data JPA
- MySQL Database
- JWT
- Hibernate
- Lombok
- Hikari
- Jakarta Validation

## Deploy Locally
You need to download : 
- Apache-Tomcat 10, required for spring-boot 3.0.0
- Change the packaging from JAR to WAR
- Run : 
```bash
./mvnw clean install
```
- Change directory to :/target
- Copy the war file and past it to your-tomcat-directory/webapps
- Change directory to : your-tomcat-directory/bin
- In command line run the following 
```bash
catalina.bat run
```

## Running the application

You can run this application from your favorite IDE or by running the following command:

```bash
./mvnw spring-boot:run
```


## Building for Production

If you want to build an artifact that can be used in production: this application uses `JAR` as the
packaging type. This means that you can run the following command to create something that is ready to be used in production.

```bash
./mvnw clean package install
```

## Testing the application
To test the api endpoints the live version, you can use this swagger documentation UI : 
[Api Documentation Test](https://achrafaitibba.com/apps/wmm.html)
or  
[Railway](https://wmm.up.railway.app/swagger-ui/index.html)

## Railway

Bring your code, we'll handle the rest. Made for any language, for projects big and small. [Railway](https://railway.app/)
is the cloud that takes the complexity out of shipping software.

If you want to deploy your code for free, instead of using aws, google engines or azure you 
can use Railway, 500H for free.

Create a new empty project in Railway and start by creating a MySQL database. Once you have that created you can create
a new project from GitHub. You can use the following environment variables based on the database you just created.

```properties
spring_profiles_active=prod
PROD_DB_HOST=HOST_HERE
PROD_DB_PORT=POST_HERE
PROD_DB_NAME=railway
PROD_DB_PASSWORD=PASSWORD_HERE
PROD_DB_USERNAME=root
```

You don't need GitHub Actions or any type of pipeline for this setup because Railway handles this for you. Simply push your code to GitHub
and a new build and deploy will be triggered. If you want to disable this functionality you can from the settings of your project
on Railway. 

## Front-End repository
Not available for now hh

## Documentation path
``` markdown
https://{HOSTING:PORT}/swagger-ui/index.html
```

## Links
[MIT LICENSE](https://github.com/achrafaitibba/where_is_my_money/blob/master/LICENSE.md)

[LinkedIn](https://www.linkedin.com/in/achrafaitibba)

[My Website](https://www.achrafaitibba.com)

[Twitter](https://www.twitter.com/achrafaitibba)
