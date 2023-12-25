package gobang;

import javax.swing.*;
import java.awt.*;

public class Mybutton extends JButton {
    private static final long serialVersionUID = 1965063150601339314L;
    public String text;
    public Mybutton(String text) {
        super();
        setOpaque(false);
        setContentAreaFilled(false); // 这一句非常重要, 否则父类还会绘制按钮的区域.
        this.text=text;
        this.setText(text);

        Font font=new Font("黑体",Font.PLAIN,30);
        this.setFont(font);
        this.setForeground(Color.decode("#0fa99a"));

        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();


        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//      设置画笔颜色
        g2d.setColor(Color.GREEN);

        g.drawImage(new ImageIcon("src/pic/btn.png").getImage(), 0, 0, width,height,this);//this代指JPanel本身，意思是把图片放这上面
        super.paintComponent(g); // 最后调用这个方法, 让父类绘制在按钮上绘制文字.
    }

}
