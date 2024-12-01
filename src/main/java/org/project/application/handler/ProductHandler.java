/*
  2024
*/
package org.project.application.handler;

import java.util.List;
import org.project.application.dto.ProductDTO;
import org.project.domain.product.exception.ProductNotFoundException;

public interface ProductHandler {

    /**
     * Creates a new product.
     *
     * @param productDTO the product data transfer object containing the details of the product to
     *                   be created
     */
    ProductDTO createProduct(ProductDTO productDTO);

    /**
     * Updates an existing product.
     *
     * @param productDTO the product data transfer object containing the updated details of the
     *                   product
     */
    ProductDTO updateProduct(ProductDTO productDTO);

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to be retrieved
     * @return the product data transfer object of the retrieved product
     */
    ProductDTO getProduct(long id) throws ProductNotFoundException;

    /**
     * Retrieves a list of all products.
     *
     * @return a list of product data transfer objects
     */
    List<ProductDTO> getProducts();

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to be deleted
     */
    void deleteProduct(long id);

}
