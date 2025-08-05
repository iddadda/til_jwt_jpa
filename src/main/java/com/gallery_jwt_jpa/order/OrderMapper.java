package com.gallery_jwt_jpa.order;

import com.gallery_jwt_jpa.order.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int save(OrderPostDto dto);
    List<OrderGetRes> findAllByMemberIdOrderByIdDesc(long memberId);
    OrderDetailGetRes findByOrderIdAndMemberId(OrderDetailGetReq req);
}
