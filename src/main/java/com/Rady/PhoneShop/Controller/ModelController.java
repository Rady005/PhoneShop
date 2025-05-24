package com.Rady.PhoneShop.Controller;


import com.Rady.PhoneShop.Dto.ModelDto;
import com.Rady.PhoneShop.Enitity.Model;
import com.Rady.PhoneShop.Mapper.ModelMapper;
import com.Rady.PhoneShop.Service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/models")
public class ModelController {

    private final ModelService modelService;
    private final ModelMapper modelMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>createModel(@RequestBody ModelDto modelDto){
        Model model=modelMapper.toModel(modelDto);
        modelService.save(model);
        return ResponseEntity.ok(model);
    }

}
