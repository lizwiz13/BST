import java.util.List;

public interface BinarySearchTree <T extends Comparable<T> > {
    public void insert(T data);
    public void delete(T data);
    public T search(T data);
    public T minimum();
    public T maximum();
    public List<T> preOrder();
    public List<T> inOrder();
    public List<T> postOrder();
}
