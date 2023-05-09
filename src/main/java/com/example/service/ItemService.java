package com.example.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.dto.Item;
import com.example.dto.ItemImage;


//자동import 단축키는 shift + alt + o 

@Service //서비스면 서비스 컨트롤러면 컨트롤러라고 명시해줘야함
public interface ItemService {

    //물품전체조회
    public List<Item> selectItemList();
    
    //물품 이미지 등록
    public int insertItemImageOne(ItemImage obj);
}
