import java.io.*;
import java.net.*;
import java.util.*;

public class SenderThread implements Runnable{
    PrintWriter pw;
    SenderThread(PrintWriter pw){
        this.pw = pw;
    }

    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter msg :");
        String msg = scan.nextLine();
        while(!msg.equals("")){
            pw.println(msg);
            msg = scan.nextLine();
        }

    }
}
