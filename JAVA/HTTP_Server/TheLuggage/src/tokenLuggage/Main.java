package tokenLuggage;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
//import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;

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

import javax.net.ssl.*;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;


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
	    
	    //SSLContext sslContext = SSLContext.getInstance("TLS");
	     
		//HttpsServer server = HttpsServer.create(new InetSocketAddress(9000), 0);
		
		/*FileReader fr = new FileReader("/home/helvestrom/workspace/TheLuggage/src/tokenLuggage/private.key");
		BufferedReader br = new BufferedReader(fr);
		String str = "";
		String rsaPriv = "";
		while((str = br.readLine())!=null){
		        rsaPriv += str + "\n";
		}
		br.close();
		System.out.println(rsaPriv);*/
		
		/*char[] keystorePassword = "rillettes".toCharArray();
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(new FileInputStream("/home/helvestrom/workspace/TheLuggage/src/tokenLuggage/server_keystore.jks"), keystorePassword);
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(ks, keystorePassword);
		                
		sslContext.init(kmf.getKeyManagers(), null, null);
		
		HttpsConfigurator configurator = new HttpsConfigurator(sslContext);
		server.setHttpsConfigurator(configurator);*/
		//HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);
	    
        try {
            // setup the socket address
            InetSocketAddress address = new InetSocketAddress(9000);

            // initialise the HTTPS server
            HttpsServer server = HttpsServer.create(address, 0);
            SSLContext sslContext = SSLContext.getInstance("TLS");

            // initialise the keystore
            char[] password = "rillettes".toCharArray();
            KeyStore ks = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream("/home/helvestrom/workspace/TheLuggage/src/tokenLuggage/server_keystore.jks");
            ks.load(fis, password);

            // setup the key manager factory
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);

            // setup the trust manager factory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);

            // setup the HTTPS context and parameters
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            server.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
                public void configure(HttpsParameters params) {
                    try {
                        // initialise the SSL context
                        SSLContext c = SSLContext.getDefault();
                        SSLEngine engine = c.createSSLEngine();
                        params.setNeedClientAuth(false);
                        params.setCipherSuites(engine.getEnabledCipherSuites());
                        params.setProtocols(engine.getEnabledProtocols());

                        // get the default parameters
                        SSLParameters defaultSSLParameters = c.getDefaultSSLParameters();
                        params.setSSLParameters(defaultSSLParameters);

                    } catch (Exception ex) {
                        System.out.println("Failed to create HTTPS port");
                    }
                }
            });
		
		System.out.println("server started at " + 9000);
		server.createContext("/", new RootHandler());
		server.createContext("/echoHeader", new EchoHeaderHandler());
		server.createContext("/echoGet", new EchoGetHandler());
		server.createContext("/echoPost", new EchoPostHandler());
		server.setExecutor(null);
		server.start();
		
        } catch (Exception exception) {
            System.out.println("Failed to create HTTPS server on port " + 8000 + " of localhost");
            exception.printStackTrace();

        }
	}

}




