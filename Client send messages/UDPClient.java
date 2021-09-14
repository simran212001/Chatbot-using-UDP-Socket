import java.io.*;
import java.net.*;
import java.util.Scanner;

class client {
    public static void main(String args[]) throws Exception
    {
        Scanner inFromUser = new Scanner(System.in);
        
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
        
        while(true)  {
            byte[] sendData = new byte[1024];
            String sentence = inFromUser.nextLine();
            
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress, 9876);
            clientSocket.send(sendPacket);
            
            if(sentence.equals("ok bye")) {
                break;
            }
        }
        
        clientSocket.close();
        
    }
}