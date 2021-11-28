package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import spring.dao.DanhMucTinDAO;
import spring.models.DanhMucTin;

@Service
public class DanhMucTinService {

	@Autowired
	private DanhMucTinDAO danhMucTinDAO;
	public List<DanhMucTin> findAll(){
		List<DanhMucTin> list = danhMucTinDAO.findAll();
														//xử lý gì đó 
		return list;
	}
}
