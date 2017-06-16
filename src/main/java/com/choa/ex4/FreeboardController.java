package com.choa.ex4;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.choa.freeboard.FreeboardService;

@Controller
@RequestMapping(value="/freeboard/**")
public class FreeboardController {
	
	@Inject
	private FreeboardService freeboardService;
	
	@RequestMapping(value="test")
	public void test(){
		System.out.println("controller에서 : "+freeboardService);
	
	}


}
