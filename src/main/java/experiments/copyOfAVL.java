package experiments;
import datastructures.dictionaries.AVLTree;

public class copyOfAVL {
    AVLNode root;
    int size;

    public copyOfAVL(){
        root = null;
        size = 0;
    }

    private class AVLNode {
        int value;
        String key;
        AVLNode left, right;
        int height;
        public AVLNode(String key, Integer value) {
            this.key = key;
            this.value = value;
            height = 1;
        }
    }


    int height(AVLNode N){
        return N != null ? N.height : -1;
    }

    public AVLNode find (int value){
        return findRecursive(value, root);

    }

    private AVLNode findRecursive (int value, AVLNode root) {
        if (root == null) {
            return null;
        }
        if (value > root.value) {
            return findRecursive(value, root.right);

        } else if (value < root.value){
            return findRecursive(value, root.left);
        } else{
            return root;
        }
    }


    public void insert(String key, int value) {
        if(key == null){
            throw new IllegalArgumentException();
        }
        AVLNode node = new AVLNode(key, value);
        this.root = createTree(node, this.root);
    }

    public AVLNode createTree(AVLNode node, AVLNode root) {
        if (root == null) {
            root = new AVLNode(node.key, 1 );
        }
        if (root.key.compareTo(node.key) < 0) {
            root.right = createTree(node, root.right);
        } else if (root.key.compareTo(node.key) > 0) {
            root.left = createTree(node, root.left);
        } else {
            root.value = node.value;
        }
        return rebalance(root);
    }

    public AVLNode rotateRight(AVLNode node) {
        AVLNode right = node.right.right;
        node.right.right = right.left;
        right.left = node;
        node.height = Math.max(height(node.left.left), height(node.left.right)) + 1;
        right.height = Math.max(height(right.right), height(node)) + 1;
        return right;
    }

    public AVLNode rotateLeft(AVLNode node) {
        AVLNode left = node.left;
        node.left = left.right;
        node.right = left;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        left.height = Math.max(height(left.left), height(node)) + 1;
        return left;
    }

    private AVLNode rebalance(AVLNode node) {
        if(node == null){
            return null;
        }
        if ((height(node.right) - height(node.left) < -1)) {
            if ((height(node.left.right) - height(node.left.left)) <= 0) {
                node = rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        } else if ((height(node.right) - height(node.left)) > 1) {
            if ((height(node.right.right) - height(node.right.left)) >= 0) {
                node = rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }
    public static void main(String[] args) {
        int n = 900000000;
        copyOfAVL tree = new copyOfAVL();
        long timeStart = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            tree.insert("key", i * 2);
        }
        long timeEnd = System.currentTimeMillis();
        long totalTime = (timeEnd - timeStart);
        System.out.println(totalTime + "ms");
    }
}

