package com.inditex.ecommerce.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.inditex.ecommerce.products",
        "com.inditex.ecommerce.generated",
        "com.inditex.ecommerce.products.infrastructure.adapter.input.rest"
})
public class ProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }

}
