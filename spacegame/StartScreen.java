package spacegame;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class StartScreen extends JFrame {

    String[] args;
    String name = "";
    int score = 0;

    private JButton btnPlay, btnScores, btnExit;
    private JLabel jLabel4, jLabel5, jLabel6, jLabel7;
    private JLabel lblGameLogo;
    private JPanel pnlControls, pnlButtons;

    public StartScreen() {
        super("Space Game - Main Screen");
        this.setLocation(500, 200);
        this.setSize(335, 260);
        initComponents();
    }

    public StartScreen(String name, int score) {
        super("Space Game - Main Screen");
        this.setLocation(500, 200);
        this.setSize(335, 260);
        this.name = name;
        this.score = score;
        updateFile();
        initComponents();
    }

    public void updateFile() {
        try {
            File f = new File("\\SpaceGame\\Highscores.txt");
            FileWriter fw = new FileWriter(f, true); //boolean value true for appending to file
            PrintWriter pw = new PrintWriter(fw); //using the PrintWriter add more functionality than the Scanner

            pw.println(name + ", " + score);

            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "File Could Not Be Opened");
        }
    }

    public void initComponents() {
        lblGameLogo = new JLabel();
        pnlControls = new JPanel();
        pnlButtons = new JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        pnlControls.setBorder(BorderFactory.createTitledBorder("Controls"));
        pnlControls.setLayout(new GridLayout(4, 1));
        pnlButtons.setLayout(new GridLayout(3, 1, 0, 20));

        pnlButtons.setBorder(new EmptyBorder(10, 30, 10, 30)); //add padding for button panel

        lblGameLogo.setIcon(new ImageIcon("\\SpaceGame\\SpaceGameLogo.png"));

        btnPlay = new JButton("Play!");
        btnScores = new JButton("Highscores");
        btnExit = new JButton("Exit");

        jLabel4 = new JLabel("  Up Arrow - Start Engine  ");
        jLabel5 = new JLabel("    Left Arrow - Turn Left    ");
        jLabel6 = new JLabel("  Right Arrow - Turn Right  ");
        jLabel7 = new JLabel("    Hold Up - Speed Boost    ");

        pnlControls.add(jLabel4);
        pnlControls.add(jLabel5);
        pnlControls.add(jLabel6);
        pnlControls.add(jLabel7);

        pnlButtons.add(btnPlay);
        pnlButtons.add(btnScores);
        pnlButtons.add(btnExit);

        add(lblGameLogo, BorderLayout.NORTH);
        add(pnlControls, BorderLayout.EAST);
        add(pnlButtons, BorderLayout.CENTER);

        ActionHandler handler = new ActionHandler();
        btnPlay.addActionListener(handler);
        btnScores.addActionListener(handler);
        btnExit.addActionListener(handler);
    }

    private class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == btnPlay) {
                boolean pickLevel = false;
                String[] buttons = {
                    "Easy",
                    "Normal",
                    "Hard"
                };
                int returnValue = JOptionPane.showOptionDialog(null, "Select a Level:", "Select Difficulty",
                    JOptionPane.DEFAULT_OPTION, 3, null, buttons, buttons[2]);
                System.out.println(returnValue);

                if (returnValue == 0) {
                    SpaceGame.level = 3;
                    pickLevel = true;
                } else if (returnValue == 1) {
                    SpaceGame.level = 2;
                    pickLevel = true;
                } else if (returnValue == 2) {
                    SpaceGame.level = 1;
                    pickLevel = true;
                }

                if (pickLevel) {
                    SpaceGame SG = new SpaceGame(SpaceGame.level);
                    SG.main(args);

                    Frame frame = JOptionPane.getFrameForComponent((Component) event.getSource());
                    frame.dispose();
                }
            } else if (event.getSource() == btnScores) {
                ShowScores SS = new ShowScores();
                SS.setVisible(true);

                Frame frame = JOptionPane.getFrameForComponent((Component) event.getSource());
                frame.dispose();
            } else if (event.getSource() == btnExit) {
                System.exit(0);
            }
        }
    }

    public static void main(String args[]) {
        StartScreen screen = new StartScreen();
        screen.setVisible(true);
    }
}