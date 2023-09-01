package annona.form;

import java.util.List;

import org.springframework.stereotype.Component;

import annona.domain.CustomerBranch;
import annona.domain.CustomerSubsidiary;

@Component
public class FundsDistributeForm {
	
     private Long id;
	
	private Float buyingCostSanc;
	
	private Float distributedAmount;
	
	private Float utilizedAmount;
	
	private Float balance;
	
	private Float busBalance;
	
	private Float amount;
	
	private Integer flag;
	
	private String customerHeadName;
	
	private String customerHeadKey;
	
	private String customerName;
	
	private String email;
	
	private String managerEmail;
	
    private String custHeadEmail;
	
	private String custHeadMngEmail;
	
	private String currentRole;
	
	private String masterKey;
	
	private String transactionId;
	
	private String currency;
	
	private String customerBranchName;
	
	private String customerSubsName;
	
	private List<CustomerBranch> customerBranchList;
	
	private List<CustomerSubsidiary> customerSubsList;

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

	public String getCustomerBranchName() {
		return customerBranchName;
	}

	public void setCustomerBranchName(String customerBranchName) {
		this.customerBranchName = customerBranchName;
	}

	public String getCustomerSubsName() {
		return customerSubsName;
	}

	public void setCustomerSubsName(String customerSubsName) {
		this.customerSubsName = customerSubsName;
	}

	public List<CustomerBranch> getCustomerBranchList() {
		return customerBranchList;
	}

	public void setCustomerBranchList(List<CustomerBranch> customerBranchList) {
		this.customerBranchList = customerBranchList;
	}

	public List<CustomerSubsidiary> getCustomerSubsList() {
		return customerSubsList;
	}

	public void setCustomerSubsList(List<CustomerSubsidiary> customerSubsList) {
		this.customerSubsList = customerSubsList;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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

	public Float getBusBalance() {
		return busBalance;
	}

	public void setBusBalance(Float busBalance) {
		this.busBalance = busBalance;
	}

	public void setCustHeadMngEmail(String custHeadMngEmail) {
		this.custHeadMngEmail = custHeadMngEmail;
	}

	public Float getUtilizedAmount() {
		return utilizedAmount;
	}

	public void setUtilizedAmount(Float utilizedAmount) {
		this.utilizedAmount = utilizedAmount;
	}

}
