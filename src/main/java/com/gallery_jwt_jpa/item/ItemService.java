package com.gallery_jwt_jpa.item;

import com.gallery_jwt_jpa.cart.CartMapper;
import com.gallery_jwt_jpa.config.util.MyFileUtils;
import com.gallery_jwt_jpa.entity.Items;
import com.gallery_jwt_jpa.item.model.ItemGetRes;
import com.gallery_jwt_jpa.item.model.ItemPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final MyFileUtils myFileUtils;

    public int save(MultipartFile img, ItemPostReq req) {
        String savedFileName = myFileUtils.makeRandomFileName(img); //저장할 파일명
        //req.setImgPath(savedFileName);

        Items item = new Items();
        item.setImgPath(savedFileName);
        item.setName(req.getName());
        item.setPrice(req.getPrice());
        item.setDiscountPer(req.getDiscountPer());

        itemRepository.save(item);

        //int result = itemMapper.save(req);

        String directoryPath = String.format("/item/%d", item.getId());
        myFileUtils.makeFolders(directoryPath);

        String savedPathFileName = directoryPath + "/" + savedFileName;
        try {
            myFileUtils.transferTo(img, savedPathFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

        return 1;
    }


    public List<ItemGetRes> findAll(List<Long> ids) {
        log.info("ids:{}", ids);
        List<Items> list = null;
        if(ids == null || ids.isEmpty()) {
            list = itemRepository.findAll();
        } else {
            list = itemRepository.findAllByIdIn(ids);
        }

        List<ItemGetRes> resultList = new ArrayList<>();
        for (Items item : list ) {
            ItemGetRes itemGetRes = new ItemGetRes(item.getId(), item.getName(), item.getImgPath(), item.getPrice(), item.getDiscountPer()) ;
            resultList.add(itemGetRes);
        }
        return resultList;
    }
}
