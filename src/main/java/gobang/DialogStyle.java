package gobang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;


 public   class DialogStyle {
    public void waitDialog(JFrame frame,String text,int time)
    {

        JDialog waitDialog=new JDialog(frame);

        waitDialog.setBounds(500,500,200,100);
        waitDialog.setLocationRelativeTo(frame);
        waitDialog.setLocation(frame.getWidth()/2-100,frame.getWidth()/2-50);
        final int[] i = {time};
        JLabel label=new JLabel(text+"等待中  "+ i[0]);
        waitDialog.add(label);
        waitDialog.setVisible(true);

        Timer timer = new Timer(text+"等待中  "+ i[0]);
        timer.schedule(new TimerTask() {
            public void run() {
               i[0]--;
               label.setText(text+"等待中  "+ i[0]);
            }
        }, 0 , 1000);

        timer.schedule(new TimerTask() {
            public void run() {
               waitDialog.setVisible(false);
            }
        }, 1000*time , 1000);


    }

    public void AnswerRequest(JFrame frame,String text,int time)
    {
        JDialog waitDialog=new JDialog(frame);
        waitDialog.setBounds(600,600,200,150);
        waitDialog.setLocationRelativeTo(frame);
        waitDialog.setLocation(frame.getWidth()/2-100,frame.getWidth()/2-50);
        final int[] i = {time};
        JLabel label=new JLabel("玩家"+text+"向您发来挑战"+ i[0]);
        waitDialog.setLayout(new BorderLayout());
        waitDialog.add(label,BorderLayout.CENTER);


        Timer timer = new Timer("玩家"+text+"向您发来挑战"+ i[0]);
        timer.schedule(new TimerTask() {
            public void run() {
                i[0]--;
                label.setText(text+"玩家"+text+"向您发来挑战"+ i[0]);
            }
        }, 0 , 1000);

        timer.schedule(new TimerTask() {
            public void run() {
                i[0]=time;
                label.setText("玩家"+text+"向您发来挑战"+ i[0]);
                timer.cancel();
            }
        }, 1000*time , 1000);
        JButton yes=new JButton("同意");
        JButton no=new JButton("拒绝");

        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg=new Message(Code.STARTGAME,Vars.model.enemyID);
                Vars.model.messageModel.addLast(msg);
                Vars.controler.turnyou=true;
                Vars.roundPanel.update();
                Start.getStart();
                waitDialog.setVisible(false);
                Vars.timePanel.restart();

            }
        });

        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg=new Message(Code.REFUSE,Vars.model.enemyID);
                Vars.model.messageModel.addLast(msg);
                Vars.model.enemyID=-1;
                waitDialog.setVisible(false);

            }
        });
        JPanel btnPanel=new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(yes);
        btnPanel.add(no);

        waitDialog.add(btnPanel,BorderLayout.SOUTH);
        waitDialog.setVisible(true);



    }

     public void AnswerPeace(JFrame frame)
     {
         JDialog waitDialog=new JDialog(frame);
         waitDialog.setBounds(500,500,200,200);
         waitDialog.setLocationRelativeTo(frame);
         waitDialog.setLocation(frame.getWidth()/2-100,frame.getWidth()/2-50);
         JLabel label=new JLabel("对方向您求和");
         waitDialog.setLayout(new BorderLayout());
         waitDialog.add(label,BorderLayout.CENTER);



         JButton yes=new JButton("同意");
         JButton no=new JButton("拒绝");

         yes.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Message msg=new Message(Code.PEACEGAME);
                 Vars.model.messageModel.addLast(msg);
                 Vars.dialogStyle.Gameover(MainWindows.getMainWindows(),"平局");



             }
         });

         no.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Message msg=new Message(Code.REFUSEPEACE);
                 Vars.model.messageModel.addLast(msg);
                 waitDialog.setVisible(false);

             }
         });
         JPanel btnPanel=new JPanel();
         btnPanel.setLayout(new FlowLayout());
         btnPanel.add(yes);
         btnPanel.add(no);

         waitDialog.add(btnPanel,BorderLayout.SOUTH);
         waitDialog.setVisible(true);



     }

     public void Gameover(JFrame frame,String result)
     {
         JDialog waitDialog=new JDialog(frame);
         waitDialog.setBounds(600,600,200,150);
         waitDialog.setLocationRelativeTo(frame);
         waitDialog.setLocation(frame.getWidth()/2-100,frame.getWidth()/2-50);
         JLabel label=new JLabel("游戏结束 "+result);
         waitDialog.setLayout(new BorderLayout());
         waitDialog.add(label,BorderLayout.CENTER);


        Vars.timePanel.endtime();

         JButton yes=new JButton("返回主页面");
         JButton no=new JButton("再来一局");

         yes.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                    Start.getStart().setVisible(false);
                    Vars.model.clear();
                    Vars.model.enemyID=-1;

             }
         });

         no.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {


             }
         });
         JPanel btnPanel=new JPanel();
         btnPanel.setLayout(new FlowLayout());
         btnPanel.add(yes);
         btnPanel.add(no);

         waitDialog.add(btnPanel,BorderLayout.SOUTH);
         waitDialog.setVisible(true);



     }


     public void NETSET(Frame frame)
     {
         JDialog waitDialog=new JDialog(frame);
         waitDialog.setBounds(600,600,200,150);
         waitDialog.setLocationRelativeTo(frame);
         waitDialog.setLocation(frame.getWidth()/2-100,frame.getWidth()/2-50);

         waitDialog.setLayout(new BorderLayout());
         JPanel panel=new JPanel();
         JLabel label1=new JLabel("IP地址");
         JLabel label2=new JLabel("端口");
         JTextField IP=new JTextField("localhost",30);
         JTextField port=new JTextField("8080",20);
         panel.setLayout(new GridLayout(2,2));
         panel.add(label1);
         panel.add(IP);
         panel.add(label2);
         panel.add(port);
         waitDialog.add(panel,BorderLayout.CENTER);
         JButton submit=new JButton("确认修改");
         submit.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Vars.net.host=IP.getText();
                 System.out.println(IP.getText());
                 System.out.println(port.getText());
                 Vars.net.port=Integer.parseInt(port.getText());
                 Vars.net.SETNET();
                 waitDialog.setVisible(false);
             }
         });

         waitDialog.add(submit,BorderLayout.SOUTH);
         waitDialog.setVisible(true);
     }











}


