/*
* Author: Kody Ho
* Project 02
* ----------
* 10/20/25
*
* fair warning im new to javadoc so idk if im doing this right
* /

/**
* Massive Motion
* - Simulates celestial bodies (stars and comets) moving in space
* - i manually copied and pasted code from my original Massive Motion code bc i didnt see the git assignment and didnt realize we got a skeleton structure (so sorry if the code is a little messy)
*/


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import javax.swing.*;

public class MassiveMotion extends JPanel implements ActionListener {

    /**
     * declare important variables including properties, timer, list of bodies, and random number generator
     */
    protected Timer tm;
    private List<Body> bodies;
    private Random rand;

    private int windowX, windowY;
    private double genX, genY, bodySize, bodyMass, bodyVel;
    private double starX, starY, starSize, starMass, starVX, starVY;


    /**
     * Constructor that initializes the simulation based on a configuration file
     * @param configFile the path to the configuration file
     * fulfills Requirement 1
     */
    public MassiveMotion(String configFile) {
        // Load properties
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configFile)) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("couldn't load config: " + e.getMessage());
            System.exit(1);
        }

        windowX    = Integer.parseInt(props.getProperty("window_size_x"));
        windowY    = Integer.parseInt(props.getProperty("window_size_y"));
        int timerDelay = Integer.parseInt(props.getProperty("timer_delay"));

        starX      = Double.parseDouble(props.getProperty("star_position_x"));
        starY      = Double.parseDouble(props.getProperty("star_position_y"));
        starSize   = Double.parseDouble(props.getProperty("star_size"));
        starMass   = Double.parseDouble(props.getProperty("star_mass"));
        starVX     = Double.parseDouble(props.getProperty("star_velocity_x"));
        starVY     = Double.parseDouble(props.getProperty("star_velocity_y"));

        genX       = Double.parseDouble(props.getProperty("gen_x"));
        genY       = Double.parseDouble(props.getProperty("gen_y"));
        bodySize   = Double.parseDouble(props.getProperty("body_size"));
        bodyMass   = Double.parseDouble(props.getProperty("body_mass"));
        bodyVel    = Double.parseDouble(props.getProperty("body_velocity"));

        bodies = new MyArrayList<>();
        rand = new Random();

        // Add the star
        Body star = new Body(starX, starY, starSize, starMass, starVX, starVY, Color.RED);
        bodies.add(star);

        // Set up timer
        tm = new Timer(timerDelay, this);
        tm.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < bodies.size(); i++) {
            Body b = bodies.get(i);
            g.setColor(b.color);
            g.fillOval((int)b.x, (int)b.y, (int)b.size, (int)b.size);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update all bodies
        for (int i = 0; i < bodies.size(); i++) {
            bodies.get(i).update();
        }

        // Remove off-screen comets (not the star)
        Body star = bodies.get(0);
        for (int i = bodies.size() - 1; i >= 1; i--) { // iterate backwards to safely remove
            if (bodies.get(i).isOffScreen(windowX, windowY)) {
                bodies.remove(i);
            }
        }

        // Spawn new comets
        spawnComets();

        repaint();
    }

    /**
     * Spawns comets at the edges of the window
     * fulfills Requirement 4
     */
    private void spawnComets() {
        Body star = bodies.get(0);
        boolean spawned = false;

        // Top/bottom spawn
        if (rand.nextDouble() < genX) {
            double x = rand.nextDouble() * windowX;
            double y = rand.nextBoolean() ? 0 : windowY;
            double vx = (rand.nextDouble() * 2 * bodyVel - bodyVel);
            double vy = (y == 0) ? rand.nextDouble() * bodyVel : -rand.nextDouble() * bodyVel;
            if (vx == 0) vx = bodyVel / 2;
            bodies.add(new Body(x, y, bodySize, bodyMass, vx, vy, Color.BLACK));
            spawned = true;
        }

        // Left/right spawn
        if (rand.nextDouble() < genY) {
            double y = rand.nextDouble() * windowY;
            double x = rand.nextBoolean() ? 0 : windowX;
            double vy = (rand.nextDouble() * 2 * bodyVel - bodyVel);
            double vx = (x == 0) ? rand.nextDouble() * bodyVel : -rand.nextDouble() * bodyVel;
            if (vy == 0) vy = bodyVel / 2;
            bodies.add(new Body(x, y, bodySize, bodyMass, vx, vy, Color.BLACK));
            spawned = true;
        }

        if (spawned) {
            System.out.println("Spawned comet. Total bodies: " + bodies.size());
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("expected config file as argument");
            System.exit(1);
        }

        MassiveMotion mm = new MassiveMotion(args[0]);
        /**
         * Set up JFrame for the simulation
         * This is the main window where the simulation will be displayed
         * Fulfills Requirement 3
         */
        JFrame jf = new JFrame("Massive Motion");
        jf.setSize(mm.windowX, mm.windowY);
        jf.add(mm);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
