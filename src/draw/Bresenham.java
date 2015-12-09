package draw;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tristan-PC on 09.12.2015.
 */
public class Bresenham {

    int x0;
    int y0;
    int x1;
    int y1;

    int dx;
    int dy;

    boolean xDirection;

    double error;

    public Bresenham(int x0, int y0, int x1, int y1) {

        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;

        dx = x1 - x0;
        System.out.println(dx);
        if(dx < 0) {
            dx = dx * -1;
            System.out.println(dx);
        }

        dy = y1 - y0;
        System.out.println(dy);
        if(dy < 0) {
            dy = dy * -1;
            System.out.println(dy);
        }

        setDirection();
        System.out.println(xDirection);
        setError();
        System.out.println(error);

        calcCoord();

    }

    private void setDirection() {

        if(dx >= dy) {

            xDirection = true;

        } else {

            xDirection = false;

        }

    }

    private void setError() {

        if(xDirection) {

            error = (double) dx / 2;

        } else {

            error = (double) dy / 2;

        }

    }

    private void calcCoord() {

        int x = x0;
        int y = y0;

        Map<Integer, Integer> coords = new HashMap<>();
        coords.put(0, dy);

        if(xDirection) {
            for (int i = 1; i <= dx; i++) {
                if (x0 < x1 && y0 < y1) {
                    x++;
                    error = error - dy;
                    if (error < 0) {
                        y++;
                        error = error + dx;
                    }
                    System.out.println("Schritt " + i + ": X-Koordinate: " + x + " ; Y-Koordiante: " + y);
                } else if (x0 < x1 && y0 == y1) {
                    x++;
                    System.out.println("Schritt " + i + ": X-Koordinate: " + x + " ; Y-Koordiante: " + y);
                } else if (x0 < x1 && y0 > y1) {
                    x++;
                    error = error - dy;
                    if (error < 0) {
                        y--;
                        error = error + dx;
                    }
                    System.out.println("Schritt " + i + ": X-Koordinate: " + x + " ; Y-Koordiante: " + y);
                } else if (x0 > x1 && y0 > y1) {
                    x--;
                    error = error - dy;
                    if (error < 0) {
                        y--;
                        error = error + dx;
                    }
                    System.out.println("Schritt " + i + ": X-Koordinate: " + x + " ; Y-Koordiante: " + y);
                } else if (x0 > x1 && y0 == y1) {
                    x--;
                    System.out.println("Schritt " + i + ": X-Koordinate: " + x + " ; Y-Koordiante: " + y);
                } else if (x0 > x1 && y0 < y1) {
                    x--;
                    error = error - dy;
                    if (error < 0) {
                        y++;
                        error = error + dx;
                    }
                    System.out.println("Schritt " + i + ": X-Koordinate: " + x + " ; Y-Koordiante: " + y);
                }
            }
        } else {
            for (int i = 1; i <= dy; i++) {
                if (x0 < x1 && y0 < y1) {
                    y++;
                    error = error - dx;
                    if (error < 0) {
                        x++;
                        error = error + dy;
                    }
                    System.out.println("Schritt " + i + ": X-Koordinate: " + x + " ; Y-Koordiante: " + y);
                } else if (x0 < x1 && y0 > y1) {
                    y--;
                    error = error - dx;
                    if (error < 0) {
                        x++;
                        error = error + dy;
                    }
                    System.out.println("Schritt " + i + ": X-Koordinate: " + x + " ; Y-Koordiante: " + y);
                } else if (x0 == x1 && y0 > y1) {
                    y--;
                    System.out.println("Schritt " + i + ": X-Koordinate: " + x + " ; Y-Koordiante: " + y);
                } else if (x0 > x1 && y0 > y1) {
                    y--;
                    error = error - dx;
                    if (error < 0) {
                        x--;
                        error = error + dy;
                    }
                    System.out.println("Schritt " + i + ": X-Koordinate: " + x + " ; Y-Koordiante: " + y);
                } else if (x0 > x1 && y0 < y1) {
                    y++;
                    error = error - dx;
                    if (error < 0) {
                        x--;
                        error = error + dy;
                    }
                    System.out.println("Schritt " + i + ": X-Koordinate: " + x + " ; Y-Koordiante: " + y);
                } else if (x0 == x1 && y0 < y1) {
                    y++;
                    System.out.println("Schritt " + i + ": X-Koordinate: " + x + " ; Y-Koordiante: " + y);
                }
            }
        }
    }
}
