package gobang;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class NET {

    public  Socket s;

    public String host="localhost";
    public int port=8080;


    NET() {

        try {
            s = new Socket("localhost", 8080);
            ObjectOutputStream os=new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream is=new ObjectInputStream(s.getInputStream());
            SendThread sendThread=new SendThread(os);
            ReceiveThread receiveThread=new ReceiveThread(is);
            sendThread.start();
            receiveThread.start();

        }catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void SETNET()
    {
        try {
            s.close();
            s = new Socket(host,port);
            ObjectOutputStream os=new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream is=new ObjectInputStream(s.getInputStream());
            SendThread sendThread=new SendThread(os);
            ReceiveThread receiveThread=new ReceiveThread(is);
            sendThread.start();
            receiveThread.start();

        }catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
