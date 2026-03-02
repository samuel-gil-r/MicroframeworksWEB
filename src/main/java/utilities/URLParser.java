package utilities;

import java.net.MalformedURLException;
import java.net.URL;

public class URLParser {
    public static void main(String[] args) throws MalformedURLException {

        URL myurl = new URL("http://is.escuelaing.edu.co:7654/respuestas/respuesta.txt?val=7&t=3#pubs");

        System.out.println("Protocolo: " + myurl.getProtocol());
        System.out.println("Protocolo: " + myurl.getProtocol());
        System.out.println("Host: "      + myurl.getHost());
        System.out.println("Puerto: "    + myurl.getPort());
        System.out.println("Path: "      + myurl.getPath());
        System.out.println("Query: "     + myurl.getQuery());
        System.out.println("Ref: "       + myurl.getRef());
    }
}