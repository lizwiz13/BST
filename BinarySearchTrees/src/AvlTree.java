public class AvlTree <T extends Comparable<T>> extends BaseTree<T> {
    private int height(Node<T> node) {
        if (node == null) return -1;
        return node.height;
    }

    private void updateHeight(Node<T> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private int balanceFactor(Node<T> node) {
        return height(node.right) - height(node.left);
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> right = node.right;

        node.right = right.left;
        right.left = node;

        updateHeight(node);
        updateHeight(right);

        return right;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> left = node.left;

        node.left = left.right;
        left.right = node;

        updateHeight(node);
        updateHeight(left);

        return left;
    }

    private Node<T> rebalance(Node<T> node) {
        int balanceFactor = balanceFactor(node);

        if (balanceFactor < -1) {
            // Left-heavy
            if (balanceFactor(node.left) > 0) {
                // Left-right rotation
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (balanceFactor > 1) {
            // Right-heavy
            if (balanceFactor(node.right) < 0) {
                // Right-left rotation
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    @Override
    Node<T> insertNode(T data, Node<T> ptr) {
        ptr = super.insertNode(data, ptr);

        updateHeight(ptr);

        return rebalance(ptr);
    }

    @Override
    Node<T> deleteNode(T data, Node<T> ptr) {
        ptr = super.deleteNode(data, ptr);

        if (ptr == null) return null;

        updateHeight(ptr);

        return rebalance(ptr);
    }

}