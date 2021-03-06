package spring.models;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor	
@NoArgsConstructor

public class TinTuc {
	
	private int idTinTuc;
	
	@NotEmpty
	@Size(min=5 , max= 20)
	private String tenTinTuc;
	
	@NotEmpty
	@Size(min=5 , max= 100)
	private String moTa;
	
	private String hinhAnh;
	
	@NotEmpty
	@Size(min=5 , max= 500)
	private String chiTiet;
	
	private Timestamp ngayDang;
	
	
	private int idDanhMucTin;
	
	private String picDes;		// tên hình ảnh sau khi được chỉnh sửa vd:1-67495188607699.jpg
	private MultipartFile pic;

	
	public TinTuc(int idTinTuc, String tenTinTuc, String moTa, String chiTiet, Timestamp ngayDang, int idDanhMucTin) {
		super();
		this.idTinTuc = idTinTuc;
		this.tenTinTuc = tenTinTuc;
		this.moTa = moTa;
		this.chiTiet = chiTiet;
		this.ngayDang = ngayDang;
		this.idDanhMucTin = idDanhMucTin;
	}
	
}
