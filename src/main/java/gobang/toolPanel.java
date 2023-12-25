package gobang;

import com.sun.javafx.geom.Vec4d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class toolPanel extends JPanel {
    toolPanel(){
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();

        this.setPreferredSize(new Dimension(300,900));
        this.setLayout(new FlowLayout());
        this.setBackground(Color.decode("#000816"));
        JPanel btnPanel=new JPanel();



        btnPanel.setLayout(new GridLayout(3,1));
        btnPanel.setPreferredSize(new Dimension(270,200));
        btnPanel.setBackground(Color.decode("#000816"));
        Mybutton jumpback=new Mybutton("悔棋");
        Mybutton lostgame=new Mybutton("认输");
        Mybutton peace=new Mybutton("求和");
        jumpback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vars.controler.back();

                Message msg=new Message(Code.BACK,Vars.model.username);
                Vars.model.messageModel.addLast(msg);
                Message Mmsg=new Message(Code.MESSAGE,Vars.model.username,"玩家"+Vars.model.username+"悔棋了,还剩"+Vars.controler.Backnum+"次悔棋机会",sdf.format(date));
                Vars.model.messageModel.addLast(Mmsg);
                Vars.talkPanel.showarea.append("玩家"+Vars.model.username+"悔棋了,还剩"+Vars.controler.Backnum+"次悔棋机会");
            }
        });
        lostgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Message msg=new Message(Code.LOST,Vars.model.username);
                Vars.model.messageModel.addLast(msg);
                Message Mmsg=new Message(Code.MESSAGE,Vars.model.username,"玩家"+Vars.model.username+"认输了",sdf.format(date));
                Vars.model.messageModel.addLast(Mmsg);
                Vars.dialogStyle.Gameover(Start.getStart(),"游戏失败");
                Vars.talkPanel.showarea.append("玩家"+Vars.model.username+"认输了");
            }
        });

        peace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg=new Message(Code.PEACE,Vars.model.username);
                Vars.model.messageModel.addLast(msg);
                Message Mmsg=new Message(Code.MESSAGE,Vars.model.username,"玩家"+Vars.model.username+",还剩发起了求和"+Vars.controler.Backnum+"次求和机会",sdf.format(date));
                Vars.model.messageModel.addLast(Mmsg);
                Vars.talkPanel.showarea.append("玩家"+Vars.model.username+",还剩发起了求和"+Vars.controler.Backnum+"次求和机会");
            }
        });
        jumpback.setPreferredSize(new Dimension(100,50));
        lostgame.setPreferredSize(new Dimension(100,50));
        peace.setPreferredSize(new Dimension(100,50));
        btnPanel.add(jumpback);
        btnPanel.add(lostgame);
        btnPanel.add(peace);

        this.add(Vars.timePanel);
        this.add(Vars.roundPanel);
        this.add(btnPanel);
        this.add(Vars.talkPanel);
    }
}
