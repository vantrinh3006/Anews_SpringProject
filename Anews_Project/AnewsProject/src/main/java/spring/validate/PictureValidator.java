package spring.validate;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component   // để biến PictureValidator thành bean trong spring
public class PictureValidator implements Validator {
	
	@Autowired
	private MessageSource messageSource;	//@Autowired sẽ tìm đến những @bean có tên giống messageSource


	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		
	}

	public void validate(MultipartFile multipartFile, BindingResult errors) {
		if (multipartFile.getOriginalFilename().equals("")) {	 
			errors.rejectValue("hinhAnh", null, messageSource.getMessage("validPicture", null, Locale.getDefault()));
				// reject cho Error cũng như reject cho BindingResult rs vì  BindingResult extend với Error : dòng 31,35 valid form
		}
	}
	
}
