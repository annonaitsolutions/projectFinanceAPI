package annona.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class SellerBuyingCost {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String masterKey;

	private String customerName;

	private String buyerName;

	private String finalPro;

	private Float quantity;

	private Float amt;

	private Float costperUnit;

	private String transactionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getFinalPro() {
		return finalPro;
	}

	public void setFinalPro(String finalPro) {
		this.finalPro = finalPro;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Float getAmt() {
		return amt;
	}

	public void setAmt(Float amt) {
		this.amt = amt;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Float getCostperUnit() {
		return costperUnit;
	}

	public void setCostperUnit(Float costperUnit) {
		this.costperUnit = costperUnit;
	}



}
