import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
//import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

public class FontListApplet extends Applet {

    private String[] fontNames;
    private Font[] fonts;

    @Override
    //nitialize resources that the applet will use
    public void init() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        fontNames = toolkit.getFontList();
        fonts = new Font[fontNames.length];

        // Initialize fonts b/c setfont() in applet expects Font objects
        for (int i = 0; i < fontNames.length; i++) {
            // create objects from each available fonts
            fonts[i] = new Font(fontNames[i], Font.PLAIN, 22);
        }
    }

    @Override
    public void paint(Graphics g) {
        int y = 20;
        for (int i = 0; i < fontNames.length; i++) {
            g.setFont(fonts[i]);
            g.drawString(fontNames[i], 22, y);
            y += 30;
        }
    }
}
