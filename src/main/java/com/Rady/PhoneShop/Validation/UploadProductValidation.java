package com.Rady.PhoneShop.Validation;

import com.Rady.PhoneShop.Enitity.Product;
import com.Rady.PhoneShop.Enitity.ProductImportHistory;
import com.Rady.PhoneShop.Repository.ProductImportHistoryRepository;
import com.Rady.PhoneShop.Repository.ProductRepository;
import com.Rady.PhoneShop.exception.ApiException;
import com.Rady.PhoneShop.exception.RowError;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UploadProductValidation {

    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final View error;


    public void validateSheet(Sheet sheet) {
        if (sheet == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Sheet 'Product' not found in the Excel file");
        }
    }
    public List<RowError> processExcelRows(Sheet sheet) {
        List<RowError> errors = new ArrayList<>();
        int lastRowNum = sheet.getLastRowNum();

        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
            Row row = sheet.getRow(rowIndex);

            if (shouldSkipRow(row)) continue;
            if (isEndOfData(row)) break;

            try {
                processDataRow(row, rowIndex + 1);
            } catch (ApiException e) {
                errors.add(new RowError(rowIndex + 1, e.getMessage()));
            } catch (Exception e) {
                errors.add(new RowError(rowIndex + 1, "Unexpected error: " + e.getMessage()));
            }
        }

        return errors;
    }

    public boolean shouldSkipRow(Row row) {
        return row == null || isRowEmpty(row);
    }
    public boolean isEndOfData(Row row) {
        Cell firstCell = row.getCell(0);
        return firstCell == null || firstCell.getCellType() == CellType.BLANK;
    }
    private void processDataRow(Row row, int rowNumber) {
        // Extract and validate data from row
        Long modelId = extractAndValidateModelId(row, rowNumber);
        Long colorId = extractAndValidateColorId(row, rowNumber);
        BigDecimal price = extractAndValidatePrice(row, rowNumber);
        Integer importUnit = extractAndValidateImportUnit(row, rowNumber);
        LocalDateTime importDate = extractAndValidateImportDate(row, rowNumber);

        // Process the validated data
        updateProductStock(modelId, colorId, importUnit);
        saveImportHistory(modelId, colorId, price, importUnit, importDate);
    }
    private Long extractAndValidateModelId(Row row, int rowNumber) {
        Cell cell = row.getCell(0);
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Model ID cannot be null or empty at row %d", rowNumber));
        }

        if (cell.getCellType() != CellType.NUMERIC) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Model ID must be a number at row %d", rowNumber));
        }

        long modelId = (long) cell.getNumericCellValue();
        if (modelId <= 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Model ID must be greater than 0 at row %d", rowNumber));
        }

        return modelId;
    }
    private Long extractAndValidateColorId(Row row, int rowNumber) {
        Cell cell = row.getCell(1);
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Color ID cannot be null or empty at row %d", rowNumber));
        }

        if (cell.getCellType() != CellType.NUMERIC) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Color ID must be a number at row %d", rowNumber));
        }

        long colorId = (long) cell.getNumericCellValue();
        if (colorId <= 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Color ID must be greater than 0 at row %d", rowNumber));
        }

        return colorId;
    }
    private BigDecimal extractAndValidatePrice(Row row, int rowNumber) {
        Cell cell = row.getCell(2);
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Import Price cannot be null or empty at row %d", rowNumber));
        }

        if (cell.getCellType() != CellType.NUMERIC) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Import Price must be a number at row %d", rowNumber));
        }

        BigDecimal price = BigDecimal.valueOf(cell.getNumericCellValue());
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Import Price must be greater than 0 at row %d", rowNumber));
        }

        return price;
    }
    private Integer extractAndValidateImportUnit(Row row, int rowNumber) {
        Cell cell = row.getCell(3);
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Import Unit cannot be null or empty at row %d", rowNumber));
        }

        if (cell.getCellType() != CellType.NUMERIC) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Import Unit must be a number at row %d", rowNumber));
        }

        int importUnit = (int) cell.getNumericCellValue();
        if (importUnit <= 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Import Unit must be greater than 0 at row %d", rowNumber));
        }

        return importUnit;
    }
    private LocalDateTime extractAndValidateImportDate(Row row, int rowNumber) {
        Cell cell = row.getCell(4);
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Import Date cannot be null or empty at row %d", rowNumber));
        }

        LocalDateTime importDate;

        if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                importDate = cell.getLocalDateTimeCellValue();
            } else {
                throw new ApiException(HttpStatus.BAD_REQUEST,
                        String.format("Invalid date format at row %d", rowNumber));
            }
        } else if (cell.getCellType() == CellType.STRING) {
            importDate = parseStringDate(cell.getStringCellValue(), rowNumber);
        } else {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Invalid date format at row %d", rowNumber));
        }

        validateDateLogic(importDate, rowNumber);
        return importDate;
    }
    private LocalDateTime parseStringDate(String dateString, int rowNumber) {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("M/d/yyyy H:mm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(dateString, formatter);
            } catch (DateTimeParseException ignored) {
                // Try next formatter
            }
        }

        throw new ApiException(HttpStatus.BAD_REQUEST,
                String.format("Unable to parse date '%s' at row %d. Supported formats: M/d/yyyy H:mm, yyyy-MM-dd HH:mm:ss",
                        dateString, rowNumber));
    }
    private void validateDateLogic(LocalDateTime date, int rowNumber) {
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        // Check for invalid dates
        if (!isValidDate(year, month, day)) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Invalid date %d-%02d-%02d at row %d. This date does not exist.",
                            year, month, day, rowNumber));
        }

        // Additional business rule: no future dates
        if (date.isAfter(LocalDateTime.now())) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    String.format("Import date cannot be in the future at row %d", rowNumber));
        }
    }
    private boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }


    private boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }

        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    // Remove the duplicated methods and use the service methods instead
    private void updateProductStock(Long modelId, Long colorId, Integer importUnit) {
        Product product = productRepository.findByModelIdAndColorId(modelId, colorId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST,
                        "Product with modelId " + modelId + " and colorId " + colorId + " not found"));
        Integer availableUnit = product.getAvailableUnits() != null ? product.getAvailableUnits() : 0;
        product.setAvailableUnits(availableUnit + importUnit);
        productRepository.save(product);
    }

    private void saveImportHistory(Long modelId, Long colorId, BigDecimal price, Integer importUnit, LocalDateTime importDate) {
        Product product = productRepository.findByModelIdAndColorId(modelId, colorId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST,
                        "Product with modelId " + modelId + " and colorId " + colorId + " not found"));

        ProductImportHistory importHistory = new ProductImportHistory();
        importHistory.setDateImport(importDate);
        importHistory.setImportUnit(importUnit);
        importHistory.setPricePerUnit(price);
        importHistory.setProduct(product);
        productImportHistoryRepository.save(importHistory);
    }


}
