package utilities;

import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println("Mensaje: " + inputLine);

            if (inputLine.equalsIgnoreCase("Bye")) {
                out.println("Respuesta: Bye.");
                break;
            }
            out.println("Respuesta: " + inputLine);
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}