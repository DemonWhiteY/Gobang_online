package gobang;

import javax.swing.*;
import java.awt.*;

public class messageManager {
    public void getMessage(Message msg)
    {
        if(msg.code.equals("message")) {
            System.out.println(msg.time + "\n" + msg.username + ":" + msg.content);
            Vars.talkPanel.showarea.append(msg.time + "\n" + msg.username + ":" + msg.content + "\n");
        }
        else if(msg.code.equals("chess")){
            System.out.println();
            Vars.controler.addNetChess(msg.x, msg.y);
        }
        else if(msg.code.equals(Code.BACK)){
            Vars.controler.NETback();
        } else if (msg.code.equals(Code.LOST)) {
            Vars.dialogStyle.Gameover(Start.getStart(),"对方认输");

        } else if (msg.code.equals(Code.PEACE)) {
            Vars.dialogStyle.AnswerPeace(Start.getStart());
        }
        else if(msg.code.equals(Code.LOADING)){
            Vars.model.userList=msg.userlist;
            System.out.println();
        }
        else if (msg.code.equals(Code.REQUEST)) {
            Vars.dialogStyle.AnswerRequest(MainWindows.getMainWindows(), msg.username, 30);
            Vars.model.enemyID= msg.ID;
            Vars.model.enemyname=msg.username;
        } else if (msg.code.equals(Code.REFUSE)) {
            Vars.dialogStyle.waitDialog(MainWindows.getMainWindows(),"对方拒绝了你的邀请",30);
            Vars.model.enemyID=-1;
        }else if(msg.code.equals(Code.STARTGAME)){
            Vars.controler.turnyou=false;
            Vars.roundPanel.update();
            Start start=Start.getStart();
            Vars.timePanel.restart();
        }else if(msg.code.equals(Code.PEACEGAME)){
            Vars.dialogStyle.Gameover(Start.getStart(),"平局");
        }else if(msg.code.equals(Code.REFUSEPEACE)){
            Vars.dialogStyle.waitDialog(Start.getStart(),"对方拒绝了你的求和",10);
        }else if(msg.code.equals(Code.UPDATE)){

            System.out.println(msg.userlist.getFirst().username+" "+msg.userlist.getLast().username);
            System.out.println(msg.userlist.size());
            MainWindows.getMainWindows().Player.removeAll();

            Vars.model.userList=msg.userlist;



            if(Vars.model.userList.size()==1)
            {

                JLabel label=new JLabel("当前无其他用户");
                Font font=new Font("黑体",Font.PLAIN,50);
                label.setFont(font);
                label.setForeground(Color.decode("#0fa99a"));
                MainWindows.getMainWindows().Player.add(label);
            }
            else {
                for (int i = 0; i < Vars.model.userList.size(); i++) {
                    if(i!=Vars.model.ID) {
                        PlayerLabel player = new PlayerLabel(msg.userlist.get(i).username, msg.userlist.get(i).userID);
                        MainWindows.getMainWindows().Player.add(player);
                    }



                }
            }
            JLabel label2=new JLabel("当前在线人数"+String.valueOf(msg.userlist.size()));
            Font font=new Font("黑体",Font.PLAIN,50);
            label2.setFont(font);
            label2.setForeground(Color.decode("#0fa99a"));
            MainWindows.getMainWindows().Player.add(label2);
            MainWindows.getMainWindows().Player.updateUI();
        } else if (msg.code.equals(Code.GETID)) {
            System.out.println("用户信息加载成功");
            System.out.println(msg.ID+msg.username);
            Vars.model.ID=msg.ID;
            Vars.model.username= msg.username;

        }
    }


}
