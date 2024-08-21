import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;

public class upgrade_FontListApplet extends Applet {

    private String[] fontNames;
    private Font[] fonts;

    @Override
    public void init() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontNames = ge.getAvailableFontFamilyNames();
        fonts = new Font[fontNames.length];

        // Initialize fonts
        for (int i = 0; i < fontNames.length; i++) {
            fonts[i] = new Font(fontNames[i], Font.PLAIN, 12);
        }
    }

    @Override
    public void paint(Graphics g) {
        int y = 20;
        for (int i = 0; i < fontNames.length; i++) {
            g.setFont(fonts[i]);
            g.drawString(fontNames[i], 10, y);
            y += 15;
        }
    }
}

