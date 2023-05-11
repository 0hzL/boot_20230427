 package com.example.controller.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Board1View;
import com.example.repository.Board1ViewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/board1view")
@RequiredArgsConstructor
public class Board1ViewController {
    
    final Board1ViewRepository b1vRepository;
    //num이 0 또는 없으면 전체
    //num이 1이면 and
    //num이 2이면 or
    //num이 3이면 글번호 in
    //num이 4이면 제목 in


    //127.0.0.1:9090/ROOT/board1view/selectlist.pknu
    @GetMapping(value = "/selectlist.pknu")
    public String selectlistGET(Model model,
                                @RequestParam(name = "num",defaultValue = "0") int num,
                                @ModelAttribute Board1View board1view ,
                                @RequestParam(name = "nos", defaultValue = "") String nos,
                                @RequestParam(name = "titles",defaultValue = "") String titles){

        try {
            List<Board1View> list = b1vRepository.findAllByOrderByNoDesc();

            if(num ==1){
                list = b1vRepository.findByNoAndTitleOrderByNoDesc(board1view.getNo(), board1view.getTitle());
            }

            else if(num ==2){
                list = b1vRepository.findByNoOrTitleOrderByNoDesc(board1view.getNo(), board1view.getTitle());
            }

            else if(num ==3){
                String[] obj = nos.split(",");
                List<Long> list2 = new ArrayList<>();
                
                for(int i=0; i<obj.length; i++){
                   list2.add(Long.parseLong(obj[i]));
                }

                list = b1vRepository.findByNoInOrderByNoDesc(list2);
            }
            else if(num ==4){
                String[] obj = titles.split(",");
                List<String> list2 = new ArrayList<>();

                for(int i=0; i<obj.length; i++){
                    list2.add(obj[i]);
                }
                list = b1vRepository.findByTitleInOrderByNoDesc(list2);
            }
            
            model.addAttribute("list", list);
            return "/board1view/selectlist";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
    
}
