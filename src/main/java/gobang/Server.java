package gobang;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    public static ServerModel model=new ServerModel();
    public static LinkedList<ObjectOutputStream>online=new LinkedList<ObjectOutputStream>();
    public Server(){}
    public static void main(String[] arg) throws IOException {


           //ServerSocket ss = new ServerSocket(8000);
            int num = 1;
            int ID=0;
            System.out.println("正在连接中……");

            while (true) {
                //Socket s = ss.accept();
               // ServerThread newServerThtear=new ServerThread(s,ID);
                //newServerThtear.start();
                //user newuser=new user("新用户"+ID,ID);
                //model.adduser(newuser);
                System.out.println("新用户加入,当前在线用户"+num+"人");

                num++;
                ID++;
            }

    }



}


class ServerThread extends Thread{

    private Socket s;

    private ObjectInputStream ois;
   private ObjectOutputStream oos;

   private int enemyID=-1;

   private int ID;





    public ServerThread(Socket s,int ID){
        super();
            this.s=s;
            this.ID=ID;

    }
    public void run(){


        try {

            ois = new ObjectInputStream(s.getInputStream());
            oos = new ObjectOutputStream(s.getOutputStream());
            Server.online.addLast(oos);
            Message Mmsg=new Message(Code.GETID,ID,Server.model.getdata().get(ID).username);
            oos.writeObject(Mmsg);


            while(true)
            {
                Message msg=(Message)ois.readObject();
                if(msg.code.equals("message")) {
                    if (enemyID != -1) {
                        System.out.println(msg.time + "\n" + msg.username + ":" + msg.content);
                        Server.online.get(enemyID).writeObject(msg);
                    }
                }
                else if(msg.code.equals("chess"))
                {
                    if (enemyID != -1) {

                        System.out.println(msg.time + "\n" + msg.username + ":" + msg.content);
                        Server.online.get(enemyID).writeObject(msg);
                    }
                }
                else if(msg.code.equals(Code.LOADING))
                {
                   Message answermsg=new Message(Code.LOADING,Server.model.getdata());
                    oos.writeObject(answermsg);
                    System.out.println(Server.model.getdata().getFirst().username);
                } else if (msg.code.equals(Code.UPDATE)) {
                    LinkedList<user> List=new LinkedList<>();
                    for(int i=0;i<Server.model.getdata().size();i++)
                    {
                        List.addLast(Server.model.getdata().get(i));
                    }

                    Message answermsg=new Message(Code.UPDATE,List);
                    System.out.println(answermsg.userlist.size());
                    System.out.println(answermsg.userlist.getFirst().username+" "+answermsg.userlist.getLast().username);
                   oos.writeObject(answermsg);



                } else if(msg.code.equals(Code.RENAME))
                {
                    Server.model.rename(this.ID,msg.username);
                    System.out.println("更改名字成功");

                }
                else if(msg.code.equals(Code.REQUEST))
                {
                   Message requestmsg=new Message(Code.REQUEST,ID, msg.username);
                    Server.online.get(msg.ID).writeObject(requestmsg);
                    System.out.println("请求发送给"+msg.ID);
                    this.enemyID=msg.ID;


                }else if(msg.code.equals(Code.STARTGAME))
                {
                    Server.online.get(msg.ID).writeObject(msg);
                    enemyID= msg.ID;

                }else if(msg.code.equals(Code.REFUSE))
                {
                    Server.online.get(msg.ID).writeObject(msg);
                } else
                {
                    if (enemyID != -1) {
                        Server.online.get(enemyID).writeObject(msg);
                    }
                }
//
            }




            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
    }
