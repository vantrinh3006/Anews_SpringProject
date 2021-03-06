package spring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.models.DanhMucTin;

@Repository //để spring hiểu đây là anotation tương tác database
public class DanhMucTinDAO {	//class này sẽ biến thành 1 bean trong spring 
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<DanhMucTin> findAll(){
		String sql = "SELECT * FROM danhmuctin";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<DanhMucTin>(DanhMucTin.class));
	}
	
}
