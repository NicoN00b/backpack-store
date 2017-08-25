import com.google.gson.Gson;
import dao.Sql2oBikepackDao;
import dao.Sql2oHikepackDao;
import dao.Sql2oSurfpackDao;
import models.Backpack;
import models.Bikepack;
import models.Hikepack;
import models.Surfpack;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import dao.BackPackDao;

import java.util.List;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        Sql2oBikepackDao bikepackDao;
        Sql2oHikepackDao hikepackDao;
        Sql2oSurfpackDao surfpackDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";

        Sql2o sql2o = new Sql2o(connectionString, "", "");
        bikepackDao = new Sql2oBikepackDao(sql2o);
        hikepackDao = new Sql2oHikepackDao(sql2o);
        surfpackDao = new Sql2oSurfpackDao(sql2o);
        conn = sql2o.open();

        get("/bikepacks", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(bikepackDao.getAll());
        });


        post("/bikepacks/new", "application/json", (req, res) ->{
            Bikepack bikepack= gson.fromJson(req.body(), Bikepack.class);
            bikepackDao.add(bikepack);
            res.status(201);
            res.type("application/json");
            return gson.toJson(bikepack);
        });

        post("/hikepacks/new", "application/json", (req, res) ->{
            Hikepack hikepack= gson.fromJson(req.body(), Hikepack.class);
            hikepackDao.add(hikepack);
            res.status(201);
            res.type("application/json");
            return gson.toJson(hikepack);
        });

        post("/surfpacks/new", "application/json", (req, res) ->{
            Surfpack surfpack= gson.fromJson(req.body(), Surfpack.class);
            surfpackDao.add(surfpack);
            res.status(201);
            res.type("application/json");
            return gson.toJson(surfpack);
        });


        get("/hikepacks", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(hikepackDao.getAll());
        });

        get("/surfpacks", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(surfpackDao.getAll());
        });

        get("/backpacks/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int backpackId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson((backpackId));
        });

    }
}
