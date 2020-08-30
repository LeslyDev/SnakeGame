import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static org.junit.Assert.*;

public class FoodTest {

    @Test
    public void createApple() {
        int DOT_SIZE = 10;
        Food apple = new Food(new Point(new Random().nextInt(32) * DOT_SIZE + 30,
                new Random().nextInt(35) * DOT_SIZE + 30),
                new ImageIcon("apple.jpg").getImage());
        for (int i = 0; i < 1000; i++) {
            apple = new Food(apple.createApple(), new ImageIcon("apple.jpg").getImage());
            Assert.assertTrue(apple.getAppleCoordinate().x >= 30 && apple.getAppleCoordinate().x <= 350);
            Assert.assertTrue(apple.getAppleCoordinate().y <= 380 && apple.getAppleCoordinate().x >= 30);
        }
    }
}