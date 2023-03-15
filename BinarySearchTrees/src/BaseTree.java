import java.util.List;
import java.util.ArrayList;

public class BaseTree<T extends Comparable<T> > implements BinarySearchTree<T> {
    Node<T> root;

    Node<T> insertNode(T data, Node<T> node) {
        if (node == null) return new Node<>(data);

        if (data.compareTo(node.value) < 0) {
            // Inserted value is less than node's value
            node.left = insertNode(data, node.left);
            node.left.parent = node;
        } else {
            // Inserted value is greater or equal to node's value
            node.right = insertNode(data, node.right);
            node.right.parent = node;
        }
        return node;
    }

    @Override
    public void insert(T data) {
        root = insertNode(data, root);
    }

    Node<T> deleteRoot(Node<T> rootNode) {
        if (rootNode.right == null) {
            return rootNode.left;
        }
        boolean isLeftChild = false;
        Node<T> parent = rootNode;
        Node<T> successor = rootNode.right;
        while (successor.left != null) {
            parent = successor;
            successor = successor.left;
            isLeftChild = true;
        }
        rootNode.value = successor.value;
        if (isLeftChild) {
            parent.left = successor.right;
        } else {
            parent.right = successor.right;
        }
        return rootNode;
    }
    Node<T> deleteNode(T data, Node<T> ptr) {
        if (ptr == null) {
            return null;
        }
        int comparison = data.compareTo(ptr.value);
        if (comparison == 0) {
            return deleteRoot(ptr);
        } else if (comparison < 0) {
            ptr.left = deleteNode(data, ptr.left);
        } else {
            ptr.right = deleteNode(data, ptr.right);
        }
        return ptr;
    }

    @Override
    public void delete(T data) {
        root = deleteNode(data, root);
    }

    @Override
    public T search(T data) {
        if (root == null) return null;
        Node<T> ptr = root;
        int comparison = data.compareTo(ptr.value);
        while (comparison != 0) {
            if (comparison < 0) {
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
            if (ptr == null) return null;
            comparison = data.compareTo(ptr.value);
        }
        return ptr.value;
    }

    @Override
    public T minimum() {
        if (root == null) return null;
        Node<T> res = root;
        while (res.left != null) res = res.left;
        return res.value;
    }

    @Override
    public T maximum() {
        if (root == null) return null;
        Node<T> res = root;
        while (res.right != null) res = res.right;
        return res.value;
    }

    List<T> nodePreOrder(Node<T> node) {
        if (node == null) {
            return new ArrayList<>();
        }

        List<T> middle = new ArrayList<>(1);
        middle.add(node.value);
        List<T> left = nodePreOrder(node.left);
        List<T> right = nodePreOrder(node.right);

        middle.addAll(left);
        middle.addAll(right);
        return middle;
    }

    List<T> nodeInOrder(Node<T> node) {
        if (node == null) {
            return new ArrayList<>();
        }

        List<T> left = nodeInOrder(node.left);
        List<T> middle = new ArrayList<>(1);
        middle.add(node.value);
        List<T> right = nodeInOrder(node.right);

        left.addAll(middle);
        left.addAll(right);
        return left;
    }

    List<T> nodePostOrder(Node<T> node) {
        if (node == null) {
            return new ArrayList<>();
        }

        List<T> left = nodePostOrder(node.left);
        List<T> right = nodePostOrder(node.right);
        List<T> middle = new ArrayList<>(1);
        middle.add(node.value);

        left.addAll(right);
        left.addAll(middle);
        return left;
    }

    @Override
    public List<T> preOrder() {
        return nodePreOrder(root);
    }

    @Override
    public List<T> inOrder() {
        return nodeInOrder(root);
    }

    @Override
    public List<T> postOrder() {
        return nodePostOrder(root);
    }
}
