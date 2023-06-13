import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BST {
    private BSTNodePassword root;

    public BST() {
        root = null;
    }

    public BSTNodePassword getRoot(){
        return this.root;
    }

    public void insert(String key, String value) {
        root = insertNode(root, key, value);
    }

    private BSTNodePassword insertNode(BSTNodePassword root, String key, String value) {
        if (root == null) {
            return new BSTNodePassword(key, value);
        }

        int compareResult = key.compareTo(root.key);
        if (compareResult < 0) {
            root.left = insertNode(root.left, key, value);
        } else if (compareResult > 0) {
            root.right = insertNode(root.right, key, value);
        } else {
            // Update value if the key already exists
            root.value = value;
        }

        return root;
    }

    public String search(String key) {
        BSTNodePassword node = searchNode(root, key);
        return (node != null) ? node.value : null;
    }

    private BSTNodePassword searchNode(BSTNodePassword root, String key) {
        if (root == null || root.key.equals(key)) {
            return root;
        }
        int compareResult = key.compareTo(root.key);
        if (compareResult < 0) {
            return searchNode(root.left, key);
        } else {
            return searchNode(root.right, key);
        }
    }

    public void delete(String key) {
        root = deleteNode(root, key);
    }

    private BSTNodePassword deleteNode(BSTNodePassword root, String key) {
        if (root == null) {
            return null;
        }

        int compareResult = key.compareTo(root.key);
        if (compareResult < 0) {
            root.left = deleteNode(root.left, key);
        } else if (compareResult > 0) {
            root.right = deleteNode(root.right, key);
        } else {
            // BSTNodePassword to be deleted found
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // BSTNodePassword to be deleted has two children
            BSTNodePassword successor = getMinValueNode(root.right);
            root.key = successor.key;
            root.value = successor.value;
            root.right = deleteNode(root.right, successor.key);
        }

        return root;
    }

    private BSTNodePassword getMinValueNode(BSTNodePassword root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public void display() {
        displayTree(root);
    }

    private void displayTree(BSTNodePassword root) {
        if (root != null) {
            displayTree(root.left);
            String[] keyArr = root.key.split("<->");
            System.out.printf("| %-8s | %-8s | %-8s |\n", keyArr[0], keyArr[1], root.value);
            displayTree(root.right);
        }
    }

    public void saveTree() {
        File file = new File("passwordDB.txt");
        FileWriter fileWriter;
        try{
            fileWriter = new FileWriter(file, true);
            saveTreeToTextFile(root, fileWriter, file);
            fileWriter.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveTreeToTextFile(BSTNodePassword root, FileWriter fileWriter, File file) {
        if (root != null) {
            // run on left subtree
            saveTreeToTextFile(root.left, fileWriter, file);

            // add to textfile
            String[] keyArr = root.key.split("<->");
            String data = keyArr[0] + "<->" + keyArr[1] + "<->" + root.value + "\n";
            try {
                fileWriter.write(data);
            } catch (IOException e) {
                System.out.println("Error");
                throw new RuntimeException(e);
            }

            // run on right subtree
            saveTreeToTextFile(root.right, fileWriter, file);
        }
    }
}
