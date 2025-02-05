import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class DataStructureDisplay extends JPanel {
    protected String title;
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial",1,20));
        g.setColor(Color.BLACK);
        g.drawString(title, (Demo.SCREEN_WIDTH / 2) - (g.getFontMetrics().stringWidth(title) / 2), 50);
    }
}
