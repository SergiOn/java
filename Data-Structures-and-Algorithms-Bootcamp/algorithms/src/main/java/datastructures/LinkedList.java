package datastructures;

public class LinkedList<T> {

    private class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node head;

    public void addFront(T data) {

        // Create new node
        Node newNode = new Node(data);

        // if head...
        if (head == null) {
            head = newNode;
            return;
        }

        // Set it's next to current head
        newNode.next = head;

        // Set current head equal to this new head
        head = newNode;
    }


}
