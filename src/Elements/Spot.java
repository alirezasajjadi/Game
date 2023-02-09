package Elements;
import BoardGame.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Spot extends JPanel {
    private int x;
    private int y;
    private boolean random;
    private Board board;
    private JLabel label;

    public JLabel setImage(ImageIcon icon) {
        label = new JLabel();
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(Square.SIZE, Square.SIZE, Image.SCALE_SMOOTH);
        ImageIcon iconScale = new ImageIcon(imgScale);
        label.setBounds(getX() * Square.SIZE, getY() * Square.SIZE, Square.SIZE, Square.SIZE);
        label.setIcon(iconScale);
        return label;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public boolean addPiece() {
        return false;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public boolean getRandom() {
        return !random;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
