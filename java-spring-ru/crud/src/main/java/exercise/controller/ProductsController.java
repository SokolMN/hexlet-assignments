package exercise.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import exercise.dto.CategoryCreateDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.model.Product;
import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @GetMapping(path = "")
    public ArrayList<ProductDTO> showAllProducts(){
        var productList = productRepository.findAll();
        ArrayList<ProductDTO> productDTOArrayList = new ArrayList<>();
        for(Product product:productList){
            productDTOArrayList.add(productMapper.map(product));
        }

        return  productDTOArrayList;
    }

    @GetMapping(path = "/{id}")
    public ProductDTO showProductById(@PathVariable long id){
        var product = productRepository.findById(id).get();
        var productDTO = productMapper.map(product);
        productDTO.setCategoryName(product.getCategory().getName());
        productDTO.setCategoryId(product.getCategory().getId());

        //product.setCategory(categoryRepository.findById(product.getCategory().getName()));
        return productDTO;
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductCreateDTO productCreateDTO){
        try {
            var category = categoryRepository.findById(productCreateDTO.getCategoryId());
            var product = productMapper.map(productCreateDTO);
            product.setCategory(category.get());
            productRepository.save(product);


            var productDTO = productMapper.map(product);
            productDTO.setCategoryName(category.get().getName());

            return ResponseEntity.status(201).body(productDTO);
        }catch(NoSuchElementException e){
            return  ResponseEntity.status(400).body(new ProductDTO());
        }
    }


    @PutMapping(path = "/{id}")
    public ProductDTO updateProduct(@RequestBody ProductUpdateDTO productUpdateDTO, @PathVariable long id){
        var category = categoryRepository.findById(productUpdateDTO.getCategoryId().get()).orElseThrow(() -> new ResourceNotFoundException("noe"));

        var product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("noe re"));
        productMapper.update(productUpdateDTO,product);
        product.setCategory(category);
        productRepository.save(product);

        return productMapper.map(product);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable long id){
        productRepository.deleteById(id);
    }
    // END
}
