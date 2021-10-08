package mx.uv;

import static spark.Spark.*;

import java.io.Console;
import com.google.gson.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        options("/*", (request, response) -> {
        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
        if (accessControlRequestHeaders != null) {
            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
        }
        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
        if (accessControlRequestMethod != null) {
            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
        }
        return "OK";
    });

    before((request, response) -> response.header("Access-Control-Allow-Origin", "*" ));

    post("/iniciarSesion", (rq, rs) -> {
        String request = rq.body();
        System.out.println("Request: " + request );
        JsonParser parser = new JsonParser();
        JsonElement arbol = parser.parse( request );
        JsonObject peticion = arbol.getAsJsonObject();
        Object nombre =  peticion.get("nombre") ;
        Object contrasena = peticion.get("contrasena");
        String nombreS = nombre.toString().substring(1, nombre.toString().length()-1);
        String contrasenaS = contrasena.toString().substring(1, contrasena.toString().length()-1);      
        if(nombreS.equals("justin") && contrasenaS.equals("1234")) {
            return true;
        }else{
            return false;
        }
    });

    get("/registrarUsuario", (rq, rs) -> {
        JsonObject respuesta = new JsonObject();
        respuesta.addProperty("nombre", rq.queryParams("nombre"));
        respuesta.addProperty("contrasena", rq.queryParams("contrasena"));
        return respuesta;
    });

    }

}