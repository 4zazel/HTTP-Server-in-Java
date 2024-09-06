import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception{

        final int PORT = 8080;

        //The server itself
        final ServerSocket server = new ServerSocket(PORT);
        System.out.println("Listening on prot 8080...");

        //The server runs forever otherwise the program will stop and the connection will end
        while(true) {

            //This is the socket that allows the server to accept a GET request from the browser
            try(Socket socket = server.accept()) {

                //Reading the request from the server
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                BufferedReader reader = new BufferedReader(isr);

                String line = reader.readLine();

                //Reading lines until its empty
                while (!line.isEmpty()) {
                    System.out.println(line);
                    line = reader.readLine();
                }

                //Sending back a welcome message with today's date
                Date today = new Date();
                String httpResponse = "Welcome to my server! Todays date and time is: " + today;
                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
            }
        }
    }
}