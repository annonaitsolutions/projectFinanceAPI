package annona.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Regulation {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String countryName;

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

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
