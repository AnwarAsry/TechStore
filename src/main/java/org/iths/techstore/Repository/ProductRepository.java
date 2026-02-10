package org.iths.techstore.Repository;

import org.iths.techstore.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {
}
