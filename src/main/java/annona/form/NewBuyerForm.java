package annona.form;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import annona.domain.CustomerHead;
import annona.domain.EndUser;
import annona.domain.NewBuyer;

@Component
public class NewBuyerForm {
	private Long id;

	private String name;

	private String customerPrefix;

	private String buyerName;

	private String companyName;

	private Long companyId;

	private String bank;

	private String branch;

	private String ifsc;

	private String bankEmail;

	private String address;

	private Integer pinCode;

	private String country;

	private Float custLimit;

	private String rating;

	private String state;

	private String city;

	private String contactNum;

	private String altcontactNum;

	private String email;

	private String altEmail;

	private String comment;

	private Date date;

	private String uniqueKey;

	private Date approveDate;

	private String status;

	private String bName;
	
	private String finalPro;

	private String cStatus;

	private EndUser endUser;

	private String currencydeal;

	private String transactionId;

	private CustomerHead customerDetails;

	private List<NewBuyer> buyerList;

	private Date accExpiryDate;

	private Float rate;

	public List<NewBuyer> getBuyerList() {
		return buyerList;
	}

	public void setBuyerList(List<NewBuyer> buyerList) {
		this.buyerList = buyerList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * public String getCustomer() { return customer; }
	 * 
	 * public void setCustomer(String customer) { this.customer = customer; }
	 */

	public String getCustomerPrefix() {
		return customerPrefix;
	}

	public void setCustomerPrefix(String customerPrefix) {
		this.customerPrefix = customerPrefix;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBankEmail() {
		return bankEmail;
	}

	public void setBankEmail(String bankEmail) {
		this.bankEmail = bankEmail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getAltcontactNum() {
		return altcontactNum;
	}

	public void setAltcontactNum(String altcontactNum) {
		this.altcontactNum = altcontactNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAltEmail() {
		return altEmail;
	}

	public void setAltEmail(String altEmail) {
		this.altEmail = altEmail;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public EndUser getEndUser() {
		return endUser;
	}

	public void setEndUser(EndUser endUser) {
		this.endUser = endUser;
	}

	public CustomerHead getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerHead customerDetails) {
		this.customerDetails = customerDetails;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCurrencydeal() {
		return currencydeal;
	}

	public void setCurrencydeal(String currencydeal) {
		this.currencydeal = currencydeal;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getcStatus() {
		return cStatus;
	}

	public void setcStatus(String cStatus) {
		this.cStatus = cStatus;
	}

	public Float getCustLimit() {
		return custLimit;
	}

	public void setCustLimit(Float custLimit) {
		this.custLimit = custLimit;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Date getAccExpiryDate() {
		return accExpiryDate;
	}

	public void setAccExpiryDate(Date accExpiryDate) {
		this.accExpiryDate = accExpiryDate;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	
	public String getFinalPro() {
		return finalPro;
	}

	public void setFinalPro(String finalPro) {
		this.finalPro = finalPro;
	}


}
