package com.gallery_jwt_jpa.item;

import com.gallery_jwt_jpa.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface ItemRepository extends JpaRepository<Items, Long> {
    List<Items> findAllByIdIn(List<Long> ids);

}
