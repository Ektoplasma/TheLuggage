package tokenLuggage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EchoGetHandler implements HttpHandler {

    @Override

    public void handle(HttpExchange he) throws IOException {
            // parse request
            Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = he.getRequestURI();
            String query = requestedUri.getRawQuery();
            Main.parseQuery(query, parameters);

            // send response
            Headers respheader = he.getResponseHeaders();
            respheader.set("Content-Type", "application/json; charset=utf-8");
            final GsonBuilder builder = new GsonBuilder();
            final Gson gson = builder.create();
            String response = "";
            for (String key : parameters.keySet())
                     response += key + " = " + parameters.get(key) + "\n";
            final StatusHttp stathttp = new StatusHttp(response);
            final String json = gson.toJson(stathttp);
            System.out.println("Resultat = " + json);
            he.sendResponseHeaders(200, json.length());
            OutputStream os = he.getResponseBody();
            os.write(json.toString().getBytes());

            os.close();
    }
}
