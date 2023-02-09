package Elements;
import BoardGame.*;
import Game.*;
import javax.swing.*;

public class Star extends Spot {
    private static int count = 0;

    public Star(int x, int y, boolean random,Board board) {
        setX(x);
        setY(y);
        setRandom(random);
        setBoard(board);
    }

    @Override
    public boolean addPiece(){
        if (getBoard().squares[getX()][getY()].getIsEmpty().equalsIgnoreCase(null) || getBoard().squares[getX()][getY()].getIsEmpty().equalsIgnoreCase("empty")) {
            this.add(setImage(new ImageIcon("src/Photo/Star.png")));
            getBoard().setStars(getBoard().getStars() + 1);
            getBoard().squares[getX()][getY()].add(this);
            getBoard().squares[getX()][getY()].validate();
            getBoard().squares[getX()][getY()].setIsEmpty("Star");
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
