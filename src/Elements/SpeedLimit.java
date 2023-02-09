package Elements;
import BoardGame.*;
import Game.*;

import javax.swing.*;

public class SpeedLimit extends Spot {
    private int limit;
    ImageIcon image;

    public SpeedLimit(int x, int y, int limit, boolean random, Board board) {
        setLimit(limit);
        setX(x);
        setY(y);
        setRandom(random);
        setBoard(board);
    }

    @Override
    public boolean addPiece(){
        if (getBoard().squares[getX()][getY()].getIsEmpty().equalsIgnoreCase(null) ||
                getBoard().squares[getX()][getY()].getIsEmpty().equalsIgnoreCase("empty")) {

            JTextField limitField = new JTextField();

            int reply = JOptionPane.showConfirmDialog(null, limitField, "please enter limit", JOptionPane.PLAIN_MESSAGE);
            if (reply == JOptionPane.OK_OPTION
                    && Integer.parseInt(limitField.getText()) <= Board.x
                    && Integer.parseInt(limitField.getText()) <=Board.y) {
                setLimit(Integer.parseInt(limitField.getText()));
            }
            else if(Integer.parseInt(limitField.getText()) > Board.x || Integer.parseInt(limitField.getText()) > Board.y){
                JOptionPane.showMessageDialog(null,"you can't set limit bigger than Board.x or Board.y","ERROR", JOptionPane.ERROR_MESSAGE);
                this.addPiece();
            }


            String limit = Integer.toString(this.limit);
            this.add(setImage(new ImageIcon("src/Photo/speedLimit.png")));
            getBoard().squares[getX()][getY()].add(this);
            getBoard().squares[getX()][getY()].validate();
            JLabel limitText = new JLabel(limit);
            limitText.setBounds(0, 0, 15, 15);
            getLabel().add(limitText);
            getBoard().validate();
            getBoard().squares[getX()][getY()].setIsEmpty("SpeedLimit");
            getBoard().speedLimits[getX()][getY()] = this.limit;
            return true;
        }
        else {
            if (getRandom()) {
                JOptionPane.showMessageDialog(null,"you can't add a piece on another piece","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
