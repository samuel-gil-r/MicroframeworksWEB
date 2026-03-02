package utilities;

public class Response {

    private String contentType = "text/html";
    private int statusCode = 200;

    public String getContentType()          { return contentType; }
    public void setContentType(String ct)   { this.contentType = ct; }
    public int getStatusCode()              { return statusCode; }
    public void setStatusCode(int code)     { this.statusCode = code; }
}
