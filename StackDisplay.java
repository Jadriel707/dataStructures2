import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class StackDisplay extends DataStructureDisplay {
    private Stack<String> list;
    private DrawPanel renderer;
    private GUIPanel gui;
    private HashMap<Integer, Visual> visuals;

    private JButton addButton;
    private JTextField listValue;

    public StackDisplay(String title) {
        this.title=title;
        list=new Stack<String>();
        visuals = new HashMap<Integer, Visual>();
        renderer=new DrawPanel();
        gui=new GUIPanel();
        
        listValue = new JTextField(8);
        addButton = new JButton("Add");
        addButton.setForeground(Color.BLACK);
        addButton.setBackground(Color.WHITE);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listValue.getText().length()>0) {
                    list.push(listValue.getText()); 
                    int i=-1;
                    for(String string : list) {i++;}
                    visuals.put(i, new Visual(Demo.SCREEN_WIDTH/2-150, 650-(i*30), 300,30)); 
                    renderer.repaint();     
                } 
            }
        });
    
        gui.add(addButton);
        gui.add(listValue);
        gui.setBackground(Color.lightGray);      

        

        this.setLayout(new BorderLayout());
        this.add(gui,BorderLayout.PAGE_START);
        this.add(renderer,BorderLayout.CENTER);
    }
    
    class DrawPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int i=0;
            Visual v;
            
            for(String string : list) {
                v=visuals.get(i);
                if(v!=null) {
                    g.setColor(v.bgColor);
                    g.fillRect(v.x, v.y, v.w, v.h);
                    g.setColor(v.borderColor);
                    g.drawRect(v.x, v.y, v.w, v.h);
                    g.drawString(string, (v.x+((v.w/2) - (g.getFontMetrics().stringWidth(string) / 2))), v.y+26);
                    
                    if(visuals.get(i+1)==null) {
                        g.setColor(Color.red);
                        g.drawString("TOP", (v.x+((v.w/2) - (g.getFontMetrics().stringWidth("TOP") / 2))), v.y-10);
                    }
                }
                i++;
            }
        }
    }

    class GUIPanel extends JPanel {}
}
