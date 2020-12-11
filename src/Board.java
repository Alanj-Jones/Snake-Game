import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;





public class Board extends JPanel implements ActionListener{

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY =  200;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public Board(){
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }
    private void loadImages(){
        ImageIcon im_apple = new ImageIcon(getClass().getResource("/resources/apple.png"));
        apple = im_apple.getImage();

        ImageIcon im_head = new ImageIcon(getClass().getResource("/resources/head.png"));
        head = im_head.getImage();

        ImageIcon im_ball = new ImageIcon(getClass().getResource("/resources/dot.png"));
        ball = im_ball.getImage();
    }

    private void initGame(){
        dots = 3;

        for (int i = 0; i < dots; i++){
            x[i] = 50 - i * 10;
            y[i] = 50;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g){
        if (inGame){
            g.drawImage(apple, apple_x, apple_y, this);
            
            for (int i = 0; i < dots; i++){
                if (i== 0){
                    g.drawImage(head, x[i], y[i], this);
                }else{
                    g.drawImage(ball, x[i], y[i], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }else{
            gameOver(g);
        }
        
    }


    private void gameOver(Graphics g) {
        String msg = "Game Over!";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);


        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) /2 , B_HEIGHT /2);

    }

    private void checkApple(){
        if ((x[0] == apple_x) && (y[0] == apple_y)){
            dots++;
            locateApple();
        }
    }

    private void move(){
        for (int i = dots; i > 0; i--){
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (leftDirection){
            x[0] -= DOT_SIZE;
        }

        if (rightDirection){
            x[0] += DOT_SIZE;
        }

        if (upDirection){
            y[0] -= DOT_SIZE;
        }

        if (downDirection){
            y[0] += DOT_SIZE;
        }
    }

    private void checkColition(){
        for (int i = dots; i > 0; i--){
            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])){
                inGame =false;
            }
        }

        if (y[0] >= B_HEIGHT){
            inGame = false;
        }

        if (y[0] < 0){
            inGame = false;
        }

        if (x[0] >= B_WIDTH){
            inGame = false;
        }

        if (x[0] < 0){
            inGame = false;
        }

        if (!inGame){
            timer.stop();
        }
    }

    private void locateApple(){
        int r = (int) (Math.random() * RAND_POS);
        apple_x = r * DOT_SIZE;

        r = (int)(Math.random() * RAND_POS);
        apple_y = r * DOT_SIZE;

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(inGame){
            checkApple();
            checkColition();
            move();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT && !rightDirection){
                leftDirection =true;
                upDirection = false;
                downDirection = false;
            }

            if (key == KeyEvent.VK_RIGHT && !leftDirection){
                rightDirection =true;
                upDirection = false;
                downDirection = false;
            }

            if (key == KeyEvent.VK_UP && !downDirection){
                upDirection = true;
                rightDirection =false;
                leftDirection = false;
            }

            if (key == KeyEvent.VK_DOWN && !upDirection){
                downDirection = true;
                rightDirection =false;
                leftDirection = false;
            }
        }

    }


}