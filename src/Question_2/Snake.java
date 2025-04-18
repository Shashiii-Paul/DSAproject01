package Question_2;

/**
 *
 * @shushmita shashi paul
 * aut id: 23189444
 */

import java.util.LinkedList;
import java.util.Collections;
import java.awt.Point;

public class Snake {
    private LinkedList<Character> body;
    private LinkedList<Point> positions; // Holds positions of each segment
    private int vx, vy; // Velocity

    public Snake(String initSnake, int startX, int startY) {
        body = new LinkedList<>();
        positions = new LinkedList<>();
        
        // Initialize snake with the initial character and position
        for (char ch : initSnake.toCharArray()) {
            body.add(ch);
            positions.add(new Point(startX, startY));
        }
        this.vx = 0;
        this.vy = 0;
    }

    public void move() {
        // Calculate the new head position
        Point newHead = new Point(positions.getFirst().x + vx, positions.getFirst().y + vy);
        
        // Add new head position and remove the tail position
        positions.addFirst(newHead);
        positions.removeLast();
    }

    public void changeDirection(int vx, int vy) {
        // Prevent the snake from reversing directly onto itself
        if (this.vx != -vx || this.vy != -vy) {
            this.vx = vx;
            this.vy = vy;
        }
    }

    public void eatLetter(char letter) {
        body.add(letter);
        Collections.sort(body); // Sort alphabetically
        positions.add(new Point(positions.getLast())); // Extend the body by copying the last position
    }

    public void hitNumber(int index) {
        if (!body.isEmpty()) {
            if (index < body.size()) {
                body.remove(index);
                positions.remove(index); // Remove the position corresponding to the removed letter
            } else {
                body.removeLast();
                positions.removeLast();
            }
        }
    }

    public LinkedList<Character> getBody() {
        return body;
    }

    public LinkedList<Point> getPositions() {
        return positions;
    }

    public int getHeadX() {
        return positions.getFirst().x;
    }

    public int getHeadY() {
        return positions.getFirst().y;
    }
}
