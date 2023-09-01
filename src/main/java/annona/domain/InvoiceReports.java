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
public class InvoiceReports {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
		
	private String customerName;

	private String invoicekey;
	
	private String buyerName;
	
	private String status;
	
	private String comment;
	
	private String pokey;
	
    private String goodsDetails;
    
    private String insDetails;
	
	private String goods;
	
	private Date startDate;
	
	private Date endDate;
	
	private Float cost;
	
	private String shipper;
	
	private String transportType;
	
	private String modeOfTransport;
	
	private String termsCond;
	
	private String inclusion;
	
	private String exclusion;
	
	private String surveyorName;
	
	private String surveyorCom;
	
	private String surveyorAdd;
	
	private String surveyorEmail;
	
	private String surveyorPhone;
	
	private String defectType;
	
	private Float defectQty;
	
	private String defectCostForGoods;
	
	private String damageStatus;
	
	private String replacement;
	
	private Float repairCost;
	
	private String accept;
	
      private String suppSurveyorName;
	
	private String suppSurveyorCom;
	
	private String suppSurveyorAdd;
	
	private String suppSurveyorEmail;
	
	private String suppSurveyorPhone;
	
	private String suppDefectType;
	
	private Float suppDefectQty;
	
	private String suppDefectCostForGoods;
	
	private String suppDamageStatus;
	
	private String suppReplacement;
	
	private Float suppRepairCost;
	
	private String TransactionId;
	
	private String arbNames;
	
	private String custAccept;
	
	private String loc;
	
	private String judgement;
	
	private Date judgDate;
	
	private Date compliedDate;
	
	private Float arbCost;
	
	private Float arbQty;
	
	private Date arbStartDate;
	
	private Date arbEndDate;
	
	private String docSub;
	
	private Float moneyPaid;
	
	private Date payDate;
	
	private Float GoodsReplaced;
	
	private Date repacedDate;

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

	public String getInvoicekey() {
		return invoicekey;
	}

	public void setInvoicekey(String invoicekey) {
		this.invoicekey = invoicekey;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
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

	public String getPokey() {
		return pokey;
	}

	public void setPokey(String pokey) {
		this.pokey = pokey;
	}

	public String getGoodsDetails() {
		return goodsDetails;
	}

	public void setGoodsDetails(String goodsDetails) {
		this.goodsDetails = goodsDetails;
	}

	public String getInsDetails() {
		return insDetails;
	}

	public void setInsDetails(String insDetails) {
		this.insDetails = insDetails;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public String getTermsCond() {
		return termsCond;
	}

	public void setTermsCond(String termsCond) {
		this.termsCond = termsCond;
	}

	public String getInclusion() {
		return inclusion;
	}

	public void setInclusion(String inclusion) {
		this.inclusion = inclusion;
	}

	public String getExclusion() {
		return exclusion;
	}

	public void setExclusion(String exclusion) {
		this.exclusion = exclusion;
	}

	public String getSurveyorName() {
		return surveyorName;
	}

	public void setSurveyorName(String surveyorName) {
		this.surveyorName = surveyorName;
	}

	public String getSurveyorCom() {
		return surveyorCom;
	}

	public void setSurveyorCom(String surveyorCom) {
		this.surveyorCom = surveyorCom;
	}

	public String getSurveyorAdd() {
		return surveyorAdd;
	}

	public void setSurveyorAdd(String surveyorAdd) {
		this.surveyorAdd = surveyorAdd;
	}

	public String getSurveyorEmail() {
		return surveyorEmail;
	}

	public void setSurveyorEmail(String surveyorEmail) {
		this.surveyorEmail = surveyorEmail;
	}

	public String getSurveyorPhone() {
		return surveyorPhone;
	}

	public void setSurveyorPhone(String surveyorPhone) {
		this.surveyorPhone = surveyorPhone;
	}

	public String getDefectType() {
		return defectType;
	}

	public void setDefectType(String defectType) {
		this.defectType = defectType;
	}

	public Float getDefectQty() {
		return defectQty;
	}

	public void setDefectQty(Float defectQty) {
		this.defectQty = defectQty;
	}

	public String getDefectCostForGoods() {
		return defectCostForGoods;
	}

	public void setDefectCostForGoods(String defectCostForGoods) {
		this.defectCostForGoods = defectCostForGoods;
	}

	public String getDamageStatus() {
		return damageStatus;
	}

	public void setDamageStatus(String damageStatus) {
		this.damageStatus = damageStatus;
	}

	public String getReplacement() {
		return replacement;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}

	public Float getRepairCost() {
		return repairCost;
	}

	public void setRepairCost(Float repairCost) {
		this.repairCost = repairCost;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getSuppSurveyorName() {
		return suppSurveyorName;
	}

	public void setSuppSurveyorName(String suppSurveyorName) {
		this.suppSurveyorName = suppSurveyorName;
	}

	public String getSuppSurveyorCom() {
		return suppSurveyorCom;
	}

	public void setSuppSurveyorCom(String suppSurveyorCom) {
		this.suppSurveyorCom = suppSurveyorCom;
	}

	public String getSuppSurveyorAdd() {
		return suppSurveyorAdd;
	}

	public void setSuppSurveyorAdd(String suppSurveyorAdd) {
		this.suppSurveyorAdd = suppSurveyorAdd;
	}

	public String getSuppSurveyorEmail() {
		return suppSurveyorEmail;
	}

	public void setSuppSurveyorEmail(String suppSurveyorEmail) {
		this.suppSurveyorEmail = suppSurveyorEmail;
	}

	public String getSuppSurveyorPhone() {
		return suppSurveyorPhone;
	}

	public void setSuppSurveyorPhone(String suppSurveyorPhone) {
		this.suppSurveyorPhone = suppSurveyorPhone;
	}

	public String getSuppDefectType() {
		return suppDefectType;
	}

	public void setSuppDefectType(String suppDefectType) {
		this.suppDefectType = suppDefectType;
	}

	public Float getSuppDefectQty() {
		return suppDefectQty;
	}

	public void setSuppDefectQty(Float suppDefectQty) {
		this.suppDefectQty = suppDefectQty;
	}

	public String getSuppDefectCostForGoods() {
		return suppDefectCostForGoods;
	}

	public void setSuppDefectCostForGoods(String suppDefectCostForGoods) {
		this.suppDefectCostForGoods = suppDefectCostForGoods;
	}

	public String getSuppDamageStatus() {
		return suppDamageStatus;
	}

	public void setSuppDamageStatus(String suppDamageStatus) {
		this.suppDamageStatus = suppDamageStatus;
	}

	public String getSuppReplacement() {
		return suppReplacement;
	}

	public void setSuppReplacement(String suppReplacement) {
		this.suppReplacement = suppReplacement;
	}

	public Float getSuppRepairCost() {
		return suppRepairCost;
	}

	public void setSuppRepairCost(Float suppRepairCost) {
		this.suppRepairCost = suppRepairCost;
	}

	public String getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}

	public String getArbNames() {
		return arbNames;
	}

	public void setArbNames(String arbNames) {
		this.arbNames = arbNames;
	}

	public String getCustAccept() {
		return custAccept;
	}

	public void setCustAccept(String custAccept) {
		this.custAccept = custAccept;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getJudgement() {
		return judgement;
	}

	public void setJudgement(String judgement) {
		this.judgement = judgement;
	}

	public Date getJudgDate() {
		return judgDate;
	}

	public void setJudgDate(Date judgDate) {
		this.judgDate = judgDate;
	}

	public Date getCompliedDate() {
		return compliedDate;
	}

	public void setCompliedDate(Date compliedDate) {
		this.compliedDate = compliedDate;
	}

	public Float getArbCost() {
		return arbCost;
	}

	public void setArbCost(Float arbCost) {
		this.arbCost = arbCost;
	}

	public Float getArbQty() {
		return arbQty;
	}

	public void setArbQty(Float arbQty) {
		this.arbQty = arbQty;
	}

	public Date getArbStartDate() {
		return arbStartDate;
	}

	public void setArbStartDate(Date arbStartDate) {
		this.arbStartDate = arbStartDate;
	}

	public Date getArbEndDate() {
		return arbEndDate;
	}

	public void setArbEndDate(Date arbEndDate) {
		this.arbEndDate = arbEndDate;
	}

	public String getDocSub() {
		return docSub;
	}

	public void setDocSub(String docSub) {
		this.docSub = docSub;
	}

	public Float getMoneyPaid() {
		return moneyPaid;
	}

	public void setMoneyPaid(Float moneyPaid) {
		this.moneyPaid = moneyPaid;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Float getGoodsReplaced() {
		return GoodsReplaced;
	}

	public void setGoodsReplaced(Float goodsReplaced) {
		GoodsReplaced = goodsReplaced;
	}

	public Date getRepacedDate() {
		return repacedDate;
	}

	public void setRepacedDate(Date repacedDate) {
		this.repacedDate = repacedDate;
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

}
