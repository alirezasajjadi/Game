package Game;
import BoardGame.*;
import Elements.*;
import GameGUI.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Game extends JFrame {

    Board board;
    SidePanel sidePanel;
    Square[][] square;
    static Player p1;
    static Player p2;
    static int xCoordinate;
    static int yCoordinate;
    boolean doneBTN = false;
    static int limit = 0;
    static boolean forPlayer1;
    static boolean forPlayer2;
    public static int nextX;
    public static int nextY;

    public Game() {
        p1 = new Player();
        p2 = new Player();
        this.setSize(new Dimension(1100, 740));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.lightGray);
    }

    public void run() {
        this.repaint();
        this.revalidate();
        this.validate();
        this.setSize(new Dimension(1100, 740));
        GamePanel1 gamePanel = new GamePanel1();
        this.add(gamePanel.getMainPanel());
        this.validate();

        gamePanel.getButtonPlay().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play();
            }
        });
        gamePanel.getButtonGuid().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guide();
            }
        });
        gamePanel.getButtonQuit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    void play() {
        this.getContentPane().removeAll();
        this.getContentPane().repaint();
        this.getContentPane().revalidate();

        board = new Board();
        this.getContentPane().add(board);
        this.repaint();
        this.revalidate();
        square = board.getSquares();

        sidePanel = new SidePanel();
        this.getContentPane().add(sidePanel);
        this.repaint();
        this.revalidate();

        sidePanel.getDoneButton().addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                Game.this.setSize(Board.x * Square.SIZE + 30, Board.y * Square.SIZE + 60);

                Game.this.doneBTN = true;
                for (int i = 0; i < Board.x; i++) {
                    for (int j = 0; j < Board.y; j++) {
                        if (square[i][j].getIsEmpty() == null)
                            square[i][j].setIsEmpty("empty");
                    }
                }
                for (int i = 0; i < Board.x; i++) {
                    for (int j = 0; j < Board.y; j++) {
                        square[i][j].addMouseListener(new nextCoordinate(i, j, board));
                    }
                }
            }
        });

        sidePanel.getRandomButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField starsField = new JTextField();
                JTextField wallsField = new JTextField();
                JTextField speedLimitsField = new JTextField();

                JPanel panel = new JPanel(new GridLayout(3, 0, 5, 5));
                panel.add(new JLabel("please enter number of stars:"));
                panel.add(starsField);
                //    panel.add(Box.createHorizontalStrut(2));
                panel.add(new JLabel("please enter number of walls:"));
                panel.add(wallsField);
                //    panel.add(Box.createHorizontalStrut(2));
                panel.add(new JLabel("please enter number of speedLimit:"));
                panel.add(speedLimitsField);
                //     panel.add(Box.createHorizontalStrut(2));

                int reply = JOptionPane.showConfirmDialog(null, panel, "random details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (reply == JOptionPane.OK_OPTION) {
                    int star = Integer.parseInt(starsField.getText());
                    int wall = Integer.parseInt(wallsField.getText());
                    int speedLimit = Integer.parseInt(speedLimitsField.getText());
                    //should be changed
                    if ((star + wall + speedLimit + 2) <= (board.getX() * board.getY())) {
                        SidePanel.comboBox.removeItemAt(SidePanel.comboBox.getSelectedIndex());
                        SidePanel.comboBox.removeItemAt(SidePanel.comboBox.getSelectedIndex());
                        random(board, p1, p2, star, wall, speedLimit);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"your board is too small!","ERROR",JOptionPane.ERROR_MESSAGE);
                        actionPerformed(e);
                    }
                }
            }
        });
        sidePanel.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.reset();
                SidePanel.comboBox.removeItemAt(SidePanel.comboBox.getSelectedIndex());
                SidePanel.comboBox.removeItemAt(SidePanel.comboBox.getSelectedIndex());
                SidePanel.comboBox.removeItemAt(SidePanel.comboBox.getSelectedIndex());
                SidePanel.comboBox.addItem("bluePlayer");
                SidePanel.comboBox.addItem("redPlayer");
                SidePanel.comboBox.addItem("Star");
                SidePanel.comboBox.addItem("Wall");
                SidePanel.comboBox.addItem("SpeedLimit");
            }
        });
    }

    public static void start(Board board, Player player1, Player player2, int nextX, int nextY) {
        if (Player.count % 2 == 0) {
            // JOptionPane.showMessageDialog(null,"player one's turn","turn",JOptionPane.INFORMATION_MESSAGE);
            Move move = new Move(player1, nextX, nextY, board);
            forPlayer1 = move.moving();
            if (!(forPlayer1)) {
                Player.nextRound();
            }
            if (limit == 2 && !(forPlayer1)) {
                board.setLimit(Math.max(board.getX(), board.getY()));
                limit = 0;
            }
            if (board.getLimit() != Math.max(board.getX(), board.getY()) && !(forPlayer1)) {
                limit = 1;
            }
        } else {
            //JOptionPane.showMessageDialog(null,"player two's turn","turn",JOptionPane.INFORMATION_MESSAGE);
            Move move = new Move(player2, nextX, nextY, board);
            forPlayer2 = move.moving();
            if (!(forPlayer2)) {
                Player.nextRound();
            }
            if (limit == 1 && !(forPlayer2)) {
                board.setLimit(Math.max(board.getX(), board.getY()));
                limit = 0;
            }
            if (board.getLimit() != Math.max(board.getX(), board.getY()) && !(forPlayer2)) {
                limit = 2;
            }
        }
        System.out.println(limit);
    }

    public static void setXCoordinate(int xCoordinate) {
        Game.xCoordinate = xCoordinate;
    }

    public static void setYCoordinate(int yCoordinate) {
        Game.yCoordinate = yCoordinate;
    }

    public static void setP2(Player p2) {
        Game.p2 = p2;
    }

    public static void setP1(Player p1) {
        Game.p1 = p1;
    }


    void checkStockProblemForStars(Board board, Player player1, Player player2, boolean random, int star, int wall, int speedLimit) {
        for (int i = 0; i < board.getX(); i++) {
            for (int j = 0; j < board.getY(); j++) {
                if (board.squares[i][j].getIsEmpty().equalsIgnoreCase("star")) {
                    checkStockProblem(board, player1, player2, random, i, j, star, wall, speedLimit);
                }
            }
        }
    }

    void checkStockProblemForSpeedLimits(Board board, Player player1, Player player2, boolean random, int star, int wall, int speedLimit) {
        for (int i = 0; i < board.getX(); i++) {
            for (int j = 0; j < board.getY(); j++) {
                if (board.squares[i][j].getIsEmpty().equalsIgnoreCase("speedLimit")) {
                    checkStockProblem(board, player1, player2, random, i, j, star, wall, speedLimit);
                }
            }
        }
    }

    private void checkStockProblem(Board board, Player player1, Player player2, boolean random, int i, int j, int star, int wall, int speedLimit) {
        if (i == 0 && j == 0) {
            if (board.squares[i][j + 1].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i + 1][j].getIsEmpty().equalsIgnoreCase("wall")) {
                randomCheck(board, player1, player2, random, star, wall, speedLimit);
            }
        }
        if (i >= 1 && i <= board.getX() - 2 && j == 0) {
            if (board.squares[i - 1][j].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i + 1][j].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i][j + 1].getIsEmpty().equalsIgnoreCase("wall")) {
                randomCheck(board, player1, player2, random, star, wall, speedLimit);
            }
        }
        if (i >= 1 && i <= board.getX() - 2 && j >= 1 && j <= board.getY() - 2) {
            if (board.squares[i - 1][j].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i + 1][j].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i][j - 1].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i][j + 1].getIsEmpty().equalsIgnoreCase("wall")) {
                randomCheck(board, player1, player2, random, star, wall, speedLimit);
            }
        }
        if (i == board.getX() - 1 && j == 0) {
            if (board.squares[i - 1][j].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i][j + 1].getIsEmpty().equalsIgnoreCase("wall")) {
                randomCheck(board, player1, player2, random, star, wall, speedLimit);
            }
        }
        if (i == 0 && j >= 1 && j <= board.getY() - 2) {
            if (board.squares[i][j - 1].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i][j + 1].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i + 1][j].getIsEmpty().equalsIgnoreCase("wall")) {
                randomCheck(board, player1, player2, random, star, wall, speedLimit);
            }
        }
        if (i == board.getX() - 1 && j >= 1 && j <= board.getY() - 2) {
            if (board.squares[i][j - 1].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i][j + 1].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i - 1][j].getIsEmpty().equalsIgnoreCase("wall")) {
                randomCheck(board, player1, player2, random, star, wall, speedLimit);
            }
        }
        if (i == 0 && j == board.getY() - 1) {
            if (board.squares[i + 1][j].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i][j - 1].getIsEmpty().equalsIgnoreCase("wall")) {
                randomCheck(board, player1, player2, random, star, wall, speedLimit);
            }
        }
        if (i >= 1 && i <= board.getX() - 2 && j == board.getY() - 1) {
            if (board.squares[i - 1][j].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i + 1][j].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i][j - 1].getIsEmpty().equalsIgnoreCase("wall")) {
                randomCheck(board, player1, player2, random, star, wall, speedLimit);
            }
        }
        if (i == board.getX() - 1 && j == board.getY() - 1) {
            if (board.squares[i - 1][j].getIsEmpty().equalsIgnoreCase("wall") && board.squares[i][j - 1].getIsEmpty().equalsIgnoreCase("wall")) {
                randomCheck(board, player1, player2, random, star, wall, speedLimit);
            }
        }
    }

    private void randomCheck(Board board, Player player1, Player player2, boolean random, int star, int wall, int speedLimit) {
        if (!random) {
            JOptionPane.showMessageDialog(null, "Your board has problem,Set Again !", "ERROR", JOptionPane.ERROR_MESSAGE);
            play();
            System.out.println("sdfa");
        } else {
            random(board, player1, player2, star, wall, speedLimit);
            System.out.println("1234a");
        }
    }

    void guide() {
        JFrame frame = new JFrame("main");
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        ImageIcon ii = new ImageIcon("src/Photo/guide.jpg");
        JLabel lable = new JLabel(ii);
        JScrollPane jsp = new JScrollPane(lable);
        frame.getContentPane().add(jsp);
        frame.setBounds(10, 10, 1000, 730);
        frame.setVisible(true);

    }

    void random(Board board, Player player1, Player player2, int star, int wall, int speedLimit) {
        board.reset();
        int stars;
        int walls;
        int speedLimits;
        int p1 = 1;
        int p2 = 1;
        stars = star;
        walls = wall;
        speedLimits = speedLimit;
        while (stars > 0) {
            Random r1 = new Random();
            Star star1 = new Star(r1.nextInt(board.getX()), r1.nextInt(board.getY()), true, board);
            if (star1.addPiece()) {
                stars--;
            }
        }
        while (walls > 0) {
            Random r1 = new Random();
            Wall wall1 = new Wall(r1.nextInt(board.getX()), r1.nextInt(board.getY()), true, board);
            if (wall1.addPiece()) {
                walls--;
            }
        }
        while (speedLimits > 0) {
            Random r1 = new Random();
            SpeedLimit speedLimit1 = new SpeedLimit(r1.nextInt(board.getX()), r1.nextInt(board.getY()), r1.nextInt(board.getLimit()), true, board);
            if (speedLimit1.addPiece()) {
                speedLimits--;
            }
        }
        playerCountCheck(board, player1, p1, "blue");
        playerCountCheck(board, player2, p2, "red");
        checkStockProblemForSpeedLimits(board, player1, player2, true, star, wall, speedLimit);
        checkStockProblemForStars(board, player1, player2, true, star, wall, speedLimit);
    }

    private void playerCountCheck(Board board, Player player1, int p1, String color) {
        while (p1 > 0) {
            Random r1 = new Random();
            player1.setX(r1.nextInt(board.getX()));
            player1.setY(r1.nextInt(board.getY()));
            player1.setBoard(board);
            player1.setRandom(true);
            player1.setColor(color);
            if (player1.addPiece()) {
                p1--;
            }
        }
    }

    public void end(Player player1, Player player2) {
        if (player1.getPoint() > player2.getPoint()) {
            JOptionPane.showMessageDialog(null,
                    String.format("PlayerBlue won the game, the points is : %d", player1.getPoint()),
                    "END GAME",
                    JOptionPane.PLAIN_MESSAGE);
        } else if (player1.getPoint() < player2.getPoint()) {
            JOptionPane.showMessageDialog(null,
                    String.format("PlayerRed won the game, the points is : %d", player2.getPoint()),
                    "END GAME",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    String.format("the game is draw, the points is : %d", player2.getPoint()),
                    "END GAME",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    class nextCoordinate implements MouseListener {
        private int xCoordinate;
        private int yCoordinate;
        private Board board;

        nextCoordinate(int x, int y, Board board) {
            this.xCoordinate = x;
            this.yCoordinate = y;
            this.board = board;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Game.nextX = xCoordinate;
            Game.nextY = yCoordinate;
            if (board.getStars() > 0 && !board.squares[Game.nextX][Game.nextY].getIsEmpty().equalsIgnoreCase("blue") && !board.squares[Game.nextX][Game.nextY].getIsEmpty().equalsIgnoreCase("red")) {
                Game.start(board, Game.p1, Game.p2, Game.nextX, Game.nextY);
                if (board.getStars() == 0) {
                    end(p1, p2);
                    board.removeAll();
                    board.revalidate();
                    run();
                }
            } else {
                if (board.getStars() != 0) {
                    JOptionPane.showMessageDialog(null, "you can't go to another player", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}


