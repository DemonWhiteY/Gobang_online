package gobang;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimePanel extends JPanel {


    public int roundTime=30;

    public int time=30;

    public boolean timeStart;

    JLabel label;

    public  Image background=((Image) new ImageIcon("src/pic/round.png").getImage());
    public TimePanel()
    {
        this.setPreferredSize(new Dimension(100,100));
        this.setLayout(new BorderLayout());
        Font font=new Font("黑体",Font.PLAIN,25);
        java.util.Timer timer = new Timer();

        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        label=new JLabel(String.valueOf(time),JLabel.CENTER);
        label.setFont(font);
        label.setForeground(Color.decode("#0fa99a"));

        this.add(label,BorderLayout.CENTER);



        timer.schedule(new TimerTask() {
            public void run() {
                if(time==0&&timeStart) {
                    if (Vars.controler.OutTimenum > 0) {
                        if(Vars.controler.turnyou) {
                            Vars.controler.OutTimenum--;
                            Message mmsg = new Message(Code.MESSAGE, Vars.model.username, "玩家" + Vars.model.username + "超时，还有" + Vars.controler.OutTimenum + "次超时机会", sdf.format(date));
                            Vars.model.messageModel.addLast(mmsg);
                            Vars.talkPanel.showarea.append(sdf.format(date) + "\n玩家" + Vars.model.username + "超时，还有" + Vars.controler.OutTimenum + "次超时机会\n");
                        }

                        time=roundTime;
                        label.setText(String.valueOf(time));

                    } else {
                        Message msg = new Message(Code.OUTOFDATE);
                        Vars.model.messageModel.addLast(msg);
                        Vars.dialogStyle.Gameover(Start.getStart(), "超时！游戏失败");
                    }
                }  else
                {

                    if(time<=3)
                    {
                        label.setForeground(Color.RED);
                    }
                    time--;
                    label.setText(String.valueOf(time));


                }


            }
        }, 0 , 1000);


    }

    public void restart()
    {
        time=roundTime;
        timeStart=true;
        label.setForeground(Color.decode("#0fa99a"));
    }

    public void endtime()
    {
        timeStart=false;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }


}
