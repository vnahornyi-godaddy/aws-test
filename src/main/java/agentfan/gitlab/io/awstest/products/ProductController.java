package agentfan.gitlab.io.awstest.products;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Products", description = "products API")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{productId}")
    public ProductEntity getProductById(@PathVariable UUID productId) {
        return service.getProductById(productId);
    }

    @GetMapping("")
    public List<ProductEntity> getAll() {
        return service.getAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity createProduct(@RequestBody ProductEntity productEntity) {
        return service.createProduct(productEntity);
    }

    @PostMapping("/delayed")
    public ProductEntity delayedCreateProduct(@RequestBody ProductEntity productEntity) {
        service.delayedCreateProduct(productEntity);
        return productEntity;
    }
}
