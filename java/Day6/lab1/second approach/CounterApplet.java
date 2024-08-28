import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterApplet extends Applet {
    int count = 0;  // Initialize count to 0
    Button incrementButton, decrementButton;

    public void init() {
        // Initialize the buttons
        incrementButton = new Button("Increment");
        decrementButton = new Button("Decrement");

        // Add the buttons to the applet
        add(incrementButton);
        add(decrementButton);

        // Add action listeners to the buttons using separate classes
        incrementButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                count++;  // Decrement the count
                repaint();  // Request a repaint to update the display
            }   
        }
    );



        decrementButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    count--;  // Decrement the count
                    repaint();  // Request a repaint to update the display
                }   
            }
        );
    }

    @Override
    public void paint(Graphics g) {
        // Draw the count as a string
        g.drawString("Count: " + count, 50, 100);
    }

    // Inner class for increment button listener
    //class IncrementListener implements ActionListener {
    //    @Override
    //    public void actionPerformed(ActionEvent e) {
    //        count++;  // Increment the count
    //        repaint();  // Request a repaint to update the display
    //    }
    //}

    // Inner class for decrement button listener
    //class DecrementListener implements ActionListener {
    //    @Override
    //    public void actionPerformed(ActionEvent e) {
    //        count--;  // Decrement the count
    //        repaint();  // Request a repaint to update the display
    //    }
    //}
}
