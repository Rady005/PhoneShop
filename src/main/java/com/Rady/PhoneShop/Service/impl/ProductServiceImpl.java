package com.Rady.PhoneShop.Service.impl;

import com.Rady.PhoneShop.Enitity.Product;
import com.Rady.PhoneShop.Repository.ProductRepository;
import com.Rady.PhoneShop.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product create (Product product) {
        String name="%s %s".formatted(product.getModel().getModelName(),product.getColor().getColorName());
        product.setProductName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById (Integer id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
    }
}
