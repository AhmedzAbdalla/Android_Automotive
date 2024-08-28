import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class linedrawer extends Applet implements MouseListener, MouseMotionListener {

    int x1, y1;
    int x2, y2;
    boolean drag = false;
    final int MAX_LINES = 3; // Maximum number of lines that can be drawn
    Line[] lines = new Line[MAX_LINES]; // Array to store lines
    int lineCount = 0; // Counter to track the number of lines drawn

    // Inner class to represent a line
    class Line {
        int x1, y1, x2, y2;

        Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public void init() {
        addMouseListener(this); // Register mouse listener
        addMouseMotionListener(this); // Register mouse motion listener
    }

    public void paint(Graphics g) {
        super.paint(g);
        // Redraw all stored lines
        for (int i = 0; i < lineCount; i++) {
            g.drawLine(lines[i].x1, lines[i].y1, lines[i].x2, lines[i].y2);
        }
        // Draw the current line being dragged
        if (drag) {
            g.drawLine(x1, y1, x2, y2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Not used
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (lineCount < MAX_LINES) { // Allow only three lines
            x1 = e.getX();
            y1 = e.getY();
            drag = true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (lineCount < MAX_LINES) { // Allow dragging only if line count is less than three
            x2 = e.getX();
            y2 = e.getY();
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (drag && lineCount < MAX_LINES) {
            lines[lineCount] = new Line(x1, y1, x2, y2); // Store the line in the array
            lineCount++; // Increment line count
            drag = false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Not used
    }
}
