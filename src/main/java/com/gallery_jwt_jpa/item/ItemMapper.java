package com.gallery_jwt_jpa.item;

import com.gallery_jwt_jpa.item.model.ItemGetRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    List<ItemGetRes> findAllByIdIn(List<Integer> ids);
}
