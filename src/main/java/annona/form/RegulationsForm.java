package annona.form;

import org.springframework.stereotype.Component;

@Component
public class RegulationsForm {

	private Long id;

	private String countryName;

	private String state;

	private String regulation;

	private String exportedGoods;

	private String bannedGoods;

	private String transactionId;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getRegulation() {
		return regulation;
	}

	public void setRegulation(String regulation) {
		this.regulation = regulation;
	}

	public String getExportedGoods() {
		return exportedGoods;
	}

	public void setExportedGoods(String exportedGoods) {
		this.exportedGoods = exportedGoods;
	}

	public String getBannedGoods() {
		return bannedGoods;
	}

	public void setBannedGoods(String bannedGoods) {
		this.bannedGoods = bannedGoods;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}	
}
