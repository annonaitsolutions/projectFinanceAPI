package annona.services;

import org.springframework.stereotype.Service;

import annona.form.ColumnGraphForm;

@Service
public class ColumnGraphService {
	
	/**
	 * Method to populate ColumnGraphForm
	 * @param name
	 * @param data
	 * @return
	 */
	public static ColumnGraphForm generateObject(String name, float[] data) {
		ColumnGraphForm columnGraphForm = new ColumnGraphForm();
		columnGraphForm.setName(name);
		columnGraphForm.setData(data);
		
		return columnGraphForm;
	}
}
