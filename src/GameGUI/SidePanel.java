package GameGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePanel extends JPanel {

    private JButton randomButton;
    public static JComboBox<String> comboBox;
    private JButton addButton;
    private JButton resetButton;
    private JButton doneButton;
    private JLabel label;
    public static boolean btn = false;
    public static String check;

    private JButton removebutton;
public static boolean btnremove = false;

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDoneButton() {
        return doneButton;
    }

    public JButton getRandomButton() {
        return randomButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public SidePanel() {
        this.setBounds(750, 0, 380, 700);
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        createSidePanel();
    }

    public void createSidePanel() {
        String[] piecs = {"bluePlayer", "redPlayer", "Star", "Wall", "SpeedLimit"};
        comboBox = new JComboBox<>(piecs);
        comboBox.setFont(new Font("Dido", Font.BOLD, 20));
        comboBox.setBounds(0, 100, 200, 30);
        this.add(comboBox);
        addButton = new JButton("ADD");
        addButton.setFont(new Font("Dido", Font.PLAIN, 20));
        addButton.setBounds(220, 100, 100, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn = true;
                String pieceName = comboBox.getItemAt(comboBox.getSelectedIndex());
                if (pieceName.equalsIgnoreCase("bluePlayer")) {
                     check = "bluePlayer";
                } else if (pieceName.equalsIgnoreCase("redPlayer")) {
                    check = "redPlayer";
                } else if (pieceName.equalsIgnoreCase("Star")) {
                    check = "Star";
                } else if (pieceName.equalsIgnoreCase("Wall")) {
                    check = "Wall";
                } else if (pieceName.equalsIgnoreCase("SpeedLimit")) {
                    check = "SpeedLimit";
                }
            }
        });

        doneButton = new JButton("DONE");
        doneButton.setFont(new Font("Dido", Font.PLAIN, 20));
        doneButton.setBounds(90, 500, 110, 70);
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SidePanel.this.removeAll();
                SidePanel.this.repaint();
                SidePanel.this.revalidate();
                SidePanel.this.setEnabled(false);

            }
        });


        randomButton = new JButton("RANDOM");
        randomButton.setFont(new Font("Dido", Font.PLAIN, 15));
        randomButton.setBounds(30, 200, 110, 70);

        resetButton = new JButton("RESET");
        resetButton.setFont(new Font("Dido", Font.PLAIN, 15));
        resetButton.setBounds(150, 200, 110, 70);

        removebutton = new JButton("re");
        removebutton.setFont(new Font("Dido", Font.PLAIN, 15));
        removebutton.setBounds(100,300,110,70);
        removebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnremove = true;
            }
        });

        this.add(removebutton);
        this.add(resetButton);
        this.add(randomButton);
        this.add(doneButton);
        this.add(addButton);
    }

    public String getCheck() {
        return check;
    }
}
