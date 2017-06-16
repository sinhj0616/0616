package com.choa.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

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
	private DataSource dataSource;
	
	
	/*//setter 방식 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
*/
	

	//View
	@Override
	public BoardDTO boardView(int num)throws Exception{
		Connection con = dataSource.getConnection();
		
		PreparedStatement st =null;
		ResultSet rs =null;
		String sql ="select * from notice where num=?";
		BoardDTO boardDTO =null;
		
		st=con.prepareStatement(sql);
		st.setInt(1, num);
		rs =st.executeQuery();
		
		if(rs.next()){
			boardDTO =new BoardDTO();
			boardDTO.setNum(rs.getInt("num"));
			boardDTO.setWriter(rs.getString("writer"));
			boardDTO.setTitle(rs.getString("title"));
			boardDTO.setContents(rs.getString("contents"));
			boardDTO.setReg_date(rs.getDate("reg_date"));
			boardDTO.setHit(rs.getInt("hit"));	
		}
		//close
		DBConnect.disConnect(rs, st, con);
		return boardDTO;
	}
	


	//TotalCount
	@Override
	public int boardCount()throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement st =null;
		ResultSet rs =null;
		
		String sql="select nvl(count(num), 0) from notice";
		
		st =con.prepareStatement(sql);
		
		rs =st.executeQuery();
		
		rs.next();
		
		int result =rs.getInt(1);
		
		DBConnect.disConnect(rs, st, con);
		
		return result;
	}
	
	
	
	//List 
	@Override
	public List<BoardDTO> boardList(RowMaker rowMaker)throws Exception{
		Connection con =dataSource.getConnection();
		PreparedStatement st =null;
		ResultSet rs =null;
		List<BoardDTO> ar =new ArrayList<BoardDTO>();
		
		String sql="select * from "
				+ "(select rownum R, N.* from "
				+ "(select * from notice order by num desc) N )"
				+ "where R between ? and ?";
		st= con.prepareStatement(sql);
		st.setInt(1, rowMaker.getStartRow());
		st.setInt(2, rowMaker.getLastRow());
		rs =st.executeQuery();
		
		while(rs.next()){
		
		BoardDTO boardDTO =new BoardDTO();
		boardDTO.setNum(rs.getInt("num"));
		boardDTO.setWriter(rs.getString("writer"));
		boardDTO.setTitle(rs.getString("title"));
		boardDTO.setContents(rs.getString("contents"));
		boardDTO.setHit(rs.getInt("hit"));
		ar.add(boardDTO);
		}
		DBConnect.disConnect(rs, st, con);
		
		return ar;
	}
	
	//Write 
	@Override
	public int boardWrite(BoardDTO boardDTO)throws Exception{
		Connection con =dataSource.getConnection();
		PreparedStatement st =null;
		int result =0;
		
		String sql="insert into notice values(point_seq.nextval, ?,?,?, sysdate, 0)"; //0은 hit수
		st =con.prepareStatement(sql);
		st.setString(1, boardDTO.getWriter());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		result =st.executeUpdate();
		DBConnect.disConnect(st, con);
		
		return result;
	}
	//update
	@Override
	public int boardUpdate(BoardDTO boardDTO)throws Exception{
		Connection con =dataSource.getConnection();
		PreparedStatement st =null;
		int result=0;
		
		String sql="update notice set title=?, contents=? where num=?";
		st =con.prepareStatement(sql);
		
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getContents());
		st.setInt(3, boardDTO.getNum());
		DBConnect.disConnect(st, con);
		
		return result;
	}
	//delete 
	@Override
	public int boardDelete(int num)throws Exception{
		Connection con =dataSource.getConnection();
		PreparedStatement st =null;
		int result =0;
		
		String sql="delete notice where num=?";
		st=con.prepareStatement(sql);
		st.setInt(1, num);
		result =st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		return result;
	}



	@Override
	public int boardhit(int num) throws Exception {
		Connection con =dataSource.getConnection();
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
