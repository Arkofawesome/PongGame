import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {
    public void start(final int portNumber,final Scanner scanner) throws UnknownHostException, IOException {
        GameFrame gameFrame = new GameFrame();
        //If you're trying on the same game
//        try (var socket = new Socket("localhost", portNumber);
        //If you're accessing remotely
        try (var socket = new Socket("10.0.0.45", portNumber);
             var writer = new PrintWriter(socket.getOutputStream(), true);
             var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            System.out.println("Socket created");
//            for (String userInput; !(userInput = scanner.nextLine()).isEmpty();){
//                writer.println(userInput);
//                System.out.println("Response " + reader.readLine());
//            }
            while(true){
                writer.println(gameFrame.getBumperY());
                System.out.println(reader.readLine());
//                System.out.println("Sending y at: " +gameFrame.getBumperY());
            }

        } catch (Error e) {
            throw new RuntimeException(e);
        }

    }
}
