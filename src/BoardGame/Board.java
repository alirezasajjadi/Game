package BoardGame;
import javax.swing.*;
import Elements.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Board extends JPanel {
    public static int x;
    public static int y;
    private int limit;
    private int stars = 0;
    public int[][] speedLimits;
    public Square[][] squares;

    public Square[][] getSquares() {
        return squares;
    }

    public Board() {
        JTextField xField = new JTextField();
        JTextField yField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(2, 0, 5, 5));
        panel.add(new JLabel("please enter width:"));
        panel.add(xField);
        panel.add(Box.createHorizontalStrut(2));
        panel.add(new JLabel("please enter height:"));
        panel.add(yField);
        panel.add(Box.createHorizontalStrut(2));

        int reply = JOptionPane.showConfirmDialog(null, panel, "please enter the width of board", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (reply == JOptionPane.OK_OPTION) {
            x = Integer.parseInt(xField.getText());
            y = Integer.parseInt(yField.getText());
        } else {
            System.exit(0);
        }
        squares = new Square[x][y];
        setY(y);
        setX(x);
        setLimit(Math.max(x, y));
        speedLimits = new int[x][y];
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                speedLimits[i][j] = Math.max(x, y);
            }
        }
        createBoard();
    }


    public void createBoard() {
        this.setSize(x * Square.SIZE + 30 , y * Square.SIZE);
        this.setBackground(Color.lightGray);
        this.setLayout(null);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                squares[i][j] = new Square(i, j, (i + j) % 2 == 0, this);
                this.add(squares[i][j]);
            }
        }
    }


    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public int getStars() {
        return stars;
    }

    public void setX(int x) {
        Board.x = x;
    }

    public void setY(int y) {
        Board.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void reset() {
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                squares[i][j].removeAll();
                squares[i][j].setIsEmpty("empty");
            }
        }
        this.repaint();
        setStars(0);
    }
}