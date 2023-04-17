import java.io.*;
import java.net.*;
import java.util.*;

// create both TCP and UDP Server
public class SocketServer{

    // Threads for TCP and UDP pass through quote
    public static void main(String... args) throws Exception {

        
        String q1 = "Life is pain, Highness. Anyone who says differently is selling something. (Princess Bride)";
        String q2 = "The truth. It is a beautiful and terrible thing, and should therefore be treated with great caution. - Albus Dumbledore (Harry Potter and the Sorcerer's Stone)";

        new Thread( () -> TCPServer(q1)).start(); 
        new Thread( () -> UDPServer(q2)).start(); 

    } 
    

    public static void TCPServer(String quote){
        //TCP Socket
       //  ServerSocket server = new ServerSocket(17);
        // Socket socket = server.accept();

        {/*}
        Socket socket = null;

        while ((socket = server.accept()) != null) {
            System.out.println("Accepted client request");
            final Socket threadSocket = socket;
            new Thread( () -> handleRequest(threadSocket) ).start();
            // exec.submit( () -> handleRequest(threadSocket));
        }
        */}

        try {
            ServerSocket server = new ServerSocket(17);
            Socket socket = null;

            while ((socket = server.accept()) != null) {
                System.out.println("Accepted client request");
            final Socket threadSocket = socket;
            new Thread( () -> handleRequest(threadSocket, quote) ).start();
            }
            server.close();
        
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void UDPServer(String quote){
        try{
            // UDP Socket
            DatagramSocket udpSocket = new DatagramSocket(17);
            byte[] receive = new byte[65535];
            DatagramPacket DpReceive = null;

            // DatagramPacket to receive data
            DpReceive = new DatagramPacket(receive, receive.length);
            udpSocket.receive(DpReceive);

            // converts quote to bytes
            byte[] buffer = quote.getBytes();

            InetAddress clientAddress = DpReceive.getAddress();
            int clientPort = DpReceive.getPort();

            // DatagramPacket response to client
            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            udpSocket.send(response);
    
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    // function for TCP
    public static void handleRequest(Socket socket, String quote) {
        // exec = Executors.newFixedThreadPool(5);
        try {
            System.out.print("INCOMING CLIENT: TCP ");
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            int ch = 0;

            {/*}

            while ((ch = in.read()) != '\n') {
                System.out.print((char)ch);
               
                // out.write((char)ch);
                
            }
            */}
            out.write(quote.getBytes());
            System.out.println();
            in.close();
            out.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}