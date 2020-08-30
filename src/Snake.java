import java.awt.*;
import java.util.stream.IntStream;

class Snake {
    private Image dot;
    private int[] x = new int[1600];
    private int[] y = new int[1600];
    private int dots = 1;
    private Point startPosition;
    private String m_direction;

    Snake(Image image, int startX, int startY, String direction) {
        dot = image;
        startPosition = new Point(startX, startY);
        m_direction = direction;
    }

    String getM_direction() {
        return m_direction;
    }

    void setM_direction(String m_direction) {
        this.m_direction = m_direction;
    }

    int getDots() {
        return dots;
    }

    void setX(int[] x) {
        this.x = x;
    }

    void setY(int[] y) {
        this.y = y;
    }

    int[] getXCoordinates() {
        return x;
    }

    int[] getYCoordinates() {
        return y;
    }

    void setDots(int dots) {
        this.dots = dots;
    }

    Image getDot() {
        return dot;
    }

    void initSnake() {
        x[0] = startPosition.x;
        y[0] = startPosition.y;
    }

    void move(){
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        int DOT_SIZE = 10;
        switch (m_direction) {
            case "left":
                x[0] -= DOT_SIZE;
                break;
            case "right":
                x[0] += DOT_SIZE;
                break;
            case "up":
                y[0] -= DOT_SIZE;
                break;
            case "down":
                y[0] += DOT_SIZE;
                break;
        }
    }

    String chooseDirection(int appleX, int appleY) {
        String direction = null;
        if (x[0] < appleX) {
            direction = "right";
        }
        else if (x[0] > appleX) {
            direction = "left";
        }
        else if (y[0] < appleY) {
            direction = "down";
        }
        else if (y[0] > appleY) {
            direction = "up";
        }
        return direction;
    }

    boolean checkApple(int appleX, int appleY){
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            return true;
        }
        return false;
    }

    boolean checkCollisions() {
        for (int i = getDots() - 1; i >= 0 ; i--) {
            if(i > 3 && x[0] == x[i] && y[0] == y[i]){
                return false;
            }
        }
        int SIZE = 380;

        if(x[0] >= SIZE){
            return false;
        }
        else if(x[0] < 30){
            return false;
        }
        else if(y[0] >= SIZE){
            return false;
        }
        else return y[0] >= 30;
    }

    boolean checkTouch(Snake this, Snake other) {
        return IntStream.of(this.x).noneMatch(x -> x == other.x[0]) || IntStream.of(this.y).noneMatch(x -> x == other.y[0]);
    }
}
