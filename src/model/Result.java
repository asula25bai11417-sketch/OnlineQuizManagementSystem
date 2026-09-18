package model;

public class Result {

    private String studentId;
    private String quizName;
    private int score;
    private int totalQuestions;

    public Result(String studentId, String quizName, int score, int totalQuestions) {
        this.studentId = studentId;
        this.quizName = quizName;
        this.score = score;
        this.totalQuestions = totalQuestions;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getQuizName() {
        return quizName;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public double getPercentage() {
        if (totalQuestions == 0) {
            return 0;
        }
        return (score * 100.0) / totalQuestions;
    }

    @Override
    public String toString() {
        return studentId + "|" + quizName + "|" +
               score + "|" + totalQuestions + "|" +
               String.format("%.2f", getPercentage());
    }
}