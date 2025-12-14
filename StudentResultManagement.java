import java.util.Scanner;

// Custom Exception
class InvalidMarksException extends Exception {
    InvalidMarksException(String message) {
        super(message);
    }
}

// Student Class
class Student {
    int rollNumber;
    String studentName;
    int[] marks = new int[3];

    Student(int rollNumber, String studentName, int[] marks)
            throws InvalidMarksException {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.marks = marks;
        validateMarks(); // validation
    }

    // throws custom exception
    void validateMarks() throws InvalidMarksException {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < 0 || marks[i] > 100) {
                throw new InvalidMarksException(
                        "Invalid marks for subject " + (i + 1) + ": " + marks[i]
                );
            }
        }
    }

    double calculateAverage() {
        int sum = 0;
        for (int m : marks) {
            sum += m;
        }
        return sum / 3.0;
    }

    void displayResult() {
        System.out.println("Roll Number : " + rollNumber);
        System.out.println("Name        : " + studentName);
        System.out.print("Marks       : ");
        for (int m : marks) {
            System.out.print(m + " ");
        }
        System.out.println();
        double avg = calculateAverage();
        System.out.println("Average     : " + avg);

        if (avg >= 40) {
            System.out.println("Result      : PASS");
        } else {
            System.out.println("Result      : FAIL");
        }
    }
}

// Main Class
public class StudentResultManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static Student[] students = new Student[20];
    static int count = 0;

    static void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                marks[i] = sc.nextInt();
            }

            students[count++] = new Student(roll, name, marks);
            System.out.println("Student added successfully.\n");

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Returning to main menu...\n");

        } catch (Exception e) {
            System.out.println("Input mismatch error. Please enter valid data.\n");

        } finally {
            sc.nextLine(); // clear buffer
        }
    }

    static void showStudentDetails() {
        System.out.print("Enter Roll Number to search: ");
        int roll = sc.nextInt();

        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (students[i].rollNumber == roll) {
                students[i].displayResult();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("===== Student Result Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Show Student Details");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    showStudentDetails();
                    break;
                case 3:
                    System.out.println("Thank you for using the system.");
                    break;
                default:
                    System.out.println("Invalid choice.\n");
            }

        } while (choice != 3);
    }
}
