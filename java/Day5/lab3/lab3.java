import javax.swing.JApplet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.lang.Exception;


public class lab3 extends JApplet implements Runnable {

        
    int Width = 40 , height = 40, size = 18;
    boolean moveRight = true;
    boolean moveDown = true;
    public void init()
    {
        //Font font = new Font("Forte",Font.PLAIN,size);
       Thread myThread = new Thread(this);
        myThread.start();
        
    }

    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.drawString("Hello Java", Width, height);   
        g.fillOval(Width, height, 20, 20);
    }

    public void run()
    {
        while(true)
        {
            try{
            repaint();

            //ubdate the position according to this criteria
            if (moveRight) {
                Width += 10; 
                if (Width >= getWidth() - 20) {
                    moveRight = false; 
                }
            } else {
                Width -= 10; 
                if (Width <= 0) {
                    moveRight = true; 
                }
            }

            if (moveDown) {
                height += 10; 
                if (height >= getHeight() - 20) {
                    moveDown = false; 
                }
            } else {
                height -= 10;
                if (height <= 0) {
                    moveDown = true; 
                }
            }
                Thread.sleep(100);
            }
            catch (InterruptedException e) 
            { 
                e.printStackTrace();
            }
            

        }
    }
}
