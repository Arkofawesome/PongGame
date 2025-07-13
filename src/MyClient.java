import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {
    public void start(final int portNumber,final Scanner scanner) throws UnknownHostException, IOException {
        GameFrame gameFrame = new GameFrame();
        gameFrame.setServer(false);
        //If you're trying on the same game
        try (var socket = new Socket("localhost", portNumber);
        //If you're accessing remotely
//        try (var socket = new Socket("10.0.0.45", portNumber);
             var writer = new PrintWriter(socket.getOutputStream(), true);
             var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            System.out.println("Socket created");
//            for (String userInput; !(userInput = scanner.nextLine()).isEmpty();){
//                writer.println(userInput);
//                System.out.println("Response " + reader.readLine());
//            }
            while(true){
                writer.println(gameFrame.getBumperY());
                //Changing the Line into a readable int string
                //The format of the string is this:
                // CursorY_ballX_ballY_Direction
                String line = reader.readLine();
                System.out.println(line);
                gameFrame.setEnemyBumperY(Integer.parseInt(line.substring(0,line.indexOf('_'))));
                line = line.substring(line.indexOf('_') + 1);
                gameFrame.setBall_X(Integer.parseInt(line.substring(0,line.indexOf('_'))));
                line = line.substring(line.indexOf('_') + 1);
                gameFrame.setBall_Y(Integer.parseInt(line.substring(0,line.indexOf('_'))));
                line = line.substring(line.indexOf('_') + 1);
                gameFrame.setDirection(line.substring(0,2));

//                System.out.println(reader.readLine());
//                System.out.println("Sending y at: " +gameFrame.getBumperY());
            }

        } catch (Error e) {
            throw new RuntimeException(e);
        }

    }
}
