import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpServer;

import initToken.Projet;

public class serverADDON {
		
		private HttpServer server;
		private Projet p;
		
		public void setProjet(Projet p){
			this.p=p;
		}
		
		public void setServ(HttpServer s){
			this.server=s;
		}
		
		public HttpServer getServ(){
			return this.server;
		}
		
		public Projet getProjet(){
			return this.p;
		}
		
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
		
		public void stopServer(){
			getServ().stop(0);
		}
		
		public void initServer() {
		    
			try {
				// setup the socket address
				InetSocketAddress address = new InetSocketAddress(9000);
				EchoPostHandler e = new EchoPostHandler();
				e.setProjet(getProjet());

				// initialise the HTTPS server
				HttpServer server = HttpServer.create(address, 0);
				
				System.out.println("server started at 9000");
				server.createContext("/", new RootHandler());
				server.createContext("/theluggage", e);
				server.setExecutor(null);
				setServ(server);
				getServ().start();
				
				
			} catch (Exception exception) {
				System.out.println("Failed to create HTTP server on port 9000 of localhost");
				exception.printStackTrace();

			}
		}
	   }
	 		

