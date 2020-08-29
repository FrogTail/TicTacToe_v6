import javax.swing.*;
import java.awt.*;

public class Field extends JPanel
{
    Field (int size)
    {
        setLayout(new GridLayout(size,size));
        setBackground(Color.red);
        setVisible(true);
        setMinimumSize(new Dimension(100,100));
        setMaximumSize(new Dimension(100,100));
        setPreferredSize(new Dimension(100,100));
        JButton jb = new JButton("ТЕСТ");
        add(jb);
        repaint();

        System.out.println("Мы все сделали! Опять нифига не видно?");
    }
}
