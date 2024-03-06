<p align="center">
    <b>Java Database Programming</b>
</p>

This my homework on MSIB-06-java [link sumber](https://github.com/Zolusca/msib-java-database)

## What You Need
1. Java JDK v8
2. Mysql / another database SQL
3. Maven v3+

## Quick Start
1. setting your configuration database `username`,`password`,`driver` on `src/main/resources`
2. install dependency with maven
3. create a database with schema on `src/main/resources/DatabaseSchema`
        
    Note : theres have 2 sql file, pick `db_hr.sql` for get a dummy data
4. try to run

## Short Description
1. `Repository` -> wheres a code interacting with database. On this folder you can found a sql query
2. `Service` -> is the second layer to handle bussiness logic the program
3. `Entity` -> represent the table on database and prototype the object
4. `Controller` -> Control flow the request
5. `View` -> give a interface wheres user can get and send data
6. `Utility` -> wheres the tools/utilities, such as database connection
7. `Resources` -> wheres save a configuration file or other   
