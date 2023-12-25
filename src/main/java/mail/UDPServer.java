package mail;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(8000);
            int i=0;
            while(true){
                System.out.println("begin receive");
                byte[] buf = new byte[1000];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                s.receive(packet);
                System.out.println("received data");
                packet.getLength();
                String line = new String(buf,0,packet.getLength());
                System.out.println("client:"+line);
                line = line+i++;
                buf = line.getBytes();
                packet=new DatagramPacket(buf, buf.length,packet.getAddress() , 8100);
                s.send(packet);


            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
