Online Quiz Management System

1. Project Overview

The Online Quiz Management System is a Java-based console application developed to manage online quizzes for students and administrators.

The system allows students to register, log in, attempt quizzes, and view their quiz results. Administrators can securely log in and manage quiz questions using add, view, search, update, and delete operations.

The project demonstrates Java programming concepts such as classes and objects, encapsulation, methods, collections, file handling, exception handling, validation, and modular programming.

2. Objectives

- Provide a simple online quiz management system.
- Allow students to register and log in securely.
- Allow students to attempt available quiz questions.
- Calculate quiz scores and percentages automatically.
- Store quiz results using file handling.
- Allow administrators to manage quiz questions.
- Validate user input and handle invalid inputs.
- Maintain a modular and organized Java project structure.

3. Features

Student Module

- Student registration
- Student login
- Student dashboard
- Start quiz
- Automatic score calculation
- Percentage calculation
- View previous quiz results
- Student logout

Admin Module

- Admin login
- Add questions
- View all questions
- Search questions
- Update questions
- Delete questions
- Admin logout

Validation and Error Handling

- Empty-field validation
- Email format validation
- Password validation
- Answer option validation
- Duplicate student ID/email detection
- Duplicate question ID detection
- Invalid menu input handling
- File input/output exception handling

4. Technologies and Tools

- Programming Language: Java
- Development Environment: Visual Studio Code
- Storage: Text files
- Version Control: Git and GitHub
- Operating System: Windows

5. Project Structure

OnlineQuizManagementSystem/
│
├── src/
│   ├── Main.java
│   │
│   ├── model/
│   │   ├── Student.java
│   │   ├── Admin.java
│   │   ├── Question.java
│   │   └── Result.java
│   │
│   ├── service/
│   │   ├── StudentService.java
│   │   ├── AdminService.java
│   │   ├── QuizService.java
│   │   └── ResultService.java
│   │
│   └── util/
│       ├── FileManager.java
│       └── InputValidator.java
│
├── data/
│   ├── students.txt
│   ├── questions.txt
│   └── results.txt
│
├── README.md
├── statement.md
└── .gitignore

6. How to Run

1. Install Java JDK.
2. Open the project folder in Visual Studio Code.
3. Open the terminal in the project folder.
4. Compile the project:

javac -d out src/Main.java src/model/*.java src/service/*.java src/util/*.java

5. Run the application:

java -cp out Main

7. Default Admin Login

Username: admin
Password: admin123

8. Student Workflow

1. Student registers an account.
2. Student logs in using email and password.
3. Student opens the dashboard.
4. Student starts the available quiz.
5. The system checks the answers.
6. The system calculates the score and percentage.
7. The result is saved to the results file.
8. Student can view the saved result.

9. Admin Workflow

1. Admin logs in.
2. Admin opens the dashboard.
3. Admin can add, view, search, update, or delete questions.
4. Questions are stored in the questions text file.
5. Students can attempt the available questions.

10. Testing

The following functions were tested:

- Student registration
- Student login
- Admin login
- Adding questions
- Viewing questions
- Searching questions
- Updating questions
- Deleting questions
- Starting the quiz
- Answer validation
- Score calculation
- Percentage calculation
- Result storage
- Viewing student results
- Invalid menu input handling

A five-question quiz was successfully executed, producing a score of 5/5 and a percentage of 100%.

11. Future Enhancements

- Database integration using MySQL.
- Graphical user interface.
- Timer-based quizzes.
- Multiple quiz categories.
- Difficulty-based quiz selection.
- Randomized questions.
- Leaderboard functionality.
- Password hashing and stronger authentication.
- Detailed performance analytics.

12. Conclusion

The Online Quiz Management System provides a structured solution for conducting and managing quizzes through a Java console application. The project applies object-oriented programming, file handling, validation, exception handling, and modular service-based design to implement the required functionality.