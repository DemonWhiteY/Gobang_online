package mail;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class SendThread extends Thread {
private ObjectOutputStream os;
private String username;
public SendThread(ObjectOutputStream os,String username)
{
    super();
    this.os=os;
    this.username=username;
}

public void run()
{

    try {


        while(true)
        {

            if(!Client.getClient().sendMessage.isEmpty())
            {
                System.out.println("111");

                Message msg=Client.getClient().sendMessage.getFirst();

                    os.writeObject(msg);
                    Client.getClient().sendMessage.removeFirst();

                }
            }
        }catch (IOException e) {
        throw new RuntimeException(e);

}
}


}
