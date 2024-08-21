import javax.swing.JApplet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.lang.Exception;


public class lab2 extends JApplet implements Runnable {

        
    int Width = 0 , height = 20, size = 18;
    public void init()
    {
        //Font font = new Font("Forte",Font.PLAIN,size);
       Thread myThread = new Thread(this);
        myThread.start();
        
    }

    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Hello Java", Width, height);   
    }

    public void run()
    {
        while(true)
        {
            try{
            repaint();

            //ubdate the position according to this criteria
            if(Width == getWidth())
            {
            height +=40;
            Width = 0;
            }
            else
            {
                Width +=50;
            }
                Thread.sleep(500);
            }
            catch (InterruptedException e) 
            { 
                e.printStackTrace();
            }
            

        }
    }
}
