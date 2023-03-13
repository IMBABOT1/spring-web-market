package ru.geekbrains.spring.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.dto.ProductDto;
import ru.geekbrains.spring.entities.Product;

@Component
public class ProductConverter {

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

    public Product dtoToEntity(ProductDto productDto){
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
    }

}
