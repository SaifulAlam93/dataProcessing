# Data Processing
> Customer processing is when we process large-scale customers into the database, and
we store the data of the customer .by using this user store customer from a large-scale
data file, which needs to store in the system database in an optimized way and very fast
to manage easily. You have to implement a process by which the user can process the
file easily and store customer data.


## Table of Contents
* [Technologies Used](#technologies-used)
Java-8,
Maven-3.6.3,
Spring boot,
MySQL

* [Screenshots](#screenshots)

First of all, you need to create a database in your MySQL DB and configure the application.properties file.
You also need to configure your file path "file.upload-dir=D:/Exam_17_11_2022" in the application.properties file.
Then run the project. 
There are two APIs in this project.
One is for file upload and another is for getting .csv file.
Generated CSV files will save in your given directory.

- First API Calling:
![image](https://user-images.githubusercontent.com/59245133/204107347-0abad9cc-75dc-4fee-b72f-472b29c0a3e2.png)

-You will find details in log file.
![image](https://user-images.githubusercontent.com/59245133/204107386-48696b1b-8b4a-4575-9315-ab7fec02768a.png)
-Second API call(GET API):
![image](https://user-images.githubusercontent.com/59245133/204107599-9170f53f-c172-44b3-9713-4d5aa0d3412b.png)
-Data saved in given derectory.
![image](https://user-images.githubusercontent.com/59245133/204108267-99841d06-ef81-497f-827f-4faea81776e0.png)


## General Information
- Just open the git bash and run "mvn clean install"
- You will find a jar file in the target folder.
then run that using this command "java -...jar"

