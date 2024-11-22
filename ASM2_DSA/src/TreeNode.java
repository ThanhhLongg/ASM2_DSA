

public class TreeNode {
    private Student student;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Student student) {
        this.student = student;
        this.left = null;
        this.right = null;
    }

    // Getter for student
    public Student getStudent() {
        return student;
    }

    // Setter for student
    public void setStudent(Student student) {
        this.student = student;
    }

    // Getter for left child
    public TreeNode getLeft() {
        return left;
    }

    // Setter for left child
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    // Getter for right child
    public TreeNode getRight() {
        return right;
    }

    // Setter for right child
    public void setRight(TreeNode right) {
        this.right = right;
    }
}
