import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;

public class MyServer {
    public static void start(final int portNumber) throws IOException {
        GameFrame gameFrame = new GameFrame();
        try (ServerSocket serverSocket = new ServerSocket(portNumber)){
            try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                while (true) {
                    Socket client = serverSocket.accept();
                    executor.submit(() -> {
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
                                System.out.println(clientIp + ":" + clientPort + "): " + inputLine);
                                output.println("Heres my y: " + gameFrame.getBumperY());
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
