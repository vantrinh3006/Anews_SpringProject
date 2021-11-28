package spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.models.TinTuc;

@Controller
public class DemoController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("demo")
	public String demo(Model model) {
		String sql = "SELECT * FROM tintuc WHERE idTinTuc = 1"; // return 1 object
		TinTuc tinTuc = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<TinTuc>(TinTuc.class));
		model.addAttribute("tinTuc", tinTuc);
		return "demo";
	}

	@GetMapping("demo2")
	public String demo2(Model model) {
		String sql = "SELECT * FROM tintuc"; // return 1 list
		List<TinTuc> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TinTuc>(TinTuc.class));
		model.addAttribute("list", list);
		return "demo2";
	}
	
	@Transactional
	@GetMapping("demo3")
	@ResponseBody			// vì không dùng view
	public String demo3() {
		String sql = "INSERT INTO tintuc(tenTinTuc, moTa, hinhAnh, chiTiet, idDanhMucTin) VALUES('Tên1', 'mô tả 1','picture1' , 'chi tiết 1',1)";
		jdbcTemplate.update(sql);
		sql = "INSERT1 INTO danhmuctin(tenDanhMucTin VALUES('Tên danh mục tin1')"; 
		jdbcTemplate.update(sql);
		return "";
	}
}
