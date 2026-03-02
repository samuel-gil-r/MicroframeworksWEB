package utilities;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private final String method;
    private final String path;
    private final Map<String, String> queryParams;
    private final Map<String, String> headers;

    public Request(String method, String path, String query, Map<String, String> headers) {
        this.method = method;
        this.path = path;
        this.queryParams = parseQuery(query);
        this.headers = headers;
    }

    private Map<String, String> parseQuery(String query) {
        Map<String, String> params = new HashMap<>();
        if (query == null || query.isEmpty()) return params;
        for (String pair : query.split("&")) {
            String[] kv = pair.split("=", 2);
            if (kv.length == 2) params.put(kv[0], kv[1]);
            else if (kv.length == 1) params.put(kv[0], "");
        }
        return params;
    }

    public String getValues(String key) {
        return queryParams.get(key);
    }

    public String getMethod() { return method; }
    public String getPath()   { return path; }
    public Map<String, String> getHeaders() { return headers; }
}
