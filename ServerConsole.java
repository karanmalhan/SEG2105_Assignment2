import common.ChatIF;
import ocsf.server.ConnectionToClient;

import java.io.IOException;
import java.util.Scanner;

public class ServerConsole implements ChatIF {
    final public static int DEFAULT_PORT = 5555;

    EchoServer server;

    Scanner fromServer;

    public ServerConsole (String host, int port){
        try{
            server = new EchoServer(port);
        }
        catch (IOException exception){
            System.out.print("\"Error: Can't setup connection!\"\n" +
                    "                + \" Terminating server.\"");
            System.exit(1);
        }

        fromServer = new Scanner(System.in);
    }


    public void display(String message)
    {
        System.out.println("> " + message);
    }

    public static void main(String[] args)
    {
        int port = 0; //Port to listen on

        try
        {
            port = Integer.parseInt(args[0]); //Get port from command line
        }
        catch(Throwable t)
        {
            port = DEFAULT_PORT; //Set port to 5555
        }

        EchoServer sv = new EchoServer(port);

        try
        {
            sv.listen(); //Start listening for connections
        }
        catch (Exception ex)
        {
            System.out.println("ERROR - Could not listen for clients!");
        }
    }
}

