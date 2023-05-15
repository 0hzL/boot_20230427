package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.library.Library2;
import com.example.repository.Library2Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/library2")
@RequiredArgsConstructor
public class RestLibrary2Controller {
    
    final Library2Repository lRepository;

    //127.0.0.1:9090/ROOT/api/library2/insert.json
    //@RequestBody Library2 obj => 기본으로 보낼때(디폴트)
    //@ModelAttribute Library2 obj => 위의 방식이 불가능할때 (ex 이미지 등)
    //{result : 1}
    @PostMapping(value = "/insert.json")
    public Map<String , Object> insertPOST(@RequestBody Library2 obj){ //input으로 보내는 세개가 와야함
        Map<String, Object> retMap = new HashMap<>();

        try {
            log.info("{}",obj.toString());
            lRepository.save(obj);
            retMap.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace(); //개발자용 debug
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }

        return retMap;
    }

    //127.0.0.1:9090/ROOT/api/library2/selectlist.json
    @GetMapping(value = "/selectlist.json")
    public Map<String, Object> selectlistGET(){
        Map<String, Object> retMap = new HashMap<>();
        try {
            List<Library2> list = lRepository.findAllByOrderByNameAsc();

            // [클래스,클래스, ... ]
            log.info("{}",list.toString());
            retMap.put("status", 200);
            retMap.put("list", list);
           
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }


}
