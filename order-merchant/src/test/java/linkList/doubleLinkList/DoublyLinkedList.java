package linkList.doubleLinkList;

/**
 * Created by zhangxiong on 16/4/26.
 */
public class DoublyLinkedList<T> {
    private Node<T> header;
    private int size;


    public DoublyLinkedList() {
        header = new Node(null, null, null);
        header.prev = header.next = header;
        size = 0;
    }

    public boolean add(T value) {
        return false;
    }


    boolean add(Node<T> node, T value) {
        Node newNode = new Node(value, node, node.next);
//        newNode.prev.next =
        return true;
    }


    public int size() {
        return this.size;
    }


    private class Node<T> {
        private T value;
        public Node<T> prev;
        public Node<T> next;

        public Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }


}
