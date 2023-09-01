package annona.form;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import annona.domain.CustomerDetails;

@Component
public class DisputeForm {
	
	private Long id;
	
	private String customerName;
	
	private String customerPrefix;
	
	private String wareHousrMng;
	
	private Date date;
	
	private Float weight;
	
	private String masterKey;
	
	private String goodsSummarry;
	
	private String pokey;
	
   private String country;
   
   private String transactionId;
   
   
	private String missingGoods;
	
	private Float missingGoodsCost;
	
	private Float missingGoodsQty;
	
	private String partiallyDamagedGoods;
	
	private Float partiallyDamagedCost;
	
	private Float partiallyDamagedQty;
	
	private String fullyDamagedGoods;
	
	private Float fullyDamagedQty;
	
	private Float fullyDamagedCost;
	
    private String fir;
	
	private Date firDate;
	
	private String location;
	
	private String state;
	
	private String supplierName;
	
	private Float supplierPaidAmt;
	
	private Float supplierAmt;
	
	private String supplierResuppliedGoods;
	
	private String quantityResupplied;
	
	private String insuranceCoverage;
	
	private String insuranceMoney;
	
	private String reason;
	
	private String applyNewDispute;
	
	private String arbitration;
	
	private String setelled;
	
	private String revoked;
	
	private String pending;
	
	private String supplierEmail;
	
	private String goods;
	
	private String goodsInfo;
	
	private Float totalCost;
	
	private Float quantity;
	
	private Float answer;
	
	private String goodsDefect;
	
	private Float noOfDefect;
	
	private Float answer1;
	
	private Float answer2;
	
	private String status;
	
	private String comment;
	
	private String disputeStatus;
	
	private Date approveDate;
	
	private String customerEmail;
	
	private String disputeKey;
	
	private String d;
	
	private String businessKey;
	
	private List<MultipartFile> files;
	
	private CustomerDetails customerDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPrefix() {
		return customerPrefix;
	}

	public void setCustomerPrefix(String customerPrefix) {
		this.customerPrefix = customerPrefix;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public String getPokey() {
		return pokey;
	}

	public void setPokey(String pokey) {
		this.pokey = pokey;
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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Float getSupplierPaidAmt() {
		return supplierPaidAmt;
	}

	public void setSupplierPaidAmt(Float supplierPaidAmt) {
		this.supplierPaidAmt = supplierPaidAmt;
	}

	public Float getSupplierAmt() {
		return supplierAmt;
	}

	public void setSupplierAmt(Float supplierAmt) {
		this.supplierAmt = supplierAmt;
	}

	public String getSupplierResuppliedGoods() {
		return supplierResuppliedGoods;
	}

	public void setSupplierResuppliedGoods(String supplierResuppliedGoods) {
		this.supplierResuppliedGoods = supplierResuppliedGoods;
	}

	public String getQuantityResupplied() {
		return quantityResupplied;
	}

	public void setQuantityResupplied(String quantityResupplied) {
		this.quantityResupplied = quantityResupplied;
	}

	public String getInsuranceCoverage() {
		return insuranceCoverage;
	}

	public void setInsuranceCoverage(String insuranceCoverage) {
		this.insuranceCoverage = insuranceCoverage;
	}

	public String getInsuranceMoney() {
		return insuranceMoney;
	}

	public void setInsuranceMoney(String insuranceMoney) {
		this.insuranceMoney = insuranceMoney;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApplyNewDispute() {
		return applyNewDispute;
	}

	public void setApplyNewDispute(String applyNewDispute) {
		this.applyNewDispute = applyNewDispute;
	}

	public String getArbitration() {
		return arbitration;
	}

	public void setArbitration(String arbitration) {
		this.arbitration = arbitration;
	}

	public String getSetelled() {
		return setelled;
	}

	public void setSetelled(String setelled) {
		this.setelled = setelled;
	}

	public String getRevoked() {
		return revoked;
	}

	public void setRevoked(String revoked) {
		this.revoked = revoked;
	}

	public String getPending() {
		return pending;
	}

	public void setPending(String pending) {
		this.pending = pending;
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

	public String getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public Float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Float totalCost) {
		this.totalCost = totalCost;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Float getAnswer() {
		return answer;
	}

	public void setAnswer(Float answer) {
		this.answer = answer;
	}

	public String getGoodsDefect() {
		return goodsDefect;
	}

	public void setGoodsDefect(String goodsDefect) {
		this.goodsDefect = goodsDefect;
	}

	public Float getNoOfDefect() {
		return noOfDefect;
	}

	public void setNoOfDefect(Float noOfDefect) {
		this.noOfDefect = noOfDefect;
	}

	public Float getAnswer1() {
		return answer1;
	}

	public void setAnswer1(Float answer1) {
		this.answer1 = answer1;
	}

	public Float getAnswer2() {
		return answer2;
	}

	public void setAnswer2(Float answer2) {
		this.answer2 = answer2;
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

	public String getDisputeStatus() {
		return disputeStatus;
	}

	public void setDisputeStatus(String disputeStatus) {
		this.disputeStatus = disputeStatus;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getDisputeKey() {
		return disputeKey;
	}

	public void setDisputeKey(String disputeKey) {
		this.disputeKey = disputeKey;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getMissingGoods() {
		return missingGoods;
	}

	public void setMissingGoods(String missingGoods) {
		this.missingGoods = missingGoods;
	}

	public Float getMissingGoodsCost() {
		return missingGoodsCost;
	}

	public void setMissingGoodsCost(Float missingGoodsCost) {
		this.missingGoodsCost = missingGoodsCost;
	}

	public Float getMissingGoodsQty() {
		return missingGoodsQty;
	}

	public void setMissingGoodsQty(Float missingGoodsQty) {
		this.missingGoodsQty = missingGoodsQty;
	}

	public String getPartiallyDamagedGoods() {
		return partiallyDamagedGoods;
	}

	public void setPartiallyDamagedGoods(String partiallyDamagedGoods) {
		this.partiallyDamagedGoods = partiallyDamagedGoods;
	}

	public Float getPartiallyDamagedCost() {
		return partiallyDamagedCost;
	}

	public void setPartiallyDamagedCost(Float partiallyDamagedCost) {
		this.partiallyDamagedCost = partiallyDamagedCost;
	}

	public Float getPartiallyDamagedQty() {
		return partiallyDamagedQty;
	}

	public void setPartiallyDamagedQty(Float partiallyDamagedQty) {
		this.partiallyDamagedQty = partiallyDamagedQty;
	}

	public String getFullyDamagedGoods() {
		return fullyDamagedGoods;
	}

	public void setFullyDamagedGoods(String fullyDamagedGoods) {
		this.fullyDamagedGoods = fullyDamagedGoods;
	}

	public Float getFullyDamagedQty() {
		return fullyDamagedQty;
	}

	public void setFullyDamagedQty(Float fullyDamagedQty) {
		this.fullyDamagedQty = fullyDamagedQty;
	}

	public Float getFullyDamagedCost() {
		return fullyDamagedCost;
	}

	public void setFullyDamagedCost(Float fullyDamagedCost) {
		this.fullyDamagedCost = fullyDamagedCost;
	}

	public String getFir() {
		return fir;
	}

	public void setFir(String fir) {
		this.fir = fir;
	}

	public Date getFirDate() {
		return firDate;
	}

	public void setFirDate(Date firDate) {
		this.firDate = firDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getWareHousrMng() {
		return wareHousrMng;
	}

	public void setWareHousrMng(String wareHousrMng) {
		this.wareHousrMng = wareHousrMng;
	}

	public String getGoodsSummarry() {
		return goodsSummarry;
	}

	public void setGoodsSummarry(String goodsSummarry) {
		this.goodsSummarry = goodsSummarry;
	} 
	
	
}