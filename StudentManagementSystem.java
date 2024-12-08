import java.util.*;
import java.io.*;

public class StudentManagementSystem {
    static class Student {
        private String name;
        private int rollNumber;
        private String grade;
        public Student(String name, int rollNumber, String grade) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
        }
        public String getName() {
            return name;
        }
        public int getRollNumber() {
            return rollNumber;
        }
        public String getGrade() {
            return grade;
        }
        @Override
        public String toString() {
            return "Student [Name=" + name + ", Roll Number=" + rollNumber + ", Grade=" + grade + "]";
        }
    }
    private List<Student> students = new ArrayList<>();
    public void addStudent(Student student) {
        students.add(student);
    }
    public boolean removeStudent(int rollNumber) {
        return students.removeIf(student -> student.getRollNumber() == rollNumber);
    }
    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
    public void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
                writer.newLine();
            }
        }
    }
    public void loadFromFile(String fileName) throws IOException {
        students.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int rollNumber = Integer.parseInt(parts[1]);
                String grade = parts[2];
                students.add(new Student(name, rollNumber, grade));
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        try {
            sms.loadFromFile("students.txt");
        } catch (IOException e) {
            System.out.println("No saved data found. Starting fresh.");
        }
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save and Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    scanner.nextLine(); // Consume newline
                    String name = scanner.nextLine();
                    System.out.print("Enter Roll Number: ");
                    int rollNumber = scanner.nextInt();
                    System.out.print("Enter Grade: ");
                    String grade = scanner.next();
                    sms.addStudent(new Student(name, rollNumber, grade));
                    System.out.println("Student added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Roll Number to Remove: ");
                    int rollToRemove = scanner.nextInt();
                    if (sms.removeStudent(rollToRemove)) {
                        System.out.println("Student removed successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter Roll Number to Search: ");
                    int rollToSearch = scanner.nextInt();
                    Student foundStudent = sms.searchStudent(rollToSearch);
                    if (foundStudent != null) {
                        System.out.println("Student Found: " + foundStudent);
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case 4:
                    sms.displayAllStudents();
                    break;
                case 5:
                    try {
                        sms.saveToFile("students.txt");
                        System.out.println("Data saved successfully,  Exiting...");
                    } catch (IOException e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, Please try again.");
            }
        }
    }
}
