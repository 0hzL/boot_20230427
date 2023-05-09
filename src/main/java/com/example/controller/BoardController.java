package com.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/board")
public class BoardController {
    //127.0.0.1:9090/ROOT/board/select.do
    @GetMapping(value="/select.do")
    public String selectGET(Model model){

        model.addAttribute("title", "SELECT!!!!!!!!!!");
        return "/board/select";
    }

     //127.0.0.1:9090/ROOT/board/insert.do
    @GetMapping(value="/insert.do")
    public String insertGET(Model model){

        model.addAttribute("title", "INSERT!!!!!!!!!!");

        return "/board/insert";
    }

    //127.0.0.1:9090/ROOT/board/update.do
    @GetMapping(value="/update.do")
    public String updateGET(Model model){

        model.addAttribute("title", "UPDATE!!!!!!!!!!");

        return "/board/update";
    }
}
