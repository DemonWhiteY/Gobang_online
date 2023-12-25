
package mail;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReciveThread extends Thread{

    private ObjectInputStream ois;
    private volatile boolean flag = true;

    public ReciveThread(ObjectInputStream ois) {
                 super();
                 this.ois = ois;
            }
  public void run() {
        while(true)
        {

            System.out.println("111");
            Message rmsg= null;
            try {
                rmsg = (Message)ois.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

                System.out.println(rmsg.time + "\n" + rmsg.username + ":" + rmsg.content);
                Client.getClient().show.append(rmsg.time + "\n" + rmsg.username + ":" + rmsg.content + "\n");

        }

  }
}

