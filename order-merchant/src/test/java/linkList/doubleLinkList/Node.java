package linkList.doubleLinkList;

/**
 * Created by zhangxiong on 16/4/26.
 */
public class Node<T> {
    private T data;
    public Node<T> prev;
    public Node<T> next;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        if (data != null ? !data.equals(node.data) : node.data != null) return false;
        if (prev != null ? !prev.equals(node.prev) : node.prev != null) return false;
        return !(next != null ? !next.equals(node.next) : node.next != null);

    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (prev != null ? prev.hashCode() : 0);
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
    }

}
