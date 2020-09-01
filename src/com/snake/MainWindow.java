package com.snake;

import com.snake.main.Game;

import javax.swing.*;

public class MainWindow extends JFrame {
    private MainWindow() {
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 425);
        setLocation(400, 240);
        add(new Game());
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow mv = new MainWindow();
    }
}

