import model.Student;
import model.Question;
import model.Result ;

import service.StudentService;
import service.AdminService;
import service.QuizService;
import service.ResultService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentService studentService = new StudentService();
    private static final AdminService adminService = new AdminService();
    private static final QuizService quizService = new QuizService();
    private static final ResultService resultService = new ResultService();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n========================================");
            System.out.println("       ONLINE QUIZ MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Student Login");
            System.out.println("2. Student Registration");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.println("========================================");

            int choice = readInteger("Enter your choice: ");

            switch (choice) {

                case 1:
                    studentLogin();
                    break;

                case 2:
                    studentRegistration();
                    break;

                case 3:
                    adminLogin();
                    break;

                case 4:
                    System.out.println("\nThank you for using Online Quiz Management System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice. Please select 1-4.");
            }
        }
    }

    private static void studentRegistration() {

        System.out.println("\n========== STUDENT REGISTRATION ==========");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (studentId.isEmpty() || name.isEmpty()
                || email.isEmpty() || password.isEmpty()) {

            System.out.println("All fields are required.");
            return;
        }

        if (!util.InputValidator.isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return;
        }

        if (!util.InputValidator.isValidPassword(password)) {
            System.out.println("Password must contain at least 4 characters.");
            return;
        }

        Student student =
                new Student(studentId, name, email, password);

        if (studentService.registerStudent(student)) {
            System.out.println("\nRegistration successful!");
        } else {
            System.out.println("\nStudent ID or email already exists.");
        }
    }

    private static void studentLogin() {

        System.out.println("\n============== STUDENT LOGIN ==============");

        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Student student =
                studentService.loginStudent(email, password);

        if (student == null) {
            System.out.println("\nInvalid email or password.");
            return;
        }

        System.out.println("\nLogin successful!");
        System.out.println("Welcome, " + student.getName() + "!");

        studentDashboard(student);
    }

    private static void studentDashboard(Student student) {

        while (true) {

            System.out.println("\n============= STUDENT DASHBOARD =============");
            System.out.println("1. Start Quiz");
            System.out.println("2. View My Results");
            System.out.println("3. Logout");
            System.out.println("=============================================");

            int choice = readInteger("Enter your choice: ");

            switch (choice) {

                case 1:
                    quizService.startQuiz(scanner, student.getStudentId());
                    break;

                case 2:
                    resultService.displayResults(student.getStudentId());
                    break;

                case 3:
                    System.out.println("\nLogged out successfully.");
                    return;

                default:
                    System.out.println("\nInvalid choice.");
            }
        }
    }

    private static void adminLogin() {

        System.out.println("\n================ ADMIN LOGIN ================");

        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (adminService.login(username, password)) {

            System.out.println("\nAdmin login successful!");
            adminDashboard();

        } else {

            System.out.println("\nInvalid admin username or password.");
        }
    }

    private static void adminDashboard() {

        while (true) {

            System.out.println("\n============== ADMIN DASHBOARD ==============");
            System.out.println("1. Add Question");
            System.out.println("2. View Questions");
            System.out.println("3. Search Question");
            System.out.println("4. Update Question");
            System.out.println("5. Delete Question");
            System.out.println("6. Logout");
            System.out.println("=============================================");

            int choice = readInteger("Enter your choice: ");

            switch (choice) {

                case 1:
                    addQuestion();
                    break;

                case 2:
                    viewQuestions();
                    break;

                case 3:
                    searchQuestion();
                    break;

                case 4:
                    updateQuestion();
                    break;

                case 5:
                    deleteQuestion();
                    break;

                case 6:
                    System.out.println("\nAdmin logged out.");
                    return;

                default:
                    System.out.println("\nInvalid choice.");
            }
        }
    }

    private static void addQuestion() {

        System.out.println("\n============== ADD QUESTION ==============");

        System.out.print("Question ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Question: ");
        String questionText = scanner.nextLine().trim();

        System.out.print("Option A: ");
        String optionA = scanner.nextLine().trim();

        System.out.print("Option B: ");
        String optionB = scanner.nextLine().trim();

        System.out.print("Option C: ");
        String optionC = scanner.nextLine().trim();

        System.out.print("Option D: ");
        String optionD = scanner.nextLine().trim();

        System.out.print("Correct Answer (A/B/C/D): ");
        String correctAnswer = scanner.nextLine().trim().toUpperCase();

        System.out.print("Category: ");
        String category = scanner.nextLine().trim();

        System.out.print("Difficulty (Easy/Medium/Hard): ");
        String difficulty = scanner.nextLine().trim();

        if (id.isEmpty() || questionText.isEmpty()
                || optionA.isEmpty() || optionB.isEmpty()
                || optionC.isEmpty() || optionD.isEmpty()
                || category.isEmpty() || difficulty.isEmpty()) {

            System.out.println("All fields are required.");
            return;
        }

        if (!util.InputValidator.isValidOption(correctAnswer)) {
            System.out.println("Correct answer must be A, B, C, or D.");
            return;
        }

        Question question = new Question(
                id,
                questionText,
                optionA,
                optionB,
                optionC,
                optionD,
                correctAnswer,
                category,
                difficulty
        );

        if (adminService.addQuestion(question)) {
            System.out.println("\nQuestion added successfully!");
        } else {
            System.out.println("\nQuestion ID already exists.");
        }
    }

    private static void viewQuestions() {

        System.out.println("\n============== ALL QUESTIONS ==============");

        List<Question> questions =
                adminService.getAllQuestions();

        if (questions.isEmpty()) {
            System.out.println("No questions found.");
            return;
        }

        for (Question q : questions) {

            System.out.println("\nID: " + q.getQuestionId());
            System.out.println("Question: " + q.getQuestionText());
            System.out.println("A. " + q.getOptionA());
            System.out.println("B. " + q.getOptionB());
            System.out.println("C. " + q.getOptionC());
            System.out.println("D. " + q.getOptionD());
            System.out.println("Correct Answer: " + q.getCorrectAnswer());
            System.out.println("Category: " + q.getCategory());
            System.out.println("Difficulty: " + q.getDifficulty());
            System.out.println("-------------------------------------------");
        }
    }

    private static void searchQuestion() {

        System.out.println("\n============== SEARCH QUESTION ==============");

        System.out.print("Enter Question ID: ");
        String id = scanner.nextLine().trim();

        Question q = adminService.searchQuestion(id);

        if (q == null) {
            System.out.println("Question not found.");
            return;
        }

        System.out.println("\nQuestion Found!");
        System.out.println("ID: " + q.getQuestionId());
        System.out.println("Question: " + q.getQuestionText());
        System.out.println("A. " + q.getOptionA());
        System.out.println("B. " + q.getOptionB());
        System.out.println("C. " + q.getOptionC());
        System.out.println("D. " + q.getOptionD());
        System.out.println("Correct Answer: " + q.getCorrectAnswer());
        System.out.println("Category: " + q.getCategory());
        System.out.println("Difficulty: " + q.getDifficulty());
    }

    private static void updateQuestion() {

        System.out.println("\n============== UPDATE QUESTION ==============");

        System.out.print("Enter Question ID to update: ");
        String id = scanner.nextLine().trim();

        Question existing = adminService.searchQuestion(id);

        if (existing == null) {
            System.out.println("Question not found.");
            return;
        }

        System.out.println("Enter new question details:");

        System.out.print("Question: ");
        String questionText = scanner.nextLine().trim();

        System.out.print("Option A: ");
        String optionA = scanner.nextLine().trim();

        System.out.print("Option B: ");
        String optionB = scanner.nextLine().trim();

        System.out.print("Option C: ");
        String optionC = scanner.nextLine().trim();

        System.out.print("Option D: ");
        String optionD = scanner.nextLine().trim();

        System.out.print("Correct Answer (A/B/C/D): ");
        String correctAnswer = scanner.nextLine().trim().toUpperCase();

        System.out.print("Category: ");
        String category = scanner.nextLine().trim();

        System.out.print("Difficulty: ");
        String difficulty = scanner.nextLine().trim();

        if (!util.InputValidator.isValidOption(correctAnswer)) {
            System.out.println("Invalid correct answer.");
            return;
        }

        Question updated = new Question(
                id,
                questionText,
                optionA,
                optionB,
                optionC,
                optionD,
                correctAnswer,
                category,
                difficulty
        );

        if (adminService.updateQuestion(updated)) {
            System.out.println("\nQuestion updated successfully!");
        } else {
            System.out.println("\nUnable to update question.");
        }
    }

    private static void deleteQuestion() {

        System.out.println("\n============== DELETE QUESTION ==============");

        System.out.print("Enter Question ID to delete: ");
        String id = scanner.nextLine().trim();

        System.out.print("Are you sure? (Y/N): ");
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("Y")) {

            if (adminService.deleteQuestion(id)) {
                System.out.println("\nQuestion deleted successfully!");
            } else {
                System.out.println("\nQuestion not found.");
            }

        } else {

            System.out.println("\nDelete operation cancelled.");
        }
    }

    private static int readInteger(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }
