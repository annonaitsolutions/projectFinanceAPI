package annona.trade.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class TDraftsMasterPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	private String customer;

	private String currencySymbol;

	private Float buyingCost;

	private String category;

	private Integer flag;

	private String product;

	private String description;

	private String licence;

	private Float weight;
	
	private Date draftsDate;

	private Float quantity;

	private Integer tenure;

	private Float workingCapital;

	private Integer WcTenure;

	private Float sellingCost;

	private Integer sellingTenure;

	private String masterKey;

	private String transactionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public Float getBuyingCost() {
		return buyingCost;
	}

	public void setBuyingCost(Float buyingCost) {
		this.buyingCost = buyingCost;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public Float getWorkingCapital() {
		return workingCapital;
	}

	public void setWorkingCapital(Float workingCapital) {
		this.workingCapital = workingCapital;
	}

	public Integer getWcTenure() {
		return WcTenure;
	}

	public void setWcTenure(Integer wcTenure) {
		WcTenure = wcTenure;
	}

	public Float getSellingCost() {
		return sellingCost;
	}

	public void setSellingCost(Float sellingCost) {
		this.sellingCost = sellingCost;
	}

	public Integer getSellingTenure() {
		return sellingTenure;
	}

	public void setSellingTenure(Integer sellingTenure) {
		this.sellingTenure = sellingTenure;
	}

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getDraftsDate() {
		return draftsDate;
	}

	public void setDraftsDate(Date draftsDate) {
		this.draftsDate = draftsDate;
	}

}
