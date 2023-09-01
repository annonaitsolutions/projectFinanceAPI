package annona.form;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import annona.domain.BankDetails;
import annona.domain.LimitBurst;
import annona.domain.PurchaseOrder;
import annona.domain.WareHouse;

@Component
public class PurchaseOrderForm {
	
    private Long id;
	
	private Date purchaseDate;
	
	private String goodsCategory;
	
	private String whMngName;
	
	private String masterKey;
	
	private String reason;
	
	private String contactPerson;
	
	private String ifsc;
	
	private Date uploadDate;
	
	private Date fromDate;
	
	private Date toDate;
	
    private Date sentDate;
	
	private Date receiveDate;
	
	private Date vendorAppDate;
	
	private String cancelComment;
	
	private String wareHouseName;
	
	private String goodsStatus;

	private List<MultipartFile> files;
	
	private String fileName;
	
	private String accNo;
	
	private String document;
	
	private String country;
	
	private String state;
	
	private String supplierName;
	
	private String customerName;
	
	private String customerHeadName;
	
	private String customerHeadEmail;
	
	private String customerBranchEmail;
	
	private List<PurchaseOrder> purchaseList;
	
	private Float limitAmt;
	
	private Date limitDate;
	
	private String limitStatus;

	private String typeOfLc;
	
	
	private String bankType;

	private String bankBranch;
	
	private String bankAddress;
	
	private String swiftCode;
	
	private String comment;
	
	private String vComment;
	
	private String payMade;
	
	private String vStatus;
	
	private String status;
	
	private String goodsDetails;
	
	private List<WareHouse> wareHouseList;
	
	private String goods;
	
	private String Suppliercountry;

	private String supplierState;
	
	private String supplierCity;
	
	private String supplierBank;
	
	private String supplierBankEmail;
	
	private String supplierEmail;
	
	private Float utilizedBusnsAmt;
	
	private Float distributedAmount;
	
	private String quantity;

	private String availableQuantity;
	
	private Float weight;
	
	private Float balance;
	
	private String licence;
	
	private String transactionId;
	
	private String poKey;
	
	private Float amount;
	
	private Integer tenure;

	private Integer flag;
	
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
	
	private String rComment;
	
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
	
	private String request;
	
    private String typeOfTrans;
	
	private String chequenum;
	
    private String signAuth;
	
	private Float amtLimit;

	private String transStatus;
	
	private String transResult;
	
	private String contactNum;
	
	private List<BankDetails> banklist;
	
	private String customerPrefix;
	
	private String bankName;
	
	private Float payAdvance;
	
	private Float payBalance;
	
	private Float payNow;
	
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
	
	public String getTypeOfLc() {
		return typeOfLc;
	}

	public void setTypeOfLc(String typeOfLc) {
		this.typeOfLc = typeOfLc;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
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

	public String getSuppliercountry() {
		return Suppliercountry;
	}

	public void setSuppliercountry(String suppliercountry) {
		Suppliercountry = suppliercountry;
	}

	public String getSupplierState() {
		return supplierState;
	}

	public void setSupplierState(String supplierState) {
		this.supplierState = supplierState;
	}

	public String getSupplierCity() {
		return supplierCity;
	}

	public void setSupplierCity(String supplierCity) {
		this.supplierCity = supplierCity;
	}

	public String getSupplierBank() {
		return supplierBank;
	}

	public void setSupplierBank(String supplierBank) {
		this.supplierBank = supplierBank;
	}

	public String getSupplierBankEmail() {
		return supplierBankEmail;
	}

	public void setSupplierBankEmail(String supplierBankEmail) {
		this.supplierBankEmail = supplierBankEmail;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getTransactionId() {
		return transactionId;
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

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public Float getUtilizedBusnsAmt() {
		return utilizedBusnsAmt;
	}

	public void setUtilizedBusnsAmt(Float utilizedBusnsAmt) {
		this.utilizedBusnsAmt = utilizedBusnsAmt;
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

	public void setFunalAmt(Float funalAmt) {
		this.funalAmt = funalAmt;
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

	public List<BankDetails> getBanklist() {
		return banklist;
	}

	public void setBanklist(List<BankDetails> banklist) {
		this.banklist = banklist;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Float getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(Float taxAmt) {
		this.taxAmt = taxAmt;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getChequenum() {
		return chequenum;
	}

	public void setChequenum(String chequenum) {
		this.chequenum = chequenum;
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

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public List<PurchaseOrder> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<PurchaseOrder> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public String getCancelComment() {
		return cancelComment;
	}

	public void setCancelComment(String cancelComment) {
		this.cancelComment = cancelComment;
	}

	public Date getVendorAppDate() {
		return vendorAppDate;
	}

	public void setVendorAppDate(Date vendorAppDate) {
		this.vendorAppDate = vendorAppDate;
	}
	
	public List<WareHouse> getWareHouseList() {
		return wareHouseList;
	}

	public void setWareHouseList(List<WareHouse> wareHouseList) {
		this.wareHouseList = wareHouseList;
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

	public String getCustomerPrefix() {
		return customerPrefix;
	}

	public void setCustomerPrefix(String customerPrefix) {
		this.customerPrefix = customerPrefix;
	}


	public Float getLimitAmt() {
		return limitAmt;
	}

	public void setLimitAmt(Float limitAmt) {
		this.limitAmt = limitAmt;
	}

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	public String getLimitStatus() {
		return limitStatus;
	}

	public void setLimitStatus(String limitStatus) {
		this.limitStatus = limitStatus;
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

	public Float getPayNow() {
		return payNow;
	}

	public void setPayNow(Float payNow) {
		this.payNow = payNow;
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

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
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

	public String getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(String availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
}
