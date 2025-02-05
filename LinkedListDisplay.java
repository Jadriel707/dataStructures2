import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LinkedListDisplay extends DataStructureDisplay {
    private LinkedList<String> list;
    private DrawPanel renderer;
    private GUIPanel gui;
    private HashMap<Integer, Visual> visuals;

    private JButton addButton;
    private JButton reorderButton;
    private JTextField listValue;

    public LinkedListDisplay(String title) {
        this.title=title;
        list=new LinkedList<String>();
        visuals = new HashMap<Integer, Visual>();
        renderer=new DrawPanel();
        gui=new GUIPanel();
        
        listValue = new JTextField(8);
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listValue.getText().length()>0) {
                    list.add(listValue.getText()); 
                    int i=-1;
                    for(String string : list) {i++;}
                    visuals.put(i, new Visual(10+(i*100), 50)); 
                    renderer.repaint();     
                } 
            }
        });
        reorderButton= new JButton("Reorder");
        gui.add(addButton);
        gui.add(listValue);
        gui.add(reorderButton);

        

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
                    g.drawString(string, v.x+10, v.y+30);
                }
                i++;
            }
        }
    }

    class GUIPanel extends JPanel {}
}
