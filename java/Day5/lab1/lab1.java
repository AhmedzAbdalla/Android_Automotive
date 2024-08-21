import javax.swing.JApplet;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Date;
import java.lang.Exception;


public class lab1 extends JApplet implements Runnable {

        

    public void init()
    {
       Thread myThread = new Thread(this);
        myThread.start();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Date mydate = new Date();
        
        

        g.drawString(mydate.toString(), 300, 150);
    }

    public void run()
    {
        while(true)
        {
            try{
                repaint();
                Thread.sleep(1000);
            }
            catch (InterruptedException e) 
            { 
                e.printStackTrace();
            }
            

        }
    }
}
