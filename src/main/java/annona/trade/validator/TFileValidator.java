package annona.trade.validator;  
  
import org.springframework.validation.Errors;  
import org.springframework.validation.Validator;  

import annona.domain.UploadedFile;
import annona.form.UploadedFileForm;
import annona.trade.form.TUploadedFileForm;
 
  
public class TFileValidator implements Validator {  
  

  
 @Override  
 public void validate(Object TUploadedFile, Errors errors) {  
  
  TUploadedFileForm file = (TUploadedFileForm) TUploadedFile;  
  
  if (file.getFile().getSize() == 0) {  
   errors.rejectValue("file", "tuploadForm.salectFile",  
     "Please select a file!");  
  }  
  
 }

@Override
public boolean supports(Class<?> clazz) {
	// TODO Auto-generated method stub
	return false;
}  
  
}  