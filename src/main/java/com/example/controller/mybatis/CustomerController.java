package com.example.controller.mybatis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.CustomUser;
import com.example.dto.Member;
import com.example.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/customer")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    final String format = "CustomerController => {}";
    final MemberMapper mMapper;

    @GetMapping(value = "/login.do")
    public String loginGET(){
        return "/customer/login";
    }



    @GetMapping(value = "/join.do")
    public String joinGET(){
        return "/customer/join";
    }

    @PostMapping(value = "/join.do")
    public String joinPOST(@ModelAttribute Member member){
        log.info(format , member.toString()); //화면에 정확하게 표시되고 사용자가 입력한 항목을 member객체에 저장했음 . 확인용

        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder(); //salt값을 자동으로 부여함
        member.setPassword(bcpe.encode(member.getPassword())); //기존암호를 암호화시켜서 다시 저장함
        int ret = mMapper.insertMemberOne(member);
        if(ret == 1){
            return "redirect:joinok.do";  //주소창에 127.0.0.1:9090/ROOT/customer/joinok.do 입력후 엔터키를 자동화
        }
        return "redirect:join.do"; //실패시 회원가입화면으로
    }
   
    @GetMapping(value = "/joinok.do")
    public String joinokGET(){
        return "/customer/joinok";
    }


    @GetMapping(value = "/home.do")
    public String homeGET(
        Model model,
        @AuthenticationPrincipal User user,
        @RequestParam(name = "menu", required = false, defaultValue = "0") int menu
    ){
        if(menu == 1){
            Member member = mMapper.selectMemberOne1(user.getUsername());
            model.addAttribute("member", member);
            //체크박스에 표시할 항목들
            String[] checkLabel = {"김과","김놔","김솨","김촤","김화"};
            model.addAttribute("checklabel", checkLabel);
        }
        return "/customer/home";
    }

    //AuthenticationPrincipal User user => HttpSession httpSession => httpSession.getAttribute("user")
    @PostMapping(value = "/home.do")
    public String homePOST(@RequestParam(name = "menu", required = false) int menu,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            @AuthenticationPrincipal CustomUser user,
                            @ModelAttribute Member member ){
        log.info("CustomerController menu => {}", menu);
        log.info("CustomerController menu => {}", user.toString());
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        if(menu == 1){
            member.setId(user.getUsername());
            mMapper.updateMemberOne(member);
            log.info("CustomerController updateone => {}", member.toString());
            return "redirect:/customer/home.do?menu=1";
        }
        else if(menu == 2){
            //아이디 정보를 이용해서 DB에서 1명 조회
            Member obj = mMapper.selectMemberOne1(user.getUsername());

            //조회된 정보의 아이디와 사용자가 입력한 아이디를 mathes로 비교
            //비밀번호 확인 => matches (바꾸기전 비번, 해시된 비번)
            if(bcpe.matches(member.getPassword(), obj.getPassword())){
                member.setId(user.getUsername());
                member.setPassword2(bcpe.encode(member.getPassword2()));
                mMapper.updateMemberPassword(member);
                log.info("CustomerController updatepw =>{}", member.toString());
            }
            return "redirect:/customer/home.do?menu=2";
        }
        else if(menu == 3){
            //아이디 정보를 이용해서 db에서 1명 조회
            Member m = mMapper.selectMemberOne1(user.getUsername());

            //조회된 정보와 현재 암호가 일치하는지 matches로 비교
            //비교가 true이면 db에서 삭제 , 로그아웃
            if(bcpe.matches(member.getPassword(), m.getPassword())){
                member.setId(user.getUsername());
                mMapper.deleteMemberOne(member);

                //컨트롤러에서 logout 처리하기
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if( auth != null){
                    new SecurityContextLogoutHandler().logout(request, response, auth);
                }
            }

            return "redirect:/customer/home.do?menu=3";
        }
        return "redirect:/customer/home.do";
    }
}
