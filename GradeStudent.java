

/**Hàm begin() để hiển thị thông điệp chào mừng.
*Hàm midterm() để nhập và tính toán điểm thi giữa kỳ.
*Hàm finalterm() để nhập và tính toán điểm thi cuối kỳ.
*Hàm homework() để nhập và tính toán điểm bài tập về nhà.
*Hàm report() để tính toán về hiển thị kết quả GPA cũng như thông báo nhận xét tương ứng.
**/

import java.util.*;

public class GradeStudent {

    private static int scale = 100;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        intro();

        //Thông tin cần tìm
        float scoreWeightedMidterm = midterm(console);
        float scoreWeightedFinal = finalterm(console);
        float scoreWeightedHomework = homework(console);

        report(scoreWeightedMidterm, scoreWeightedFinal, scoreWeightedHomework);
    }

    //Hàm intro() để hiển thị thông điệp chào mừng
    public static void intro() {
        System.out.println("This program reads exam/homework scores and reports your overall course grades.");
        System.out.println();
    }

    //Hàm midterm() để nhập và tính toán điểm thi giữa kỳ
    public static float midterm(Scanner console) {
        System.out.println("Midterm:");
        return getExamResult(console);
    }

    //Hàm finalterm() để nhập và tính toán điểm thi cuối kỳ
    public static float finalterm(Scanner console) {
        System.out.println("Finalterm:");
        return getExamResult(console);
    }

    //Hàm homework() để nhập và tính toán điểm bài tập về nhà
    public static float homework(Scanner console) {
        System.out.println("Homework:");
        int weightHomework = getScore(console, "Weight (0-100)? ");

        // Tổng 3 trọng số chính xác là 100
        while (weightHomework != scale) {
            System.out.println("Total weights add up to 100");
            weightHomework = getScore(console, "Weight (0-100)? ");
        }

        // Tính điểm assignment
        int assign = getScore(console, "Number of assignments? ");
        int assignScore = 0;
        int assignMax = 0;
        for (int i = 1; i <= assign; i++) {
            System.out.print("Assignment " + i + " score and max? ");
            assignScore += getInt(console);
            assignMax += getInt(console);
        }

        // Tính điểm section
        int sectScore = getScore(console, "How many sections did you attend? ") * 5;

        // Điểm tối đa cho assignment 150 và attendance 30
        int totalScore = Math.min(assignScore, 150) + Math.min(sectScore, 30);
        int totalMax = Math.min(assignMax, 150) + 30;

        float scoreWeightedHomework = caculateWeightedExam(weightHomework, (float) totalScore/totalMax * 100);

        System.out.println("Section points = " + sectScore + " / 30");
        System.out.println("Total points = " + totalScore + " / " + totalMax);
        System.out.printf("Weighted score = %.1f / %d%n", scoreWeightedHomework, weightHomework);
        System.out.println();

        return scoreWeightedHomework;
    }

    //Hàm report() để tính toán về hiển thị kết quả GPA cũng như thông báo nhận xét tương ứng
    public static void report(float scoreWeightedMidterm, float scoreWeightedFinal, float scoreWeightedHomework) {
        float grade = scoreWeightedMidterm + scoreWeightedFinal + scoreWeightedHomework;
        System.out.printf("Overall percentage = %.1f", grade);
        System.out.println();
        if (grade >= 85) {
            System.out.println("Your grade will be at least: 3.0");
        } else if (grade >= 75) {
            System.out.println("Your grade will be at least: 2.0");
        } else if (grade >= 60) {
            System.out.println("Your grade will be at least: 0.7");
        } else {
            System.out.println("Your grade will be at least: 0.0");
        }

        System.out.println("<3 <3 <3 Score is just a number <3 <3 <3");
    }

    //Thu thập kết quả thi và báo cáo điểm trung bình
    public static float getExamResult(Scanner console) {
        int weightExam = getScore(console, "Weight (0-100)? ");
        scale -= weightExam;
        int score = getScore(console, "Score earned? ");

        //switch
        if (getScore(console, "Were scores shifted (1=yes, 2=no)? ") == 1) {
            int scoreShifted = getScore(console, "Shift amount? ");
            score = Math.min(score + scoreShifted, 100);
        }

        float scoreWeightedExam = caculateWeightedExam(weightExam, score);
        System.out.println("Total points = " + score + " / 100");
        System.out.printf("Weighted score = %.1f / %d%n", scoreWeightedExam, weightExam);
        System.out.println();
        return scoreWeightedExam;
    }

    //Ham getScore() kiem tra so nguyen input co trong pham vi gia tri 1 den 100
    public static int getScore(Scanner console, String promt) {
        System.out.print(promt);
        int num = getInt(console);
        while (num < 1 || num > 100) {
            System.out.println("Please input a score between 1 and 100!");
            num = getInt(console);
        }
        return num;
    }

    //Ham getInt() kiem tra input la so nguyen
    public static int getInt(Scanner console) {
        while (!console.hasNextInt()) {
            console.next();
            System.out.println("Not an integer, please try again!");
        }

        return console.nextInt();
    }

    public static float caculateWeightedExam(int weightExam, float score) {
        return ((float) weightExam * score) / 100;
    }



}
