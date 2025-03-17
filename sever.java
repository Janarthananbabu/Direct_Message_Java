import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class sever {
    static ServerSocket serverSocket;
    static Socket socket;
    static PrintWriter pw;
    static BufferedReader bufferedReader;

    public static void main(String[] args) {
        sever server = new sever();
        try{
           server.startServer();
        }
        catch (IOException e){
            System.out.println("Error Occurred");
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your Name :");
        String userName = scan.nextLine();
        Thread t1 = new Thread(new SenderThread(pw));
        Thread t2 = new Thread(new ReceiverThread(bufferedReader, userName));
        t1.start();
        t2.start();

        try {
            t2.join();
            t1.join();
            server.closeAll();
        }
        catch (Exception e){
            System.out.println("Error Occurred");
        }
    }

    public void startServer() throws IOException{
        serverSocket = new ServerSocket(11111);
        System.out.println("Waiting...");
        socket = serverSocket.accept();
        System.out.println("Connected...");
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw = new PrintWriter(socket.getOutputStream(),true);
    }

    public void closeAll() throws IOException {
        if(serverSocket != null) serverSocket.close();
        if(socket != null) socket.close();
        if(pw != null) pw.close();
        if(bufferedReader!= null) bufferedReader.close();
        System.out.println("\n --------------------------------------------------------- \n");
    }

}