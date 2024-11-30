/*
  2024
*/
package org.project.infrastructure.persistence.mongo.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.project.domain.product.Exception.ProductNotFoundException;
import org.project.domain.product.model.ProductModel;
import org.project.domain.product.repository.ProductRepository;
import org.project.infrastructure.persistence.mongo.mapper.MongoProductModelMapper;
import org.project.infrastructure.persistence.mongo.repository.MongoProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private MongoProductRepository mongoProductRepository;

    @Override
    public void save(ProductModel productModel) {
        log.info("Saving product");
        mongoProductRepository.save(MongoProductModelMapper.toInfra(productModel));
    }

    @Override
    public ProductModel findById(String id) throws ProductNotFoundException {
        log.info("Finding product by id");
        return mongoProductRepository.findById(id).map(MongoProductModelMapper::toDomain)
            .orElseThrow(() -> new ProductNotFoundException(
                String.format("Product with id %s not found", id)));
    }

    @Override
    public List<ProductModel> findAll() {
        log.info("Finding all products");
        return mongoProductRepository.findAll().stream().map(MongoProductModelMapper::toDomain)
            .toList();
    }

    @Override
    public void update(ProductModel productModel) {
        log.info("Updating product with id {}", productModel.id());
        // TODO merge with save method ?
        mongoProductRepository.save(MongoProductModelMapper.toInfra(productModel));
    }

    @Override
    public void deleteById(String id) {
        log.info("Deleting product by id");
        mongoProductRepository.deleteById(id);
    }

}
