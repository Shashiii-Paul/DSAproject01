package Question_3;

/**
 *
 * @shushmita shashi paul
 * aut id: 23189444
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Panel extends JPanel implements KeyListener {

    int number_ship = 20;
    boolean program_starts = false;
    boolean synchronizedMode = true;  // Default to synchronized mode
    Ship[] ships = new Ship[number_ship];
    Port port;
    Image ship_image;
    Image island_image;
    Image boat_island_image;
    String crashMessage = "";
    private Timer crashMessageTimer;
    private ConcurrentLinkedQueue<Ship> shipQueue = new ConcurrentLinkedQueue<>();
    private boolean shipInProgress = false;

    public Panel() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setBackground(Color.WHITE); // Set background to white
        port = new Port(900, 500);

        // Initialize ships with appropriate spacing
        for (int i = 0; i < number_ship; i++) {
            ships[i] = new Ship(20, i * 50 + 50, port, this); // Increased spacing to avoid overlap
            shipQueue.add(ships[i]); // Add all ships to the queue
        }

        // Load images
        ship_image = new ImageIcon("boat.png").getImage();
        island_image = new ImageIcon("land.png").getImage();
        boat_island_image = new ImageIcon("boat_land.png").getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Monospaced", Font.BOLD, 20));

        // Draw each ship
        for (Ship ship : ships) {
            g.drawImage(ship_image, ship.x, ship.y, this);
        }

        // Draw the port (island) image based on occupation status
        if (port.isOccupied()) {
            g.drawImage(boat_island_image, port.x, port.y, this); // Island with boat image when occupied
        } else {
            g.drawImage(island_image, port.x, port.y, this); // Island image when unoccupied
        }

        // Display crash message if any
        if (!crashMessage.isEmpty()) {
            g.setColor(Color.RED); // Red color for crash message
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString(crashMessage, 100, 100);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (ke.getKeyChar() == ' ') {
            if (!program_starts) {
                program_starts = true;
            }
            // Start the first ship in the queue if no ship is currently moving
            if (!shipInProgress) {
                Ship nextShip = shipQueue.poll();
                if (nextShip != null) {
                    shipInProgress = true;
                    nextShip.start(); // Start the next ship
                }
            }
        } else if (ke.getKeyChar() == 's') {
            synchronizedMode = !synchronizedMode; // Toggle between synchronized and unsynchronized modes
            System.out.println("Synchronized Mode: " + synchronizedMode);
            crashMessage = ""; // Clear crash message when switching modes
            if (crashMessageTimer != null) {
                crashMessageTimer.stop(); // Stop any existing timer
            }
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {}

    public boolean isSynchronizedMode() {
        return synchronizedMode;
    }

    public void showCrashMessage(String message) {
        crashMessage = message;
        if (crashMessageTimer != null) {
            crashMessageTimer.stop(); // Stop any existing timer
        }
        crashMessageTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crashMessage = "";  // Clear message after 3 seconds
                repaint();
            }
        });
        crashMessageTimer.setRepeats(false);
        crashMessageTimer.start();
        repaint(); // Direct repaint call; ensure it’s safe
    }

    public void setShipInProgress(boolean status) {
        this.shipInProgress = status;
        if (!status) {
            // Start the next ship if no ship is currently moving
            Ship nextShip = shipQueue.poll();
            if (nextShip != null) {
                shipInProgress = true;
                nextShip.start();
            }
        }
    }
}
