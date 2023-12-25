package gobang;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.LinkedList;

public class ChessPanel extends JPanel {
    Point tempPoint=new Point(10000,10000);
    Image background=((Image) new ImageIcon("src/pic/backgroun.png").getImage());

    Image chess1=((Image) new ImageIcon("src/pic/chess1.png").getImage());
    Image chess2=((Image) new ImageIcon("src/pic/chess2.png").getImage());

    public ChessPanel(){
            this.setPreferredSize(new Dimension(800,800));
            this.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    super.mouseDragged(e);
                    tempPoint=e.getPoint();

                    repaint();
                }
            });

            this.setBackground(Color.decode("#000816"));
            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(e.getPoint().x+""+e.getPoint().y);
                    try {
                        Vars.controler.addChess(e.getPoint());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    try {
                        Vars.controler.addChess(e.getPoint());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    tempPoint.x=10000;
                    tempPoint.y=10000;
                }
                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

    }
    protected  void drawtempchess(Graphics g)
    {
        int cellWidth = Vars.chessPanel.getWidth() / 20;
        int cellHeight = Vars.chessPanel.getHeight() / 20;
        int x,y;
        x= tempPoint.x/cellWidth;
        y= tempPoint.y/cellHeight;
        System.out.println(x+" "+y);
        if(Vars.controler.getColor()){
            g.drawImage(chess1,cellWidth/2+cellWidth*x,cellWidth/2+cellWidth*y,cellWidth,cellHeight,this);
        }
        else{
            g.drawImage(chess2,cellWidth/2+cellWidth*x,cellWidth/2+cellWidth*y,cellWidth,cellHeight,this);
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,0,0,this.getWidth(),this.getHeight(),this);
        System.out.println(background.getHeight(this));


        int cellWidth = getWidth() / 20;
        int cellHeight = getHeight() / 20;
        ((Graphics2D)g).setStroke(new BasicStroke(3));
       for(int i=0;i<19;i++)
       {
           g.setColor(Color.decode("#0fa99a"));
           g.drawLine(cellWidth,cellWidth*(i+1),cellWidth*(19),cellWidth*(i+1));
           g.drawLine(cellWidth*(i+1),cellWidth,cellWidth*(i+1),cellWidth*(19));
       }

        if(Vars.controler.turnyou)drawtempchess(g);
        LinkedList<chess> data=Vars.model.getModel();
       for(chess chess:data)
       {
           if(chess.color){
               g.drawImage(chess1,cellWidth/2+cellWidth*chess.x,cellWidth/2+cellWidth*chess.y,cellWidth,cellHeight,this);
           }
           else{
               g.drawImage(chess2,cellWidth/2+cellWidth*chess.x,cellWidth/2+cellWidth*chess.y,cellWidth,cellHeight,this);
           }

       }

    }

    }
