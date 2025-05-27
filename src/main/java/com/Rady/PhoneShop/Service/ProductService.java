package com.Rady.PhoneShop.Service;

import com.Rady.PhoneShop.Enitity.Product;

public interface ProductService {
    Product create(Product product);
    Product getById(Integer id);
}
