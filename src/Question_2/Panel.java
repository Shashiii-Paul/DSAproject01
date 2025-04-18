package Question_2;

/**
 *
 * @shushmita shashi paul
 * aut id: 23189444
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener {

    private final Snake snake;
    private final ArrayList<Point> numbers;
    private final ArrayList<Integer> numberValues;
    private Point letter;
    private char currentLetter;
    private final Random random;
    private final int boardWidth = 1000; // Match the JFrame size
    private final int boardHeight = 1000;

    public Panel() {
        this.snake = new Snake("@", 100, 100); // Initial position
        this.numbers = new ArrayList<>();
        this.numberValues = new ArrayList<>();
        this.random = new Random();

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow(); // Ensure the panel has focus for key events
        generateRandomNumbers(10);
        generateRandomLetter();

        // Start the game loop
        new Thread(this::run).start();
    }

    private void generateRandomNumbers(int count) {
        numbers.clear();
        numberValues.clear();
        for (int i = 0; i < count; i++) {
            numbers.add(generateRandomLocation());
            numberValues.add(random.nextInt(10)); // Generate a random number between 0 and 9
        }
    }

    private void generateRandomLetter() {
        currentLetter = (char) (random.nextInt(26) + 'A');
        letter = generateRandomLocation();
    }

    private Point generateRandomLocation() {
        int x = random.nextInt(boardWidth / 10) * 10;
        int y = random.nextInt(boardHeight / 10) * 10;
        return new Point(x, y);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        // Draw the snake
        g.setColor(Color.GREEN);
        java.util.LinkedList<Point> positions = snake.getPositions();
        java.util.LinkedList<Character> body = snake.getBody();
        for (int i = 0; i < body.size(); i++) {
            Point pos = positions.get(i);
            g.drawString(String.valueOf(body.get(i)), pos.x, pos.y);
        }

        // Draw the numbers
        g.setColor(Color.RED);
        for (int i = 0; i < numbers.size(); i++) {
            Point number = numbers.get(i);
            g.drawString(String.valueOf(numberValues.get(i)), number.x, number.y);
        }

        // Draw the letter
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString(String.valueOf(currentLetter), letter.x, letter.y);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                snake.changeDirection(0, -10);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                snake.changeDirection(0, 10);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                snake.changeDirection(-10, 0);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                snake.changeDirection(10, 0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Optional: stop movement
    }

    private void updateGame() {
        snake.move();

        // Check collision with letter
        if (snake.getHeadX() == letter.x && snake.getHeadY() == letter.y) {
            snake.eatLetter(currentLetter); // Add letter to snake's body
            generateRandomLetter(); // Generate a new letter
        }

        // Check collision with numbers
        for (int i = 0; i < numbers.size(); i++) {
            Point number = numbers.get(i);
            if (snake.getHeadX() == number.x && snake.getHeadY() == number.y) {
                snake.hitNumber(numberValues.get(i)); // Drop a letter based on the number's value
                numbers.set(i, generateRandomLocation()); // Regenerate number
                numberValues.set(i, random.nextInt(10)); // Generate new number value
            }
        }
    }

    public void run() {
        while (true) {
            updateGame();
            repaint();
            try {
                Thread.sleep(100); // Controls the speed of the game
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
