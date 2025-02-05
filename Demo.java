import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Demo {
    private JFrame frame;
    protected DataStructureDisplay active;
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 1000;
    

    public Demo() {
        LinkedListDisplay linkedDemo=new LinkedListDisplay("Miku Miku oo ee oo");
        StackDisplay stackDemo=new StackDisplay("Stacks");
        active=stackDemo;
        frame=new JFrame("Data Structures Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        JPanel menuPanel=new JPanel();
        JButton stackButton=new JButton("Stack Demo");
        stackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                active=stackDemo;
                frame.getContentPane().removeAll();
                frame.add(menuPanel, BorderLayout.PAGE_START);
                frame.add(active, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                System.out.println("AAAAAAAAAAAA");
            }

        });
        JButton linkedButton=new JButton("LinkedList Demo");
        linkedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                active=linkedDemo;
                frame.getContentPane().removeAll();
                frame.add(menuPanel, BorderLayout.PAGE_START);
                frame.add(active, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                System.out.println("BBBBBBBBBBBBBBBBB");
            }

        });

        menuPanel.add(linkedButton);
        menuPanel.add(stackButton);
        

        frame.setLayout(new BorderLayout());
        frame.add(menuPanel, BorderLayout.PAGE_START);
        frame.add(active, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Demo();
    }
}