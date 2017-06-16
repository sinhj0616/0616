package com.choa.notice;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;
import javax.swing.Spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.choa.util.PageMaker;

import junit.framework.Assert;


public class NoticeDAOImplTest extends MyAbstractClass{
	
	@Inject
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
}
