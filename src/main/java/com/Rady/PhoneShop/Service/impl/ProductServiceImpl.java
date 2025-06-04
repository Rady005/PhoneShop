package com.Rady.PhoneShop.Service.impl;

import com.Rady.PhoneShop.Dto.ProductImportDto;
import com.Rady.PhoneShop.Enitity.Product;
import com.Rady.PhoneShop.Enitity.ProductImportHistory;
import com.Rady.PhoneShop.Mapper.ProductMapper;
import com.Rady.PhoneShop.Repository.ProductImportHistoryRepository;
import com.Rady.PhoneShop.Repository.ProductRepository;
import com.Rady.PhoneShop.Service.ProductService;
import com.Rady.PhoneShop.Validation.UploadProductValidation;
import com.Rady.PhoneShop.exception.ApiException;
import com.Rady.PhoneShop.exception.RowError;
import com.Rady.PhoneShop.exception.UploadResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductMapper productMapper;
    private final UploadProductValidation uploadProductValidation;
    @Override
    public Product create (Product product) {
        String name="%s %s".formatted(product.getModel().getModelName(),product.getColor().getColorName());
        product.setProductName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById (Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("Product with id %d not found", id)));
    }

    @Override
    public Product getByModelIdAndColorId (Long modelId, Long colorId) {
        return productRepository.findByModelIdAndColorId(modelId, colorId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Product with modelId %d and colorId %d not found".formatted(modelId, colorId)));
    }

    @Override
    public void importProducts (ProductImportDto productImportDto) {
        //update availble products units

        Product product=getById(productImportDto.getId());
        Integer availableUnit=0;
        if(product.getAvailableUnits()!=null){
            availableUnit=product.getAvailableUnits();
        }
        product.setAvailableUnits(availableUnit+ productImportDto.getImportUint());
        productRepository.save(product);


        //save product import history

        ProductImportHistory productImportHistory=productMapper.toProductImportHistory(productImportDto,product);
        productImportHistoryRepository.save(productImportHistory);

    }

    @Override
    public void setSalePrice (Integer id, BigDecimal salePrice) {
        Product product=getById(id);
        product.setSalePrice(salePrice);
        productRepository.save(product);

    }

    @Override
    public void validateStock (Integer productId, Integer number) {

    }
/*    @Override
    public void uploadProduct(MultipartFile file) {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet("Product");

            uploadProductValidation.validateSheet(sheet);

            uploadProductValidation.processExcelRows(sheet);
            List<RowError> errors=uploadProductValidation.processExcelRows(sheet);

            workbook.close();
            if(!errors.isEmpty()){
                errors.forEach(error -> {;
                    System.out.println("Row " + error.getRow() + ": " + error.getErrorMessage());
                });


            }
            StringBuilder message = new StringBuilder("Some rows were not processed:\n");
            for (RowError error : errors) {
                message.append("Row ").append(error.getRow()).append(": ").append(error.getErrorMessage()).append("\n");

            }
            throw new ApiException(HttpStatus.BAD_REQUEST, message.toString());

        } catch (IOException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error reading Excel file: " + e.getMessage());
        }
    }*/



    @Override
    public UploadResponse uploadProduct(MultipartFile file) {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet("Product");

            uploadProductValidation.validateSheet(sheet);
            List<RowError> errors = uploadProductValidation.processExcelRows(sheet);

            workbook.close();

            String message = errors.isEmpty()
                    ? "All rows processed successfully."
                    : "Some rows were processed successfully. See errors for details.";

            return new UploadResponse(message, errors);

        } catch (IOException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error reading Excel file: " + e.getMessage());
        }
    }



}
