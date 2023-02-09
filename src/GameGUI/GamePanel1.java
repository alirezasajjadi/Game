package GameGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel1 implements ActionListener {
    private JPanel mainPanel;

    private JButton buttonPlay,
            buttonGuid,
            buttonQuit;

    public GamePanel1() {
        mainPanel = new JPanel();
        mainPanel.setBounds(0,0,1100, 700);
        mainPanel.setLayout(new GridLayout(3, 1, 0, 10));
        mainPanel.setBackground(Color.lightGray);
        createButtons();
        mainPanel.add(buttonPlay);
        mainPanel.add(buttonGuid);
        mainPanel.add(buttonQuit);
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void createButtons() {

        buttonPlay = new JButton("play");
        buttonPlay.setFont(new Font("Didot",Font.PLAIN,140));
        buttonPlay.setBackground(Color.green);
        buttonPlay.addActionListener(this);
        buttonGuid = new JButton("how to play?");
        buttonGuid.setFont(new Font("Didot",Font.PLAIN,140));
        buttonGuid.setBackground(Color.gray);
        buttonGuid.addActionListener(this);
        buttonQuit = new JButton("quit");
        buttonQuit.setFont(new Font("Didot",Font.PLAIN,140));
        buttonQuit.setBackground(Color.red);
        buttonQuit.addActionListener(this);
    }
 public JButton getButtonPlay() {
     return buttonPlay;
 }

    public JButton getButtonGuid() {
        return buttonGuid;
    }

    public JButton getButtonQuit() {
        return buttonQuit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
