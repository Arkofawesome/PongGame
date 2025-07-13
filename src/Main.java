import java.io.IOException;
import java.util.Scanner;

public class Main {
    final static int PORT_NUMBER = 12345;
    public static void main(String[] args) throws IOException {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Ready to play? (y/n)");
            if (scan.nextLine().equalsIgnoreCase("y")) {
                new MyServer().start(PORT_NUMBER);
            }
            else {
                new MyClient().start(PORT_NUMBER, scan);
            }
        }

    }
}