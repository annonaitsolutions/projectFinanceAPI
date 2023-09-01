package annona.form;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class RepaymentForm {
	
	private Long id;

    private String customer;
	
	private String customerEmail;
	
    private Float buyingCostSanc;
	
	private Date buyingCostDate;
	
	private String status;
	
	private String accept;
	
	private String payConfirm;
	
	private Float amountPaid;
	
	private String comment;
	
   private String cStatus;
  
   private String bStatus;
	
	private String cComment;
	
	private Integer tenure;
	
	private Float wcSancAmount;
	
	private Float wcTotalInterest;
	
	private String masterKey;

	private String transactionId;
	
	private Float rateOfInt1;
	
	private Float funalAmt;
	
	private String payOption;
	
	private String amtType;
	
	private String moneyStatus;

	private Float totalAmt;

	private Float otherCharges;

	private Integer WcTenure;

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

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Float getBuyingCostSanc() {
		return buyingCostSanc;
	}

	public void setBuyingCostSanc(Float buyingCostSanc) {
		this.buyingCostSanc = buyingCostSanc;
	}

	public Date getBuyingCostDate() {
		return buyingCostDate;
	}

	public void setBuyingCostDate(Date buyingCostDate) {
		this.buyingCostDate = buyingCostDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public Float getWcSancAmount() {
		return wcSancAmount;
	}

	public String getbStatus() {
		return bStatus;
	}

	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
	}

	public void setWcSancAmount(Float wcSancAmount) {
		this.wcSancAmount = wcSancAmount;
	}

	public Float getWcTotalInterest() {
		return wcTotalInterest;
	}

	public void setWcTotalInterest(Float wcTotalInterest) {
		this.wcTotalInterest = wcTotalInterest;
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

	public Float getRateOfInt1() {
		return rateOfInt1;
	}

	public void setRateOfInt1(Float rateOfInt1) {
		this.rateOfInt1 = rateOfInt1;
	}

	public Float getFunalAmt() {
		return funalAmt;
	}

	public void setFunalAmt(Float funalAmt) {
		this.funalAmt = funalAmt;
	}

	public String getPayOption() {
		return payOption;
	}

	public void setPayOption(String payOption) {
		this.payOption = payOption;
	}

	public String getAmtType() {
		return amtType;
	}

	public void setAmtType(String amtType) {
		this.amtType = amtType;
	}

	public String getMoneyStatus() {
		return moneyStatus;
	}

	public void setMoneyStatus(String moneyStatus) {
		this.moneyStatus = moneyStatus;
	}

	public String getcStatus() {
		return cStatus;
	}

	public void setcStatus(String cStatus) {
		this.cStatus = cStatus;
	}

	public String getcComment() {
		return cComment;
	}

	public void setcComment(String cComment) {
		this.cComment = cComment;
	}

	public Float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Float amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getAccept() {
		return accept;
	}

	public String getPayConfirm() {
		return payConfirm;
	}

	public void setPayConfirm(String payConfirm) {
		this.payConfirm = payConfirm;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public Float getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Float totalAmt) {
		this.totalAmt = totalAmt;
	}

	public Float getOtherCharges() {
		return otherCharges;
	}

	public void setOtherCharges(Float otherCharges) {
		this.otherCharges = otherCharges;
	}

	public Integer getWcTenure() {
		return WcTenure;
	}

	public void setWcTenure(Integer wcTenure) {
		WcTenure = wcTenure;
	}
}
