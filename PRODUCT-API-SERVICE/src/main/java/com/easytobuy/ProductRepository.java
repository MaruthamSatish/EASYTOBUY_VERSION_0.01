/**
 * 
 */
package com.easytobuy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author SatishReddy
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategoryId(Integer categoryId);
}
