package service;

import model.Question;
import model.Result;
import util.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizService {

    private final String FILE_NAME = "data/questions.txt";

    public void startQuiz(Scanner scanner, String studentId) {

        List<Question> questions = getQuestions();

        if (questions.isEmpty()) {
            System.out.println("\nNo questions are available.");
            System.out.println("Please ask the admin to add questions first.");
            return;
        }

        int score = 0;
        int questionNumber = 1;

        System.out.println("\n========================================");
        System.out.println("              JAVA QUIZ");
        System.out.println("========================================");

        for (Question question : questions) {

            System.out.println("\nQuestion " + questionNumber);
            System.out.println("----------------------------------------");
            System.out.println(question.getQuestionText());

            System.out.println("A. " + question.getOptionA());
            System.out.println("B. " + question.getOptionB());
            System.out.println("C. " + question.getOptionC());
            System.out.println("D. " + question.getOptionD());

            String answer;

            while (true) {
                System.out.print("Enter your answer (A/B/C/D): ");
                answer = scanner.nextLine().trim().toUpperCase();

                if (answer.equals("A") || answer.equals("B")
                        || answer.equals("C") || answer.equals("D")) {
                    break;
                }

                System.out.println("Invalid answer. Please enter A, B, C, or D.");
            }

            if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
                score++;
            }

            questionNumber++;
        }

        System.out.println("Student ID : " + studentId);
System.out.println("Score      : " + score + "/" + questions.size());

double percentage = (score * 100.0) / questions.size();

System.out.printf("Percentage : %.2f%%%n", percentage);

// Save result
Result result = new Result(
        studentId,
        "Java Quiz",
        score,
        questions.size()
);

new ResultService().saveResult(result);

System.out.println("Result saved successfully!");
System.out.println("========================================");
    }

    public List<Question> getQuestions() {

        List<Question> questionList = new ArrayList<>();
        List<String> questions = FileManager.readFromFile(FILE_NAME);

        for (String line : questions) {

            String[] data = line.split("\\|");

            if (data.length == 9) {

                Question question = new Question(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        data[6],
                        data[7],
                        data[8]
                );

                questionList.add(question);
            }
        }

        return questionList;
    }
}