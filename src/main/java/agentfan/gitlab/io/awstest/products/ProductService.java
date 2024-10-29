package agentfan.gitlab.io.awstest.products;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductEntity getProductById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<ProductEntity> getAll() {
        return repository.findAll();
    }

    public ProductEntity createProduct(ProductEntity productEntity) {
        productEntity.setId(null);
        return repository.save(productEntity);
    }
}
