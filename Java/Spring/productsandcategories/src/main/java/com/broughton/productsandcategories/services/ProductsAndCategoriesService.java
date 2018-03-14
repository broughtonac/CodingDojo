package com.broughton.productsandcategories.services;

import java.math.BigInteger;
import java.util.*;

import org.springframework.stereotype.Service;
import com.broughton.productsandcategories.models.*;
import com.broughton.productsandcategories.repositories.*;

@Service
public class ProductsAndCategoriesService {
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	public ProductsAndCategoriesService(
			ProductRepository productRepository,
			CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	// product services
	public void createProduct(Product product) {
		productRepository.save(product);
	}
	public Product findProduct(Long id) {
		return productRepository.findOne(id);
	}
	public List<Product> findAllProducts() {
		return (List<Product>) productRepository.findAll();
	}
	public List<String> getProductCategories(Long productId) {
		return productRepository.queryProductCategories(productId);
	}
	public List<Category> getNotProductCategories(Long productId) {
		if (productRepository.jointRows().equals(BigInteger.valueOf(0))) {
			return this.findAllCategories();
		}
		else {
			List<Category> notProductCategories = new ArrayList<Category>();
			List<BigInteger> categoryIds = productRepository.queryNotProductCategories(productId);
			for (BigInteger id : categoryIds) {
				if (id != null) {
					Category category = this.findCategory(id.longValue());
					notProductCategories.add(category);
				}
			}
			return notProductCategories;
		}
	}
	public void bindCategory(Long productId, Long categoryId) {
		Product product = this.findProduct(productId);
		Category category = this.findCategory(categoryId);
		List<Category> categories = product.getCategories();
		if (!(categories.contains(category))) {
			categories.add(category);
			productRepository.save(product);
		}
	}
	// category services
	public void createCategory(Category category) {
		categoryRepository.save(category);
	}
	public Category findCategory(Long id) {
		return categoryRepository.findOne(id);
	}
	public List<Category> findAllCategories() {
		return (List<Category>) categoryRepository.findAll();
	}
	public List<String> getCategoryProducts(Long categoryId) {
		return categoryRepository.queryCategoryProducts(categoryId);
	}
	public List<Product> getNotCategoryProducts(Long categoryId) {
		if (productRepository.jointRows().equals(BigInteger.valueOf(0))) {
			return this.findAllProducts();
		}
		else {
			List<Product> notCategoryProducts = new ArrayList<Product>();
			List<BigInteger> productIds = categoryRepository.queryNotCategoryProducts(categoryId);
			for (BigInteger id : productIds) {
				if (id != null) {
					Product product = this.findProduct(id.longValue());
					notCategoryProducts.add(product);
				}
			}
			return notCategoryProducts;
		}
	}
	public void bindProduct(Long categoryId, Long productId) {
		Category category = this.findCategory(categoryId);
		Product product = this.findProduct(productId);
		List<Product> products = category.getProducts();
		if (!(products.contains(product))) {
			products.add(product);
			categoryRepository.save(category);
		}
	}
}
