import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RootHandler implements HttpHandler {

	
	private String username = "";
	
	public RootHandler(String s)
	{
		this.setUsername(s);
	}
    @Override

    public void handle(HttpExchange he) throws IOException {
            String response = getUsername();
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
    }
    
    public String getUsername(){
    	return this.username;
    }
    public void setUsername(String s){
    	this.username=s;
    }
}
