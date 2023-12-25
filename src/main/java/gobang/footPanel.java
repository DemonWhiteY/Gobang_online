package gobang;



import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class footPanel extends JPanel {
    Image background=((Image) new ImageIcon("src/pic/foot.png").getImage());
    public footPanel(){
        this.setPreferredSize(new Dimension(800,50));
        this.setBackground(Color.decode("#000816"));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }



}

