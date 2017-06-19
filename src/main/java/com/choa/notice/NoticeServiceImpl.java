package com.choa.notice;


import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.board.BoardService;
import com.choa.util.PageMaker;
import com.choa.util.RowMaker;


@Service
//@Service의 의미 : Notice noticeDao =new NoticeDAO(); 와 같은 것 
public class NoticeServiceImpl implements BoardService{
	
	
	
	@Autowired
	private NoticeDAOImpl noticeDAO;
	
	
	/*//생성자(Constructor) 방식
	public NoticeService(NoticeDAO noticeDAO) throws Exception{
		this.noticeDAO =noticeDAO;
	}
	
	//setter 방식 
	public void setNoticeDAO(NoticeDAO noticeDAO) throws Exception{
		this.noticeDAO = noticeDAO;
	}*/

	

	@Override
	public List<BoardDTO> boardList(int curPage) throws Exception {
		int result =noticeDAO.boardCount();
		PageMaker pageMaker =new PageMaker(curPage, result);
		return noticeDAO.boardList(pageMaker.getRowMaker());
	}

	@Override
	public BoardDTO boardView(int num) throws Exception {
		return noticeDAO.boardView(num);
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		return noticeDAO.boardWrite(boardDTO);
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		return noticeDAO.boardUpdate(boardDTO);
	}

	@Override
	public int boardDelete(int num) throws Exception {
		return noticeDAO.boardDelete(num);
	
	}
}
