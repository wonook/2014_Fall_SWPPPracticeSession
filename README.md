# swppwarmup

This is for 'SWPP Warm Up Assignment', which is due April 10, 2015.

A [link](https://sites.google.com/site/snuswppspr2015/programming-assignment) to the site.


# CSE SWPP, SNU, Spring 2015<br>
# 1st Warm-up Programming Assignment

This assignment is for you to become familiar with web frameworks you will use to build your system. We borrowed main ideas from a UCB CS169 warm-up project. You must do this work INDIVIDUALLY, not along with your team members, although everyone do this assignment on the same platform (Ruby/Rails + HTML5/Javascript). 

You must use Github from the beginning of the development for version control. You need to create a public repo for your programming assignment and use it for your development. You must notify the name of your public github repo to the staff (swpp-staff [at] cmslab.snu.ac.kr) right after creating the public repo. We will check the quality of your code and your development process. 

You must deploy your Back-end server on a public cloud platform so that TAs can access it. The server should implement the APIs in the specification precisely.

## 1. Features

You will have to implement a simple login counter web application, that can be used to add users to a database, allow them to log in using a password, and keep track how many times they have logged in. 

Your application will have two screens. A "main" screen that is shown initially to input credentials, and a "welcome" screen, that is shown once a user has successfully logged in. The UI screens and the transitions between them are shown below:

![Image1](https://lh6.googleusercontent.com/hd0c3mjphlBUW7-mPviw8sk6ATx_UKMKAlXXQfxZO8Nhr2B7y8SHkosshGNxgH6vK902AS_JIOG6gQtYbcZ3aLnVkk4TJOaaQykR2_Cof2bjdB1PMePbEY04RH4ucc7Ewg)

Initially, the message box on the main screen should say "Please enter your credentials below". The same message should be shown when the main screen is reached from the Logout button. 

When the main screen is displayed in response to an error, the error messages should be one of those shown below. In parentheses we show the error code that this message corresponds to (see the Design section for a more precise specification of error codes):<br>
- "The user name should be 5~20 characters long. Please try again." 
(error_code: -1)
- "The password should be 8~20 characters long. Please try again."
(error_code: -2)
- "This user name already exists. Please try again."
(error_code: -3)
- "Invalid username and password combination. Please try again. "
(error_code: -4)

Your project must use a persistent database running on a server to store the user data. The user interface of the application runs on a separate client device, which you can choose to be one of:
- a mobile device using a native or web browser user interface
- a desktop using a web browser user interface

You can select the framework to use from the list that we have prepared. 

### Notes and Non-Requirements:<br>
- You can assume all user names and passwords are ASCII strings.  You do not need to validate this.  You do not need to support internationalization or localization of your application.
- You do not need to do any form of validation on user passwords.  You are allowed to store passwords as clear-text in the database.
 - Note: Normally, this would be exceedingly bad practice for a real application.  In fact, it may be legally negligent (not legal advice).  
 - As a reminder, it is strongly recommended that you do not use any of your real passwords in testing!  
- You can ignore concurrent updates and reads to the database.  You do not need to provide any form of offline capability or synchronization
- We do not have specific requirements about colors, fonts, etc.
- The text box for entering the user password must hide the password as you type it.

## 2. Design: Backend<br>
The backend must use one of the frameworks that we mentioned above. The backend will have a relational DB, with one table with the following schema: "username : string, password : string, count : int", with username enforced to be non-empty, a unique key. The username string will be greater than equal to 5 and up to 20 characters long. And the password string will be greater than equal to 8 and up to 20 characters long. The backend will have one class "UsersModel" that encapsulates all the communication with the DB. If you want to use a NoSql DB like Mongo, you are free to do so. You do not have to use the exact name UsersModel for your model class if your framework has other naming conventions.

### Design: Web Service Protocol

Your backend will have to expose a web service (HTTP) with a REST API using JSON for data transport. The web service must support the following URLs. We will grade your work(details in the “Grading” section) using the exact same URLs as following: 

REFER TO TABLE AT [THIS SITE](https://sites.google.com/site/snuswppspr2015/programming-assignment)


- All the API must be deployed on a server and publicly accessible. TAs will grade your work by visiting $YOUR_BACKEND_IP_ADDRESS/$API. We describe how to deploy your application in Heroku in Appendix A for your information. It is fine to use other public cloud platforms you have access to.
- Exceptions (1), (2), (3) are handled in the order of (1), (2), (3). (e.g. if exception (1) is caught then exception (2), (3) are ignored)
- $USER_NAME depends on user input and $LOGIN_CNT depends on number of times the user has logged in
- On successful /signup, the user is automatically logged in (thus, login_count: 1) 

In addition, your backend can provide other GET methods (e.g., for serving HTML, CSS, and JS files for your front-end). 

Note that it is very important that your requests use the specified Content-Type header. Otherwise, you may be getting strange errors when deploying to Heroku (e.g., 406). 

## 3. Design: User Interface
The user interface must appear and function as described above in the Requirements section. We do not have specific requirements about colors, fonts, etc. Additionally, the text box for entering the user password must hide the password as you type it. You must implement the user interface using the client technology that you will use for your project (HTML5/Javascript). 

## 4. Grading (10 points in total)<br>
Front-end(5 points): <br>
We will visit your website from github and grade your Front-End manually. Grading will be mostly based on whether your service displays proper messages. You don’t need to worry about the aesthetics.

Back-end(5 points): <br>
We will grade your Back-End using a checker program that makes a series of requests to your backend API. Be sure to deploy your backend API so that it can be accessed by us. Also be sure to precisely implement the APIs instructed in the Design section above.  For Example, the checker program takes your $backend_ip_address as an argument and does POST /signup?username=hi&password=12345678. Then it looks at the returned json value and if it is correct prints ‘O’ and otherwise prints ‘X’.

## 5. Submission
Due: 4/10 (Fri) 6:00PM (This is a hard deadline)

Submit your assignment report to swpp-staff [at] cmslab.snu.ac.kr. The email subject should be “[SWPP] Spr2015-SWPP-hw1-$student_name”.<br>
Your report should contain
- Student: $student_id $student_name
- Github: $your_github_repo_name
               (we will check the snapshot of the master branch at the deadline)
- Back-End API: $your_backend_API_IP_address
- Back-End instruction: how to run and deploy your back-end server code 
- Front-End instruction: how to run your front-end code
