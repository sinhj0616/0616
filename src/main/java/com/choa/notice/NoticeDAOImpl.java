package com.choa.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.util.DBConnect;
import com.choa.util.RowMaker;

import oracle.jdbc.proxy.annotation.Pre;

@Repository
// Notice noticeDao =new NoticeDAO(); 와 같은 것 
public class NoticeDAOImpl implements BoardDAO {

	

	@Autowired
	private SqlSession sqlSession;
	private static final String NAMESPACE="Noticemapper.";
	


	//View
	@Override
	public BoardDTO boardView(int num)throws Exception{
	BoardDTO boardDTO =sqlSession.selectOne(NAMESPACE+"view", num); //무조건 1개 넘겨야 한다.
	
		return boardDTO;
		
		
	}
	


	//TotalCount
	
	@Override
	public int boardCount()throws Exception{
		return sqlSession.selectOne(NAMESPACE+"count");
		
		
	}
	
	
	
	//List 
	
	@Override
	public List<BoardDTO> boardList(RowMaker rowMaker)throws Exception{
		
		return sqlSession.selectList(NAMESPACE+"list", rowMaker);
	
	}
	
	//insert
	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
	
		return sqlSession.insert(NAMESPACE+"write", boardDTO);
	}

	
	//update
	
	@Override
	public int boardUpdate(BoardDTO boardDTO)throws Exception{
		
		return sqlSession.update(NAMESPACE+"update", boardDTO);
		
		
	}
	
	
	//delete 
	
	@Override
	public int boardDelete(int num)throws Exception{
		
		return sqlSession.delete(NAMESPACE+"delete", num);
		
		
	}


	//hit
	
	@Override
	public int boardhit(int num) throws Exception {
		Connection con = null;
		PreparedStatement st =null;
		int result =0;
		
		String sql="update notice set hit=hit+1 where num=? ";
		st =con.prepareStatement(sql);
		st.setInt(1, num);
		result =st.executeUpdate();

		DBConnect.disConnect(st, con);
		
		return result;
	}
}
