package CourseWork.OnlineStore.models;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private Double price;

    private String image;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    public Product() {}

    public Product(String name, Double price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }
}
