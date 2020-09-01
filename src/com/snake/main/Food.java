package com.snake.main;

import java.awt.*;
import java.util.Random;

public class Food {
    private Image m_appleImage;
    private Point appleCoordinate;

    public Food(Point appleCoordinate, Image image) {
        m_appleImage = image;
        this.appleCoordinate = appleCoordinate;
    }

    public Point createApple(){
        int DOT_SIZE = 10;
        Point appleCoordinate;
        appleCoordinate = new Point(new Random().nextInt(33) * DOT_SIZE + 30,
                new Random().nextInt(35) * DOT_SIZE + 30);
        return appleCoordinate;
    }

    public Point getAppleCoordinate() {
        return appleCoordinate;
    }

    Image getM_appleImage() {
        return m_appleImage;
    }
}

