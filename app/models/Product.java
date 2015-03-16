package models;

import play.db.ebean.Model;

import javax.persistence.*;

@Table(
    uniqueConstraints=
        @UniqueConstraint(columnNames={"name"})
)
@Entity
public class Product extends Model {

    @Id
    public Integer id;

    public String name;

    public float price;

    public int amountAvailable;

    public static Finder<Integer, Product> find = new Finder<>(Integer.class, Product.class);

    public static Product findByName(String name) {
        return find.where().eq("name", name).findUnique();
    }
}
