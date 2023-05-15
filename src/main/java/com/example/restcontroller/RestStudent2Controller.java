package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.exceptions.AlreadyInitializedException;

import com.example.entity.Student2Projection;
import com.example.entity.library.Student2;
import com.example.repository.Student2Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/student2")
public class RestStudent2Controller {


    final Student2Repository sRepository;
    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

    //127.0.0.1:9090/ROOT/api/student2/insert.json
    @PostMapping(value = "/insert.json")
    public Map<String , Object> insertPOST(@RequestBody Student2 obj){ //input으로 보내는 세개가 와야함
        Map<String, Object> retMap = new HashMap<>();
        try {
            obj.setPassword(bcpe.encode(obj.getPassword()));
            log.info("{}",obj.toString());
            sRepository.save(obj);
            retMap.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }


    //email 중복확인용
    //ROOT/api/student2/emailcheck.json?email=이메일
    @GetMapping(value = "/emailcheck.json")
    public Map<String , Object> emailcheckGET(@RequestParam(name = "email") String email){ //input으로 보내는 세개가 와야함
        Map<String, Object> retMap = new HashMap<>();
        try {
            retMap.put("status", 200);
            retMap.put("chk", sRepository.countByEmail(email)); 
            if(sRepository.countByEmail(email)==1) {
                retMap.put("chk", "중복됩니다");
                retMap.put("status", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }

    //email이 전달되면 회원의 이름, 연락처가 반환됨
    @GetMapping(value = "/selectone.json")
    public Map<String , Object> selectoneGET(@RequestBody Student2 obj){ 
        Map<String, Object> retMap = new HashMap<>();
        log.info("{}",obj.getEmail().toString());
        try {
            Student2Projection stprojection = sRepository.findByEmail(obj.getEmail());

            retMap.put("status", 200);
            retMap.put("obj", stprojection);
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }
}
