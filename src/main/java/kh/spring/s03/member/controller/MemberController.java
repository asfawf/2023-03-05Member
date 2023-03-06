package kh.spring.s03.member.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
			, RedirectAttributes rttr) throws Exception {
		
		int result =-1;
		
		
			result = service.insert(vo);
		
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
	public ModelAndView viewUpdate(
			// @RequestParam("XXX") String XXX --> XXX 라는 이름의 Parameter 가 꼭 있어야 한다  
			// String XXX --> XXX 라는 이름의 Parameter 가 없어도 된다 . 없는 경우  null 값이 들어간다.
			ModelAndView mv
			, @RequestParam("id") String id // String id = request.getParameter("id"); 이건 반드시 있어야 한다

			//, @RequestParam("aaa") int aaa --> int aaa = request.getParameter("id"); 이건 반드시 있어야 한다
			//, @RequestParam(name = "ccc" , required = false , defaultValue = "100") String ccc --> required = false 는 있을수도 있고 없을수도 있다. 
			) throws Exception {
		MemberVo vo = service.selectOne(id);
		mv.addObject("membervo", vo);
		mv.setViewName("/member/update");
		return mv;
	}
	@PostMapping("/update")
	public ModelAndView update(ModelAndView mv	
			, MemberVo vo) throws Exception {
		service.update(vo);
		return mv;
	}
	@GetMapping("/delete")
	public ModelAndView delete(ModelAndView mv
			, String id
		) throws Exception{
		
		service.delete(id);
		
		return mv;
	}
	@GetMapping("/info")
	public ModelAndView selectOne(ModelAndView mv
			, String id   // request.getParameter("id")  
			// url " /member/info?id=user3
			// url " /member/info/user3
			) throws Exception {
		if(id == null) {
			mv.setViewName("redirect:/");
			return mv;
		}
		MemberVo result = service.selectOne(id);
		mv.addObject("membervo", result);
		mv.setViewName("member/info");
		return mv;
	}
	@GetMapping("/list")
	public ModelAndView selectList(ModelAndView mv) throws Exception{
		List<MemberVo> result = service.selectList();
		mv.addObject("membervolist", result);
		return mv;
	}
	@GetMapping("/selflist")
	public ModelAndView selectList(ModelAndView mv, HttpServletRequest req) throws Exception{
		
		// memberlist 에 값 담고 setAttribute
		mv.addObject("memberlist", service.selectList());
		// memberlist 위치 지정
		mv.setViewName("member/list");
		return mv;		
	}
	
	
	// 이건  NullPointerException 만
	@ExceptionHandler(NullPointerException.class) 
	public ModelAndView memberNullPointExceptionHandler( Exception e) {
		
		e.printStackTrace();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()+ "오류가 발생했습니다.");
		mv.setViewName("error/500error");
		return mv;
		
	}
	
	// 이건  NumberFormatException 만
	@ExceptionHandler(NumberFormatException.class) 
	public ModelAndView memberNumberFormatExceptionHandler( Exception e) {
			
		e.printStackTrace();
			
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()+ "오류가 발생했습니다.");
		mv.setViewName("error/500error");
		return mv;
			
	}
	
	// 이건  SQLException 만
	@ExceptionHandler(SQLException.class) 
	public ModelAndView memberSQLExceptionHandler( Exception e) {
					
		e.printStackTrace();
					
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()+ "오류가 발생했습니다.");
		mv.setViewName("error/500error");
		return mv;

	}		
		
	@ExceptionHandler(Exception.class)
	public ModelAndView memberExceptionHandler( Exception e) {
		
		e.printStackTrace();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()+ "오류가 발생했습니다.");
		mv.setViewName("error/500error");
		return mv;
		
	}
	
	
	
}
