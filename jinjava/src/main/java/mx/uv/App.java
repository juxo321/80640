package mx.uv;

import static spark.Spark.*;

import spark.ModelAndView;
import spark.template.jinjava.JinjavaEngine;
//import spark.template.pebble.PebbleTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.*;
import com.hubspot.jinjava.Jinjava;

import mx.uv.datos.Automovil;

/**
 * Hello world!
 *
 */
public class App {
    private static Gson gson = new Gson();
    private static Map<String, Automovil> automoviles = new HashMap<>();
    public static void main( String[] args ){
        port(getHerokuAssignedPort());
        staticFiles.location("/");

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

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("nombre", automoviles.values());
            return new JinjavaEngine().render(
                new ModelAndView(model, "index.html")
            );
		});
        
        post("/registrar", (req, res) -> {
            String json = req.body();
            Automovil a = gson.fromJson(json, Automovil.class);
            String id = UUID.randomUUID().toString();
            
            a.setId(id);
           
            System.out.println(a.toString());

            automoviles.put(id, a);

            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", "creado");
            respuesta.addProperty("id", id);
            return respuesta;
        });

        // do this
        get("/jinjava", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("nombre", automoviles.values());
            return new JinjavaEngine().render(
                new ModelAndView(model, "templates/jinjava.jinja")
            );
		});
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
