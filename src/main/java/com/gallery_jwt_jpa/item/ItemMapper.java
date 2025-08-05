package com.gallery_jwt_jpa.item;

import com.gallery_jwt_jpa.item.model.ItemGetRes;
import com.gallery_jwt_jpa.item.model.ItemPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    int save(ItemPostReq req);
    List<ItemGetRes> findAllByIdIn(List<Long> ids);
}
