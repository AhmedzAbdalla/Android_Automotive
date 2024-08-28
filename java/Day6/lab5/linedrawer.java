import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener; //it is interface
import java.awt.event.MouseMotionListener;

// use inner classes

public class linedrawer extends Applet implements MouseListener, MouseMotionListener{
    

    int x1 , y1;
    int x2 , y2;
    boolean drag = false;
    
    public void init() {
        addMouseListener(this); // Register mouse listener
        addMouseMotionListener(this); // Register mouse listener

    }

    //@Override
    public void paint(Graphics g) {
        super.paint(g);

        //if(drag)
        g.drawLine(x1, y1, x2, y2);
        
        
    }

       
    @Override
    public void mouseClicked(MouseEvent e) {
        //x1 = e.getX();
        //y1 = e.getY();
        //drag = false;
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        drag = false;
    
    }

    public void mouseMoved(MouseEvent e)
    {

    }
    @Override
    public void mouseDragged(MouseEvent e) {

        x2 = e.getX();
        y2 = e.getY();
        repaint();
        

        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {

        //x1 = e.getX();
        //y1 = e.getY();
        //x2 = e.getX();
        //y2 = e.getY();
        //repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
 }

    


