package gobang;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.ScrollPane;

public class TalkPanel extends JPanel {

    public JTextArea showarea;
    public  Image background=((Image) new ImageIcon("src/pic/talkpanel.png").getImage());

    TalkPanel(){
        this.setSize(380,600);
        this.setPreferredSize(new Dimension(280,400));
        Font font=new Font("黑体",Font.PLAIN,30);
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        this.setLayout(new BorderLayout());

        JPanel controllerPanel=new JPanel();

         showarea=new JTextArea(30,20);
        showarea.setFont(font);
        showarea.setLineWrap(true);
        showarea.setWrapStyleWord(true);

        JScrollPane showPanel=new JScrollPane(showarea);
       showarea.setBackground(Color.decode("#000816"));
       controllerPanel.setBackground(Color.decode("#000816"));
        showPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        showPanel.setPreferredSize(new Dimension(350,350));
        JTextField input=new JTextField(23);
        input.setBackground(Color.decode("#000816"));
        input.setForeground(Color.white);
        Font font2=new Font("黑体",Font.PLAIN,15);
        input.setFont(font2);
        JButton submit=new JButton("发送");
        controllerPanel.setLayout(new FlowLayout());
        controllerPanel.setPreferredSize(new Dimension(350,50));
        controllerPanel.add(input);
        controllerPanel.add(submit);

        this.add(showPanel,BorderLayout.CENTER);
        this.add(controllerPanel,BorderLayout.SOUTH);


      

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg=new Message("message",Vars.model.username,input.getText(),sdf.format(date));

                Vars.model.messageModel.addLast(msg);
                Vars.talkPanel.showarea.append(msg.time + "\n" + msg.username + ":" + msg.content + "\n");
                System.out.println(Vars.model.messageModel.size());
            }
        });
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
