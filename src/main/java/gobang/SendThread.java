package gobang;



import java.io.IOException;
import java.io.ObjectOutputStream;

public class SendThread extends Thread {
    private ObjectOutputStream os;

    public SendThread(ObjectOutputStream os) {
        super();
        this.os = os;

    }

    public void run() {

        try {


            while (true) {
                System.out.print("");
                if (!Vars.model.messageModel.isEmpty()) {


                    Message msg = Vars.model.messageModel.getFirst();
                    System.out.println(msg.time+"\n"+msg.username+":"+msg.content);
                    os.writeObject(msg);
                    Vars.model.messageModel.removeFirst();

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

}