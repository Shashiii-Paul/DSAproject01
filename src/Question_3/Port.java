package Question_3;

/**
 *
 * @shushmita shashi paul
 * aut id: 23189444
 */

public class Port {
    int x;
    int y;
    volatile boolean isOccupied = false;  // Track if the port is occupied

    public Port(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized void occupy() {
        isOccupied = true; // Mark the port as occupied
    }

    public synchronized void release() {
        isOccupied = false; // Mark the port as unoccupied
    }

    public boolean isOccupied() {
        return isOccupied; // Return the current occupation status
    }
}
