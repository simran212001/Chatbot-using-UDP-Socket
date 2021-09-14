import java.io.*;
import java.net.*;

class UDPServer {
    public static void main(String args[]) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(9876);
    
        while(true)
        {
            byte[] receiveData = new byte[10000];
            byte[] sendData = new byte[10000];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
            
            sentence = sentence.toUpperCase();
            
            System.out.println(sentence);
                        
            if(sentence.startsWith("OK BYE") && sentence.length() == 6) {
                System.out.println("matched " + sentence);
                break;
            }
            
            int vowel = 0, consonant = 0, digit = 0, whitespace = 0;
            for(int i = 0; i < sentence.length(); i++) {
                char ch = sentence.charAt(i);
                
                if(ch == ' ')  {
                    whitespace++;
                }
                else if('A' <= ch && ch <= 'Z') {
                    if(ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
                        vowel++;
                    }
                    else {
                        consonant++;
                    }
                }
                else if('0' <= ch && ch <= '9') {
                    digit++;
                }
            }
            
            String info = "No of vowels = " + Integer.toString(vowel) + "\nNo of consonant = " + Integer.toString(consonant) + "\nNo of digit = " + Integer.toString(digit) + "\nNo of whitespace = " + Integer.toString(whitespace) + "\n";
            System.out.println(info);
            
        }
        
        System.out.println("OK closing");
        
        serverSocket.close();
    }
}