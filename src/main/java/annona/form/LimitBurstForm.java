package annona.form;

import org.springframework.stereotype.Component;

@Component
public class LimitBurstForm {
	
	private Long id;
	
	private String customerHeadName;
	
	private String supplierName;
	
	private String customerEmail;
	
	private String masterKey;
	
	private String poKey;
	
	private Float reqAmt;
	
	private Integer tenure;
	
	private Float intRate;
	
	private Float totalRate;
	
	private Float finalAmt;
	
	private String bStatus;
	
	private String aStatus;
	
	private String transactionId;
	
	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerHeadName() {
		return customerHeadName;
	}

	public void setCustomerHeadName(String customerHeadName) {
		this.customerHeadName = customerHeadName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public String getPoKey() {
		return poKey;
	}

	public void setPoKey(String poKey) {
		this.poKey = poKey;
	}

	public Float getReqAmt() {
		return reqAmt;
	}

	public void setReqAmt(Float reqAmt) {
		this.reqAmt = reqAmt;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public Float getIntRate() {
		return intRate;
	}

	public void setIntRate(Float intRate) {
		this.intRate = intRate;
	}

	public Float getFinalAmt() {
		return finalAmt;
	}

	public void setFinalAmt(Float finalAmt) {
		this.finalAmt = finalAmt;
	}

	public String getbStatus() {
		return bStatus;
	}

	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
	}

	public String getaStatus() {
		return aStatus;
	}

	public void setaStatus(String aStatus) {
		this.aStatus = aStatus;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Float getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(Float totalRate) {
		this.totalRate = totalRate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
