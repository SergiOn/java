package datastructures;

public class LinkedList {

    public class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            // O(1)
        }

        // O(1)
    }

    public Node head;

    public void addFront(int data) {

        // Create new node
        Node newNode = new Node(data);
        // O(1)

        // if head...
        if (head == null) {
            head = newNode;
            return;
            // O(1)
        }

        // Set it's next to current head
        newNode.next = head;
        // O(1)

        // Set current head equal to this new head
        head = newNode;
        // O(1)

        // O(1)
    }

    public int getFirst() {
        return head.data;
        // O(1)
    }

    public int getLast() {
        if (head == null) {
            throw new IllegalStateException("Empty list!");
            // O(1)
        }

        Node current = head;
        // O(1)

        // while we are not at the tail
        while (current.next != null) {
            current = current.next;
            // O(n)
        }

        // We are at the tail
        return current.data;
        // O(1)

        // O(n)
    }

    public void addBack(int data) {
        Node newNode = new Node(data);
        // O(1)

        // if head... set and return
        if (head == null) {
            head = newNode;
            return;
            // O(1)
        }

        // Else starting at head...
        Node current = head;
        // O(1)

        // Walk until to hit tail
        while (current.next != null) {
            current = current.next;
            // O(n)
        }

        // Set current node to equal newNode
        current.next = newNode;
        // O(1)

        // O(n)
    }

    public int size() {

        if (head == null) {
            return 0;
            // O(1)
        }

        int count = 1;
        Node current = head;
        // O(1)

        while (current.next != null) {
            current = current.next;
            count++;
            // O(n)
        }

        return count;

        // O(n)
    }

    public void clear() {
        head = null;
        // O(1)
    }

    public void deleteValue(int data) {

        // if head
        if (head == null) {
            return;
            // O(1)
        }
        if (head.data == data) {
            head = head.next;
            return;
            // O(1)
        }

        // else walk the list
        Node current = head;
        // O(1)

        while (current.next != null) {
            // O(n)
            if (current.next.data == data) {
                current.next = current.next.next;
                return;
                // O(1)
            }
            current = current.next;
            // O(1)

            // O(n)
        }

        // O(n)
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("");
    }

}
