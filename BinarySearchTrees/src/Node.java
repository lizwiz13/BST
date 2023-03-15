class Node <T> {
    T value;
    Node<T> left, right;

    Node<T> parent;
    int height;

    boolean color;

    public Node(T data) {
        value = data;
    }

}
