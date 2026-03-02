package utilities;

import java.net.*;
import java.io.*;
import java.util.*;

public class URLReader {
    public static void main(String[] args) throws Exception {

        URL siteURL = new URL("http://www.google.com/");
        URLConnection urlConnection = siteURL.openConnection();

        Map<String, List<String>> headers = urlConnection.getHeaderFields();
        Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();

        System.out.println("===== HEADERS =====");
        for (Map.Entry<String, List<String>> entry : entrySet) {
            String headerName = entry.getKey();
            if (headerName != null) {
                System.out.print(headerName + ": ");
            }
            List<String> headerValues = entry.getValue();
            for (String value : headerValues) {
                System.out.print(value);
            }
            System.out.println("");
        }

        System.out.println("\n-----message-body-----");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            System.out.println(inputLine);
        }
        reader.close();
    }
}