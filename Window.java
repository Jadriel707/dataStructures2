import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Window {
    JFrame frame;
    int x=6;
    

    public Window() {
        JPanel drawPanel;
        JPanel inputPanel;
        JButton myButton;

        
        frame = new JFrame("JFrame Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        drawPanel = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                g.setColor(java.awt.Color.RED);
                g.fillRect(x, 50, 100, 100);
            }
        };


        inputPanel = new JPanel();

        JTextField texttext = new JTextField(10);
        myButton = new JButton("I AM IRON MAN");
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x=Integer.valueOf(texttext.getText());
                drawPanel.repaint();
            }
        });

        
        
        
        inputPanel.add(myButton);
        inputPanel.add(texttext);

        
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.LINE_END);
        frame.add(drawPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        new Window();
    }
}