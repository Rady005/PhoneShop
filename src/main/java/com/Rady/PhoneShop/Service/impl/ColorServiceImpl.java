package com.Rady.PhoneShop.Service.impl;

import com.Rady.PhoneShop.Enitity.Color;
import com.Rady.PhoneShop.Repository.ColorRepository;
import com.Rady.PhoneShop.Service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
    @Override
    public Color create (Color color) {
        return null;
    }

    public Color getById(Integer id) {
        return colorRepository.getColorById(id)
                .orElseThrow(() -> new RuntimeException("Color not found with id: " + id));
    }
}
