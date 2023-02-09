package Elements;
import BoardGame.*;
import Game.*;
import javax.swing.*;
import java.awt.*;

public class Player extends Spot {
    public static int count = 0;
    private int point = 0;
    private String color;

    public Player(int x, int y, boolean random,Board board, String color) {
        setX(x);
        setY(y);
        setRandom(random);
        setBoard(board);
        setColor(color);
    }
    public Player() {

    }

    @Override
    public boolean addPiece() {
        if (getBoard().squares[getX()][getY()].getIsEmpty().equalsIgnoreCase(null) || getBoard().squares[getX()][getY()].getIsEmpty().equalsIgnoreCase("empty")) {
            if (color.equalsIgnoreCase("red"))
                this.add(setImage(new ImageIcon("src/Photo/redPlayer.png")));
            else
                this.add(setImage(new ImageIcon("src/Photo/bluePlayer.png")));

            getBoard().squares[getX()][getY()].add(this);
            getBoard().squares[getX()][getY()].validate();
            getBoard().squares[getX()][getY()].setIsEmpty(color);
            return true;
        }
        else {
            if (getRandom()) {
                JOptionPane.showMessageDialog(null,"you can't add a piece on another piece","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
            return color;
    }

    public int getPoint() {
        return point;
    }

    public static void setCount(int count) {
        Player.count = count;
    }

    public static void nextRound() {
        Player.count++;
    }
}
