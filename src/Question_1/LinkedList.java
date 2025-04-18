package Question_1;

/**
 *
 * @shushmita shashi paul
 * aut id: 23189444
 */

public class LinkedList<E extends Comparable<E>> {

    public int size = 0;
    public Node<E> head = null;

    // Adds a new node to the head of the linked list
    public void addHead(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Returns the head node of the linked list
    public Node<E> getHead() {
        return head;
    }

    // Adds a new node to the end of the linked list
    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            add(head, newNode);
        }
        size++;
    }

    // Helper method to add a new node to the end of the linked list recursively
    private void add(Node<E> current, Node<E> newNode) {
        if (current.next == null) {
            current.next = newNode;
        } else {
            add(current.next, newNode);
        }
    }

    // Prints the contents of the linked list
    public void printLinkedList() {
        printLinkedList(head);
    }

    // Helper method to print the linked list recursively
    private void printLinkedList(Node<E> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            printLinkedList(node.next);
        }
    }

    // Checks if the linked list contains a node with the same data as the provided node
    public boolean contains(Node<E> node) {
        return contains(head, node);
    }

    // Helper method to check for the node recursively
    private boolean contains(Node<E> current, Node<E> node) {
        if (current == null) {
            return false;
        }
        if (current.equals(node)) {
            return true;
        }
        return contains(current.next, node);
    }

    // Removes a node with the same data as the provided node
    public void remove(Node<E> node) {
        if (head == null) return;

        if (head.equals(node)) {
            head = head.next;
            size--;
        } else {
            removeFromBody(head, node);
        }
    }

    // Helper method to remove a node from the body of the linked list recursively
    private void removeFromBody(Node<E> current, Node<E> node) {
        if (current.next == null) return;

        if (current.next.equals(node)) {
            current.next = current.next.next;
            size--;
        } else {
            removeFromBody(current.next, node);
        }
    }

    // Removes a node at a specific index
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (index == 0) {
            head = head.next;
        } else {
            removeByIndex(head, index - 1);
        }
        size--;
    }

    // Helper method to remove a node by index recursively
    private void removeByIndex(Node<E> current, int index) {
        if (index == 0) {
            current.next = current.next.next;
        } else {
            removeByIndex(current.next, index - 1);
        }
    }

    // Removes and returns the head node of the linked list
    public Node<E> removeFromHead() {
        if (head == null) return null;

        Node<E> removedNode = head;
        head = head.next;
        size--;
        return removedNode;
    }

    // Removes and returns the tail node of the linked list
    public Node<E> removeFromTail() {
        if (head == null) return null;

        if (head.next == null) {
            Node<E> removedNode = head;
            head = null;
            size--;
            return removedNode;
        } else {
            return removeFromTail(head);
        }
    }

    // Helper method to remove the tail node recursively
    private Node<E> removeFromTail(Node<E> current) {
        if (current.next.next == null) {
            Node<E> removedNode = current.next;
            current.next = null;
            size--;
            return removedNode;
        } else {
            return removeFromTail(current.next);
        }
    }

    // Adds a node to the linked list in order (numerical or alphabetical)
    public void addInOrder(E data) {
        Node<E> newNode = new Node<>(data);

        if (head == null || head.data.compareTo(data) > 0) {
            newNode.next = head;
            head = newNode;
        } else {
            addInOrder(head, newNode);
        }
        size++;
    }

    // Helper method to add a node in order recursively
    private void addInOrder(Node<E> current, Node<E> newNode) {
        if (current.next == null || current.next.data.compareTo(newNode.data) > 0) {
            newNode.next = current.next;
            current.next = newNode;
        } else {
            addInOrder(current.next, newNode);
        }
    }

    // Retrieves a node at a specific index
    public Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return getNode(index, head);
    }

    // Helper method to retrieve a node by index recursively
    private Node<E> getNode(int index, Node<E> current) {
        if (index == 0) {
            return current;
        }
        return getNode(index - 1, current.next);
    }

    // Retrieves the data of the node at a specific index
    public E getData(int index) {
        return getNode(index).data;
    }

    int getSize() {
        return size;
    }

    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
