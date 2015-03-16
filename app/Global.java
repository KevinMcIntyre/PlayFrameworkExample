import models.Customer;
import models.Product;
import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        // This is just to add some initial (seed) data so that
        // there is something in the application when you launch
        // it for the first time.

        if (Product.find.all().isEmpty()) {
            Product bananas = new Product();
            bananas.name = "Bananas";
            bananas.price = 4.00f;
            bananas.amountAvailable = 201;
            bananas.save();

            Product apples = new Product();
            apples.name = "Apples";
            apples.price = 3.00f;
            apples.amountAvailable = 105;
            apples.save();

            Product oranges = new Product();
            oranges.name = "Oranges";
            oranges.price = 2.00f;
            oranges.amountAvailable = 54;
            oranges.save();
        }

        if (Customer.find.all().isEmpty()) {
            Customer customer1 = new Customer();
            customer1.firstName = "Johnny";
            customer1.lastName = "Quest";
            customer1.moneySpent = 0;
            customer1.save();

            Customer customer2 = new Customer();
            customer2.firstName = "Bob";
            customer2.lastName = "Roberts";
            customer2.moneySpent = 0;
            customer2.save();
        }
    }
}
