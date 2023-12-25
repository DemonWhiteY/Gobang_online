package gobang;



import java.util.LinkedList;

public class Model {
    private LinkedList<chess> model=new LinkedList<>();

    public LinkedList<user> userList=null;

    public  LinkedList<Message>messageModel=new LinkedList<Message>();

    public String username;

    public int enemyID=-1;

    public String enemyname;

    public int ID;

    private int[][]board=new int[22][22];


    public void addChess(int x,int y,boolean color)
    {

            chess newChess = new chess(x, y, color);
            model.add(newChess);
            if (color)
                board[x][y] = 1;
            else
                board[x][y] = 2;

    }

    public  void removeChess()
    {
        board[model.getLast().x][model.getLast().y]=0;
        model.removeLast();

    }

    public LinkedList<chess> getModel()
    {
        return  model;
    }

    public int[][] getboard()
    {
        return board;
    }

    public int getboard(int x,int y)
    {
        return board[x][y];
    }

    public void clear(){
        while(!model.isEmpty())
        {
            model.removeLast();
        }
        for(int i=0;i<22;i++)
            for(int j=0;j<22;j++)
            board[i][j]=0;
    }





}
class chess{
    boolean color;
    int x;
    int y;
    chess(int x,int y,boolean color)

    {
        this.x=x;
        this.y=y;
        this.color=color;
    }
}