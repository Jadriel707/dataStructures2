import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Demo {
    private JFrame frame;
    protected JPanel active;
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 1000;
    

    public Demo() {
        LinkedListDisplay linkedDemo=new LinkedListDisplay("Miku Miku oo ee oo");
        StackDisplay stackDemo=new StackDisplay("Stacks");
        active=linkedDemo;
        frame=new JFrame("Data Structures Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        JPanel menuPanel=new JPanel();
        menuPanel.setBackground(Color.lightGray);
        JButton stackButton=new JButton("Stack Demo");
        stackButton.setForeground(Color.BLACK);
        stackButton.setBackground(Color.WHITE);
        stackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                active=stackDemo;
                frame.getContentPane().removeAll();
                frame.add(menuPanel, BorderLayout.PAGE_START);
                frame.add(active, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
            }

        });
        JButton linkedButton=new JButton("LinkedList Demo");
        linkedButton.setForeground(Color.BLACK);
        linkedButton.setBackground(Color.WHITE);
        linkedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                active=linkedDemo;
                frame.getContentPane().removeAll();
                frame.add(menuPanel, BorderLayout.PAGE_START);
                frame.add(active, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
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