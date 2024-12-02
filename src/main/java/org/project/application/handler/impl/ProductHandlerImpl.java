/*
  2024
*/
package org.project.application.handler.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.project.application.dto.ProductDTO;
import org.project.application.handler.ProductHandler;
import org.project.application.mapper.ProductDTOMapper;
import org.project.domain.product.exception.ProductNotFoundException;
import org.project.domain.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductHandlerImpl implements ProductHandler {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        log.info("Creating product with name {}", productDTO.name());
        return ProductDTOMapper.toApp(
            productRepository.save(ProductDTOMapper.toDomain(productDTO)));
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        log.info("Updating product with id {}", productDTO.id());
        return ProductDTOMapper.toApp(
            productRepository.update(ProductDTOMapper.toDomain(productDTO)));
    }

    @Override
    public ProductDTO getProduct(long id) throws ProductNotFoundException {
        log.info("Getting product with id {}", id);
        return ProductDTOMapper.toApp(productRepository.findById(id));
    }

    @Override
    public List<ProductDTO> getProducts() {
        log.info("Find all products");
        return productRepository.findAll().stream().map(ProductDTOMapper::toApp).toList();
    }

    @Override
    public void deleteProduct(long id) {
        log.info("Deleting product with id {}", id);
        productRepository.deleteById(id);
    }

}
