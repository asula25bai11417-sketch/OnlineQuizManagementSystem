package service;

import model.Question;
import util.FileManager;

import java.util.ArrayList;
import java.util.List;

public class AdminService {

    private final String FILE_NAME = "data/questions.txt";

    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "admin123";

    public boolean login(String username, String password) {
        return ADMIN_USERNAME.equals(username)
                && ADMIN_PASSWORD.equals(password);
    }

    public boolean addQuestion(Question question) {
        List<String> questions = FileManager.readFromFile(FILE_NAME);

        for (String line : questions) {
            String[] data = line.split("\\|");

            if (data.length > 0 &&
                    data[0].equalsIgnoreCase(question.getQuestionId())) {
                return false;
            }
        }

        FileManager.appendToFile(FILE_NAME, question.toString());
        return true;
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        List<String> questions = FileManager.readFromFile(FILE_NAME);

        for (String line : questions) {
            Question question = convertToQuestion(line);

            if (question != null) {
                questionList.add(question);
            }
        }

        return questionList;
    }

    public Question searchQuestion(String questionId) {
        List<String> questions = FileManager.readFromFile(FILE_NAME);

        for (String line : questions) {
            Question question = convertToQuestion(line);

            if (question != null &&
                    question.getQuestionId().equalsIgnoreCase(questionId)) {
                return question;
            }
        }

        return null;
    }

    public boolean deleteQuestion(String questionId) {
        List<String> questions = FileManager.readFromFile(FILE_NAME);
        List<String> updatedQuestions = new ArrayList<>();

        boolean found = false;

        for (String line : questions) {
            String[] data = line.split("\\|");

            if (data.length > 0 &&
                    data[0].equalsIgnoreCase(questionId)) {
                found = true;
            } else {
                updatedQuestions.add(line);
            }
        }

        if (found) {
            FileManager.writeToFile(FILE_NAME, updatedQuestions);
        }

        return found;
    }

    public boolean updateQuestion(Question updatedQuestion) {
        List<String> questions = FileManager.readFromFile(FILE_NAME);
        List<String> updatedQuestions = new ArrayList<>();

        boolean found = false;

        for (String line : questions) {

            String[] data = line.split("\\|");

            if (data.length > 0 &&
                    data[0].equalsIgnoreCase(updatedQuestion.getQuestionId())) {

                updatedQuestions.add(updatedQuestion.toString());
                found = true;

            } else {
                updatedQuestions.add(line);
            }
        }

        if (found) {
            FileManager.writeToFile(FILE_NAME, updatedQuestions);
        }

        return found;
    }

    private Question convertToQuestion(String line) {
        String[] data = line.split("\\|");

        if (data.length != 9) {
            return null;
        }

        return new Question(
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
    }
}