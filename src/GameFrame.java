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
