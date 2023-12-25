package gobang;



import java.io.Serializable;
import java.util.LinkedList;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;


    public String username;
    public String content;
    public String time;

    public String code;

    public boolean color;

    public int x,y;
    public int ID;

    public LinkedList<user> userlist;
    public Message(String code,int ID,String username)
    {
        this.code=code;
        this.ID=ID;
        this.username=username;

    }

    public Message(String code,int ID)
    {
        this.code=code;
        this.ID=ID;

    }

    public Message(String code, LinkedList<user> userlist)
    {
        this.code=code;
        this.userlist=userlist;
    }

    public Message(String code,String username,String content,String time){
        super();
        this.code=code;
        this.username=username;
        this.content=content;
        this.time=time;

    }


    public Message(String code,int x,int y,boolean color){
        super();
        this.code=code;
        this.x=x;
        this.y=y;
        this.color=color;
    }

    public Message(String code,String username)
    {
        this.code=code;
        this.username=username;
    }

    public Message(String code)
    {
        this.code=code;

    }


    public Message()
    {
        super();
    }

}

class   Code{
    public static final String MESSAGE="message";
    public static final String CHESS="chess";

    public static final String REQUEST="request";
    public static final String BACK="back";
    public static final String LOST="lost";
    public static final String PEACE="peace";

    public static final String LOADING="loading";

    public static final String RENAME="rename";

    public static final String GETID="getid";

    public static final String REFUSE="refuse";
    public static final String STARTGAME="startgame";

    public static final String PEACEGAME="peacegame";

    public static final String WINGAME="wingane";

    public static final String REFUSEPEACE="refusepeace";

    public static final String UPDATE="update";

    public static final String OUTOFDATE="outofdate";



}
