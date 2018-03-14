package com.broughton.productsandcategories.controllers;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.broughton.productsandcategories.models.*;
import com.broughton.productsandcategories.services.ProductsAndCategoriesService;

@Controller
public class ProductsAndCategoriesController {
	private ProductsAndCategoriesService service;
	public ProductsAndCategoriesController(ProductsAndCategoriesService service) {
		this.service = service;
	}
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	// product controls
	@RequestMapping("/products/new")
	public String renderNewProduct(Model model) {
		model.addAttribute("product", new Product());
		return "newproduct.jsp";
	}
	@RequestMapping(path="/create/product", method=RequestMethod.POST)
	public String createProduct(
			@ModelAttribute("product") Product product,
			BindingResult result) {
		service.createProduct(product);
		return "redirect:/products/new";
	}
	@RequestMapping("/products/{id}")
	public String renderViewProduct(@PathVariable("id") Long id, Model model) {
		Product product = service.findProduct(id);
		List<String> productCategories = service.getProductCategories(id);
		List<Category> notProductCategories = service.getNotProductCategories(id);
		model.addAttribute("product", product);
		model.addAttribute("productCategories", productCategories);
		model.addAttribute("notProductCategories", notProductCategories);
		return "viewproduct.jsp";
	}
	@RequestMapping(path="/bind/category/{id}", method=RequestMethod.POST)
	public String bindCategory(
			@PathVariable("id") Long id,
			@RequestParam(value="categoryId") String categoryId) {
		service.bindCategory(id, Long.valueOf(categoryId));
		return "redirect:/products/{id}";
	}
	// category controls
	@RequestMapping("/categories/new")
	public String renderNewCategory(Model model) {
		model.addAttribute("category", new Category());
		return "newcategory.jsp";
	}
	@RequestMapping(path="/create/category", method=RequestMethod.POST)
	public String createCategory(
			@ModelAttribute("category") Category category,
			BindingResult result) {
		service.createCategory(category);
		return "redirect:/categories/new";
	}
	@RequestMapping("/categories/{id}")
	public String renderViewCategory(@PathVariable("id") Long id, Model model) {
		Category category = service.findCategory(id);
		List<String> categoryProducts = service.getCategoryProducts(id);
		List<Product> notCategoryProducts = service.getNotCategoryProducts(id);
		model.addAttribute("category", category);
		model.addAttribute("categoryProducts", categoryProducts);
		model.addAttribute("notCategoryProducts", notCategoryProducts);
		return "viewcategory.jsp";
	}
	@RequestMapping(path="/bind/product/{id}", method=RequestMethod.POST)
	public String bindProduct(
			@PathVariable("id") Long id,
			@RequestParam(value="productId") String productId) {
		service.bindProduct(id, Long.valueOf(productId));
		return "redirect:/categories/{id}";
	}
	
}
