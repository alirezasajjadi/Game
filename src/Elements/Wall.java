package Elements;
import BoardGame.*;
import Game.*;
import javax.swing.*;

public class Wall extends Spot {


    public Wall(int x, int y, boolean random,Board board) {
        setY(y);
        setX(x);
        setRandom(random);
        setBoard(board);
    }
    @Override
    public boolean addPiece(){
        if (getBoard().squares[getX()][getY()].getIsEmpty().equalsIgnoreCase(null) || getBoard().squares[getX()][getY()].getIsEmpty().equalsIgnoreCase("empty")) {
            this.add(setImage(new ImageIcon("src/Photo/wall.png")));
            getBoard().squares[getX()][getY()].add(this);
            getBoard().squares[getX()][getY()].validate();
            getBoard().squares[getX()][getY()].setIsEmpty("Wall");
            return true;
        }
        else {
            if (getRandom()) {
                JOptionPane.showMessageDialog(null,"you can't add a piece on another piece","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }
}
