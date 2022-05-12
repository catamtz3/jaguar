package experiments;

import cse332.datastructures.trees.BinarySearchTree;

public class BST {
    private String key;
    private int value;
    private BSTNode left;
    private BSTNode right;

    public class BSTNode {
        String key;
        int value;
        BSTNode left, right;
    }

    public void BSTNode (String name, int item){
        this.key = name;
        this.value = item;
        left = right = null;
    }

    BSTNode root;

    public BST (){
        root = null;
    }
    public BST(String key, int value) {
        root = new BSTNode();
    }

    public void insert (String key, int value) {
        root = insertRecursive(root, value);
    }

    public BSTNode insertRecursive(BSTNode root, int value){
        if (root == null) {
            root = new BSTNode();
            return root;
        }
        if (value < root.value) {
            root.left = insertRecursive(root.left, root.value);
        } else if (value > root.value) {
            root.right = insertRecursive(root.right, root.value);
        }
        return root;
    }

    public void inOrder(){
        inOrderRecursive(root);
    }

    public void inOrderRecursive(BSTNode root){
        if (root != null){
            inOrderRecursive(root.left);
            inOrderRecursive(root.right);
        }
    }

    private static int n = 900000000;

    //end of timing for insertion
    long timeEnd = System.currentTimeMillis();
    public static void main(String[] args){
        BST tree = new BST();
        long timeStart = System.currentTimeMillis();
        for(int i = 0; i < n; i++){
            tree.insert("name", i * 2);
        }
        tree.inOrder();
        long timeEnd = System.currentTimeMillis();
        long totalTime = (timeEnd - timeStart);
        System.out.println("Time " + totalTime + "ms");

    }
}
