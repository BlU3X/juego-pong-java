package juegos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Juegos extends JPanel {

    Pelota pelota = new Pelota(this);
    Raqueta raqueta = new Raqueta(this);
    int speed = 1;

    private int getScore() {
        return speed - 1;
    }

    public Juegos() throws InterruptedException {

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                raqueta.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                raqueta.keyReleased(e);
            }
        });

        setFocusable(true);
        Sound.BACK.loop();

        JFrame frame = new JFrame("Mini Tennis");
        frame.add(this);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon(getClass().getResource("/ruta/a/icono.png")).getImage());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pelota.randPositions();

    }

    private void move() {
        pelota.move();
        raqueta.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        pelota.paint(g2d);
        raqueta.paint(g2d);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(getScore()), 10, 30);
    }

    public void gameOver() {
        Sound.BACK.stop();
        Sound.GAMEOVER.play();
        JOptionPane.showMessageDialog(this, "Tu puntuación es: " + getScore(), "Game Over", JOptionPane.YES_NO_OPTION);
        this.newGame();
    }

    public void newGame() {
        this.speed = 1;
        pelota.randPositions();
        pelota.xa = 1;
        pelota.ya = 1;
        Sound.BACK.loop();
    }

    public static void main(String[] args) throws InterruptedException {
        Juegos juegos = new Juegos();

        while (true) {
            juegos.move();
            juegos.repaint();
            Thread.sleep(10);
        }
    }

}
