package CourseWork.OnlineStore.repo;

import CourseWork.OnlineStore.models.ProductType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends CrudRepository<ProductType, Long>
{

}
