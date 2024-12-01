/*
    2024
*/
package org.project.domain.product.repository;

import java.util.List;
import org.project.domain.product.exception.ProductNotFoundException;
import org.project.domain.product.model.ProductModel;

public interface ProductRepository {

    /**
     * Save a product
     *
     * @param productModel The product to save
     */
    ProductModel save(ProductModel productModel);

    /**
     * Find a product by id
     *
     * @param id The id of the product to find
     * @return The product with the given id
     */
    ProductModel findById(String id) throws ProductNotFoundException;

    /**
     * Find all products
     *
     * @return All products
     */
    List<ProductModel> findAll();

    /**
     * Update a product
     *
     * @param productModel The updated product infos
     */
    ProductModel update(ProductModel productModel);

    /**
     * Delete a product by id
     *
     * @param id The id of the product to delete
     */
    void deleteById(long id);

}
