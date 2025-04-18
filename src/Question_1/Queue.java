package Question_1;

/**
 *
 * @shushmita shashi paul
 * aut id: 23189444
 */

public class Queue<E extends Comparable<E>> {

    private final LinkedList<E> queue = new LinkedList<>();  // Manages the queue using LinkedList

    // Enqueue method: Adds an element to the queue
    public void enqueue(E data) {
        queue.add(data);  // Adds the element to the end of the queue
    }

    // Dequeue method: Removes and returns the element at the front of the queue
    public Node<E> dequeue() {
        if (queue.getSize() == 0) {
            return null;  // Returns null if the queue is empty
        }
        return queue.removeFromHead();  // Removes and returns the data from the head of the list
    }

    // GetSize method: Returns the number of elements in the queue
    public int getSize() {
        return queue.getSize();  // Returns the size of the linked list
    }

    // PrintQueue method: Prints all elements in the queue
    public void printQueue() {
        queue.printLinkedList();  // Uses LinkedList's print method to print the queue
    }
}
