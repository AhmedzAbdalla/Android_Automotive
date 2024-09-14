import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MovableStringApplet extends Applet {

    private int x = 50; 
    private int y = 50; 
    private String text = "Move me with arrow keys!";

    @Override
    public void init() {
        addKeyListener(new MyKeyAdapter());
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(text, x, y);
    }

    class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    x -= 10;
                    break;
                case KeyEvent.VK_RIGHT:
                    x += 10;
                    break;
                case KeyEvent.VK_UP:
                    y -= 10;
                    break;
                case KeyEvent.VK_DOWN:
                    y += 10;
                    break;
            }
            repaint(); 
        }
    }
}
