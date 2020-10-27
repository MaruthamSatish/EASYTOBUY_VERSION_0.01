/**
 * 
 */
package com.easytobuy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author SatishReddy
 *
 */
@CrossOrigin(origins = "http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategoryId(Integer categoryId);
}
