package kr.co.softsoldesk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import kr.co.softsoldesk.beans.DataBean1;


@Controller
public class TestController {
	/*
	 
	@GetMapping("/test1")
	public String test1(HttpServletRequest request) {
		
		request.setAttribute("data1", "JAVA");
		
		return "redirect:/result1"; //새로운 요청정보가 발생되므로 request소멸 => null
	}

	@GetMapping("/result1")
	public String result1(HttpServletRequest request) {
		//redirect로 요청시 request 객체 소멸 
		String data1=(String)request.getAttribute("data1");
		System.out.println("data1 : "+data1);
		
		
		return "result1";
	}
	 */
	
	@GetMapping("/test1")
	public String test1(HttpServletRequest request) {
		
		request.setAttribute("data1", "JAVA");
		
		return "forward:/result1"; //새로운 요청정보가 발생되므로 request소멸 => null
	}
	@GetMapping("/result1")
	public String result1(HttpServletRequest request) {
		//forward로 전달시 request 객체 유지 
		String data1=(String)request.getAttribute("data1");
		
		System.out.println("data1 : "+data1);
		
		
		
		return "result1";
	}
	
	@GetMapping("/test2")
	public String test1(Model model) {
		//model 사용시 request로 저장
		model.addAttribute("data2","JSP");
		
		return "forward:/result2";
	}
	
	/*
	@GetMapping("/result2")
	public String result(Model model) {
		//model 사용시 새로운 Model 요청으로 인해 덮어쓰기 발생
		String data2=(String)model.getAttribute("data2");
		System.out.println("data2 : "+data2);
		//console 창에 data2=null 
		
		return "result2";
	}
	 * */
	@GetMapping("/result2")
	public String result2(HttpServletRequest request) {
		//model 사용시 request로 사용
		String data2=(String)request.getAttribute("data2");
		System.out.println("data2 : "+data2);
		
		
		return "result2";
	}
	@GetMapping("/test3")
	public ModelAndView test3(ModelAndView mv) {
		//model 사용시 request로 저장
		mv.addObject("data3","Spring");
		mv.setViewName("forward:/result3");
		
		return mv;
	}
	
	@GetMapping("/result3")
	public String result3(HttpServletRequest request) {
		//ModelAndView 사용시 request로 사용
		String data3=(String)request.getAttribute("data3");
		System.out.println("data3 : "+data3);
		
		
		return "result3";
	}
	@GetMapping("/test4")
	public String test4(Model model) {
		
		DataBean1 bean1=new DataBean1();
		bean1.setData1("HTML/CSS");
		bean1.setData2("JavaScript");
		model.addAttribute("bean1", bean1);
		
		return "forward:/result4";  //request영역에 data유지 되면서 처리됨
	}
	@GetMapping("/result4")
	public String result4(HttpServletRequest request) {
		//ModelAndView 사용시 request로 사용
		DataBean1 bean1=(DataBean1)request.getAttribute("bean1");
		System.out.println("bean1.data1 : "+bean1.getData1());
		System.out.println("bean1.data2 : "+bean1.getData2());
		
		
		return "result4";
	}
	@GetMapping("/test5")
	public String test5(@ModelAttribute("bean1") DataBean1 bean1) {
		
		bean1.setData1("Python");
		bean1.setData2("BigData");
		

		return "forward:/result5";
	}
	@GetMapping("/result5")
	public String result5(HttpServletRequest request) {
		DataBean1 bean1=(DataBean1)request.getAttribute("bean1");
		System.out.println("bean1.data1 : "+bean1.getData1());
		System.out.println("bean1.data2 : "+bean1.getData2());
		return "result5";
	}
}