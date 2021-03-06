package spring.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.commons.DaoUtil;
import spring.constants.MessageConstant;
import spring.models.DanhMucTin;
import spring.models.TinTuc;
import spring.service.DanhMucTinService;
import spring.service.TinTucService;
import spring.validate.PictureValidator;

@Controller
@RequestMapping("anews")
public class AnewsController {

	@Autowired
	private DanhMucTinService danhMucTinService;
	@Autowired
	private TinTucService tinTucService;
	private static final String DIR_UPLOAD = "uploads";	//set folder name: upload
	private List<TinTuc> listTinTuc = new ArrayList<TinTuc>();
	
	@Autowired	//để liên kết 2 bean lại với nhau: pictureValidator và validFormController
	private PictureValidator pictureValidator;
	
	@Autowired
	private MessageSource messageSource;
	
	@ModelAttribute										// tất cả phương thức chứa trong AnewsController đều có setDanhMucTin
	public void setDanhMucTin(Model model) {
		List<DanhMucTin> listDanhMucTin = danhMucTinService.findAll();
		model.addAttribute("listDanhMucTin", listDanhMucTin);
	}
	
	@GetMapping("index")
	public String index(Model model) {
		List<TinTuc> listTinTuc = tinTucService.findAll();
		model.addAttribute("listTinTuc", listTinTuc);
		
		return "anews.index";
	}

	@GetMapping("cat/{id}")
	public String cat(@PathVariable int id, Model model) {
		List<TinTuc> listTinTuc = tinTucService.findByCatId(id); 
		model.addAttribute("listTinTuc", listTinTuc);
		return "anews.cat";
	}

	@GetMapping("detail/{name}/{id}")
	public String detail(@PathVariable int id, Model model) {
		TinTuc tinTuc = tinTucService.findById(id);
		model.addAttribute("tinTuc", tinTuc);
		return "anews.detail";
	}
	
	@GetMapping("add")
	public String addTinTuc(Model model) {
		List<DanhMucTin> listDanhMucTin = danhMucTinService.findAll();
		model.addAttribute("listDanhMucTin", listDanhMucTin);
		return "anews.add";
	}
	
	@PostMapping("add")
	public String addTinTuc(@Valid @ModelAttribute("tinTucError") TinTuc tinTuc, BindingResult rs
				, RedirectAttributes re, HttpServletRequest request, HttpSession session, Model model
				,@RequestParam("idDanhMucTin") String idDanhMucTin, @RequestParam("pic") MultipartFile multipartFile) throws IllegalStateException, IOException {
		
		pictureValidator.validate(multipartFile, rs);
		if (rs.hasErrors()) {
			model.addAttribute("tinTuc", tinTuc);
			model.addAttribute("multipartFile", multipartFile.getOriginalFilename());
			return "anews.add";
		}

		MultipartFile infoFile = tinTuc.getPic(); // get file information
		String fileName = getFileNameServer(infoFile.getOriginalFilename()); // to get file name with extention and path :myfile/.../../pic1.png
		infoFile.transferTo(pathFile(fileName, DIR_UPLOAD, request));
		tinTuc.setPicDes(fileName);
		tinTuc.setIdDanhMucTin(Integer.parseInt(idDanhMucTin));
		
//		listTinTuc.add(tinTuc);
//		session.setAttribute("datas", listTinTuc);
		
		re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_ADD);
		
		int saved = tinTucService.addTinTuc(tinTuc, fileName); // số dòng insert trả về 
		if(DaoUtil.isSuccess(saved)) {
			re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_ADD);
		return "redirect:/anews/index";
		} else {
			re.addFlashAttribute("msg", MessageConstant.MSG_ERROR);
		}
		return "redirect:/anews/index";
		
		
	}
	
	public String getFileNameServer(String fileName) {
		// file name, null, check empty
		if(!StringUtils.isEmpty(fileName)){
			String extention = FilenameUtils.getExtension(fileName); // to get file extention , ex: png, jpg,...
			String baseName = FilenameUtils.getBaseName(fileName); // file name without extention and path  ex: 1
			StringBuilder builder = new StringBuilder(); // to concatenate String
			builder.append(baseName).append("-").append(System.nanoTime()).append(".").append(extention); // đặt lại tên theo cấu trúc để ko trùng tên 
			return builder.toString();
		}
		return StringUtils.EMPTY;
	}
	public File pathFile(String fileName, String folder, HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("");	//trả về đường dẫn tuyệt đối web
		File disry = new File(rootPath + folder);	// đường dẫn folder: đường dẫn tuyệt đối=> cần httpservlet request
		
		if(!disry.exists()) {	// chưa tồn tại file=> tạo
			disry.mkdirs();
		}
		File file = new File(rootPath + folder + "/" + fileName);
		return file;
	}
}
