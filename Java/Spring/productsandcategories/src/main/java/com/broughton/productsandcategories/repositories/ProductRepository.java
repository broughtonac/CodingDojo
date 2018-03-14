package com.broughton.productsandcategories.repositories;

import java.math.*;
import java.util.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.broughton.productsandcategories.models.*;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
	// get number of rows in join table
	@Query(value=
			"SELECT \n" + 
			"    COUNT(product_id)\n" + 
			"FROM\n" + 
			"    categories_products", nativeQuery=true)
	BigInteger jointRows();
	// get list of categories to which product belongs
	@Query(value=
			"SELECT \n" + 
			"    categories.name\n" + 
			"FROM\n" + 
			"    categories\n" + 
			"        JOIN\n" + 
			"    categories_products ON categories.id = categories_products.category_id\n" + 
			"WHERE\n" + 
			"    categories_products.product_id = :productId", nativeQuery=true)
	List<String> queryProductCategories(@Param("productId") Long productId);
	// get list of categories to which product does not belong
	@Query(value=
			"SELECT \n" + 
			"    categories.id\n" + 
			"FROM\n" + 
			"    categories\n" + 
			"WHERE\n" + 
			"    categories.id NOT IN (SELECT \n" + 
			"            category_id\n" + 
			"        FROM\n" + 
			"            categories_products\n" + 
			"        WHERE\n" + 
			"            categories_products.product_id = :productId);", nativeQuery=true)
	List<BigInteger> queryNotProductCategories(@Param("productId") Long productId);
}
