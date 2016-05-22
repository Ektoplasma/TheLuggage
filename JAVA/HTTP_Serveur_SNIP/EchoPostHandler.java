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
            /*ICI tu récupères le corps de la requête (data=blabla&toto=blibli...)*/
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
            /*ICI tu mets ça dans un buffer pour le traitement*/
            BufferedReader br = new BufferedReader(isr);
            /*ICI tu récupères la première ligne (celle qui nous intéresse pour ce genre de requête)*/
            String query = br.readLine();
            /*ICI tu le rentres dans un parser que j'ai codé dans le main, ça te permettra de récupérer les noms :
                data et toto, par exemple, dans une map (donc tu peux récup les données par rapport aux champs, c'est une map quoi)*/
            Main.parseQuery(query, parameters);

            /* C'EST ICI QUE TU RECUP PUIS TRAITES LA DONNEE PARSEE*/
            String parsed = "";
            /*SOIT "data" le mot clé qui nous intéresse (elle contient la donnée parsée)*/
            String key = "data";
            parsed = parameters.get(key);

            /*String parsed = "";//snippet si tu veux boucler
            for (String key : parameters.keySet())
                     parsed += key + " = " + parameters.get(key) + "\n";
            */

            /* TU PEUX ENFIN DEPARSE AVEC LA CLASSE DE FLORENT PUIS JE VOUS LAISSE FAIRE LA SUITE*/



            /*.................*/



            /* ========== FIN DE VOTRE TRAITEMENT =========*/

            /* ICI ON PREPARE LA REPONSE A ENVOYER */
            /*les headers à utiliser (vive la lib qui fait ça à notre place)*/
            Headers respheader = he.getResponseHeaders();
            respheader.set("Content-Type", "application/json; charset=utf-8");

            /*la réponse à envoyer*/
            String prereponse = "votre réponse parsée par la classe de florent";

            /*un wrapper que j'ai codé qui était surtout utile quand j'utilisais le JSON */
            final StatusHttp stathttp = new StatusHttp(preresponse);
            final String reponse = stathttp.getStatus();

            /*ICI on envoie la réponse*/
            he.sendResponseHeaders(200, reponse.length());
            OutputStream os = he.getResponseBody();
            os.write(reponse.getBytes());
            os.close();
    }
}

