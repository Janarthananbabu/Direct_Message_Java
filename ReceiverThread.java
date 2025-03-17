import java.io.*;
import java.util.Scanner;

public class ReceiverThread implements Runnable {
    BufferedReader bufferedReader;
    String userName ;

    ReceiverThread(BufferedReader bufferedReader, String userName) {
        this.bufferedReader = bufferedReader;
        this.userName = userName;
    }

    @Override
    public void run() {
        try {
            String msg = bufferedReader.readLine();
            while (msg != null && !msg.isEmpty()) {
                System.out.println(userName + " says :" + msg);
                msg = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error Occurred");
        }
    }
}
