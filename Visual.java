import java.awt.Color;
import java.awt.Graphics;

public class Visual extends MyRect {
    Color bgColor;
    Color borderColor;
    Color borderColor_hover;
    Color borderColor_press;

    public Visual(int x, int y) {
        super(x, y, 100, 100);
        bgColor=Color.WHITE;
        borderColor=Color.BLUE;
        borderColor_hover=Color.RED;
        borderColor_press=Color.GREEN;
    }

    public Visual(int x, int y, int w, int h) {
        super(x, y, w, h);
        bgColor=Color.WHITE;
        borderColor=Color.BLACK;
        borderColor_hover=Color.RED;
        borderColor_press=Color.GREEN;
    }

    public String toString() {
        return "("+x+", "+y+", "+w+", "+h+")";
    }

    

}
