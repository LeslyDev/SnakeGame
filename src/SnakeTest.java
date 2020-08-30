import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static org.junit.Assert.*;

public class SnakeTest {

    @Test
    public void chooseDirection() {
        Snake snake = new Snake(new ImageIcon("images/green.jpg").getImage(), 50, 50, "right");
        snake.initSnake();
        assertNull(snake.chooseDirection(50, 50));
        Assert.assertEquals(snake.chooseDirection(50, 49), "up");
        Assert.assertEquals(snake.chooseDirection(50, 51), "down");
        Assert.assertEquals(snake.chooseDirection(49, 50), "left");
        Assert.assertEquals(snake.chooseDirection(51, 50), "right");
        Assert.assertEquals(snake.chooseDirection(49, 49), "left");
        Assert.assertEquals(snake.chooseDirection(49, 51), "left");
        Assert.assertEquals(snake.chooseDirection(51, 49), "right");
        Assert.assertEquals(snake.chooseDirection(51, 51), "right");
    }

    @Test
    public void checkApple() {
        Snake snake = new Snake(new ImageIcon("images/green.jpg").getImage(), 50, 50, "right");
        snake.initSnake();
        assertTrue(snake.checkApple(50, 50));
        assertFalse(snake.checkApple(49, 50));
    }

    @Test
    public void checkCollisions() {
        // змея укусила себя
        Snake falseSnake1 = new Snake(new ImageIcon("images/green.jpg").getImage(), 50, 50, "right");
        falseSnake1.setX(new int[]{101, 101, 102, 102, 101});
        falseSnake1.setY(new int[]{103, 102, 102, 103, 103});
        falseSnake1.setDots(5);
        Assert.assertFalse(falseSnake1.checkCollisions());
        // змея вышла за пределы поля
        Snake falseSnake2 = new Snake(new ImageIcon("images/green.jpg").getImage(), 50, 50, "right");
        falseSnake2.setX(new int[]{1, 2, 3, 4, 5});
        falseSnake2.setY(new int[]{1, 1, 1, 1, 1});
        Assert.assertFalse(falseSnake2.checkCollisions());
        // змея в поле и не кусает себя
        Snake trueSnake = new Snake(new ImageIcon("images/green.jpg").getImage(), 50, 50, "right");
        trueSnake.setX(new int[]{101, 102, 103, 104, 105});
        trueSnake.setY(new int[]{101, 101, 101, 101, 101});
        Assert.assertTrue(trueSnake.checkCollisions());
    }

    @Test
    public void checkTouch() {
        // первая змея кусает вторую
        Snake snake1 = new Snake(new ImageIcon("images/green.jpg").getImage(), 50, 50, "right");
        snake1.initSnake();
        Snake snake2 = new Snake(new ImageIcon("images/green.jpg").getImage(), 50, 50, "right");
        snake2.setX(new int[] {49, 50, 51});
        snake2.setY(new int[] {50, 50, 50});
        Assert.assertFalse(snake2.checkTouch(snake1));
        // никто никого не кусает
        Snake snake3 = new Snake(new ImageIcon("images/green.jpg").getImage(), 100, 100, "right");
        snake3.initSnake();
        Snake snake4 = new Snake(new ImageIcon("images/green.jpg").getImage(), 50, 50, "right");
        snake4.initSnake();
        Assert.assertTrue(snake3.checkTouch(snake4));
        Assert.assertTrue(snake4.checkTouch(snake3));
    }
}