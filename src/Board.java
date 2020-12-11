import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;




public class Board extends JPanel implements ActionListener{

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY =  140;

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
        addKeyListener(new IAdapter());
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

        ImageIcon im_ball = new ImageIcon(getClass().getResource("/resources/head.png"));
        ball = im_ball.getImage();
    }

    private void initGame(){
        dots = 3;

        for (int z = 0; z < dots; z++){
            x[z] = 50 - z * 10;
            y[z] = 50;
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
            
            for
        }
        
    }








    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }


}