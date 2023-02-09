package BoardGame;

import GameGUI.*;
import Elements.*;
import Game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Square extends JPanel {
    public final static int SIZE = (Board.x > Board.y) ? 690 / Board.x : 690 / Board.y; // /Board.SIZE

    private int xCoordinate;
    private int yCoordinate;
    Boolean color;
    private Board board;

    public void setIsEmpty(String isEmpty) {
        this.isEmpty = isEmpty;
    }

    public String getIsEmpty() {
        return isEmpty;
    }

    private String isEmpty = "empty";

    public Square(int xCoordinate, int yCoordinate, Boolean color, Board board) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.color = color;
        this.board = board;

        setBounds(xCoordinate * Square.SIZE, yCoordinate * Square.SIZE, Square.SIZE, Square.SIZE);

        if (color)
            setBackground(Color.white);

        else
            setBackground(Color.YELLOW);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SidePanel.btn) {
                    if (SidePanel.check.equalsIgnoreCase("bluePlayer")) {
                        Player bluePlayer = new Player(xCoordinate, yCoordinate, false, board, "blue");
                        Game.setP1(bluePlayer);
                        if (bluePlayer.addPiece()) {
                            SidePanel.comboBox.removeItemAt(SidePanel.comboBox.getSelectedIndex());
                        }
                    } else if (SidePanel.check.equalsIgnoreCase("redPlayer")) {
                        Player redPlayer = new Player(xCoordinate, yCoordinate, false, board, "red");
                        Game.setP2(redPlayer);
                        if (redPlayer.addPiece()) {
                            SidePanel.comboBox.removeItemAt(SidePanel.comboBox.getSelectedIndex());
                        }
                    } else if (SidePanel.check.equalsIgnoreCase("Star")) {
                        Star star = new Star(xCoordinate, yCoordinate, false, board);
                        star.addPiece();
                    } else if (SidePanel.check.equalsIgnoreCase("Wall")) {
                        Wall wall = new Wall(xCoordinate, yCoordinate, false, board);
                        wall.addPiece();
                    } else if (SidePanel.check.equalsIgnoreCase("SpeedLimit")) {
                        SpeedLimit speedLimit = new SpeedLimit(xCoordinate, yCoordinate, 1, false, board);
                        speedLimit.addPiece();
                    }
                    SidePanel.btn = false;
                }

                if(SidePanel.btnremove){
                    board.squares[xCoordinate][yCoordinate].removeAll();
                    board.repaint();
                    SidePanel.btnremove = false;
                    if (SidePanel.check.equalsIgnoreCase("Star")){
                        board.setStars(board.getStars() - 1);
                    }
                    if (SidePanel.check.equalsIgnoreCase("bluePlayer")){
                        SidePanel.comboBox.addItem("bluePlayer");
                    }

                }

            }
        });

    }


    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

}


