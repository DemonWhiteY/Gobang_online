package gobang;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class roundPanel extends JPanel {
    Image background=((Image) new ImageIcon("src/pic/timepanel.png").getImage());
    JLabel label=new JLabel("您的回合",JLabel.CENTER);
    public roundPanel(){
        this.setPreferredSize(new Dimension(300,130));


        label.setForeground(Color.decode("#0fa99a"));
        Font font=new Font("黑体",Font.PLAIN,30);
        label.setFont(font);
        this.setLayout(new BorderLayout());
        this.add(label,BorderLayout.CENTER);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void update()
    {
        if(Vars.controler.turnyou)
        {
            label.setText("您的回合");
        }
        else
        {
            label.setText("对方的回合");
        }
    }



}