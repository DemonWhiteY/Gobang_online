package mail;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(8100);
            for (int i = 0; i < 10; i++) {
                byte[] buf = "hello".getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length,InetAddress.getByName("localhost"),8000);
                s.send(packet);
                buf = new byte[1000];
                packet = new DatagramPacket(buf, buf.length);
                s.receive(packet);
                String line = new String(buf, 0, packet.getLength());
                System.out.println("server:" + line);
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
