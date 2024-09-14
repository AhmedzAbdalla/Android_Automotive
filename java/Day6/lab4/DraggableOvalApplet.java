import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DraggableOvalApplet extends Applet {

    private int x = 50; 
    private int y = 50; 
    private int ovalWidth = 40;  
    private int ovalHeight = 40; 

    @Override
    public void init() {
        
        addMouseMotionListener(new MyMouseMotionAdapter());
    }

    public void paint(Graphics g) {
        g.fillOval(x, y, ovalWidth, ovalHeight);
    }

    class MyMouseMotionAdapter extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            x = e.getX() - ovalWidth / 2;  
            y = e.getY() - ovalHeight / 2;
            repaint(); 
        }
    }

}
