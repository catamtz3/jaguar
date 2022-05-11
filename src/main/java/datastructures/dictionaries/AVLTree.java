package datastructures.dictionaries;

import cse332.datastructures.trees.BinarySearchTree;

/**
 * AVLTree must be a subclass of BinarySearchTree<E> and must use
 * inheritance and calls to superclass methods to avoid unnecessary
 * duplication or copying of functionality.
 * <p>
 * 1. Create a subclass of BSTNode, perhaps named AVLNode.
 * 2. Override the insert method such that it creates AVLNode instances
 * instead of BSTNode instances.
 * 3. Do NOT "replace" the children array in BSTNode with a new
 * children array or left and right fields in AVLNode.  This will
 * instead mask the super-class fields (i.e., the resulting node
 * would actually have multiple copies of the node fields, with
 * code accessing one pair or the other depending on the type of
 * the references used to access the instance).  Such masking will
 * lead to highly perplexing and erroneous behavior. Instead,
 * continue using the existing BSTNode children array.
 * 4. Ensure that the class does not have redundant methods
 * 5. Cast a BSTNode to an AVLNode whenever necessary in your AVLTree.
 * This will result a lot of casts, so we recommend you make private methods
 * that encapsulate those casts.
 * 6. Do NOT override the toString method. It is used for grading.
 * 7. The internal structure of your AVLTree (from this.root to the leaves) must be correct
 */

public class AVLTree<K extends Comparable<? super K>, V> extends BinarySearchTree<K, V> {
    private class AVLNode extends BSTNode{
        int height;
        AVLNode left;
        AVLNode right;
        public AVLNode(K key, V value) {
            super(key, value);
            this.height = 0;
        }
    }
    public AVLTree(){
        super();
        this.size = size();
    }

    public V insert(K key, V val){
        if(key == null || val == null){
            throw new IllegalArgumentException();
        }
        V returnVal = this.find(key);
        AVLNode node = new AVLNode(key,val);
        if(returnVal == null){
            this.size++;
            returnVal = val;
        }
        this.root = createTree(node, (AVLNode)this.root);
        return returnVal;
    }

    public AVLNode createTree(AVLNode node, AVLNode root){
        if (root == null){
            return node;
        }
        if(root.key.compareTo(node.key) < 0){
            root.right = createTree(node, root.right);
        } else if(root.key.compareTo(node.key) > 0){
            root.left = createTree(node, root.left);
        } else{
            root.value = node.value;
        }
        return rebalance(root);
    }

    public int height(AVLNode h){
        return h != null ? h.height: -1;
    }

    public AVLNode rotateRight(AVLNode node){
        AVLNode right = node.right.right;
        node.right.right = right.left;
        right.left = node;
        node.height = Math.max(height(node.left.left), height(node.left.right)) + 1;
        right.height = Math.max(height(right.right), height(node)) + 1;
        return right;
    }

    public AVLNode rotateLeft(AVLNode node){
        AVLNode left = node.left;
        node.left = left.right;
        node.right = left;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        left.height = Math.max(height(left.left), height(node)) + 1;
        return left;
    }

    private AVLNode rebalance(AVLNode node){
        if(node == null){
            return node;
        }
        if((height(node.right) - height(node.left) < -1)){
            if ((height(node.left.right) - height(node.left.left)) <= 0){
                node = rotateRight(node);
            } else{
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        } else if ((height(node.right) - height(node.left)) > 1){
            if((height(node.right.right) - height(node.right.left)) >= 0){
                node = rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }
}
