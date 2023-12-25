package gobang;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Controler {
    boolean chessColor=false;
    boolean turnyou=true;

    int OutTimenum=3;

    int peacenum=3;

    int Backnum=3;

    public void addChess(Point point) throws IOException {
        if(turnyou) {

            int cellWidth = Vars.chessPanel.getWidth() / 20;
            int cellHeight = Vars.chessPanel.getHeight() / 20;
            int x, y;
            x = point.x / cellWidth;
            y = point.y / cellHeight;
            System.out.println(x + " " + y);
            if (Vars.model.getboard(x, y) == 0) {

                Vars.model.addChess(x, y, chessColor);
                if (chessColor) {
                    chessColor = false;
                } else {
                    chessColor = true;
                }
                Vars.chessPanel.repaint();

               Message msg=new Message("chess",x,y,chessColor);
               Vars.model.messageModel.addLast(msg);
                turnyou = false;
                Vars.timePanel.restart();
                Vars.roundPanel.update();
                judgement();
            }
        }

    }




    public void addNetChess(int x,int y) {

        System.out.println(x + " " + y);
        if (Vars.model.getboard(x, y) == 0) {

            Vars.model.addChess(x, y, chessColor);
            if (chessColor) {
                chessColor = false;
            } else {
                chessColor = true;
            }
            Vars.chessPanel.repaint();
            turnyou=true;
            Vars.timePanel.restart();
            Vars.roundPanel.update();
            judgement();

        }
    }






        public  void judgement() {
            // 示例输入
            int[][] chessboard = Vars.model.getboard();  // 初始化一个21x21的棋盘，初始值都为0

            int winner = checkWinner(chessboard);
            if (winner == 1) {
                System.out.println("白棋获胜！");
               Vars.dialogStyle.Gameover(Start.getStart(),"白棋获胜");

            } else if (winner == 2) {
                System.out.println("黑棋获胜！");
                Vars.dialogStyle.Gameover(Start.getStart(),"黑棋获胜");

            } else {
                System.out.println("游戏尚未结束，或者平局！");
            }
        }

        public int checkWinner(int[][] chessboard) {
            // 检查每一行
            for (int i = 0; i < 22; i++) {
                if (checkRow(chessboard, i) != 0) {
                    return checkRow(chessboard, i);
                }
            }

            // 检查每一列
            for (int j = 0; j < 22; j++) {
                if (checkColumn(chessboard, j) != 0) {
                    return checkColumn(chessboard, j);
                }
            }

            // 检查两个对角线
            if (checkDiagonal(chessboard) != 0) {
                return checkDiagonal(chessboard);
            }

            // 游戏尚未结束
            return 0;
        }

        public int checkRow(int[][] chessboard, int row) {
            for (int i = 0; i < 16; i++) {  // 因为是五子棋，所以只需检查前17个位置
                if (chessboard[row][i] == chessboard[row][i + 1] &&
                        chessboard[row][i + 1] == chessboard[row][i + 2] &&
                        chessboard[row][i + 2] == chessboard[row][i + 3] &&
                        chessboard[row][i + 3] == chessboard[row][i + 4] &&
                        chessboard[row][i] != 0) {
                    return chessboard[row][i];
                }
            }
            return 0;
        }

        public int checkColumn(int[][] chessboard, int col) {
            for (int i = 0; i < 16; i++) {  // 因为是五子棋，所以只需检查前17个位置
                if (chessboard[i][col] == chessboard[i + 1][col] &&
                        chessboard[i + 1][col] == chessboard[i + 2][col] &&
                        chessboard[i + 2][col] == chessboard[i + 3][col] &&
                        chessboard[i + 3][col] == chessboard[i + 4][col] &&
                        chessboard[i][col] != 0) {
                    return chessboard[i][col];
                }
            }
            return 0;
        }

        public int checkDiagonal(int[][] chessboard) {
            // 检查左上到右下对角线
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    if (chessboard[i][j] == chessboard[i + 1][j + 1] &&
                            chessboard[i + 1][j + 1] == chessboard[i + 2][j + 2] &&
                            chessboard[i + 2][j + 2] == chessboard[i + 3][j + 3] &&
                            chessboard[i + 3][j + 3] == chessboard[i + 4][j + 4] &&
                            chessboard[i][j] != 0) {
                        return chessboard[i][j];
                    }
                }
            }

            // 检查左下到右上对角线
            for (int i = 0; i < 16; i++) {
                for (int j = 4; j < 19; j++) {
                    if (chessboard[i][j] == chessboard[i + 1][j - 1] &&
                            chessboard[i + 1][j - 1] == chessboard[i + 2][j - 2] &&
                            chessboard[i + 2][j - 2] == chessboard[i + 3][j - 3] &&
                            chessboard[i + 3][j - 3] == chessboard[i + 4][j - 4] &&
                            chessboard[i][j] != 0) {
                        return chessboard[i][j];
                    }
                }
            }

            return 0;
        }


        public boolean getColor(){
        return chessColor;
        }

    public void reportColor(){
        if (chessColor) {
            chessColor = false;
        } else {
            chessColor = true;
        }
    }

    public void back(){

        if(this.Backnum>0) {
            if(!turnyou) {
                Vars.model.removeChess();
                Vars.chessPanel.repaint();
                Vars.controler.reportColor();
                turnyou=true;
            } else if (turnyou) {
                Vars.model.removeChess();
                Vars.model.removeChess();
                Vars.chessPanel.repaint();

            }
            Backnum--;
        }
    }

    public void NETback()
    {
        if(!turnyou) {
            Vars.model.removeChess();
            Vars.model.removeChess();
            Vars.chessPanel.repaint();

        } else if (turnyou) {


            Vars.model.removeChess();
            Vars.chessPanel.repaint();
            Vars.controler.reportColor();
            turnyou=false;
        }
    }



    public void Peace()
    {

    }

    public void NETPeace()
    {

    }











}


