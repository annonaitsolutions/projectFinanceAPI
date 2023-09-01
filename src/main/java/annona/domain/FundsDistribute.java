package annona.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Configurable;


@Entity
@Configurable
public class FundsDistribute {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private Float buyingCostSanc;
	
	private Float instantAmount;
	
	private Float distributedAmount;
	
	private Float utilizedAmount;
	
     private String email;
	
	private String managerEmail;
	
	private String custHeadEmail;
	
	private String custHeadMngEmail;
	
	private Float balance;
	
	private Float busBalance;
	
	private Float amount;
	
	private String customerHeadName;
	
	private String customerHeadKey;
	
	private String customerName;
	
	private String currentRole;
	
	private String masterKey;
	
	private String transactionId;
	
	private Integer flag;
	
	private String currency;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getBuyingCostSanc() {
		return buyingCostSanc;
	}

	public void setBuyingCostSanc(Float buyingCostSanc) {
		this.buyingCostSanc = buyingCostSanc;
	}

	public Float getDistributedAmount() {
		return distributedAmount;
	}

	public void setDistributedAmount(Float distributedAmount) {
		this.distributedAmount = distributedAmount;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getCustomerHeadName() {
		return customerHeadName;
	}

	public void setCustomerHeadName(String customerHeadName) {
		this.customerHeadName = customerHeadName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
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

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCustomerHeadKey() {
		return customerHeadKey;
	}

	public void setCustomerHeadKey(String customerHeadKey) {
		this.customerHeadKey = customerHeadKey;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Float getInstantAmount() {
		return instantAmount;
	}

	public void setInstantAmount(Float instantAmount) {
		this.instantAmount = instantAmount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getCustHeadEmail() {
		return custHeadEmail;
	}

	public void setCustHeadEmail(String custHeadEmail) {
		this.custHeadEmail = custHeadEmail;
	}

	public String getCustHeadMngEmail() {
		return custHeadMngEmail;
	}

	public Float getUtilizedAmount() {
		return utilizedAmount;
	}

	public void setUtilizedAmount(Float utilizedAmount) {
		this.utilizedAmount = utilizedAmount;
	}

	public void setCustHeadMngEmail(String custHeadMngEmail) {
		this.custHeadMngEmail = custHeadMngEmail;
	}

	public Float getBusBalance() {
		return busBalance;
	}

	public void setBusBalance(Float busBalance) {
		this.busBalance = busBalance;
	}

	

}
