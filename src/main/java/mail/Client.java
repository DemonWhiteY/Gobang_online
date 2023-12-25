package mail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


import static java.lang.System.out;


public class Client {

    private JFrame mainFrame;
    private JTextField username;
    private JScrollPane statusLabel;
    private JPanel controlPanel;
    public JTextArea show;
    private static Client client=new Client();

    public static LinkedList<Message> sendMessage=new LinkedList<Message>();

   private     Client(){
              prepareGUI();


        }
        public static  Client getClient()
        {
            return client;
        }



    private void prepareGUI(){
        mainFrame = new JFrame("Java Swing JTextArea示例");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        username=new JTextField(20);
        show=new JTextArea(20,20);
        statusLabel=new JScrollPane(show);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(username,BorderLayout.NORTH);
        mainFrame.add(statusLabel,BorderLayout.CENTER);
        mainFrame.add(controlPanel,BorderLayout.SOUTH);

        mainFrame.setVisible(true);
    }





    public static void main(String[] args) {



        Client client=Client.getClient();

        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();// 获取当前时间


        try {
            Socket s = new Socket("localhost",8080);
            ObjectOutputStream os=new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream is=new ObjectInputStream(s.getInputStream());






            client.username.setText("新用户");
            JLabel  commentlabel= new JLabel("发送: ", JLabel.RIGHT);

            JTextArea commentTextArea =
                    new JTextArea("你好",3,20);

            JScrollPane scrollPane = new JScrollPane(commentTextArea);
            JButton showButton = new JButton("发送");

            showButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Message msg=new Message(client.username.getText(),commentTextArea.getText(),sdf.format(date));
                    out.println(client.username.getText());
                    client.sendMessage.addLast(msg);
                    System.out.println(client.sendMessage.size());

                }
            });
            client.controlPanel.add(commentlabel);
            client.controlPanel.add(scrollPane);
            client.controlPanel.add(showButton);
            client.mainFrame.setVisible(true);

            SendThread sendThread=new SendThread(os,client.username.getText());
            ReciveThread reciveThread=new ReciveThread(is);

            sendThread.start();
            reciveThread.start();


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }
}



