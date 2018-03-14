package com.broughton.productsandcategories.repositories;

import java.math.BigInteger;
import java.util.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.broughton.productsandcategories.models.*;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
	@Query(value=
			"SELECT \n" + 
			"    products.name\n" + 
			"FROM\n" + 
			"    products\n" + 
			"        JOIN\n" + 
			"    categories_products ON products.id = categories_products.product_id\n" + 
			"WHERE\n" + 
			"    categories_products.category_id = :categoryId", nativeQuery=true)
	List<String> queryCategoryProducts(@Param("categoryId") Long categoryId);
	@Query(value=
			"SELECT \n" + 
			"    products.id\n" + 
			"FROM\n" + 
			"    products\n" + 
			"WHERE\n" + 
			"    products.id NOT IN (SELECT \n" + 
			"            product_id\n" + 
			"        FROM\n" + 
			"            categories_products\n" + 
			"        WHERE\n" + 
			"            categories_products.category_id = :categoryId);", nativeQuery=true)
	List<BigInteger> queryNotCategoryProducts(@Param("categoryId") Long cateogryId);
}
