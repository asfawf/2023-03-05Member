package kh.spring.s03.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.spring.s03.member.service.MemberService;
import kh.spring.s03.member.vo.MemberVo;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	//	DAO 긁어오기 + return 타입 ModelAndView 로 바꾸기
	
	@Autowired
	private MemberService service;

	
	@GetMapping("/signUp")
	public ModelAndView viewInsert(ModelAndView mv) {
		mv.setViewName("member/signUp");
		return mv;
	}
	
	// 연결된 jsp input(name =" ex) A ") 과 같은 이름을 가진 변수가  ( 여기에 선언된다면 == String A ){} 자동으로 대입됨
	@PostMapping("/signUp")
	public ModelAndView insert(ModelAndView mv
			, MemberVo vo
			, String id
			, RedirectAttributes rttr) {
		
		int result =-1;
		
		try {
			result = service.insert(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result > 0) {
			// 방법 1
			// 회원가입 성공			
			//mv.setViewName("redirect:/?msg=회원가입성공");
			
			/* 방법 2
			 * mv.addObject("alert", "성공"); 
			 * mv.setViewName("error/errorFilure");
			 */
			
			// 방법 3 : 스프링에서만 (RedirectAttributes) + addAttribute 소용 없음 
			// HomeController 로 보내지만 HomeController에서는 꺼낼 수 없다. 즉 HomeController 에서 바로 보내는 jsp 에서 꺼내는 것이 가능하다.
			rttr.addFlashAttribute("alert", "성공");
			mv.setViewName("redirect:/");
		} else {
			/*
			 * mv.addObject("alert", "실패"); 
			 * mv.setViewName("error/errorFilure");
			 */
			
			rttr.addFlashAttribute("alert", "실패");
			mv.setViewName("redirect:/member/signUp");
		}
		return mv;
	}
	@GetMapping("/update")
	public ModelAndView viewUpdate(ModelAndView mv
			, String id
			) {
		MemberVo vo = service.selectOne(id);
		mv.addObject("membervo", vo);
		mv.setViewName("/member/update");
		return mv;
	}
	@PostMapping("/update")
	public ModelAndView update(ModelAndView mv	
			, MemberVo vo) {
		service.update(vo);
		return mv;
	}
	@GetMapping("/delete")
	public ModelAndView delete(ModelAndView mv			) {
//		TODO
		String id = "copy";
		service.delete(id);
		return mv;
	}
	@GetMapping("/info")
	public ModelAndView selectOne(ModelAndView mv
			, String id   // request.getParameter("id")  
			// url " /member/info?id=user3
			// url " /member/info/user3
			) {
		if(id == null) {
			mv.setViewName("redirect:/");
			return mv;
		}
		MemberVo result = service.selectOne(id);
		return mv;
	}
	@GetMapping("/list")
	public ModelAndView selectList(ModelAndView mv) {
		List<MemberVo> result = service.selectList();
		return mv;
	}
	@GetMapping("/selflist")
	public ModelAndView selectList(ModelAndView mv, HttpServletRequest req) {
		
		// memberlist 에 값 담고 setAttribute
		mv.addObject("memberlist", service.selectList());
		// memberlist 위치 지정
		mv.setViewName("member/list");
		return mv;		
	}
	
}
