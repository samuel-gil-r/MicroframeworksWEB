package utilities;

import java.net.*;
import java.io.*;
import java.util.*;

public class HttpServer {


    private static final Map<String, Webmethod> routes = new LinkedHashMap<>();
    private static String staticFilesLocation = "/webroot";

    public static void get(String path, Webmethod handler) {
        routes.put(path, handler);
    }

    public static void staticfiles(String location) {
        staticFilesLocation = location;
    }


    public static void main(String[] args) throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        boolean running = true;

        while (running) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            boolean firstLine = true;
            String reqpath = "";
            String query = "";

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (firstLine) {
                    String[] reqTokens = inputLine.split(" ");
                    String method   = reqTokens[0];
                    String struri   = reqTokens[1];
                    String protocol = reqTokens[2];

                    URI requri = new URI(struri);
                    reqpath = requri.getPath();
                    query   = requri.getQuery() != null ? requri.getQuery() : "";

                    System.out.println("Request path: " + reqpath);
                    firstLine = false;
                }
                if (!in.ready()) {
                    break;
                }
            }

            String outputLine;


            if (routes.containsKey(reqpath)) {
                Request  req  = new Request("GET", reqpath, query, new LinkedHashMap<>());
                Response res  = new Response();
                String   body = routes.get(reqpath).handle(req, res);

                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: " + res.getContentType() + "\r\n"
                        + "Content-Length: " + body.length() + "\r\n"
                        + "\r\n"
                        + body;


            } else if (reqpath.equals("/pi")) {
                outputLine = "HTTP/1.1 200 OK\n\r"
                        + "Content-Type: text/html\n\r"
                        + "\n\r"
                        + "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>PI</title>\n"
                        + "</head>"
                        + "<body>"
                        + Math.PI
                        + "</body>"
                        + "</html>";


            } else {
                String filePath = reqpath.equals("/") ? "/webroot/index.html" : reqpath;
                InputStream fileStream = HttpServer.class
                        .getResourceAsStream(staticFilesLocation + filePath);

                if (fileStream != null) {
                    byte[] fileBytes = fileStream.readAllBytes();
                    fileStream.close();
                    String contentType = getContentType(filePath);
                    String header = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: " + contentType + "\r\n"
                            + "Content-Length: " + fileBytes.length + "\r\n"
                            + "\r\n";
                    clientSocket.getOutputStream().write(header.getBytes());
                    clientSocket.getOutputStream().write(fileBytes);
                    clientSocket.getOutputStream().flush();
                    out.close(); in.close(); clientSocket.close();
                    continue;
                }


                outputLine = "HTTP/1.1 200 OK\n\r"
                        + "Content-Type: text/html\n\r"
                        + "\n\r"
                        + "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>Title of the document</title>\n"
                        + "</head>"
                        + "<body>"
                        + "My Web Site"
                        + "</body>"
                        + "</html>";
            }

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    private static String getContentType(String path) {
        if (path.endsWith(".html") || path.endsWith(".htm")) return "text/html";
        if (path.endsWith(".css"))  return "text/css";
        if (path.endsWith(".js"))   return "application/javascript";
        if (path.endsWith(".json")) return "application/json";
        if (path.endsWith(".png"))  return "image/png";
        if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
        if (path.endsWith(".gif"))  return "image/gif";
        if (path.endsWith(".svg"))  return "image/svg+xml";
        if (path.endsWith(".ico"))  return "image/x-icon";
        return "application/octet-stream";
    }
}