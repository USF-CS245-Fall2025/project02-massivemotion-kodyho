/*
* Author: Kody Ho
* Project 02
* ----------
* 10/20/25
* 
* Body
*
*/


import java.awt.Color;
import java.awt.Graphics;

public class Body {
    double x, y;
    double vx, vy;
    double size;
    double mass;
    Color color;

    public Body(double x, double y, double size, double mass, double vx, double vy, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.mass = mass;
        this.vx = vx;
        this.vy = vy;
        this.color = color;
    }

    public void update() {
        x += vx;
        y += vy;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)(x - size/2), (int)(y - size/2), (int)size, (int)size);
    }

    public boolean isOffScreen(int width, int height) {
        return (x + size < 0 || x - size > width || y + size < 0 || y - size > height);
    }
}
