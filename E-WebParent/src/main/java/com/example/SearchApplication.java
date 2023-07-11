package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SpringBootApplication(scanBasePackages = {"com"})
@RequestMapping("/search")
public class SearchApplication {

    //Simple GET call to test this application.
    //cách đơn giản nhất để kiểm tra app có ổn không
    @RequestMapping("/hello")
    @GetMapping
    public String hello() {
        return "Search: Hello";
    }

    //Simple GET call to see whether we are able return a class as JSON.
    //kiểm tra 1 sản phẩm có tồn tại không
    @RequestMapping(value = "/product",
            method = RequestMethod.GET,
            produces = {"application/json"})
    public Product product() {
        return new Product(1, "Laptop", 45000d);
    }

    //Get call using a Path param and return a Product as JSON.
    //tìm 1 sản phẩm
    @RequestMapping(value = "/product/{id}",
            method = RequestMethod.GET,
            produces = {"application/json"})
    public Product productWith(@PathVariable("id") long id) {
        Optional<Product> product = ProductService.getProducts()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        return product.get();
    }

    //GET to return list of Products as JSON.
    //lấy danh sách sản phẩm
    @RequestMapping(value = "/products",
            method = RequestMethod.GET,
            produces = {"application/json"})
    public List<Product> products() {
        return ProductService.getProducts();
    }

    //POST call to insert a new Product.
    //thêm sản phẩm mới
    @RequestMapping(value = "/product/create",
            method = RequestMethod.POST,
            consumes = {"application/json"}
    )

    // post_create_product
    public String createProduct(@RequestBody Product product) {
        if (ProductService.createProduct(product)) {
            return "Created Product = " + product.toString();
        }

        return "Creation failed for Product = " + product.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }
}