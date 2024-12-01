/*
  2024
*/
package org.project.infrastructure.api.controller.external;

import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.project.application.handler.ProductHandler;
import org.project.domain.product.exception.ProductNotFoundException;
import org.project.infrastructure.api.entity.ApiCreateProductEntity;
import org.project.infrastructure.api.entity.ApiPatchProductEntity;
import org.project.infrastructure.api.entity.ApiProductEntity;
import org.project.infrastructure.api.mapper.ApiProductModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping(path = "/api/products")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,
    RequestMethod.DELETE, RequestMethod.PATCH})
public class ProductController {

    @Autowired
    private ProductHandler productHandler;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiProductEntity> getProduct(@PathVariable String id)
        throws ProductNotFoundException {
        log.info("Getting product with id {}", id);
        return ResponseEntity.ok(ApiProductModelMapper.toInfra(productHandler.getProduct(id)));
    }

    @GetMapping
    public ResponseEntity<List<ApiProductEntity>> getProducts() {
        log.info("Find all products");
        return ResponseEntity.ok(
            productHandler.getProducts().stream().map(ApiProductModelMapper::toInfra).toList());
    }

    @PostMapping
    public ResponseEntity<ApiProductEntity> createProduct(
        @Valid @RequestBody ApiCreateProductEntity createProduct) {
        log.info("Creating product with name {}", createProduct.getName());
        return ResponseEntity.ok(ApiProductModelMapper.toInfra(
            productHandler.createProduct(ApiProductModelMapper.toApp(createProduct))));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        log.info("Deleting product with id {}", id);
        productHandler.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ApiProductEntity> patchProduct(@PathVariable long id,
        @Valid @RequestBody ApiPatchProductEntity patchProduct) {
        log.info("Patching product with id {}", id);
        return ResponseEntity.ok(ApiProductModelMapper.toInfra(
            productHandler.updateProduct(ApiProductModelMapper.toApp(id, patchProduct))));
    }

}
