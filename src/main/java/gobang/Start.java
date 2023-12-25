package gobang;



import javax.swing.*;
import java.awt.*;
import  java.awt.Graphics;

public class Start extends JFrame  {
    private static Start start=new Start();

    public static Start getStart(){
        return start;
    }
    private Start(){
        this.setTitle("五子棋");
        this.setSize(1100,900);
        JPanel GAME=new JPanel();
        headPanel headPanel=new headPanel();
        footPanel footPanel=new footPanel();
        GAME.setLayout(new BorderLayout());
        GAME.setBackground(Color.decode("#000816"));
        GAME.add(Vars.chessPanel,BorderLayout.CENTER);
        GAME.add(headPanel,BorderLayout.NORTH);
        GAME.add(footPanel,BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.add(GAME,BorderLayout.CENTER);
        this.add(Vars.tool,BorderLayout.EAST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);



    }


    public static void main(String[] org){
        Start start=getStart();



    }

}
