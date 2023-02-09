package Game;
import BoardGame.*;
import Elements.*;
import javax.swing.*;

public class Move {
    private final int x;
    private final int y;
    private final Player presentPlayer;
    private final Board board;

    public Move(Player player1, int x, int y, Board board) {
        this.presentPlayer = player1;
        this.board = board;
        this.x = x;
        this.y = y;
    }

    boolean moving() {
        if (x == presentPlayer.getX() && y != presentPlayer.getY()) {
            if (y < presentPlayer.getY()) {
                if (presentPlayer.getY() - y > board.getLimit()) {
                    JOptionPane.showMessageDialog(null,
                            "bigger than limit the other player catch",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return true;
                }
                boolean wall = false;
                for (int i = y; i < presentPlayer.getY(); i++) {
                    if (board.squares[x][i].getIsEmpty().equalsIgnoreCase("Wall")) {
                        JOptionPane.showMessageDialog(null,
                                "you can't cross over the wall or stay on wall",
                                "error",
                                JOptionPane.ERROR_MESSAGE);
                        wall = true;
                        break;
                    }
                }
                if (!wall) {
                    for (int i = y; i < presentPlayer.getY(); i++) {
                        starAndSpeedLimitCheck(i, x);
                    }
                    changePLayerPlace();
                }
                return wall;
            } else if (y > presentPlayer.getY()) {
                if (y - (presentPlayer.getY()) > board.getLimit()) {
                    JOptionPane.showMessageDialog(null,
                            "bigger than limit the other player catch",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return true;
                }
                boolean wall = false;
                for (int i = presentPlayer.getY(); i <= y; i++) {
                    if (board.squares[x][i].getIsEmpty().equalsIgnoreCase("Wall")) {
                        JOptionPane.showMessageDialog(null,
                                "you can't cross over the wall or stay on wall",
                                "error",
                                JOptionPane.ERROR_MESSAGE);
                        wall = true;
                        break;
                    }
                }
                if (!wall) {
                    for (int i = presentPlayer.getY(); i <= y; i++) {
                        starAndSpeedLimitCheck(i, x);
                    }
                    changePLayerPlace();
                }
                return wall;
            }
        } else if (y == presentPlayer.getY() && x != presentPlayer.getX()) {
            if (x < presentPlayer.getX()) {
                if (presentPlayer.getX() - x > board.getLimit()) {
                    JOptionPane.showMessageDialog(null,
                            "bigger than limit the other player catch",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return true;
                }
                boolean wall = false;
                for (int i = x; i < presentPlayer.getX(); i++) {
                    if (board.squares[i][y].getIsEmpty().equalsIgnoreCase("Wall")) {
                        JOptionPane.showMessageDialog(null,
                                "you can't cross over the wall or stay on wall",
                                "error",
                                JOptionPane.ERROR_MESSAGE);
                        wall = true;
                        break;
                    }
                }
                if (!wall) {
                    for (int i = x; i < presentPlayer.getX(); i++) {
                        starAndSpeedLimitCheck(y, i);
                    }
                    changePLayerPlace();
                }
                return wall;
            }
            if (x > presentPlayer.getX()) {
                if (x - (presentPlayer.getX()) > board.getLimit()) {
                    JOptionPane.showMessageDialog(null,
                            "bigger than limit the other player catch",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return true;
                }
                boolean wall = false;
                for (int i = presentPlayer.getX(); i <= x; i++) {
                    if (board.squares[i][y].getIsEmpty().equalsIgnoreCase("Wall")) {
                        JOptionPane.showMessageDialog(null,
                                "you can't cross over the wall or stay on wall",
                                "error",
                                JOptionPane.ERROR_MESSAGE);
                        wall = true;
                        break;
                    }
                }
                if (!wall) {
                    for (int i = presentPlayer.getX(); i <= x; i++) {
                        starAndSpeedLimitCheck(y, i);
                    }
                    changePLayerPlace();
                }
                return wall;
            }
        } else {
            return true;
        }
        return true;
    }

    private void changePLayerPlace() {
        board.squares[presentPlayer.getX()][presentPlayer.getY()].setIsEmpty("Empty");
        board.squares[presentPlayer.getX()][presentPlayer.getY()].remove(presentPlayer);
        board.squares[x][y].setIsEmpty(presentPlayer.getColor());
        board.squares[x][y].add(presentPlayer);
        board.repaint();
        board.revalidate();
        presentPlayer.setX(x);
        presentPlayer.setY(y);
    }

    private void starAndSpeedLimitCheck(int i, int x) {
        if (board.squares[x][i].getIsEmpty().equalsIgnoreCase("Star")) {
            presentPlayer.setPoint(presentPlayer.getPoint() + 1);
            board.setStars(board.getStars() - 1);
            board.squares[x][i].setIsEmpty("Empty");
            board.squares[x][i].removeAll();
        }
        if (board.squares[x][i].getIsEmpty().equalsIgnoreCase("SpeedLimit")) {
            board.setLimit(board.speedLimits[x][i]);
            board.squares[x][i].setIsEmpty("Empty");
            board.squares[x][i].removeAll();
        }
    }


}
