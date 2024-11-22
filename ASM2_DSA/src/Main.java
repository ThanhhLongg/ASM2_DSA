import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentBST bst = new StudentBST();

        while (true) {
            try {
                System.out.println("\nMenu:");
                System.out.println("1. Add a new student");
                System.out.println("2. Edit student by ID");
                System.out.println("3. Delete student by ID");
                System.out.println("4. Display all students by ID (Quick Sort)");
                System.out.println("5. Display all students by score (Bubble Sort) ");
                System.out.println("6. Search for a student by ID");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        try {
                            System.out.print("Enter Student ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();  // Consume newline

                            String name;
                            while (true) {
                                System.out.print("Enter Student Name: ");
                                name = scanner.nextLine();
                                if (!name.matches(".*\\d.*")) {  // Check if name contains any digits
                                    break;
                                } else {
                                    System.out.println("Invalid name! Name should not contain numbers.");
                                }
                            }

                            double score;
                            while (true) {
                                System.out.print("Enter Student Score (1-10): ");
                                score = scanner.nextDouble();
                                if (score >= 1 && score <= 10) {  // Validate score range
                                    break;
                                } else {
                                    System.out.println("Invalid score! Score must be between 1 and 10.");
                                }
                            }

                            bst.insert(new Student(id, name, score));
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number for ID or score.");
                            scanner.nextLine();  // Consume the invalid input
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("Enter Student ID to edit: ");
                            int editId = scanner.nextInt();
                            scanner.nextLine();  // Consume newline

                            String newName;
                            while (true) {
                                System.out.print("Enter new name: ");
                                newName = scanner.nextLine();
                                if (!newName.matches(".*\\d.*")) {  // Check if name contains any digits
                                    break;
                                } else {
                                    System.out.println("Invalid name! Name should not contain numbers.");
                                }
                            }

                            double newScore;
                            while (true) {
                                System.out.print("Enter new score (1-10): ");
                                newScore = scanner.nextDouble();
                                if (newScore >= 1 && newScore <= 10) {  // Validate score range
                                    break;
                                } else {
                                    System.out.println("Invalid score! Score must be between 1 and 10.");
                                }
                            }

                            if (bst.edit(editId, newName, newScore))
                                System.out.println("Student updated.");
                            else
                                System.out.println("Student not found.");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number for ID or score.");
                            scanner.nextLine();  // Consume the invalid input
                        }
                        break;

                    case 3:
                        try {
                            System.out.print("Enter Student ID to delete: ");
                            int deleteId = scanner.nextInt();
                            bst.delete(deleteId);
                            System.out.println("Student deleted.");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number for ID.");
                            scanner.nextLine();  // Consume the invalid input
                        }
                        break;

                    case 4:
                        System.out.println("Students sorted by ID (Quick Sort):");
                        bst.inOrderTraversal();
                        break;

                    case 5:
                        System.out.println("Students sorted by score (Bubble Sort) :");
                        bst.sortByScore();
                        break;

                    case 6:
                        try {
                            System.out.print("Enter Student ID to search: ");
                            int searchId = scanner.nextInt();
                            Student student = bst.search(searchId);
                            if (student != null)
                                System.out.println("Found student: " + student);
                            else
                                System.out.println("Student not found.");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number for ID.");
                            scanner.nextLine();  // Consume the invalid input
                        }
                        break;

                    case 7:
                        System.out.println("Exiting.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid menu choice! Please enter a valid number.");
                scanner.nextLine();  // Consume the invalid input
            }
        }
    }
}
