/*
  2024
*/
package org.project.infrastructure.persistence.mongo.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.project.domain.product.Exception.ProductNotFoundException;
import org.project.domain.product.model.Product;
import org.project.domain.product.repository.ProductRepository;
import org.project.infrastructure.persistence.mongo.mapper.MongoProductMapper;
import org.project.infrastructure.persistence.mongo.repository.MongoProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MongoProductRepositoryImpl implements ProductRepository {

    @Autowired
    private MongoProductRepository mongoProductRepository;


    @Override
    public void save(Product product) {
        log.info("Saving product");
        mongoProductRepository.save(MongoProductMapper.toInfra(product));
    }

    @Override
    public Product findById(String id) throws ProductNotFoundException {
        log.info("Finding product by id");
        return mongoProductRepository.findById(id).map(MongoProductMapper::toDomain).orElseThrow(
            () -> new ProductNotFoundException(String.format("Product with id %s not found", id)));
    }

    @Override
    public List<Product> findAll() {
        log.info("Finding all products");
        return mongoProductRepository.findAll().stream().map(MongoProductMapper::toDomain).toList();
    }

    @Override
    public Product update(Product product) {
        log.info("Updating product");
        return MongoProductMapper.toDomain(
            mongoProductRepository.save(MongoProductMapper.toInfra(product)));
    }

    @Override
    public void deleteById(String id) {
        log.info("Deleting product by id");
        mongoProductRepository.deleteById(id);
    }

}
