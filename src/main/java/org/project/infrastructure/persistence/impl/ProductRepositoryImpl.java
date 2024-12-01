/*
  2024
*/
package org.project.infrastructure.persistence.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.project.domain.product.exception.ProductNotFoundException;
import org.project.domain.product.model.ProductModel;
import org.project.domain.product.repository.ProductRepository;
import org.project.infrastructure.persistence.mapper.ProductEntityModelMapper;
import org.project.infrastructure.persistence.repository.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private JpaProductRepository jpaProductRepository;

    @Override
    public ProductModel save(ProductModel productModel) {
        log.info("Saving product with name {}", productModel.name());
        return ProductEntityModelMapper.toDomain(
            jpaProductRepository.save(ProductEntityModelMapper.toInfra(productModel)));
    }

    @Override
    public ProductModel findById(String id) throws ProductNotFoundException {
        log.info("Finding product by id {}", id);
        return jpaProductRepository.findById(id).map(ProductEntityModelMapper::toDomain)
            .orElseThrow(() -> new ProductNotFoundException(
                String.format("Product with id %s not found", id)));
    }

    @Override
    public List<ProductModel> findAll() {
        log.info("Find all products");
        return jpaProductRepository.findAll(Sort.by("createdAt").descending()).stream()
            .map(ProductEntityModelMapper::toDomain).toList();
    }

    @Override
    public ProductModel update(ProductModel productModel) {
        log.info("Updating product with id {}", productModel.id());
        // TODO merge with save method ?
        return ProductEntityModelMapper.toDomain(
            jpaProductRepository.save(ProductEntityModelMapper.toInfra(productModel)));
    }

    @Override
    public void deleteById(long id) {
        log.info("Deleting product by id {}", id);
        jpaProductRepository.deleteById(String.valueOf(id));
    }

}
