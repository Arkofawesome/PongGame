import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class GamePannel extends JPanel implements ActionListener {
    final int SCREEN_WIDTH = 600;
    final int SCREEN_HEIGHT = 600;
    final int BUMPER_WIDTH = 15;
    final int BUMPER_HEIGHT = 100;
    final int BALL_HEIGHT = 15;
    final int BALL_WIDTH = 15;
    final int DELAY = 15;
    int ball_X;
    int ball_Y;
    int my_Bumper_Y = SCREEN_HEIGHT / 2 - BUMPER_HEIGHT/2;
    int enemy_Bumper_Y = SCREEN_HEIGHT / 2 - BUMPER_HEIGHT/2;
    boolean running;
    int left_Score;
    int right_Score;
    String direction;
    Timer timer;
    Random random;

    boolean isServer;


    GamePannel(){
        random = new Random();
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
//        this.setVisible(true);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
//        this.addKeyListener(new MyKeyAdapter());
        this.addMouseMotionListener(new MyMouseAdapter());
        start();
    }

    public void setServer(boolean set){
        isServer = set;
    }
    public void start(){
        randomStart();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        g.drawLine(SCREEN_WIDTH/2, 0, SCREEN_WIDTH/2, SCREEN_HEIGHT);
        g.setColor(Color.WHITE);
        g.fillRect(0,my_Bumper_Y,BUMPER_WIDTH,BUMPER_HEIGHT);//My rect
        g.fillRect(SCREEN_WIDTH - BUMPER_WIDTH, enemy_Bumper_Y, BUMPER_WIDTH, BUMPER_HEIGHT);//enemy rect
        g.setFont(new Font("Monospaced", Font.BOLD, 50));
        g.drawString("" + left_Score, SCREEN_WIDTH/4 - 25, 50);
        g.drawString("" + right_Score, SCREEN_WIDTH/4 * 3 - 25 , 50);
        g.fillRect(ball_X, ball_Y, BALL_WIDTH, BALL_HEIGHT);//Ball rect
    }
    public String getDir(){
        return direction;
    }
    public void setScore(int myScore, int enemyScore){
        left_Score = myScore;
        right_Score = enemyScore;
    }
    public int getMyScore(){
        return left_Score;
    }
    public int getEnemyScore(){
        return  right_Score;
    }
    public int getBall_X(){
        return ball_X;
    }
    public int getBall_Y(){
        return ball_Y;
    }
    public void setDir(String direction){
        this.direction = direction;
    }
    public void setBall_X(int ball_X){
        this.ball_X = ball_X;
    }
    public void setBall_Y(int ball_Y){
        this.ball_Y = ball_Y;
    }
    public void randomStart(){
        if(isServer){
            ball_X = SCREEN_WIDTH/2 - BALL_WIDTH/2;
            ball_Y = random.nextInt(SCREEN_HEIGHT - BALL_HEIGHT);
            switch(random.nextInt(4)){
                case 1:
                    direction = "UL";
                    break;
                case 2:
                    direction = "BL";
                    break;
                case 3:
                    direction = "UR";
                    break;
                case 4:
                    direction = "BR";
                    break;
                default:
                    direction = "UL";
                    break;
            }
        }
        else{

        }


    }
    public void moveBall(){
        if(isServer){
            if(ball_X <= 0){//off the left side screen
                right_Score++;
                randomStart();
            }
            else if (ball_X >= SCREEN_WIDTH - BALL_WIDTH){//off the left side screen
                left_Score++;
                randomStart();;
            }
            else {//in play
                if(ball_Y < 0 || ball_Y + BALL_HEIGHT > SCREEN_WIDTH) {// If the ball is colliding
                    wallCollision();
                }
                //Basically if the ball touched a bumper
                else if(new Rectangle(0,my_Bumper_Y, BUMPER_WIDTH,BUMPER_HEIGHT).intersects(ball_X,ball_Y,BALL_WIDTH,BALL_HEIGHT)
                        || new Rectangle(SCREEN_WIDTH - BUMPER_WIDTH,enemy_Bumper_Y, BUMPER_WIDTH,BUMPER_HEIGHT).intersects(ball_X,ball_Y,BALL_WIDTH,BALL_HEIGHT)){
                    bumperCollision();
                }
                //Move codec
                switch(direction){
                    case("UL"):
                        ball_X -= 8;
                        ball_Y -= 8;
                        break;
                    case("BL"):
                        ball_X -= 8;
                        ball_Y += 8;
                        break;
                    case("UR"):
                        ball_X += 8;
                        ball_Y -= 8;
                        break;
                    case("BR"):
                        ball_X += 8;
                        ball_Y += 8;
                        break;
                }
            }
        }


    }
    public void bumperCollision(){
        switch(direction){
            case("UL"):
                direction= "BR";
                break;
            case("BL"):
                direction= "UR";
                break;
            case("UR"):
                direction= "BL";
                break;
            case("BR"):
                direction= "UL";
                break;
        }
    }
    public void wallCollision(){
        switch(direction){
            case("UL"):
                direction= "BL";
                break;
            case("BL"):
                direction= "UL";
                break;
            case("UR"):
                direction= "BR";
                break;
            case("BR"):
                direction= "UR";
                break;
        }

    }
    public int getBumper_Y(){
        return my_Bumper_Y;
    }
    public void setEnemy_Bumper_Y(int y){
//        System.out.println("Working good in GamePanel!");
        enemy_Bumper_Y = y;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            moveBall();
        }
        repaint();
    }

//    public class MyKeyAdapter extends KeyAdapter {
//        @Override
//        public void keyPressed(KeyEvent e){
//
//            switch(e.getKeyCode()){
//                case KeyEvent.VK_UP:
//                    if(enemy_Bumper_Y >= 25){
//                        enemy_Bumper_Y -= 25;
//                    }
//                    break;
//                case KeyEvent.VK_DOWN:
//                    if(enemy_Bumper_Y <= SCREEN_HEIGHT - BUMPER_HEIGHT - 25){
//                        enemy_Bumper_Y += 25;
//                    }
//                    break;
//            }
////            repaint();
//
//        }
//    }
    public class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e){
            if(e.getY() > 0 && e.getY() <= SCREEN_HEIGHT - BUMPER_HEIGHT){
                my_Bumper_Y = e.getY();
//                System.out.println(e.getY());
//                repaint();F
            }
        }
    }

}
