import javax.swing.*;

public class GameFrame extends JFrame {
    GamePannel gamePannel = new GamePannel();
    int myBumperY;
    int enemyBumperY;
    GameFrame(){

        this.add(gamePannel);
        this.setTitle("Pong");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public void setDirection(String direction){
        gamePannel.setDir(direction);
    }
    public void setBall_X(int ball_X){
        gamePannel.setBall_X(ball_X);
    }
    public void setBall_Y(int ball_Y){
        gamePannel.setBall_Y(ball_Y);
    }
    public String getDirection(){
        return gamePannel.getDir();
    }
    public int getBallY(){
        return gamePannel.getBall_Y();
    }
    public int getBallX(){
        return gamePannel.getBall_X();
    }
    public void setServer(boolean set){
        gamePannel.setServer(set);
    }
    public void setEnemyBumperY(int y){
//        System.out.println("Working good in GameFrame!");
        gamePannel.setEnemy_Bumper_Y(enemyBumperY);
        enemyBumperY = y;
    }
    public int getBumperY(){
        myBumperY = gamePannel.getBumper_Y();
        return myBumperY;
    }
}
