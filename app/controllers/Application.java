package controllers;

import models.Product;
import play.*;
import play.mvc.*;

import views.html.*;

import java.util.List;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Home"));
    }

    public static Result products() {
        List<Product> productList = Product.find.all();
        return ok(views.html.products.render("Products", productList));
    }
}
