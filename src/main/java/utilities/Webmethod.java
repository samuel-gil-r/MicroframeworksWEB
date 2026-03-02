package utilities;

@FunctionalInterface
public interface Webmethod {
    String handle(Request req, Response res);
}
