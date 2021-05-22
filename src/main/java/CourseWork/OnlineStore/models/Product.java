package CourseWork.OnlineStore.models;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private Double price;

    private String image;


    @Column(length = 65553)
    @Type(type = "text")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    public Product() {
    }

    public Product(String name, Double price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
