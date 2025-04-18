package Question_1;

/**
 *
 * @shushmita shashi paul
 * aut id: 23189444
 */

public class Node<E> {
    
    public E data;          // The data of the node, which can be of any type
    public Node<E> next;    // Reference to the next node in the linked list
    
    // Constructor to initialize node with data
    public Node(E data) {
        this.data = data;
        this.next = null;
    }
    
    // Default constructor
    public Node() {
        this(null);
    }
    
    // Method to check if this node is equal to another node
    public boolean equals(Node<E> node) {
        if (node == null) {
            return false;   // If the passed node is null, return false
        }
        return this.data.equals(node.data); // Use the equals method of the data object
    }
    
    // Method to compare this node to another node (assuming data implements Comparable)
    @SuppressWarnings("unchecked")
    public int compareTo(Node<E> node) {
        if (node == null || this.data == null) {
            throw new IllegalArgumentException("Node or data is null");
        }
        return ((Comparable<E>) this.data).compareTo(node.data);
    }
    
    @Override
    public String toString() {
        return data.toString();
    }
}