import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;



public class LinkedListDisplay extends DataStructureDisplay implements MouseListener, MouseMotionListener {
    private LinkedList<String> list;
    private DrawPanel renderer;
    private GUIPanel gui;
    private HashMap<Integer, Visual> visuals;

    private JButton addButton;
    private JButton reorderButton;
    private JTextField listValue;

    private boolean holding;
    private Visual selected;
    private int mouseX, mouseY;
    

    private BufferedImage bg_image;

    public LinkedListDisplay(String title) {
        this.title=title;
        try {
            this.bg_image = ImageIO.read(new File("assets/pexels-sebastian-804570.png"));
        } catch (IOException e) {}
        mouseX=0;
        mouseY=0;
        list=new LinkedList<String>();
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
                    list.add(listValue.getText()); 
                    int i=-1;
                    for(String string : list) {i++;}
                    visuals.put(i, new Visual(10+(i*110), 50)); 
                    renderer.repaint();     
                } 
            }
        });
        reorderButton= new JButton("Reorder");
        reorderButton.setForeground(Color.BLACK);
        reorderButton.setBackground(Color.WHITE);
        reorderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=0;
                for(Visual v: visuals.values()) {
                    v.x=(10+(i*110));
                    v.y=50;
                    i++;
                }
                renderer.repaint();
            }
        });
        gui.add(addButton);
        gui.add(listValue);
        gui.add(reorderButton);
        gui.setBackground(Color.lightGray);

        renderer.addMouseListener(this);
        renderer.addMouseMotionListener(this);       

        this.setLayout(new BorderLayout());
        this.add(gui,BorderLayout.PAGE_START);
        this.add(renderer,BorderLayout.CENTER);
    }
    
    class DrawPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bg_image, 0,0,null);
            int i=0;
            Visual v;
            Color bc;
            Boolean headDrawn=false;
            ArrayList<Visual> vlist=new ArrayList<Visual>();
            ArrayList<String> slist=new ArrayList<String>();
             
            
            for(String s: list){
                slist.add(0,s);
                vlist.add(0,visuals.get(i));
                i++;
            }

            for(int k=0; k<slist.size(); k++) {
                v=vlist.get(k);
                if(v!=null) {
                    if(v==selected) {
                        bc=v.borderColor_press;
                    } else if(Cursor.insideRect(v)) {
                        bc=v.borderColor_hover;
                        
                    } else {
                        bc=v.borderColor;
                        
                    }
                    g.setColor(v.bgColor);
                    g.fillRect(v.x, v.y, v.w, v.h);
                    g.setColor(bc);
                    g.drawRect(v.x, v.y, v.w, v.h);
                    g.drawString(slist.get(k), v.x+10, v.y+30);
                    if(k+1==slist.size()){
                        g.setColor(Color.red);
                        g.drawString("Head", v.x+36,v.y+94);
                        headDrawn=true;
                    } else {
                        g.setColor(Color.RED);
                        g.drawLine(v.x+50,v.y+50, vlist.get(k+1).x+50, vlist.get(k+1).y+50);
                    }
                }
                i++;
            }
            
            // g.setColor(Color.red);
            // g.drawLine(Cursor.x()-10,Cursor.y(),Cursor.x()+10,Cursor.y());
            // g.drawLine(Cursor.x(),Cursor.y()-10,Cursor.x(),Cursor.y()+10);
        }
    }

    class GUIPanel extends JPanel {}
    class NodePanel extends JPanel {
        private int nodeIndex;

        public NodePanel(int i) {
            nodeIndex=i;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Cursor.uptadePos(e.getX(), e.getY());
        if(selected!=null) {
            selected.x=Cursor.x()-mouseX;
            selected.y=Cursor.y()-mouseY;
        }
        renderer.repaint();       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Cursor.uptadePos(e.getX(), e.getY());
        renderer.repaint();    
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==1) {
            renderer.removeAll();
            holding=true;
            for(Visual v: visuals.values()) {
                if(Cursor.insideRect(v)){
                    selected=v;
                    mouseX = Cursor.x()-v.x;
                    mouseY = Cursor.y()-v.y;
                    renderer.repaint();  
                    return;

                }
            }    
            renderer.repaint();  
        } else if(e.getButton()==3) {
            System.out.println("RIGHT CLICK!!!!!!!");
            renderer.removeAll();
            int nodeIndex=0;
            for(Visual v: visuals.values()) {
                if(!Cursor.insideRect(v)){
                    nodeIndex++;
                } else {
                    break;
                }
            }
            if(!(nodeIndex>=visuals.size())) {
                NodePanel rightMenu=new NodePanel(nodeIndex);      
                rightMenu.setLayout(new BoxLayout(rightMenu, BoxLayout.Y_AXIS));
                rightMenu.setBounds(Cursor.x(),Cursor.y(),120,100);

                JButton tb= new JButton("Test");
                tb.setForeground(Color.BLACK);
                tb.setBackground(Color.WHITE);
                tb.setBounds(0,0,120,25);
                tb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        list.remove(rightMenu.nodeIndex);
                        visuals.remove(rightMenu.nodeIndex);
                    
                        renderer.repaint();
                    }
                });
                
                rightMenu.add(tb);
                renderer.add(rightMenu);
            }
            renderer.repaint();
        }  
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        holding=false;   
        selected=null; 
        renderer.repaint();    
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    
}
