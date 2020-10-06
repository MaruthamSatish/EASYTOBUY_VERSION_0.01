/**
 * 
 */
package com.easytobuy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SatishReddy
 *
 */
@FeignClient(name = "category-api")
public interface CategoryProxy {
	@RequestMapping(value = "/api/categories/{categoryName}", method = RequestMethod.GET)
	List<Category> findByCategoryName(@PathVariable("categoryName")String categoryName);
}
