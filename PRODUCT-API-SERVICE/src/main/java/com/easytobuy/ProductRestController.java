/**
 * 
 */
package com.easytobuy;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SatishReddy
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")

public class ProductRestController {
	@Autowired
	private CouponProxy couponProxy;
	
	@Autowired
	private CategoryProxy categoryProxy;

	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> findByProductCode(@PathVariable("productId") Long productId) {
		Optional<Product> getProduct = productRepository.findById(productId);
		if (getProduct.isPresent()) {
			return new ResponseEntity<List<Product>>(HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> getAllProducts = productRepository.findAll().stream().collect(Collectors.toList());
		return new ResponseEntity<List<Product>>(getAllProducts, HttpStatus.OK);

	}

	@PostMapping("/products")
	
	public ResponseEntity<Product> saveProduct(@RequestBody Product products) {
		try {
			//Get Discount By CouponCode
			List<Coupon> getCouponsByCode=couponProxy.findByCouponCode(products.getCouponCode());
			List<Category> getCategoryByName=categoryProxy.findByCategoryName(products.getCategoryName());
			double discountPrice=getCouponsByCode.stream().findFirst().get().getDiscount();
		    products.setCategoryId(getCategoryByName.stream().findFirst().get().getCategoryId());
			products.setProductPrice(products.getProductPrice()-discountPrice);
			productRepository.save(products);
			return new ResponseEntity<Product>(products, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public ResponseEntity<Product> couponProxy(@RequestBody Product products) {
		try {
			return new ResponseEntity<>(new Product(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/products/{productId}")
	public HttpStatus deleteProduct(@PathVariable("productId") Long ProductId) {
		Optional<Product> Product = productRepository.findById(ProductId);
		if (Product.isPresent()) {
			productRepository.deleteById(ProductId);
			return HttpStatus.NO_CONTENT;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}
	@GetMapping("/products/category/{categoryId}")
	public ResponseEntity<List<Product>> findByCategoryId(@PathVariable("categoryId") Integer categoryId) {
		List<Product> getProductsByCategoryId = productRepository.findByCategoryId(categoryId);
		/* getProductsByCategoryId.stream().collect(Collectors.toList()); */
		return new ResponseEntity<List<Product>>(getProductsByCategoryId, HttpStatus.OK);

	}
}
