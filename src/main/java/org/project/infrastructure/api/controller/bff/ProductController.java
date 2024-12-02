/*
  2024
*/
package org.project.infrastructure.api.controller.bff;

import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.project.application.handler.ProductHandler;
import org.project.domain.product.exception.ProductNotFoundException;
import org.project.infrastructure.api.dto.product.ApiCreateProductDTO;
import org.project.infrastructure.api.dto.product.ApiPatchProductDTO;
import org.project.infrastructure.api.dto.product.ApiProductDTO;
import org.project.infrastructure.api.mapper.ApiProductDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping(path = "/api/products")
public class ProductController {

    @Autowired
    private ProductHandler productHandler;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiProductDTO> getProduct(@PathVariable long id)
        throws ProductNotFoundException {
        log.info("Getting product with id {}", id);
        return ResponseEntity.ok(ApiProductDTOMapper.toInfra(productHandler.getProduct(id)));
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<ApiProductDTO>> getProducts() {
        log.info("Find all products");
        return ResponseEntity.ok(
            productHandler.getProducts().stream().map(ApiProductDTOMapper::toInfra).toList());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiProductDTO> createProduct(
        @Valid @RequestBody ApiCreateProductDTO createProduct) {
        log.info("Creating product with name {}", createProduct.getName());
        return ResponseEntity.ok(ApiProductDTOMapper.toInfra(
            productHandler.createProduct(ApiProductDTOMapper.toApp(createProduct))));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        log.info("Deleting product with id {}", id);
        productHandler.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping(path = "/{id}")
    public ResponseEntity<ApiProductDTO> patchProduct(@PathVariable long id,
        @Valid @RequestBody ApiPatchProductDTO patchProduct) {
        log.info("Patching product with id {}", id);
        return ResponseEntity.ok(ApiProductDTOMapper.toInfra(
            productHandler.updateProduct(ApiProductDTOMapper.toApp(id, patchProduct))));
    }

}
