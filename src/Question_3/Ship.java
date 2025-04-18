package Question_3;

/**
 *
 * @shushmita shashi paul
 * aut id: 23189444
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/* 
Which object have you chosen as a monitor object to synchronize your code?
I chose the Port object as the monitor object because it represents the shared resource
(the island) that the ships need to access. Synchronizing on the Port object ensures that
only one ship can occupy the port at any given time, preventing race conditions.

Why did you choose that object as a monitor object to synchronize your code?
The Port object is ideal because it directly represents the critical section where
synchronization is needed (i.e., ships accessing the island). By using the Port object
as the monitor, we can effectively control access to the shared resource.
*/


public class Ship extends Thread {
    int x;
    int y;
    String name;
    Port port;
    Panel panel;
    private boolean moving = false;

    public Ship(int x, int y, Port port, Panel panel) {
        this.x = x;
        this.y = y;
        this.port = port;
        this.panel = panel;
        this.name = "Ship " + this.getId();
    }

    @Override
    public void run() {
        if (!panel.program_starts) {
            return; // Exit if the program hasn't started
        }

        if (!moving) {
            moving = true;
            moveToIsland();
            moving = false;
            panel.setShipInProgress(false); // Indicate that no ship is currently moving
        }
    }

    private void moveToIsland() {
        try {
            // Move the ship incrementally to the port
            while (x < port.x - 30) { // Adjust to stop slightly before the port
                x += 5; // Move ship incrementally for smooth movement
                panel.repaint(); // Call repaint directly
                Thread.sleep(50); // Control speed of movement
            }

            boolean shipCrashed = false;
            synchronized (port) {
                if (!port.isOccupied()) {
                    port.occupy(); // Occupy the port
                    panel.repaint();

                    // Change the port image for 1.5 seconds
                    Timer timer = new Timer(1500, (ActionEvent e) -> {
                        port.release(); // Release the port after 1.5 seconds
                        panel.repaint();
                    });
                    timer.setRepeats(false);
                    timer.start();

                    // Print ship status
                    System.out.println(name + " is moving to the island.");
                    Thread.sleep(1000);  // Ship stays at the port for 1 second
                    System.out.println(name + " has left the island.");
                } else if (!panel.isSynchronizedMode()) {
                    // In unsynchronized mode, detect crashes
                    shipCrashed = true;
                }
            }

            if (shipCrashed) {
                panel.showCrashMessage("Crash detected! Multiple ships at the island!");
                System.out.println("Crash detected! Multiple ships at the island!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isMoving() {
        return moving;
    }
}
