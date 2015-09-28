package com.baobaotao.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baobaotao.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/register")
	public String register(){
		return "user/register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView createUser(User user) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/createSuccess");
	    mav.addObject("wellcome", "Hello World");
	    mav.addObject("test", "ni hao afdasfd");
	    mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping("/{userId}/show")
	public ModelAndView showUserDetail(@PathVariable("userId") String userId,
			@CookieValue("JSESSIONID") String sessionId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/showDetail");
		mav.addObject("userId", sessionId);
		return mav;
	}
	
	@RequestMapping("/handle11")	
	@ResponseBody
	public String handle11(HttpServletRequest request, HttpServletResponse response) {
		return request.getHeader("Accept-Language");
	}
	
	@RequestMapping("/handle41")
	public ModelAndView handle41(@RequestBody String requestBody) {
		ModelAndView mov = new ModelAndView("user/success");
		mov.addObject("body", requestBody);
		System.out.println(requestBody);
		return mov;
	}
	
	@ResponseBody
	@RequestMapping("/handle42/{imageName}")
	public byte[] handle42(@PathVariable String imageName) throws IOException {
		String filePath = "D:\\tmp\\" + imageName +".jpg";
		Resource res = new FileSystemResource(filePath);
		byte[] imageData = FileCopyUtils.copyToByteArray(res.getInputStream());
		return imageData;
	}
}
