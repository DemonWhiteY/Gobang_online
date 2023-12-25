package gobang;

import java.io.Serializable;
import java.util.LinkedList;

public class ServerModel {
    private LinkedList<user> userList=new LinkedList<user>();
    public void adduser(user newuser)
    {
        userList.addLast(newuser);
    }
    public LinkedList<user> getdata(){
        return userList;
    }

    public void rename(int ID,String name){
        userList.get(ID).username=name;
    }
}

class user implements Serializable {
    private static final long serialVersionUID = 1L;

    public String username;
    public int userID;

    public user(String username,int userID)
    {
        this.username=username;
        this.userID=userID;
    }

}

