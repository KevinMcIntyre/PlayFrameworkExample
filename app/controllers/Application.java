package controllers;

import com.avaje.ebean.Ebean;
import models.Product;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import java.util.List;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render());
    }

    public static Result products() {
        List<Product> productList = Product.find.all();
        return ok(views.html.products.render(productList));
    }

    public static Result newProduct() {
        return ok(views.html.addProduct.render());
    }

    public static Result createProduct() {
        DynamicForm productForm = Form.form().bindFromRequest();

        String productName = productForm.get("product-name");
        String productPrice = productForm.get("product-price");
        String productAmount = productForm.get("product-amount");

        // let's make sure the product price doesn't have a
        // leading dollar sign when it's parsed into a float
        if (productPrice.charAt(0) == '$') {
            productPrice = productPrice.substring(1, productPrice.length());
        }

        Product newProduct = new Product();
        newProduct.name = productName;
        newProduct.price = Float.parseFloat(productPrice);
        newProduct.amountAvailable = Integer.parseInt(productAmount);
        newProduct.save();

        return ok(views.html.products.render(Product.find.all()));
    }

    public static Result editProduct(Integer productId) {
        Product product = null;

        if (productId != null) {
            product = Product.find.byId(productId);
        }

        if (product == null) {
            return badRequest("Error occured");
        }

        return ok(views.html.editProduct.render(product));
    }

    public static Result updateProduct(Integer productId) {
        DynamicForm productForm = Form.form().bindFromRequest();

        String productName = productForm.get("product-name");
        String productPrice = productForm.get("product-price");
        String productAmount = productForm.get("product-amount");

        // let's make sure the product price doesn't have a
        // leading dollar sign when it's parsed into a float
        if (productPrice.charAt(0) == '$') {
            productPrice = productPrice.substring(1, productPrice.length());
        }

        Product product = Product.find.byId(productId);
        product.name = productName;
        product.price = Float.parseFloat(productPrice);
        product.amountAvailable = Integer.parseInt(productAmount);
        product.update();

        return ok(views.html.products.render(Product.find.all()));
    }

    public static Result deleteProduct(Integer productId) {
        if (productId != null && productId > 0) {
            Ebean.delete(Product.find.byId(productId));
        }

        return ok(views.html.products.render(Product.find.all()));
    }
}
