package annona.domain;  
  
import org.springframework.validation.Errors;  
import org.springframework.validation.Validator;  

import annona.form.VendorUploadedFileForm;


 
  
public class VendorFileValidator implements Validator {  
  

  
 @Override  
 public void validate(Object VendorUploadedFile, Errors errors) {  
  
	 VendorUploadedFileForm file = (VendorUploadedFileForm) VendorUploadedFile;  
  
  if (file.getFile().getSize() == 0) {  
   errors.rejectValue("file", "vendorfileUploadForm.salectFile",  
     "Please select a file!");  
  }  
  
 }

@Override
public boolean supports(Class<?> clazz) {
	// TODO Auto-generated method stub
	return false;
}  
  
}  