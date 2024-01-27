package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Product;
import exercise.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // BEGIN
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product showProduct(@PathVariable Long id){
        var product = productRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        return product;
    }

    @PutMapping(path ="{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id){
        var prod = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        prod.setPrice(product.getPrice());
        prod.setTitle(product.getTitle());

        productRepository.save(prod);
        return prod;
    }
    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
