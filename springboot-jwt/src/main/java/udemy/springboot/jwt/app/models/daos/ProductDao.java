package udemy.springboot.jwt.app.models.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import udemy.springboot.jwt.app.models.entities.Product;

import java.util.List;

public interface ProductDao extends CrudRepository<Product, Long> {

    @Query("select p from Product p where p.name like %?1%")
    List<Product> findByName(String term);

    List<Product> findByNameLikeIgnoreCase(String term);
}
