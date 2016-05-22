package tokenLuggage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EchoPostHandler implements HttpHandler {

    @Override

    public void handle(HttpExchange he) throws IOException {
            // parse request
    		
            Map<String, Object> parameters = new HashMap<String, Object>();
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String query = br.readLine();
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
            he.sendResponseHeaders(200, json.length());
            
            OutputStream os = he.getResponseBody();
            //parse response 
            os.write(json.getBytes());
            //send fields
            os.close();
    }
}




