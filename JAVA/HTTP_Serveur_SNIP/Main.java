package tokenLuggage;

import com.sun.net.httpserver.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Main {
	
	public static void parseQuery(String query, Map<String, 
			Object> parameters) throws UnsupportedEncodingException {

		         if (query != null) {
		                 String pairs[] = query.split("[&]");
		                 for (String pair : pairs) {
		                          String param[] = pair.split("[=]");
		                          String key = null;
		                          String value = null;
		                          if (param.length > 0) {
		                          key = URLDecoder.decode(param[0], 
		                          	System.getProperty("file.encoding"));
		                          }

		                          if (param.length > 1) {
		                                   value = URLDecoder.decode(param[1], 
		                                   System.getProperty("file.encoding"));
		                          }

		                          if (parameters.containsKey(key)) {
		                                   Object obj = parameters.get(key);
		                                   if (obj instanceof List<?>) {
		                                            List<String> values = (List<String>) obj;
		                                            values.add(value);

		                                   } else if (obj instanceof String) {
		                                            List<String> values = new ArrayList<String>();
		                                            values.add((String) obj);
		                                            values.add(value);
		                                            parameters.put(key, values);
		                                   }
		                          } else {
		                                   parameters.put(key, value);
		                          }
		                 }
		         }
	}
	
	public static void main(String[] args) throws Exception {
	    
		try {
			// setup the socket address
			InetSocketAddress address = new InetSocketAddress(9000);

			// initialise the HTTPS server
			HttpServer server = HttpServer.create(address, 0);
			
			System.out.println("server started at 9000");
			server.createContext("/", new RootHandler());
			server.createContext("/echoPost", new EchoPostHandler());
			server.setExecutor(null);
			server.start();
			
		} catch (Exception exception) {
			System.out.println("Failed to create HTTP server on port 9000 of localhost");
			exception.printStackTrace();

		}
	}

}


