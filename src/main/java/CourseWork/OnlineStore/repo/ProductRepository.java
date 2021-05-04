package CourseWork.OnlineStore.repo;

import CourseWork.OnlineStore.models.Product;
import CourseWork.OnlineStore.models.ProductType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    public List<Product> findByProductType(ProductType productType); // находит все продукты данного типа.

}
