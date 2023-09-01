package annona.trade.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
public class THalfYearlyForm {
	
	
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

		public Float getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(Float totalAmount) {
			this.totalAmount = totalAmount;
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

}
