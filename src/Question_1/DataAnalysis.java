package Question_1;

/**
 *
 * @shushmita shashi paul
 * aut id: 23189444
 */

public class DataAnalysis<E extends Comparable<E>> {

    private final Queue<E> queue = new Queue<>();  // Optional queue for palindrome checking
    private final Stack<E> stack = new Stack<>();  // Optional stack for palindrome checking
    private final E[] data;  // Stores the data to be analyzed

    // Constructor to initialize the DataAnalysis class with the input data
    public DataAnalysis(E[] data) {
        this.data = data;
        for (E element : data) {
            queue.enqueue(element);   // Add each element to the queue
            stack.push(element);      // Add each element to the stack
        }
    }

    // isPalindrome method: Checks if the data is a palindrome
    public boolean isPalindrome() {
        int left = 0;
        int right = data.length - 1;

        while (left < right) {
            if (!data[left].equals(data[right])) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
