import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String... args){
        String host = args[0];
        int port = Integer.valueOf(args[1]);
        String a="";
        if(args.length>2){
            for(int i = 2; i <=args.length-1; i++){
                a+=args[i]+" ";
            }
            
        }
        a += "\n";

        try (Socket s = new Socket( host, port )){
            OutputStream out = s.getOutputStream(); 

            String get = "Get / Http/1.0\n\n";
            out.write(get.getBytes());
            
            //send additional args
            out.write(a.getBytes());

            // InputStream in = s.getInputStream();
            BufferedReader in = new BufferedReader( new InputStreamReader( s.getInputStream() ) ); // reads input stream one line
            String line = in.readLine();
            while( line != null ){
                System.out.println( line );
                line = in.readLine();
            }
        }
        // prints exceptions
        catch( Exception e ){
            e.printStackTrace();
        }
    }
}

