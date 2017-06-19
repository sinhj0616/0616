package com.choa.ex4;



import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.board.BoardDTO;
import com.choa.notice.NoticeDTO;
import com.choa.notice.NoticeServiceImpl;
import com.choa.util.RowMaker;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {
	
	@Inject  //type
	private NoticeServiceImpl noticeService;
	
	

	//list
	@RequestMapping(value="NoticeList" , method=RequestMethod.GET)
	public String NoticeList(Model model, @RequestParam(defaultValue="1")Integer curPage)throws Exception{
		List<BoardDTO> ar =noticeService.boardList(curPage);
		model.addAttribute("list", ar);
		model.addAttribute("board","notice");
		return "board/boardList";
	}
	
	@RequestMapping(value="NoticeView" , method=RequestMethod.GET)
	public void NoticeView(Model model,Integer num)throws Exception{
	
		BoardDTO noticeDTO=noticeService.boardView(num);
		model.addAttribute("dto", noticeDTO);
		//if 성공 실패
		/*	if(num==null){
		
		}*/
		
	}
	//writeForm
	@RequestMapping(value="NoticeWrite" , method=RequestMethod.GET)
	public void NoticeWrite(Model model){
		
		model.addAttribute("path", "Write");
	}
	
	
	//write
	@RequestMapping(value="NoticeWrite" , method=RequestMethod.POST)
	public String NoticeWrite(NoticeDTO noticeDTO , RedirectAttributes rd)throws Exception{
		int result=noticeService.boardWrite(noticeDTO);
		String message ="fail";
		if(result>0){
			message ="success";
		}
		//if 성공 실패
		rd.addFlashAttribute("message", message);
		return "redirect:NoticeList?curPage=1";
	}
	//updateForm
		@RequestMapping(value="NoticeUpdate", method=RequestMethod.GET)
		public String noticeUpdate(Integer num, Model model) throws Exception{
			BoardDTO noticeDTO = noticeService.boardView(num);
			model.addAttribute("dto", noticeDTO);
			model.addAttribute("path", "Update");
			return "notice/NoticeWrite";
		}
	
	
	//update
	@RequestMapping(value="NoticeUpdate", method=RequestMethod.POST)
	public String noticeUpdate(NoticeDTO noticeDTO, RedirectAttributes rd) throws Exception{
		int result = noticeService.boardUpdate(noticeDTO);
		System.out.println("값:" + noticeDTO);
		String message = "FAIL";
		if(result>0){
			message="SUCCESS";
		}
		rd.addFlashAttribute("message", message);
		return "redirect:NoticeList?curPage=1";
	}
	
	@RequestMapping(value="NoticeDelete" , method=RequestMethod.GET)
	public String NoticeDelete(Integer num , RedirectAttributes rd)throws Exception{
		int result =noticeService.boardDelete(num);
		String message = "failed";
		//if 성공 실패
		if(result>0){
			message ="success";
		}
		
		rd.addFlashAttribute("message", message);
		return "redirect:NoticeList";
	}

}
