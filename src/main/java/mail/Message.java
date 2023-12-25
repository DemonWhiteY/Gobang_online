package mail;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    public String username;
    public String content;
    public String time;

    public Message(String username,String content,String time){
        super();
        this.username=username;
        this.content=content;
        this.time=time;

    }

    public Message()
    {
        super();
    }

}
