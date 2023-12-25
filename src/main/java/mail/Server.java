package mail;

import mail.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    public Server(){}
    public static void main(String[] arg) throws IOException {


        ServerSocket ss = new ServerSocket(8080);

        int num = 1;
        int userID=0;
        System.out.println("正在连接中……");

        while (true) {
            Socket s = ss.accept();
            ServerThread newServerThtear=new ServerThread(s);
            newServerThtear.start();

            System.out.println("新用户加入,当前在线用户"+num+"人");
            num++;
        }

    }



}


class ServerThread extends Thread{

    private Socket s;
    private String username;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public static LinkedList<ObjectOutputStream>online=new LinkedList<ObjectOutputStream>();



    public ServerThread(Socket s){
        super();
        this.s=s;
    }
    public void run(){


        try {

            ois = new ObjectInputStream(s.getInputStream());
            oos = new ObjectOutputStream(s.getOutputStream());
            online.addLast(oos);


            while(true)
            {
                Message msg=(Message)ois.readObject();

                System.out.println(msg.time+"\n"+msg.username+":"+msg.content);
                for(ObjectOutputStream on:online)
                {
                    on.writeObject(msg);
                }
            }




        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}