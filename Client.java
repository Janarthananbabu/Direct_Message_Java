import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    static BufferedReader bufferedReader;
    static PrintWriter pw;
    static Socket socket;
    public static void main(String[] args) {
        Client client = new Client();
        try {
            socket = new Socket("localhost",11111);
            System.out.println("Connected...");

            pw = new PrintWriter(socket.getOutputStream(),true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (Exception e){

        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your Name :");
        String userName = scan.nextLine();
        Thread t1 = new Thread(new SenderThread(pw));
        Thread t2 = new Thread(new ReceiverThread(bufferedReader, userName));
        t2.start();
        t1.start();
        try {
            t1.join();
            t2.join();
            client.closeAll();
        } catch (Exception e) {
            System.out.println("Error Occurred");
        }
    }

    public void closeAll() throws IOException {
        if(socket != null) socket.close();
        if(pw != null) pw.close();
        if(bufferedReader != null) bufferedReader.close();
        System.out.println("\n --------------------------------------------------------- \n");
    }

}
