package sit.int202.onlineshopwebapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

@Entity
@Table(name = "products")

@NamedQueries({
        @NamedQuery(name = "PRODUCT.FIND_ALL", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "PRODUCT.COUNT", query = "SELECT count(p) FROM Product p")
})
public class Product {
    @Id
    private String productCode;
    private String productName;
    private String productLine;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private int quantityInStock;
    private double buyPrice;
    private double msrp;
}
