package com.choa.notice;





import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;
import javax.swing.Spring;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.ls.LSInput;

import com.choa.board.BoardDTO;
import com.choa.util.ListInfo;
import com.choa.util.PageMaker;




public class NoticeDAOImplTest extends MyAbstractClass{
	
	@Autowired
	private NoticeDAOImpl dao;
	
	/*@Test
	public void connectionTest()throws Exception{
		NoticeDTO noticeDTO =new NoticeDTO();
		noticeDTO.setWriter("iu");
		noticeDTO.setTitle("test");
		noticeDTO.setContents("testiu");
		int result =dao.boardWrite(noticeDTO);
		System.out.println(noticeDTO);
		assertEquals(1, result);
		
		BoardDTO boardDTO=dao.boardView(2);
		System.out.println(boardDTO.getTitle());
		assertNotNull(boardDTO);
	}
	*/
	@Test
	public void connectionTest()throws Exception{
		ListInfo listInfo =new ListInfo();
		listInfo.setFind("choa");
		listInfo.setSearch("writer");
		int count = dao.boardCount(listInfo);
		System.out.println(listInfo);
		
		/*PageMaker pageMaker =new PageMaker(1);*/
		
		/*List<BoardDTO> ar =dao.boardList(pageMaker.getRowMaker(), "writer", "iu");
		System.out.println(ar.get(0).getTitle());
		System.out.println(ar.get(1).getTitle());*/
		assertNotEquals(0, count);
	}
/*	@Test
	public void counttest()throws Exception{
		int count =dao.boardCount();
		assertNotEquals(0, count);
	}
	
	
	public void update()throws Exception{
		NoticeDTO noticeDTO =new NoticeDTO();
		
		noticeDTO.setTitle("");
		noticeDTO.setContents("");
		noticeDTO.setNum(23);
		int result =dao.boardUpdate(noticeDTO);
		System.out.println(result);
		assertEquals(1, result);
		
	}
	
	
	
	
	public void delete()throws Exception{
		NoticeDTO noticeDTO =new NoticeDTO();
		noticeDTO.setNum(21);
		int result =dao.boardDelete(21);
		System.out.println(result);
		assertEquals(0, result);
		
	}*/
		
}
	
	
	
	
	
	
/*	@Inject
	private NoticeDAOImpl NoticeDAO;
	
	@Test
	public void test()throws Exception {
		PageMaker pageMaker =new PageMaker(1);
		List<NoticeDTO> ar = NoticeDAO.noticeList(pageMaker.getRowMaker());
		System.out.println(pageMaker.getRowMaker());
		assertEquals(0, ar.size()); // null 이냐 아니냐를 판단하는것 //print 대신 쓸수 있다.
	
	}
	@Test
	public void test2()throws Exception{
		int result =NoticeDAO.noticeDelete(100);
		assertEquals(1, result);
	}
}*/
