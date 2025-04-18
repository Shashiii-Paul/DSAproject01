package Question_1;

/**
 *
 * @shushmita shashi paul
 * aut id: 23189444
 */

public class Stack<E extends Comparable<E>> {

    private final LinkedList<E> stack = new LinkedList<>();  // Manages the stack using LinkedList

    // Push method: Adds an element to the stack
    public void push(E data) {
        stack.addHead(data);  // Adds the element to the top of the stack
    }

    // Pop method: Removes & returns the element from the top of the stack
    public E pop() {
        if (stack.size == 0) {
            return null;  // Returns null if the stack is empty
        }
        return stack.removeFromHead().data;  // Removes & returns the top element
    }

    // GetSize method: Returns the number of elements in the stack
    public int getSize() {
        return stack.size;  // Returns the size of the linked list
    }

    // PrintStack method: Prints all elements in the stack
    public void printStack() {
        stack.printLinkedList();  // Utilizes LinkedList's print method to print the stack
    }
}
