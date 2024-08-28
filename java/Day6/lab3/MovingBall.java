import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingBall extends Applet implements Runnable {
    
    int width = 40, height = 40;  // Initialize width and height of the oval
    boolean moveRight = true;
    boolean moveDown = true;
    Button Btn_Start;
    Button Btn_Stop;

    Thread myThread = new Thread(this);  // Create a new thread
    public void init() {

        // Initialize the buttons
        Btn_Start = new Button("Start");
        Btn_Stop = new Button("Stop");

        // Add the buttons to the applet
        add(Btn_Start);
        add(Btn_Stop );

        // Add action listeners to the buttons using separate classes
        Btn_Start.addActionListener(new StartMoving());
        Btn_Stop.addActionListener(new Stop_Moving());

        
        myThread.start();  // Start the thread
        myThread.suspend();  // Start the thread
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(width, height, 20, 20);  // Draw the oval at the current position
    }

    public void run() {
        while (true) {
            try {
                repaint();  // Request a repaint to update the display

                // Update the position according to the movement direction
                if (moveRight) {
                    width += 10; 
                    if (width >= getWidth() - 20) {
                        moveRight = false; 
                    }
                } else {
                    width -= 10; 
                    if (width <= 0) {
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

                Thread.sleep(100);  // Pause for a short time before the next update
            } catch (InterruptedException e) { 
                e.printStackTrace();
            }
        }
    }
    // Inner class for increment button listener
    class StartMoving implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //repaint();  // Request a repaint to update the display
            myThread.resume(); 
        
        }
    }

    // Inner class for decrement button listener
    class Stop_Moving implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            myThread.suspend(); 
            //repaint();  // Request a repaint to update the display
        }
    }

}
