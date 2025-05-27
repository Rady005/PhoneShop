package com.Rady.PhoneShop.Repository;

import com.Rady.PhoneShop.Enitity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository <Color, Integer> {
    Color save(Color color);
    Optional<Color> getColorById (Integer id);



}
