package annona.trade.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class TRepayment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    private String customer;
	
	private String customerEmail;
	
    private Float buyingCostSanc;
	
	private Date buyingCostDate;
	
	private Float amountPaid;
	
	private String payConfirm;
	
	private String status;
	
	private String accept;
	
	private String cStatus;
	
	private String bStatus;
	
	private String cComment;
	
	private String comment;
	
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

	public String getbStatus() {
		return bStatus;
	}

	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
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

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getPayConfirm() {
		return payConfirm;
	}

	public void setPayConfirm(String payConfirm) {
		this.payConfirm = payConfirm;
	}
}
