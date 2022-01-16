//Stanley Shi

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;

public class ButtonClicker extends JFrame implements ActionListener {

    private final int GAP = 5;
    private final int SIZE = 500;
    private CardLayout programLayout;

    //JObjects for the Menu screen
    private JPanel menuPanel, menuCenterPanel;
    private JButton easy, medium, hard, custom, exit;
    private JLabel menuLabel;

    //JObjects for the Custom Game screen
    private JPanel customPanel, customCenterPanel;
    private JLabel customHeader, rowLabel, colLabel, errorLabel;
    private JTextField rowField, colField;
    private JButton start, back;

    //Instantiate the GamePanel
    GamePanel gameScreen;

    //JObjects for Game Over screen
    private JPanel gameOverPanel;
    private JLabel finalScore;
    private JButton menuScreen;

    //Default constructor
    public ButtonClicker() {
        super("Button Clicker");

        //Window parameters
        setSize(SIZE, SIZE);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        programLayout = new CardLayout();
        setLayout(programLayout);

        //Menu screen stuff
        menuPanel = new JPanel(new BorderLayout());

        //North section
        menuLabel = new JLabel("Button Clicker");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 36));
        menuLabel.setHorizontalAlignment(JLabel.CENTER);
        menuPanel.add(menuLabel, BorderLayout.NORTH);

        //Center section
        menuCenterPanel = new JPanel(new GridLayout(5, 1, GAP, GAP));

        easy = new JButton("Easy");
        easy.setFont(new Font("Arial", Font.BOLD, 24));
        medium = new JButton("Medium");
        medium.setFont(new Font("Arial", Font.BOLD, 24));
        hard = new JButton("Hard");
        hard.setFont(new Font("Arial", Font.BOLD, 24));
        custom = new JButton("Custom");
        custom.setFont(new Font("Arial", Font.BOLD, 24));
        exit = new JButton("Exit");
        exit.setFont(new Font("Arial", Font.BOLD, 24));

        menuCenterPanel.add(easy);
        menuCenterPanel.add(medium);
        menuCenterPanel.add(hard);
        menuCenterPanel.add(custom);
        menuCenterPanel.add(exit);

        menuPanel.add(menuCenterPanel, BorderLayout.CENTER);

        //Custom Game panel stuff
        customPanel = new JPanel(new BorderLayout());

        //North section
        customHeader = new JLabel("Custom Game Settings");
        customHeader.setFont(new Font("Arial", Font.BOLD, 24));
        customHeader.setHorizontalAlignment(JLabel.CENTER);
        customPanel.add(customHeader, BorderLayout.NORTH);

        //Center section
        customCenterPanel = new JPanel(new GridLayout(3, 2, GAP, GAP));

        rowLabel = new JLabel("Rows:");
        rowLabel.setFont(new Font("Arial", Font.BOLD, 24));
        rowLabel.setHorizontalAlignment(JLabel.RIGHT);

        colLabel = new JLabel("Columns:");
        colLabel.setFont(new Font("Arial", Font.BOLD, 24));
        colLabel.setHorizontalAlignment(JLabel.RIGHT);

        rowField = new JTextField(2);
        rowField.setFont(new Font("Arial", Font.BOLD, 24));

        colField = new JTextField(2);
        colField.setFont(new Font("Arial", Font.BOLD, 24));

        start = new JButton("Start"); //Starts the game
        start.setFont(new Font("Arial", Font.BOLD, 36));

        back = new JButton("Back"); //Goes back to the Menu screen
        back.setFont(new Font("Arial", Font.BOLD, 36));

        //Add JObjects to panel
        customCenterPanel.add(rowLabel);
        customCenterPanel.add(rowField);
        customCenterPanel.add(colLabel);
        customCenterPanel.add(colField);
        customCenterPanel.add(back);
        customCenterPanel.add(start);

        customPanel.add(customCenterPanel, BorderLayout.CENTER);

        //South section
        errorLabel = new JLabel(" "); //Displays errors related to user input
        errorLabel.setHorizontalAlignment(JLabel.LEFT);

        customPanel.add(errorLabel, BorderLayout.SOUTH);

        //Game Over panel stuff
        gameOverPanel = new JPanel(new FlowLayout());

        finalScore = new JLabel(""); //We'll set this label's text after the game ends
        finalScore.setFont(new Font("Arial", Font.BOLD, 48));
        finalScore.setHorizontalAlignment(JLabel.CENTER);
        menuScreen = new JButton("Main Menu");
        menuScreen.setFont(new Font("Arial", Font.BOLD, 36));
        menuScreen.setHorizontalAlignment(JLabel.CENTER);

        gameOverPanel.add(finalScore);
        gameOverPanel.add(menuScreen);

        //Add listners
        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);
        custom.addActionListener(this);
        exit.addActionListener(this);
        start.addActionListener(this);
        back.addActionListener(this);
        menuScreen.addActionListener(this);

        //Add each panel to the CardLayout
        add(menuPanel, "Main Menu");
        add(customPanel, "Custom");
        add(gameOverPanel, "Game Over");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        //Menu screen buttons
        if (source == easy) {
            //Creates an easy Game Panel and adds it to the CardLayout
            gameScreen = new GamePanel("Easy", 3, 3);
            add(gameScreen.gamePanel, "Game");
            //Goes to the Game screen
            programLayout.show(getContentPane(), "Game");
        } else if (source == medium) {
            //Creates a medium Game Panel
            gameScreen = new GamePanel("Medium", 4, 4);
            add(gameScreen.gamePanel, "Game");
            //Goes to the Game screen
            programLayout.show(getContentPane(), "Game");
        } else if (source == hard) {
            //Creates a hard Game Panel
            gameScreen = new GamePanel("Hard", 5, 5);
            add(gameScreen.gamePanel, "Game");
            //Goes to the Game screen
            programLayout.show(getContentPane(), "Game");
        } else if (source == custom) {
            programLayout.show(getContentPane(), "Custom");
        } else if (source == exit) {
            super.dispose();
        } //Custom Game screen buttons
        else if (source == back) {
            programLayout.show(getContentPane(), "Main Menu");
        } else if (source == start) {
            //Checks if the user input for rows and columns is valid
            if (rowField.getText().isEmpty() | colField.getText().isEmpty()) {
                errorLabel.setText("Error: Fill in both fields");
            } else if (Integer.parseInt(rowField.getText()) <= 0 | Integer.parseInt(colField.getText()) <= 0) {
                errorLabel.setText("Error: Number of rows and columns must be greater than 0");
            } else {
                //Creates a custom Game Panel and adds it to the CardLayout
                int row = Integer.parseInt(rowField.getText());
                int col = Integer.parseInt(colField.getText());

                gameScreen = new GamePanel("Custom", row, col);
                add(gameScreen.gamePanel, "Game");
                //Goes to the Game screen
                programLayout.show(getContentPane(), "Game");
            }
        } else if (source == menuScreen) {
            //Go to Main Menu
            programLayout.show(getContentPane(), "Main Menu");
        }
    }

    //Nested class
    //Constructs a JPanel for our CardLayout; used for every game difficulty
    private class GamePanel extends JPanel implements ActionListener {

        private String difficulty;
        private int score, numRow, numCol;

        //JObjects for the Game screen
        private JPanel gamePanel, gameCenterPanel;
        private JButton[][] buttonGrid;
        private JLabel scoreLabel;

        private GameTimer timer;
        private JProgressBar timerBar;

        public GamePanel(String difficulty, int row, int col) {
            this.difficulty = difficulty;
            numRow = row;
            numCol = col;
            score = 0;

            //Game panel stuff
            gamePanel = new JPanel(new BorderLayout());

            //Center section
            gameCenterPanel = new JPanel(new GridLayout(numRow, numCol, GAP, GAP));

            buttonGrid = new JButton[numCol][numRow];
            //Creates each button in the array
            for (int r = 0; r < numRow; r++) {
                for (int c = 0; c < numCol; c++) {
                    buttonGrid[c][r] = new JButton();
                    //Adds listener
                    buttonGrid[c][r].addActionListener(this);
                    //Adds to panel
                    gameCenterPanel.add(buttonGrid[c][r]);
                }
            }
            randomizeButtons(); //Randomizes colors of the buttons

            gamePanel.add(gameCenterPanel, BorderLayout.CENTER);

            //North section
            scoreLabel = new JLabel("Score: 0");
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 12));
            scoreLabel.setHorizontalAlignment(JLabel.CENTER);

            gamePanel.add(scoreLabel, BorderLayout.NORTH);

            //South section
            //Instantiate JObjects
            timerBar = new JProgressBar();
            createTimer();

            //Modify timerBar's parameters
            timerBar.setMinimum(0);
            timerBar.setMaximum(timer.getTimeRemaining());
            timerBar.setStringPainted(true); //Toggles displaying time left
            timerBar.setForeground(Color.GREEN);
            timerBar.setBackground(Color.WHITE);

            gamePanel.add(timerBar, BorderLayout.SOUTH);
        }

        //Instantiates a new timer
        public void createTimer() {
            timer = new GameTimer(score);
            timerBar.setMaximum(timer.getInitialTimeRemaining()); //Updates the timerBar's maximum

            //Executes the code within the TimerTask until the timer hits 0
            timer.schedule(new TimerTask() {
                public void run() {
                    //If time hits 0, trigger a game over
                    if (timer.getTimeRemaining() == 0) {
                        gameOver();
                    } //Else, count the timer down
                    else {
                        timer.countdown();
                        timerBar.setValue(timer.getTimeRemaining());
                        timerBar.setString("" + timer.getTimeRemaining());

                        double percentTimeRemaining = (double) timer.getTimeRemaining() / (double) timer.getInitialTimeRemaining();
                        //Changes the timer bar's color as time goes down
                        if (percentTimeRemaining > 0.66) {
                            timerBar.setForeground(Color.GREEN);
                        } else if (percentTimeRemaining > 0.33) {
                            timerBar.setForeground(Color.YELLOW);
                        } else {
                            timerBar.setForeground(Color.RED);
                        }
                    }
                }
            }, timer.getDelay(), timer.getPeriod());
        }

        //Restart the timer by creating a new one
        public void restartTimer() {
            timer.cancel(); //Stop old timer
            createTimer(); //Create a new timer
        }

        //Randomizes the color of the buttons; makes 1 green, and the rest red
        public void randomizeButtons() {
            //Makes all buttons red
            for (int r = 0; r < numRow; r++) {
                for (int c = 0; c < numCol; c++) {
                    buttonGrid[c][r].setBackground(Color.RED);
                }
            }

            //Makes a random button green
            int randRow = (int) (Math.random() * numRow);
            int randCol = (int) (Math.random() * numCol);
            buttonGrid[randCol][randRow].setBackground(Color.GREEN);
        }

        //Triggers a game over
        public void gameOver() {
            timer.cancel(); //Stops the timer
            finalScore.setText("Final Score: " + score); //Displays final score
            programLayout.show(getContentPane(), "Game Over"); //Go to the Game Over screen
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            //Create a copy of the source as a JButton so that we can check its background color
            JButton buttonSource = (JButton) source;

            //Clicking a red button gives a game over
            if (buttonSource.getBackground() == Color.RED) {
                gameOver();
            } //Clicking a green button gives a point and continues the game
            else if (buttonSource.getBackground() == Color.GREEN) {
                score++; //Increments score by 1
                scoreLabel.setText("Score: " + score); //Updates score
                gamePanel.add(scoreLabel, BorderLayout.NORTH); //Displays new score
                //Shuffle the buttons and restart the timer
                randomizeButtons();
                restartTimer();
            }
        }
    }
}
