package service;

import model.Result;
import util.FileManager;

import java.util.ArrayList;
import java.util.List;

public class ResultService {

    private final String FILE_NAME = "data/results.txt";

    public void saveResult(Result result) {
        FileManager.appendToFile(FILE_NAME, result.toString());
    }

    public List<Result> getResultsByStudent(String studentId) {

        List<Result> resultList = new ArrayList<>();
        List<String> results = FileManager.readFromFile(FILE_NAME);

        for (String line : results) {

            String[] data = line.split("\\|");

            if (data.length >= 4 &&
                    data[0].equalsIgnoreCase(studentId)) {

                try {
                    int score = Integer.parseInt(data[2]);
                    int totalQuestions = Integer.parseInt(data[3]);

                    Result result = new Result(
                            data[0],
                            data[1],
                            score,
                            totalQuestions
                    );

                    resultList.add(result);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid result data found.");
                }
            }
        }

        return resultList;
    }

    public void displayResults(String studentId) {

        List<Result> results = getResultsByStudent(studentId);

        if (results.isEmpty()) {
            System.out.println("\nNo results found.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("             MY QUIZ RESULTS");
        System.out.println("========================================");

        for (Result result : results) {

            System.out.println("Quiz       : " + result.getQuizName());

            System.out.println("Score      : " +
                    result.getScore() + "/" +
                    result.getTotalQuestions());

            System.out.printf("Percentage : %.2f%%%n",
                    result.getPercentage());

            System.out.println("----------------------------------------");
        }
    }
}
