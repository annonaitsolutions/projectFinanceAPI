package annona.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class PurchaseOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private Date purchaseDate;
	
	private String goodsCategory;
	
	private String masterKey;
	
	private String forWard;
	
/*	@Column(length = 1000)
	private String fileName;*/
	
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<byte[]> files;
	
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<String> fileNames;
	
	private String reason;
	
	private String wareHouseName;
	
	private String whMngName;
	
	private Date uploadDate;
	
	private Date forwardDate;
	
	private Date vendorAppDate;
	
	private Date poPayDate;
	
	private Date closePoDate;
	
	private String document;
	
	private String country;
	
	private String state;
	
	private String comment;
	
	private String vComment;
	
	private String goodsStatus;
	
	private String supplierName;
	
	private String supplierBank;
	
	private String supplierEmail;
	
	private String buyerName;
	
	private String buyerEmail;
	
	private String customerName;
	
	private String cancelComment;
	
	private String customerHeadName;
	
	private String customerHeadEmail;
	
	private String customerBranchEmail;
	
	private String vStatus;
	
	private Float distributedAmount;
	
	private String status;
	
	private String goodsDetails;
	
	private String goods;
	
	private String quantity;
	
	private String payMade;
	
	private Float weight;
	
	private String licence;
	
	private Float amount;
	
    private String transactionId;
	
	private String poKey;
	
	private Integer tenure;
	
	private Integer flag;
	
	private String interestType;
	
	private Float rateOfInt;
	
	private Integer noOfDays;
	
	private Float plrRate;
	
	private Integer basicPoints;
	
	private Date sentDate;
	
	private Date receiveDate;
	
	private Float calPlrRate;
	
	private Float basicAmt;
	
	private Float total1;
	
	private Float rateOfInt1;
	
	private String customerPrefix;
	
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
	
	private String rStatus;
	
	private String rComment;
	
	private String request;
	
	private String bankName;
	
	private String typeOfTrans;
	
	private String chequenum;
	
	private String signAuth;
	
	private Float amtLimit;

	private String transStatus;
	
	private String transResult;
	
	private String cComment;
	
   private Float payAdvance;
	
	private Float payBalance;
	
	private String insuranceType;
	
	private Date startDate;
	
	private Date endDate;
	
	private String insuranceDetails;
	
	private Float insuranceAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getChequenum() {
		return chequenum;
	}

	public void setChequenum(String chequenum) {
		this.chequenum = chequenum;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getGoodsCategory() {
		return goodsCategory;
	}

	public void setGoodsCategory(String goodsCategory) {
		this.goodsCategory = goodsCategory;
	}

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGoodsDetails() {
		return goodsDetails;
	}

	public void setGoodsDetails(String goodsDetails) {
		this.goodsDetails = goodsDetails;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public String getGoods() {
		return goods;
	}

	public String getTransactionId() {
		return transactionId;
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

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getPoKey() {
		return poKey;
	}

	public void setPoKey(String poKey) {
		this.poKey = poKey;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getCustomerHeadName() {
		return customerHeadName;
	}

	public void setCustomerHeadName(String customerHeadName) {
		this.customerHeadName = customerHeadName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Float getDistributedAmount() {
		return distributedAmount;
	}

	public void setDistributedAmount(Float distributedAmount) {
		this.distributedAmount = distributedAmount;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getCustomerHeadEmail() {
		return customerHeadEmail;
	}

	public void setCustomerHeadEmail(String customerHeadEmail) {
		this.customerHeadEmail = customerHeadEmail;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public String getCustomerBranchEmail() {
		return customerBranchEmail;
	}

	public void setCustomerBranchEmail(String customerBranchEmail) {
		this.customerBranchEmail = customerBranchEmail;
	}

	public String getvStatus() {
		return vStatus;
	}

	public void setvStatus(String vStatus) {
		this.vStatus = vStatus;
	}

	public String getvComment() {
		return vComment;
	}

	public void setvComment(String vComment) {
		this.vComment = vComment;
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

	public Float getFunalAmt() {
		return funalAmt;
	}

	public String getForWard() {
		return forWard;
	}

	public void setForWard(String forWard) {
		this.forWard = forWard;
	}

	public void setFunalAmt(Float funalAmt) {
		this.funalAmt = funalAmt;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getBankName() {
		return bankName;
	}

	public Float getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(Float taxAmt) {
		this.taxAmt = taxAmt;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Float getTaxAmt1() {
		return taxAmt1;
	}

	public void setTaxAmt1(Float taxAmt1) {
		this.taxAmt1 = taxAmt1;
	}

	public Float getTaxAmt2() {
		return taxAmt2;
	}

	public void setTaxAmt2(Float taxAmt2) {
		this.taxAmt2 = taxAmt2;
	}

	public String getrComment() {
		return rComment;
	}

	public void setrComment(String rComment) {
		this.rComment = rComment;
	}

	public String getPayMade() {
		return payMade;
	}

	public void setPayMade(String payMade) {
		this.payMade = payMade;
	}

	/*public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}*/

	public Set<byte[]> getFiles() {
		return files;
	}

	public void setFiles(Set<byte[]> files) {
		this.files = files;
	}

	public Set<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(Set<String> fileNames) {
		this.fileNames = fileNames;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}


	public String getTypeOfTrans() {
		return typeOfTrans;
	}

	public void setTypeOfTrans(String typeOfTrans) {
		this.typeOfTrans = typeOfTrans;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getTransResult() {
		return transResult;
	}

	public void setTransResult(String transResult) {
		this.transResult = transResult;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}


	public Date getVendorAppDate() {
		return vendorAppDate;
	}

	public String getSupplierBank() {
		return supplierBank;
	}



	public void setVendorAppDate(Date vendorAppDate) {
		this.vendorAppDate = vendorAppDate;
	}



	public void setSupplierBank(String supplierBank) {
		this.supplierBank = supplierBank;
	}

	public String getCancelComment() {
		return cancelComment;
	}


	public void setCancelComment(String cancelComment) {
		this.cancelComment = cancelComment;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}


	public Date getPoPayDate() {
		return poPayDate;
	}

	public void setPoPayDate(Date poPayDate) {
		this.poPayDate = poPayDate;
	}

	public Date getClosePoDate() {
		return closePoDate;
	}

	public void setClosePoDate(Date closePoDate) {
		this.closePoDate = closePoDate;
	}
	public Date getForwardDate() {
		return forwardDate;
	}

	public void setForwardDate(Date forwardDate) {
		this.forwardDate = forwardDate;

	}

	

	public String getcComment() {
		return cComment;
	}

	public void setcComment(String cComment) {
		this.cComment = cComment;
	}
	public String getCustomerPrefix() {
		return customerPrefix;
	}

	public void setCustomerPrefix(String customerPrefix) {
		this.customerPrefix = customerPrefix;

	}

	public Float getPayAdvance() {
		return payAdvance;
	}

	public void setPayAdvance(Float payAdvance) {
		this.payAdvance = payAdvance;
	}

	public Float getPayBalance() {
		return payBalance;
	}

	public void setPayBalance(Float payBalance) {
		this.payBalance = payBalance;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getInsuranceDetails() {
		return insuranceDetails;
	}

	public void setInsuranceDetails(String insuranceDetails) {
		this.insuranceDetails = insuranceDetails;
	}

	public Float getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(Float insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getSignAuth() {
		return signAuth;
	}

	public void setSignAuth(String signAuth) {
		this.signAuth = signAuth;
	}

	public Float getAmtLimit() {
		return amtLimit;
	}

	public void setAmtLimit(Float amtLimit) {
		this.amtLimit = amtLimit;
	}

	public String getWhMngName() {
		return whMngName;
	}

	public void setWhMngName(String whMngName) {
		this.whMngName = whMngName;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	

}
