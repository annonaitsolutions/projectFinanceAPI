package annona.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class Dispute {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
		
	private String customerName;
	
	private String wareHousrMng;
	
	private Date date;
	
	private Float weight;
	
	private Date statusDate;
	
	private String poKey;
	
	private String country;
	
	private String state;
	
	private String supplierName;
	
	private String supplierEmail;
	
	private String goods;
	
	private String goodsSummarry;
	
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
	
	private String masterKey;
	
	private String transactionId;
	
	private String disputeKey;
	
	private String d;
	
	private String businessKey;
	
	private String customerPrefix;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPoKey() {
		return poKey;
	}

	public void setPoKey(String poKey) {
		this.poKey = poKey;
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

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
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

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCustomerPrefix() {
		return customerPrefix;
	}

	public void setCustomerPrefix(String customerPrefix) {
		this.customerPrefix = customerPrefix;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
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
