import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Student Grade Calculator ===");
        
        // 1. Input: Take the number of subjects
        System.out.print("Enter the total number of subjects: ");
        int numSubjects = scanner.nextInt();

        // Validation for number of subjects
        if (numSubjects <= 0) {
            System.out.println("Invalid number of subjects. Exiting program.");
            return;
        }

        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        // 2. Input & Calculate Total Marks: Take marks obtained out of 100 for each subject
        for (int i = 0; i < numSubjects; i++) {
            while (true) {
                System.out.print("Enter marks obtained in Subject " + (i + 1) + " (out of 100): ");
                int inputMark = scanner.nextInt();

                // Validate that marks are within the 0-100 range
                if (inputMark >= 0 && inputMark <= 100) {
                    marks[i] = inputMark;
                    totalMarks += inputMark;
                    break; // Exit the validation loop if input is correct
                } else {
                    System.out.println("Invalid input! Marks should be between 0 and 100. Please try again.");
                }
            }
        }

        // 3. Calculate Average Percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // 4. Grade Calculation: Assign grades based on the average percentage achieved
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 35) {
            grade = 'E';
        } else {
            grade = 'F'; // Fail
        }

        // 5. Display Results: Show total marks, average percentage, and the corresponding grade
        System.out.println("\n============================= ");
        System.out.println("           RESULTS            ");
        System.out.println("============================= ");
        System.out.println("Total Marks Obtained : " + totalMarks + " / " + (numSubjects * 100));
        System.out.printf("Average Percentage   : %.2f%%\n", averagePercentage);
        System.out.println("Grade Assigned       : " + grade);
        System.out.println("============================= ");

        scanner.close();
    }
}
