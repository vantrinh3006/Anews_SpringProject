package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.TinTucDAO;
import spring.models.TinTuc;

@Service
public class TinTucService {

	@Autowired
	private TinTucDAO tinTucDAO;
	
	public List<TinTuc> findAll(){
		List<TinTuc> list = tinTucDAO.findAll();
		return list;
	}
	
	public List<TinTuc> findByCatId(int idDanhMucTin){
		List<TinTuc> list = tinTucDAO.findByCatId(idDanhMucTin);
		return list;
	}
	
	public TinTuc findById(int idTinTuc){
		TinTuc tinTuc = tinTucDAO.findById(idTinTuc);
		return tinTuc;
	}
	
	public int addTinTuc(TinTuc tinTuc, String fileName){
		int saved = tinTucDAO.addTinTuc(tinTuc, fileName);
		return saved;
	}
	
}
