import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class Game extends JPanel implements ActionListener{
    private final int DOT_SIZE = 10;
    private Food apple = new Food(new Point(new Random().nextInt(33) * DOT_SIZE + 30,
            new Random().nextInt(35) * DOT_SIZE + 30),
            new ImageIcon("images/apple.jpg").getImage());
    private Snake userSnake = new Snake(new ImageIcon("images/green.jpg").getImage(), 50, 50, "right");
    private int userSnakeScore = 0;
    private int botSnakeScore = 0;
    private String loser;
    private int timeLeft = 101;
    private JButton startGameButton = new JButton("Начать игру");
    private JButton startOverButton = new JButton("Начать сначала");
    private int counter = 400;
    private Snake botSnake = new Snake(new ImageIcon("images/blue.jpg").getImage(), 370, 370, "left");
    private boolean inGame = false;
    private boolean isStart = false;
    private Timer timer = new Timer(250, this);


    Game(){
        setBackground(Color.black);
        setLayout(null);
        initGame();
        startGameButton.setBounds(100, 170, 190, 30);
        add(startGameButton);
        startGameButton.addActionListener(e -> {
            inGame = true;
            isStart = true;
            remove(startGameButton);
        });
        startOverButton.addActionListener(e -> {
            inGame = true;
            userSnake = new Snake(new ImageIcon("images/green.jpg").getImage(), 50, 50, "right");
            botSnake = new Snake(new ImageIcon("images/blue.jpg").getImage(), 370, 370, "left");
            apple = new Food(new Point(new Random().nextInt(33) * DOT_SIZE + 30,
                    new Random().nextInt(35) * DOT_SIZE + 30),
                    new ImageIcon("images/apple.jpg").getImage());
            initGame();
            userSnakeScore = 0;
            botSnakeScore = 0;
            timeLeft = 201;
            counter = 800;
            remove(startOverButton);
            repaint();
        });
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    private void initGame(){
        userSnake.initSnake();
        botSnake.initSnake();
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        if(inGame){
            g.setColor(Color.white);
            g.drawRect(30, 30, 350, 350);
            g.drawString("Ваш счет: " + userSnakeScore, 10, 20);
            g.drawString("Счет бота: " + botSnakeScore, 300, 20);
            g.drawString("Оставшееся время: " + timeLeft, 100, 20);
            g.drawImage(apple.getM_appleImage(), apple.getAppleCoordinate().x , apple.getAppleCoordinate().y,this);
            for (int i = 0; i < userSnake.getDots(); i++) {
                g.drawImage(userSnake.getDot(), userSnake.getXCoordinates()[i], userSnake.getYCoordinates()[i],this);
            }
            for (int i = 0; i < botSnake.getDots(); i++) {
                g.drawImage(botSnake.getDot(), botSnake.getXCoordinates()[i], botSnake.getYCoordinates()[i],this);
            }
        } else if (isStart){
            g.setColor(Color.white);
            int SIZE = 400;
            g.drawString("Game Over",160, SIZE /2 - 30);
            g.drawString(loser,110, SIZE /2);
            timer.stop();
            startOverButton.setBounds(130, 220, 150, 30);
            this.add(startOverButton);
            repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            if (counter % 4 == 0) {
                timeLeft--;
            }
            counter--;
            if (userSnake.checkApple(apple.getAppleCoordinate().x, apple.getAppleCoordinate().y)) {
                apple = new Food(apple.createApple(), new ImageIcon("images/apple.jpg").getImage());
                userSnakeScore++;
            }
            if (botSnake.checkApple(apple.getAppleCoordinate().x, apple.getAppleCoordinate().y)) {
                apple = new Food(apple.createApple(), new ImageIcon("images/apple.jpg").getImage());
                botSnakeScore++;
            }
            inGame = userSnake.checkCollisions() && botSnake.checkTouch(userSnake);
            if (!inGame) {
                if (timeLeft != 0) {
                    loser = "Вы проиграли из-за ошибки!";
                }
            }
            if (inGame) {
                inGame = botSnake.checkCollisions() && userSnake.checkTouch(botSnake);
                if (!inGame && timeLeft != 0) {
                    loser = "Вы выйграли из-за ошибки бота!";
                }
            }
            if (timeLeft == 0) {
                inGame = false;
                if (userSnakeScore > botSnakeScore) {
                    loser = "Вы выйграли по очкам!";
                }
                else if (userSnakeScore < botSnakeScore) {
                    loser = "Вы проиграли по очкам!";
                }
                else {
                    loser = "Ничья";
                }
            }
            botSnake.setM_direction(botSnake.chooseDirection(apple.getAppleCoordinate().x, apple.getAppleCoordinate().y));
            userSnake.move();
            botSnake.move();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !userSnake.getM_direction().equals("right")){
                userSnake.setM_direction("left");
            }
            if(key == KeyEvent.VK_RIGHT && !userSnake.getM_direction().equals("left")){
                userSnake.setM_direction("right");
            }

            if(key == KeyEvent.VK_UP && !userSnake.getM_direction().equals("down")){
                userSnake.setM_direction("up");
            }
            if(key == KeyEvent.VK_DOWN && !userSnake.getM_direction().equals("up")){
                userSnake.setM_direction("down");
            }
        }
    }
}
