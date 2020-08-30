import java.awt.*;
import java.util.Random;

class Food {
    private Image m_appleImage;
    private Point appleCoordinate;

    Food(Point appleCoordinate, Image image) {
        m_appleImage = image;
        this.appleCoordinate = appleCoordinate;
    }

    Point createApple(){
        int DOT_SIZE = 10;
        Point appleCoordinate;
        appleCoordinate = new Point(new Random().nextInt(33) * DOT_SIZE + 30,
                new Random().nextInt(35) * DOT_SIZE + 30);
        return appleCoordinate;
    }

    Point getAppleCoordinate() {
        return appleCoordinate;
    }

    Image getM_appleImage() {
        return m_appleImage;
    }
}

