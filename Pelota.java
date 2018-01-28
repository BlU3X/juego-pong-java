package juegos;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pelota {

    private static final int DIAMETER = 30;
    int x;
    int y;
    int xa = 1;
    int ya = 1;
    private final Juegos juego;

    public Pelota(Juegos juego) {
        this.juego = juego;
    }

    public void randPositions() {
        x = (int) (Math.random() * 450) + 51;
        y = (int) (Math.random() * 300) + 1;
    }

    public void move() {
        boolean changeDirection = true;

        if (x + xa < 0) {
            xa = juego.speed;
        } else if (x + xa > juego.getWidth() - DIAMETER) {
            xa = -juego.speed;
        } else if (y + ya < 0) {
            ya = juego.speed;
        } else if (y + ya > juego.getHeight() - DIAMETER) {
            juego.gameOver();
        } else if (collision()) {
            ya = -juego.speed;
            y = juego.raqueta.getTopY() - DIAMETER;
            juego.speed++;
        } else {
            changeDirection = false;

        }

        if (changeDirection) {
            Sound.BALL.play();
        }

        x = x + xa;
        y = y + ya;
    }

    public boolean collision() {
        return juego.raqueta.getBounds().intersects(getBounds());
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

}
