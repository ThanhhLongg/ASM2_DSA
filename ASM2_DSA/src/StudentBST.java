import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentBST {
    private TreeNode root;

    public StudentBST() {
        this.root = null;
    }

    // Insert student
    public void insert(Student student) {
        root = insertRecursive(root, student);
    }

    private TreeNode insertRecursive(TreeNode node, Student student) {
        if (node == null) return new TreeNode(student);
        if (student.getId() < node.getStudent().getId())
            node.setLeft(insertRecursive(node.getLeft(), student));
        else if (student.getId() > node.getStudent().getId())
            node.setRight(insertRecursive(node.getRight(), student));
        return node;
    }

    // Search for a student by ID
    public Student search(int id) {
        TreeNode node = searchRecursive(root, id);
        return node != null ? node.getStudent() : null;
    }

    private TreeNode searchRecursive(TreeNode node, int id) {
        if (node == null || node.getStudent().getId() == id) return node;
        return (id < node.getStudent().getId()) ? searchRecursive(node.getLeft(), id) : searchRecursive(node.getRight(), id);
    }

    // Edit student by ID
    public boolean edit(int id, String newName, double newScore) {
        Student student = search(id);
        if (student != null) {
            student.setName(newName);
            student.setScore(newScore);
            return true;
        }
        return false;
    }

    // Delete student by ID
    public void delete(int id) {
        root = deleteRecursive(root, id);
    }

    private TreeNode deleteRecursive(TreeNode node, int id) {
        if (node == null) return null;

        if (id < node.getStudent().getId()) {
            node.setLeft(deleteRecursive(node.getLeft(), id));
        } else if (id > node.getStudent().getId()) {
            node.setRight(deleteRecursive(node.getRight(), id));
        } else {
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();

            TreeNode smallestNode = findSmallestNode(node.getRight());
            node.setStudent(smallestNode.getStudent());
            node.setRight(deleteRecursive(node.getRight(), smallestNode.getStudent().getId()));
        }
        return node;
    }

    private TreeNode findSmallestNode(TreeNode node) {
        return node.getLeft() == null ? node : findSmallestNode(node.getLeft());
    }

    // In-order traversal (ascending order by ID)
    public void inOrderTraversal() {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(TreeNode node) {
        if (node != null) {
            inOrderRecursive(node.getLeft());
            System.out.println(node.getStudent());
            inOrderRecursive(node.getRight());
        }
    }

    // Method to sort students by score
    public void sortByScore() {
        ArrayList<Student> students = new ArrayList<>();
        inOrderToList(root, students);  // Collect all students in a list
        Collections.sort(students, Comparator.comparingDouble(Student::getScore).reversed());  // Sort by score in descending order

        System.out.println("Students sorted by score:");
        for (Student student : students) {
            System.out.println(student);  // Print sorted students
        }
    }

    // Helper method to collect students into a list during in-order traversal
    private void inOrderToList(TreeNode node, ArrayList<Student> students) {
        if (node != null) {
            inOrderToList(node.getLeft(), students);
            students.add(node.getStudent());  // Add student to list
            inOrderToList(node.getRight(), students);
        }
    }

    // Helper method to get all students in a sorted list
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        inOrderToList(root, students);
        return students;
    }

    // Bubble Sort by score
    public void bubbleSortByScore(ArrayList<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).getScore() > students.get(j + 1).getScore()) {
                    Collections.swap(students, j, j + 1);
                }
            }
        }
    }

    // Quick Sort by ID
    public void quickSortByID(ArrayList<Student> students, int low, int high) {
        if (low < high) {
            int pi = partition(students, low, high);
            quickSortByID(students, low, pi - 1);
            quickSortByID(students, pi + 1, high);
        }
    }

    private int partition(ArrayList<Student> students, int low, int high) {
        int pivot = students.get(high).getId();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (students.get(j).getId() < pivot) {
                i++;
                Collections.swap(students, i, j);
            }
        }
        Collections.swap(students, i + 1, high);
        return i + 1;
    }

    // Display students sorted by score (Bubble Sort)
    public void displaySortedByScore() {
        ArrayList<Student> students = getAllStudents();
        bubbleSortByScore(students);
        System.out.println("Students sorted by score (Bubble Sort):");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Display students sorted by ID (Quick Sort)
    public void displaySortedByID() {
        ArrayList<Student> students = getAllStudents();
        quickSortByID(students, 0, students.size() - 1);
        System.out.println("Students sorted by ID (Quick Sort):");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
