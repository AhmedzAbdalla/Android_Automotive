import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class LineDrawer extends Applet {

    Vector<Line> lines = new Vector<>(); 
    int x1, y1; 

    public void init() {
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);
    }

    public void paint(Graphics g) {
        for (Line line : lines) { 
            g.drawLine(line.x1, line.y1, line.x2, line.y2);
        }
    }

    class Line {
        int x1, y1, x2, y2;

        Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    class MyMouseAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
        }

        public void mouseReleased(MouseEvent e) {
            //if (lines.size() < 3) { 
                int x2 = e.getX();
                int y2 = e.getY();
                lines.add(new Line(x1, y1, x2, y2)); 
                repaint();
            //}
        }
    }
}
