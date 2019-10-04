import models.Upload;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("username", request.session().attribute("username"));
            return new ModelAndView(model, "welcome.hbs");
        }, new HandlebarsTemplateEngine());

        get("/welcome", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("username", request.session().attribute("username"));
            return new ModelAndView(model, "welcome.hbs");
        }, new HandlebarsTemplateEngine());

        get("/adminhomepage", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("username", request.session().attribute("username"));
            return new ModelAndView(model, "adminhomepage.hbs");
        }, new HandlebarsTemplateEngine());
//
        post("/uploads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String picture = request.queryParams("picture");
            String caption = request.queryParams("caption");
            String video = request.queryParams("video");
            Upload newUpload = new Upload(picture, caption, video);
            newUpload.save(newUpload);
            List<Upload> uploads = Upload.getAll();
            model.put("uploads", Upload.getAll());
            return new ModelAndView(model, "adminhomepage.hbs");
        }, new HandlebarsTemplateEngine());
//
        get("/userhomepage", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("uploads", Upload.getAll());
//            model.put("username", request.session().attribute("username"));
            return new ModelAndView(model, "userhomepage.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
