import javax.swing.JApplet;

import java.awt.Color;
import java.awt.Graphics;

public class lab2 extends JApplet {

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw an oval
        g.setColor(Color.YELLOW);
        g.fillOval(50, 50, 150, 40); // (x, y, width, height) 
        g.fillOval(50, 140, 20, 40); // (x, y, width, height)
        g.fillOval(105, 130, 40, 80); // (x, y, width, height)
        g.fillOval(175, 140, 20, 40); 

        g.setColor(Color.BLACK);
        g.drawOval(50, 50, 150, 40); // (x, y, width, height) 
        g.drawOval(50, 140, 20, 40); // (x, y, width, height)
        g.drawOval(105, 130, 40, 80); // (x, y, width, height)
        g.drawOval(175, 140, 20, 40);
        // Draw a line
        g.setColor(Color.BLACK);
        g.drawLine(50, 70, 40, 200); // (x1, y1, x2, y2)
        g.drawLine(200, 70, 210, 200); // (x1, y1, x2, y2)

        g.drawArc(40, 180, 170, 40, 180, 180); // (x, y, width, height, startAngle, arcAngle)

        // Draw a rectangle
        g.drawRect(70, 250, 110, 20); // (x, y, width, height)

        // Draw a line
        g.drawLine(105, 220, 100, 250); // (x1, y1, x2, y2)
        g.drawLine(140, 220, 145, 250); // (x1, y1, x2, y2)

        // Draw a line
        //g.drawLine(50, 200, 400, 200); // (x1, y1, x2, y2)

        // Draw an arc
        //g.drawArc(150, 250, 150, 100, 0, 180); // (x, y, width, height, startAngle, arcAngle)
    }
}
