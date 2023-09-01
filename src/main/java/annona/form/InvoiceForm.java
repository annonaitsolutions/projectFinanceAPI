package annona.form;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import annona.domain.BankDetails;
import annona.domain.Invoice;
import annona.domain.WareHouse;

@Component
public class InvoiceForm {

   private Long id;
    
	private String poKey;
	
	private Date poDate;
	
	private String poInfo;
	
    private String customerName;
    
    private String whMngName;
	
	private String customerHeadName;
	
	private String customerHeadEmail;
	
	private String customerBranchEmail;
	
	private String transComment;
	
    private Float serviceTax;
	
	private Float dutytax;
	
	private Float vattax;
	
	private Date sentDate;
		
	private Date receiveDate;
	
	private String wareHouseName;
	
	private String goodsStatus;
	
	private Float amountRec;
	
	private String chequeNum;
	
	private String buyerName;

	private List<Invoice> invoiceList;
	
	private String masterKey;
	
	private String comment;
	
	private String status;
	
    private String goodsDetails;
    
    private String goodsCategory;
	
	private String goods;
	
	private String quantity;

	private String invoiceRemaining;

	private String quantityForInvoice;
	
	private List<WareHouse> wareHouseList;
	
	private Float weight;
	
	private Float masterBuyingCost;
	
	private Float masterQty;
	
	private String licence;
	
	private Date fromDate;
	
	private Date toDate;
	
	private Float amount;
	
    private String transactionId;
    
    private String buyerEmail;
    
    private String buyerBank;
    
    private String buyerBankEmail;
    
    private String buyerCountry;
    
    private String buyerState;
    
    private String buyerCity;
    
   private String rStatus;
    
    private String rComment;
    
    private String request;
    
   private List<BankDetails> banklist;
	
	private String bankName;
	
    private String interestType;
	
	private Float rateOfInt;
	
	private Integer noOfDays;
	
	private Float plrRate;
	
	private Integer basicPoints;
	
	private String transResult;

	private String requestMoney;
	
	private String typeOfTransaction;
	
	private String transStatus;	
	
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
     
     private Integer tenure;
	
	private Float taxPercentage1;
	
	private Float taxAmt1;
	
    private String taxName2;
	
	private Float taxPercentage2;
	
	private Float taxAmt2;
	
	private Float funalAmt;
	
	private Float answer;
	
    private String insuranceType;
	
	private String insuranceDetails;
	
	private Float insuranceAmount;
	
	private Date startDate;
	
	private Date endDate;

	private Long buyerPoId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPoKey() {
		return poKey;
	}

	public void setPoKey(String poKey) {
		this.poKey = poKey;
	}

	public Date getPoDate() {
		return poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	public String getPoInfo() {
		return poInfo;
	}

	public void setPoInfo(String poInfo) {
		this.poInfo = poInfo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerHeadName() {
		return customerHeadName;
	}

	public void setCustomerHeadName(String customerHeadName) {
		this.customerHeadName = customerHeadName;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
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

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
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

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getBuyerBank() {
		return buyerBank;
	}

	public void setBuyerBank(String buyerBank) {
		this.buyerBank = buyerBank;
	}

	public String getBuyerBankEmail() {
		return buyerBankEmail;
	}

	public void setBuyerBankEmail(String buyerBankEmail) {
		this.buyerBankEmail = buyerBankEmail;
	}

	public String getBuyerCountry() {
		return buyerCountry;
	}

	public void setBuyerCountry(String buyerCountry) {
		this.buyerCountry = buyerCountry;
	}

	public String getBuyerState() {
		return buyerState;
	}

	public void setBuyerState(String buyerState) {
		this.buyerState = buyerState;
	}

	public String getBuyerCity() {
		return buyerCity;
	}

	public void setBuyerCity(String buyerCity) {
		this.buyerCity = buyerCity;
	}

	public String getGoodsCategory() {
		return goodsCategory;
	}

	public void setGoodsCategory(String goodsCategory) {
		this.goodsCategory = goodsCategory;
	}

	public String getComment() {
		return comment;
	}

	public String getCustomerHeadEmail() {
		return customerHeadEmail;
	}

	public void setCustomerHeadEmail(String customerHeadEmail) {
		this.customerHeadEmail = customerHeadEmail;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCustomerBranchEmail() {
		return customerBranchEmail;
	}

	public void setCustomerBranchEmail(String customerBranchEmail) {
		this.customerBranchEmail = customerBranchEmail;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	public String getrComment() {
		return rComment;
	}

	public void setrComment(String rComment) {
		this.rComment = rComment;
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

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public String getTransResult() {
		return transResult;
	}

	public void setTransResult(String transResult) {
		this.transResult = transResult;
	}

	public String getRequestMoney() {
		return requestMoney;
	}

	public void setRequestMoney(String requestMoney) {
		this.requestMoney = requestMoney;
	}

	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}

	public void setTypeOfTransaction(String typeOfTransaction) {
		this.typeOfTransaction = typeOfTransaction;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public String getTransComment() {
		return transComment;
	}

	public void setTransComment(String transComment) {
		this.transComment = transComment;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public Float getAmountRec() {
		return amountRec;
	}

	public void setAmountRec(Float amountRec) {
		this.amountRec = amountRec;
	}

	public String getChequeNum() {
		return chequeNum;
	}

	public void setChequeNum(String chequeNum) {
		this.chequeNum = chequeNum;
	}

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
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

	public List<WareHouse> getWareHouseList() {
		return wareHouseList;
	}

	public void setWareHouseList(List<WareHouse> wareHouseList) {
		this.wareHouseList = wareHouseList;
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

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public Float getMasterBuyingCost() {
		return masterBuyingCost;
	}

	public void setMasterBuyingCost(Float masterBuyingCost) {
		this.masterBuyingCost = masterBuyingCost;
	}

	public Float getMasterQty() {
		return masterQty;
	}

	public void setMasterQty(Float masterQty) {
		this.masterQty = masterQty;
	}

	public Float getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(Float serviceTax) {
		this.serviceTax = serviceTax;
	}

	public Float getDutytax() {
		return dutytax;
	}

	public void setDutytax(Float dutytax) {
		this.dutytax = dutytax;
	}

	public Float getVattax() {
		return vattax;
	}

	public void setVattax(Float vattax) {
		this.vattax = vattax;
	}

	public Float getAnswer() {
		return answer;
	}

	public void setAnswer(Float answer) {
		this.answer = answer;
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

	public String getWhMngName() {
		return whMngName;
	}

	public void setWhMngName(String whMngName) {
		this.whMngName = whMngName;
	}

	public String getInvoiceRemaining() {
		return invoiceRemaining;
	}

	public void setInvoiceRemaining(String invoiceRemaining) {
		this.invoiceRemaining = invoiceRemaining;
	}

	public String getQuantityForInvoice() {
		return quantityForInvoice;
	}

	public void setQuantityForInvoice(String quantityForInvoice) {
		this.quantityForInvoice = quantityForInvoice;
	}

	public Long getBuyerPoId() {
		return buyerPoId;
	}

	public void setBuyerPoId(Long buyerPoId) {
		this.buyerPoId = buyerPoId;
	}
}
