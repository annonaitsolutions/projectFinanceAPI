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
public class TQuarterly {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	   private String customer;
		
		private String customerEmail;
		
		private String status;
		
		private String comment;
		
		private Integer tenure;
		
		private String masterKey;

		private String transactionId;
		
		private String payOption;
		
		private String amtType;
		
		private Float amount;
		
		private Float totalAmount;
		
		private Float intRate;
		
		private Integer payDate;
		
		private Date loanDate;
		
        private Float amount1;
		
		private Float totalAmount1;
		
		private Float intRate1;
		
		private Integer payDate1;
		
		private Date loanDate1;
		
        private Float amount2;
		
		private Float totalAmount2;
		
		private Float intRate2;
		
		private Integer payDat2;
		
		private Date loanDate2;
		
        private Float amount3;
		
		private Float totalAmount3;
		
		private Float intRate3;
		
		private Integer payDate3;
		
		private Date loanDate3;

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

		public Float getAmount() {
			return amount;
		}

		public void setAmount(Float amount) {
			this.amount = amount;
		}

		public Float getIntRate() {
			return intRate;
		}

		public void setIntRate(Float intRate) {
			this.intRate = intRate;
		}

		public Integer getPayDate() {
			return payDate;
		}

		public void setPayDate(Integer payDate) {
			this.payDate = payDate;
		}

		public Float getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(Float totalAmount) {
			this.totalAmount = totalAmount;
		}

		public Date getLoanDate() {
			return loanDate;
		}

		public void setLoanDate(Date loanDate) {
			this.loanDate = loanDate;
		}

		public Float getAmount1() {
			return amount1;
		}

		public void setAmount1(Float amount1) {
			this.amount1 = amount1;
		}

		public Float getTotalAmount1() {
			return totalAmount1;
		}

		public void setTotalAmount1(Float totalAmount1) {
			this.totalAmount1 = totalAmount1;
		}

		public Float getIntRate1() {
			return intRate1;
		}

		public void setIntRate1(Float intRate1) {
			this.intRate1 = intRate1;
		}

		public Integer getPayDate1() {
			return payDate1;
		}

		public void setPayDate1(Integer payDate1) {
			this.payDate1 = payDate1;
		}

		public Date getLoanDate1() {
			return loanDate1;
		}

		public void setLoanDate1(Date loanDate1) {
			this.loanDate1 = loanDate1;
		}

		public Float getAmount2() {
			return amount2;
		}

		public void setAmount2(Float amount2) {
			this.amount2 = amount2;
		}

		public Float getTotalAmount2() {
			return totalAmount2;
		}

		public void setTotalAmount2(Float totalAmount2) {
			this.totalAmount2 = totalAmount2;
		}

		public Float getIntRate2() {
			return intRate2;
		}

		public void setIntRate2(Float intRate2) {
			this.intRate2 = intRate2;
		}

		public Integer getPayDat2() {
			return payDat2;
		}

		public void setPayDat2(Integer payDat2) {
			this.payDat2 = payDat2;
		}

		public Date getLoanDate2() {
			return loanDate2;
		}

		public void setLoanDate2(Date loanDate2) {
			this.loanDate2 = loanDate2;
		}

		public Float getAmount3() {
			return amount3;
		}

		public void setAmount3(Float amount3) {
			this.amount3 = amount3;
		}

		public Float getTotalAmount3() {
			return totalAmount3;
		}

		public void setTotalAmount3(Float totalAmount3) {
			this.totalAmount3 = totalAmount3;
		}

		public Float getIntRate3() {
			return intRate3;
		}

		public void setIntRate3(Float intRate3) {
			this.intRate3 = intRate3;
		}

		public Integer getPayDate3() {
			return payDate3;
		}

		public void setPayDate3(Integer payDate3) {
			this.payDate3 = payDate3;
		}

		public Date getLoanDate3() {
			return loanDate3;
		}

		public void setLoanDate3(Date loanDate3) {
			this.loanDate3 = loanDate3;
		}
		
		

}
