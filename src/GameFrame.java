import javax.swing.*;

public class GameFrame extends JFrame {
    GamePannel gamePannel = new GamePannel();
    int bumperY;
    GameFrame(){
        this.add(gamePannel);
        this.setTitle("Pong");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public int getBumperY(){
        bumperY = gamePannel.getBumper_Y();
        return bumperY;
    }
}
