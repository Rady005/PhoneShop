package com.Rady.PhoneShop.Service;

import com.Rady.PhoneShop.Dto.SaleDto;
import com.Rady.PhoneShop.Enitity.Sale;

public interface SaleService {
    void sell(SaleDto saleDto);
    Sale getById(Integer id);
    void cancelSale(Integer saleId);



}
