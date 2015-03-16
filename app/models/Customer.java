package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends Model {

    @Id
    public Integer id;

    public String firstName;

    public String lastName;

    public float moneySpent;

    @ManyToOne
    public List<Product> productsPurchased = new ArrayList<>();

    public static Finder<Integer, Customer> find = new Finder<>(Integer.class, Customer.class);

    public static List<Customer> findByFirstName(String firstName) {
        return find.where().eq("firstName", firstName).findList();
    }

    public static List<Customer> findByProduct(String productName) {
        // This method of finding the list of customers who purchased a product is very inefficient.
        // The proper way of doing it would be to write a SQL query, however, this will work for our
        // purposes.
        Product product = Product.findByName(productName);

        List<Customer> customersWhoPurchasedProduct = new ArrayList<>();

        for (Customer customer : Customer.find.all()) {
            if (customer.productsPurchased.contains(product)) {
                customersWhoPurchasedProduct.add(customer);
            }
        }

        return customersWhoPurchasedProduct;
    }
}
