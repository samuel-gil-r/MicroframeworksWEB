package appxamples;

import utilities.HttpServer;

public class Webapp {
    public static void main(String[] args) throws Exception {

        HttpServer.staticfiles("/webroot");

        HttpServer.get("/App/hello", (req, res) -> "Hello " + req.getValues("name"));

        HttpServer.get("/App/pi", (req, res) -> String.valueOf(Math.PI));

        HttpServer.main(args);
    }
}
