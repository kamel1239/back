package org.project.domain.product.repository;

import java.util.List;
import org.project.domain.product.Exception.ProductNotFoundException;
import org.project.domain.product.model.Product;

public interface ProductRepository {

    /**
     *  Save a product
     *
     * @param product  The product to save
     */
    void save(Product product);

    /**
     *  Find a product by id
     *
     * @param id  The id of the product to find
     * @return  The product with the given id
     */
    Product findById(String id) throws ProductNotFoundException;

    /**
     * Find all products
     *
     * @return  All products
     */
    List<Product> findAll();

    /**
     *  Update a product
     *
     * @param product
     * @return  The updated product
     */
    Product update(Product product);

    /**
     * Delete a product by id
     *
     * @param id  The id of the product to delete
     */
    void deleteById(String id);

}
