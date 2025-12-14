import java.util.Scanner;

// Base Class
class Employee {
    int employeeId;
    String name;
    double salary;

    Employee(int employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    // Method to be overridden
    double calculateBonus() {
        return salary * 0.05; // 5% bonus
    }

    void displayDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        System.out.println("Bonus: " + calculateBonus());
    }
}

// Derived Class Manager
class Manager extends Employee {
    String department;

    Manager(int employeeId, String name, double salary, String department) {
        super(employeeId, name, salary); // super keyword
        this.department = department;
    }

    // Method overriding
    @Override
    double calculateBonus() {
        return salary * 0.10; // 10% bonus
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Department: " + department);
    }
}

// Derived Class Developer
class Developer extends Employee {
    String programmingLanguage;

    Developer(int employeeId, String name, double salary, String language) {
        super(employeeId, name, salary);
        this.programmingLanguage = language;
    }

    // Method overriding
    @Override
    double calculateBonus() {
        return salary * 0.08; // 8% bonus
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Programming Language: " + programmingLanguage);
    }
}

// Main Class
public class EmployeeManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static Employee[] employees = new Employee[20];
    static int count = 0;

    static void addManager() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Department: ");
        String dept = sc.nextLine();

        employees[count++] = new Manager(id, name, salary, dept);
        System.out.println("Manager added successfully.\n");
    }

    static void addDeveloper() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Programming Language: ");
        String lang = sc.nextLine();

        employees[count++] = new Developer(id, name, salary, lang);
        System.out.println("Developer added successfully.\n");
    }

    static void displayEmployee() {
        System.out.print("Enter Employee ID to search: ");
        int id = sc.nextInt();

        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                employees[i].displayDetails(); // polymorphism
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee not found.");
        }
        System.out.println();
    }

    static void displayAllEmployees() {
        if (count == 0) {
            System.out.println("No employees available.\n");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println("---------------");
            employees[i].displayDetails();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("===== Employee Management System =====");
            System.out.println("1. Add Manager");
            System.out.println("2. Add Developer");
            System.out.println("3. Display Employee Details");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addManager();
                    break;
                case 2:
                    addDeveloper();
                    break;
                case 3:
                    displayEmployee();
                    break;
                case 4:
                    displayAllEmployees();
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice.\n");
            }
        } while (choice != 5);
    }
}
