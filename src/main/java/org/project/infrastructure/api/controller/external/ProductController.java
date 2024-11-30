/*
  2024
*/
package org.project.infrastructure.api.controller.external;

import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.project.application.handler.ProductHandler;
import org.project.domain.product.Exception.ProductNotFoundException;
import org.project.infrastructure.api.dto.ApiProductModel;
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
    public ResponseEntity<ApiProductModel> getProduct(@PathVariable String id)
        throws ProductNotFoundException {
        log.info("Getting product with id {}", id);
        return ResponseEntity.ok(ApiProductModelMapper.toInfra(productHandler.getProduct(id)));
    }

    @GetMapping
    public ResponseEntity<List<ApiProductModel>> getProducts() {
        log.info("Getting products");
        return ResponseEntity.ok(
            productHandler.getProducts().stream().map(ApiProductModelMapper::toInfra).toList());
    }

    @PostMapping
    public void createProduct(@Valid @RequestBody ApiProductModel product) {
        log.info("Creating product");
        productHandler.createProduct(ApiProductModelMapper.toApp(product));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable String id) {
        log.info("Deleting product with id {}", id);
        productHandler.deleteProduct(id);
    }

    @PatchMapping(path = "/{id}")
    public void patchProduct(@PathVariable String id, @Valid @RequestBody ApiProductModel product) {
        log.info("Patching product with id {}", product.id());
        productHandler.updateProduct(id, ApiProductModelMapper.toApp(product));
    }

}
