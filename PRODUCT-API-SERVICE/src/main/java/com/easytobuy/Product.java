package com.easytobuy;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;

@Entity
@Table(name = "product")
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long productId;
@Column(name = "productName")
private String productName;
@Column(name = "productDescription")
private String productDescription;
@Column(name = "productProce")
private Double productPrice;
@Column(name = "couponCode")
private String couponCode;

private Integer CategoryId;
public Long getProductId() {
	return productId;
}
public void setProductId(Long productId) {
	this.productId = productId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getProductDescription() {
	return productDescription;
}
public void setProductDescription(String productDescription) {
	this.productDescription = productDescription;
}
public Double getProductPrice() {
	return productPrice;
}
public void setProductPrice(Double productPrice) {
	this.productPrice = productPrice;
}
public String getCouponCode() {
	return couponCode;
}
public void setCouponCode(String couponCode) {
	this.couponCode = couponCode;
}
public Integer getCategoryId() {
	return CategoryId;
}
public void setCategoryId(Integer categoryId) {
	CategoryId = categoryId;
}


}
