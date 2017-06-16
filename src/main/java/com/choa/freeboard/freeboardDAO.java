package com.choa.freeboard;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class freeboardDAO {
	
	@Autowired
	private DataSource dataSource;
	
	

}
