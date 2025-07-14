import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;

public class MyServer {
    public static void start(final int portNumber) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)){
            try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                while (true) {
                    Socket client = serverSocket.accept();
                    executor.submit(() -> {
                        GameFrame gameFrame = new GameFrame();
                        gameFrame.setServer(true);
                        System.out.println("Client connected!");
                        var clientIp = client.getInetAddress().getHostAddress();
                        var clientPort = client.getPort();
                        try(var clientInput = new BufferedReader(new InputStreamReader(client.getInputStream()));
                            var output = new PrintWriter(client.getOutputStream(), true)) {

//                            for (String inputLine; (inputLine = clientInput.readLine()) != null;) {
//                                System.out.println(clientIp + ":" + clientPort + "): " + inputLine);
//
//                            }
                            String inputLine;
                            while ((inputLine = clientInput.readLine()) != null) {
//                                System.out.println(inputLine);
//                                inputLine = inputLine.replaceAll("\\s+", "");
                                gameFrame.setEnemyBumperY(Integer.parseInt(inputLine));
                                int reflectionX = 300 - gameFrame.getBallX();
                                if(reflectionX >= 0)
                                    reflectionX = gameFrame.getBallX() + 2*reflectionX;
                                else
                                    reflectionX = gameFrame.getBallX() - -2*reflectionX;
                                //TODO Fix Point bug. Not showing up on client computer.
                                output.println(gameFrame.getBumperY() + "_" + reflectionX + "_" + gameFrame.getBallY() + "_" + gameFrame.getDirection()+ "_" + gameFrame.getMyScore() + "_" + gameFrame.getEnemyScore());
                            }

                        }
                        catch (IOException e) {
                            throw new RuntimeException("Client handler failed", e);
                        }
                    });
                }
            }

        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
