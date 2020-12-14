# Jumia Phone Numbers Exercise

Jumia Phone Numbers is an exercise to Jumia company, who needs a single page application in Java that uses an SQLite 3 
[database](https://github.com/MuriloCarraro/Jumia-Phone-Numbers/blob/main/jumia.db) to list and categorize country phone numbers.

### Requirements:
  - Phone numbers should be categorized by country, state (valid or not valid), country code and number.
  - The page should render a list of all phone numbers available in the DB
  - It should be possible to filter by country and state. 

### Installation
The application was developed using Spring Boot, therefore to build the project is necessary: 
  - JDK 1.8 or later
  - Maven 3.2+

### Execution and tests:

To run the application locally you can run the command below and access the URL [http://localhost:8080/](http://localhost:8080/):
```sh
$ mvn spring-boot:run
```

To run the tests of the application you can run the command:
```sh
$ mvn test
```

If necessary, you can add new phone records using the SQL command below:
```sh
$ insert into TBL_PHONES values((select substr(u,1,8)||'-'||substr(u,9,4)||'-4'||substr(u,13,3)||'-'||v||substr(u,17,3)||'-'||substr(u,21,12) as phone_id from (select lower(hex(randomblob(16))) as u, substr('89ab',abs(random()) % 4 + 1, 1) as v)), '(251) 10345678' );
```

### Extra features:
  - Responsive Web Design on different screens: 

![Mobile](https://github.com/MuriloCarraro/Jumia-Phone-Numbers/blob/main/images/Screen%20Shot%201.png)
