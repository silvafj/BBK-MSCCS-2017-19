package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import static game.Constants.MAX_BONUS;
import static gui.Constants.ROOT;

public class OptionsPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private static double MIN_SPEED = 0.05;        //The minimum speed for the game (seconds per move)
    private static double MAX_SPEED = 0.85;        //The maximum speed for the game (seconds per move)
    long seed;
    private JSlider speedSelect;
    private JProgressBar timeRemaining;
    private JButton showSeed;
    private JLabel speedLabel;                //Description for speed slider
    private JLabel bonusLabel;                //Shows the bonus multiplier
    private JLabel coinsLabel;                //Shows number of coins we have
    private JLabel timeRemainingLabel;        //Shows the amount of time we have left
    private JLabel scoreLabel;                //Shows coins multiplied by bonus factor
    private BufferedImage background;        //Background for options pane
    private String BACKGROUND_PATH = ROOT + "info_texture.png";            //Location of background image

    /**
     * Constructor: an instance
     */
    public OptionsPanel(int x, int y, int width, int height, long seed) {
        /** The slider is used to provide a value, in seconds per move, for the speed at which the
         * character moves. The min and max values are defined as MIN_SPEED and MAX_SPEED, respectively.
         * In order to even out the scaling of speed, the actual speed s is defined relative to the
         * slider value v as follows: s = 10^(-v/1000)
         */
        int lowVal = (int) (Math.log10(MAX_SPEED) * -1000);
        int highVal = (int) (Math.log10(MIN_SPEED) * -1000);
        int startVal = (int) (-1000 * Math.log10((double) GUI.getFramesPerMove() / GUI.getFramesPerSecond()));
        speedSelect = new JSlider(JSlider.HORIZONTAL, lowVal, highVal, startVal);
        speedSelect.addChangeListener((e) -> GUI.setFramesPerMove((int) (GUI.getFramesPerSecond() * Math.pow(10, -(double) speedSelect.getValue() / 1000.0))));
        ;

        timeRemaining = new JProgressBar(0, 100);
        this.seed = seed;

        speedLabel = new JLabel("Speed:");
        bonusLabel = new JLabel("Bonus: " + MAX_BONUS);
        coinsLabel = new JLabel("Coins: 0");
        scoreLabel = new JLabel("Score: 0");
        timeRemainingLabel = new JLabel("Time Remaining: 0");

        setLayout(new GridLayout(6, 1));

        JPanel sliderPanel = new JPanel();
        sliderPanel.add(speedLabel);
        sliderPanel.add(speedSelect);
        sliderPanel.setOpaque(false);

        JPanel timeRemainingPanel = new JPanel();
        timeRemainingPanel.add(timeRemainingLabel);
        timeRemainingPanel.add(timeRemaining);
        timeRemainingPanel.setOpaque(false);

        JPanel showSeedPanel = new JPanel();
        showSeed = new JButton("Print seed");
        showSeed.addActionListener(this);
        showSeedPanel.setOpaque(false);
        showSeedPanel.add(showSeed);

        bonusLabel.setHorizontalAlignment(JLabel.CENTER);
        coinsLabel.setHorizontalAlignment(JLabel.CENTER);
        timeRemainingLabel.setHorizontalAlignment(JLabel.CENTER);
        speedLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        add(sliderPanel);
        add(timeRemainingPanel);
        add(coinsLabel);
        add(bonusLabel);
        add(scoreLabel);
        add(showSeedPanel);

        setBounds(x, y, width, height);

        //Load content
        try {
            background = ImageIO.read(new File(BACKGROUND_PATH));
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't find input file : " + e.toString());
        }
    }

    /**
     * Update bonus multiplier b as displayed by the GUI
     */
    public void updateBonus(double b) {
        DecimalFormat df = new DecimalFormat("#.##");
        bonusLabel.setText("Bonus: " + df.format(b));
    }

    /**
     * Update the number of coins c picked up as displayed on the GUI.
     * Score is the current player's score.
     */
    public void updateCoins(int c, int score) {
        coinsLabel.setText("Coins: " + c);
        scoreLabel.setText("Score: " + score);
    }

    /**
     * Update the time t remaining (before the cavern collapses) as displayed on the GUI.
     */
    public void updateTimeRemaining(int t) {
        timeRemainingLabel.setText("Time Remaining: " + t);
        timeRemaining.setValue(t);
    }

    /**
     * Update the maximum time remaining, m, for this stage.
     */
    public void updateMaxTimeRemaining(int m) {
        timeRemaining.setMaximum(m);
    }

    /**
     * Paint the commponent
     */
    @Override
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        page.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showSeed) {
            System.out.println("Seed : " + seed);
        }
    }
}
