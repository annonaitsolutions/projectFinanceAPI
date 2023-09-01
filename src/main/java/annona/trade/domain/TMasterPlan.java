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
public class TMasterPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	private String customer;
	
	private String customerPrefix;
	
	private String customerEmail;

	private String currencySymbol;

	private Float buyingCost;
	
	private Float amountPaid;
	
	private String accept;
	
	private Float buyingCostSanc;
	
	private Date buyingCostDate;
	
	private Float distributedAmt;
	
	private Float utilizedBusnsAmt;

	private Float Distributedbalance;

	private String status;
	
    private Float distributedAmount;
	
	private Float balance;
	
	private String ApprovalSent;
	
	private String managerStatus;
	
	private String comment;
	
	private String managerComment;

	private Integer flag;

	private String category;
	
	private String aStatus;
	
	private String aComment;

	private String product;

	private String description;

	private String licence;

	private Float weight;

	private Float quantity;

	private Integer tenure;

	private Float workingCapital;

	private Integer WcTenure;
	
	private Float wcSancAmount;
	
	private Float wcSancInterest;
	
	private Float wcTotalInterest;
	
	private String creditStatus;
	
	private String creditComment;
	
    private String wcStatus;
	
	private String wcComment;
	
	private Date appDate;
	
	private Float wcTotalAmount;

	private Float sellingCost;

	private Integer sellingTenure;

	private String masterKey;

	private String transactionId;

	private Date masterPlanDate;
	
private String interestType;
	
	private Float rateOfInt;
	
	private Integer noOfDays;
	
	private Float plrRate;
	
	private Integer basicPoints;
	
	
	private Float calPlrRate;
	
	private Float basicAmt;
	
	private Float total1;
	
	private Float rateOfInt1;
	
	private Integer flatCharges;
	
	private Float percentage;
	
	private Float percentAmt;
	
	private Float procFee;
	
	private Float docFee;
	
	private Float lateFee;
	
	private String taxName;
	
	private Float taxPercentage;
	
	private Float taxAmt;
	
     private String taxName1;
	
	private Float taxPercentage1;
	
	private Float taxAmt1;
	
    private String taxName2;
	
	private Float taxPercentage2;
	
	private Float taxAmt2;
	
	private Float funalAmt;

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

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getMasterPlanDate() {
		return masterPlanDate;
	}

	public void setMasterPlanDate(Date masterPlanDate) {
		this.masterPlanDate = masterPlanDate;
	}

	public Float getBuyingCostSanc() {
		return buyingCostSanc;
	}

	public void setBuyingCostSanc(Float buyingCostSanc) {
		this.buyingCostSanc = buyingCostSanc;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getApprovalSent() {
		return ApprovalSent;
	}

	public void setApprovalSent(String approvalSent) {
		ApprovalSent = approvalSent;
	}

	public String getManagerStatus() {
		return managerStatus;
	}

	public void setManagerStatus(String managerStatus) {
		this.managerStatus = managerStatus;
	}

	public Float getWcSancAmount() {
		return wcSancAmount;
	}

	public void setWcSancAmount(Float wcSancAmount) {
		this.wcSancAmount = wcSancAmount;
	}

	public Float getWcSancInterest() {
		return wcSancInterest;
	}

	public void setWcSancInterest(Float wcSancInterest) {
		this.wcSancInterest = wcSancInterest;
	}

	public Float getWcTotalInterest() {
		return wcTotalInterest;
	}

	public void setWcTotalInterest(Float wcTotalInterest) {
		this.wcTotalInterest = wcTotalInterest;
	}

	public Float getWcTotalAmount() {
		return wcTotalAmount;
	}

	public void setWcTotalAmount(Float wcTotalAmount) {
		this.wcTotalAmount = wcTotalAmount;
	}

	public String getManagerComment() {
		return managerComment;
	}

	public void setManagerComment(String managerComment) {
		this.managerComment = managerComment;
	}

	public String getCreditStatus() {
		return creditStatus;
	}

	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}

	public String getCreditComment() {
		return creditComment;
	}

	public void setCreditComment(String creditComment) {
		this.creditComment = creditComment;
	}

	public String getWcStatus() {
		return wcStatus;
	}

	public void setWcStatus(String wcStatus) {
		this.wcStatus = wcStatus;
	}

	public String getWcComment() {
		return wcComment;
	}

	public void setWcComment(String wcComment) {
		this.wcComment = wcComment;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
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

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Float getUtilizedBusnsAmt() {
		return utilizedBusnsAmt;
	}

	public void setUtilizedBusnsAmt(Float utilizedBusnsAmt) {
		this.utilizedBusnsAmt = utilizedBusnsAmt;
	}
	public Float getDistributedAmt() {
		return distributedAmt;
	}

	public void setDistributedAmt(Float distributedAmt) {
		this.distributedAmt = distributedAmt;
	}

	public Float getDistributedbalance() {
		return Distributedbalance;
	}

	public void setDistributedbalance(Float distributedbalance) {
		Distributedbalance = distributedbalance;
	}

	public String getaStatus() {
		return aStatus;
	}

	public void setaStatus(String aStatus) {
		this.aStatus = aStatus;
	}

	public String getaComment() {
		return aComment;
	}

	public void setaComment(String aComment) {
		this.aComment = aComment;
	}

	public String getInterestType() {
		return interestType;
	}

	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}

	public Float getRateOfInt() {
		return rateOfInt;
	}

	public void setRateOfInt(Float rateOfInt) {
		this.rateOfInt = rateOfInt;
	}

	public Integer getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}

	public Float getPlrRate() {
		return plrRate;
	}

	public void setPlrRate(Float plrRate) {
		this.plrRate = plrRate;
	}

	public Integer getBasicPoints() {
		return basicPoints;
	}

	public void setBasicPoints(Integer basicPoints) {
		this.basicPoints = basicPoints;
	}

	public Float getCalPlrRate() {
		return calPlrRate;
	}

	public void setCalPlrRate(Float calPlrRate) {
		this.calPlrRate = calPlrRate;
	}

	public Float getBasicAmt() {
		return basicAmt;
	}

	public void setBasicAmt(Float basicAmt) {
		this.basicAmt = basicAmt;
	}

	public Float getTotal1() {
		return total1;
	}

	public void setTotal1(Float total1) {
		this.total1 = total1;
	}

	public Float getRateOfInt1() {
		return rateOfInt1;
	}

	public void setRateOfInt1(Float rateOfInt1) {
		this.rateOfInt1 = rateOfInt1;
	}

	public Integer getFlatCharges() {
		return flatCharges;
	}

	public void setFlatCharges(Integer flatCharges) {
		this.flatCharges = flatCharges;
	}

	public Float getPercentage() {
		return percentage;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}

	public Float getPercentAmt() {
		return percentAmt;
	}

	public void setPercentAmt(Float percentAmt) {
		this.percentAmt = percentAmt;
	}

	public Float getProcFee() {
		return procFee;
	}

	public void setProcFee(Float procFee) {
		this.procFee = procFee;
	}

	public Float getDocFee() {
		return docFee;
	}

	public void setDocFee(Float docFee) {
		this.docFee = docFee;
	}

	public Float getLateFee() {
		return lateFee;
	}

	public void setLateFee(Float lateFee) {
		this.lateFee = lateFee;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public Float getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(Float taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public Float getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(Float taxAmt) {
		this.taxAmt = taxAmt;
	}

	public String getTaxName1() {
		return taxName1;
	}

	public void setTaxName1(String taxName1) {
		this.taxName1 = taxName1;
	}

	public Float getTaxPercentage1() {
		return taxPercentage1;
	}

	public void setTaxPercentage1(Float taxPercentage1) {
		this.taxPercentage1 = taxPercentage1;
	}

	public Float getTaxAmt1() {
		return taxAmt1;
	}

	public void setTaxAmt1(Float taxAmt1) {
		this.taxAmt1 = taxAmt1;
	}

	public String getTaxName2() {
		return taxName2;
	}

	public void setTaxName2(String taxName2) {
		this.taxName2 = taxName2;
	}

	public Float getTaxPercentage2() {
		return taxPercentage2;
	}

	public void setTaxPercentage2(Float taxPercentage2) {
		this.taxPercentage2 = taxPercentage2;
	}

	public Float getTaxAmt2() {
		return taxAmt2;
	}

	public void setTaxAmt2(Float taxAmt2) {
		this.taxAmt2 = taxAmt2;
	}

	public Float getFunalAmt() {
		return funalAmt;
	}

	public void setFunalAmt(Float funalAmt) {
		this.funalAmt = funalAmt;
	}

	public Date getBuyingCostDate() {
		return buyingCostDate;
	}

	public void setBuyingCostDate(Date buyingCostDate) {
		this.buyingCostDate = buyingCostDate;
	}

	public Float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Float amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getCustomerPrefix() {
		return customerPrefix;
	}

	public void setCustomerPrefix(String customerPrefix) {
		this.customerPrefix = customerPrefix;
	}

}
