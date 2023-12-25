package gobang;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class headPanel extends JPanel {
    Image background=((Image) new ImageIcon("src/pic/head.png").getImage());
    public headPanel(){
        this.setPreferredSize(new Dimension(800,50));
        this.setBackground(Color.decode("#000816"));
        JLabel label=new JLabel(Vars.model.username+" vs "+Vars.model.enemyname,JLabel.CENTER);
        label.setForeground(Color.decode("#0fa99a"));
        Font font=new Font("黑体",Font.PLAIN,20);
        this.setLayout(new BorderLayout());
        this.add(label,BorderLayout.CENTER);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }



}
