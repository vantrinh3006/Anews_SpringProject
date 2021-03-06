package spring.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.models.TinTuc;

@Repository
public class TinTucDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<TinTuc> findAll(){
		String sql = "SELECT * FROM tintuc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TinTuc.class));
	}
	
	public List<TinTuc> findByCatId(int idDanhMucTin){
		String sql = "SELECT * FROM tintuc WHERE idDanhMucTin = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TinTuc.class), idDanhMucTin);
	}
	
	public TinTuc findById(int idTinTuc) {
		String sql = "SELECT * FROM tintuc WHERE idTinTuc = ?";
//		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<TinTuc>(TinTuc.class), idTinTuc);
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<TinTuc>(TinTuc.class), idTinTuc);
	}
	
	public int addTinTuc(TinTuc tinTuc, String fileName) {
		String sql = "INSERT INTO tintuc(tenTinTuc, moTa, hinhAnh, chiTiet, ngayDang, idDanhMucTin) VALUES(?,?,?,?,?,?)";	
			tinTuc.setNgayDang(new Timestamp(System.currentTimeMillis()));
		return jdbcTemplate.update(sql,
				tinTuc.getTenTinTuc(), tinTuc.getMoTa(), fileName, tinTuc.getChiTiet(), tinTuc.getNgayDang(), tinTuc.getIdDanhMucTin());
				
		
	}
}
