package gobang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindows extends JFrame {

    private static MainWindows mainWindows=new MainWindows();

    public static MainWindows getMainWindows(){return mainWindows;}

    public JPanel Player;
    private MainWindows(){

        Message msg=new Message(Code.LOADING,Vars.model.username);
        Vars.model.messageModel.addLast(msg);
        Vars.timePanel.endtime();
        this.setLayout(new BorderLayout());
        this.setSize(600,1000);

        JMenuBar meau=new JMenuBar();
        JMenu set=new JMenu("设置");
        JMenuItem NETset=new JMenuItem("网络设置");
        NETset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vars.dialogStyle.NETSET(MainWindows.getMainWindows());
            }
        });

        set.add(NETset);
        meau.add(set);
        this.setJMenuBar(meau);

        Player=new JPanel();
        Player.setBackground(Color.decode("#000816"));
        Player.setPreferredSize(new Dimension(250,400));

        Player.setLayout(new GridLayout(5,1));
        JTextField usernameBox=new JTextField("新用户",10);
        JButton submit=new JButton("更名");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vars.model.username=usernameBox.getText();
                Vars.dialogStyle.waitDialog(MainWindows.getMainWindows(),"修改昵称成功",30);
                Message msg=new Message(Code.RENAME,Vars.model.username);
                Vars.model.messageModel.addLast(msg);
            }
        });

        JPanel namePanel=new JPanel();
        namePanel.setLayout(new FlowLayout());
        namePanel.add(usernameBox);
        namePanel.add(submit);

        if(Vars.model.userList.size()==1)
        {

            JLabel label=new JLabel("当前无其他用户");
            Font font=new Font("黑体",Font.PLAIN,30);
            label.setFont(font);
            label.setForeground(Color.decode("#0fa99a"));
            Player.add(label);
        }
        else {
            for (int i = 0; i < Vars.model.userList.size()-1; i++) {
                PlayerLabel player = new PlayerLabel(Vars.model.userList.get(i).username, Vars.model.userList.get(i).userID);
                Player.add(player);
            }
        }

        Mybutton update=new Mybutton("刷新");
        update.setFocusPainted(false);
        update.setBorderPainted(false);
        update.setPreferredSize(new Dimension(600,100));
        //update.setFocusPainted(false);
//        update.setSize(300,500);

//       ImageIcon icon = new ImageIcon("src/pic/btn.png");//根据路径创建图标
//            Image temp1 = icon.getImage().getScaledInstance(update.getWidth(),
//             update.getHeight(), icon.getImage().SCALE_SMOOTH);
//            icon = new ImageIcon(temp1);
//            update.setIcon(icon);


        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg=new Message(Code.UPDATE);
                Vars.model.messageModel.addLast(msg);
            }
        });

        this.add(Player,BorderLayout.CENTER);
        this.add(namePanel,BorderLayout.NORTH);
        this.add(update,BorderLayout.SOUTH);

        this.setVisible(true);



    }




    public static void main(String[] arg){
        MainWindows mainWindows=MainWindows.getMainWindows();
    }



}

class PlayerLabel extends JPanel
{
    String username;
    public  Image background=((Image) new ImageIcon("src/pic/label.png").getImage());
    public PlayerLabel(String username,int userID)
    {
        this.setSize(280,50);
        this.setForeground(Color.white);
        this.username=username;




        JLabel label=new JLabel(username);
        label.setBounds(100,30,300,100);
        Font font=new Font("黑体",Font.PLAIN,50);
        label.setFont(font);
            label.setForeground(Color.decode("#0fa99a"));
        this.setLayout(null);
       JButton button=new JButton("邀请");
       button.setBounds(400,100,100,30);
        this.add(label);
        this.add(button);


//        user.setBackground(new Color(0,0,0,0));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                request(userID);
            }
        });

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void request(int ID)
    {
        Vars.dialogStyle.waitDialog(MainWindows.getMainWindows(),"等待对方接受",30);
        Vars.model.enemyID=ID;
        Vars.model.enemyname=username;
        Message msg=new Message(Code.REQUEST,ID,Vars.model.username);
        Vars.model.messageModel.addLast(msg);


    }

}
