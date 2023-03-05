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
	@PostMapping("/signUp")
	public ModelAndView insert(ModelAndView mv
			, MemberVo vo) {
		
		int result = service.insert(vo);
		
		if(result > 0) {
			mv.setViewName("redirect:/");
		} else {
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
