package annona.form;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class FullAmountForm {
	
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
		
		private Float totalAmount;
		
		private Float amount;
		
		private Float intRate;
		
		private Date loanDate;
		
		private Integer payDate;
		
		

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

		public Float getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(Float totalAmount) {
			this.totalAmount = totalAmount;
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

		public Date getLoanDate() {
			return loanDate;
		}

		public void setLoanDate(Date loanDate) {
			this.loanDate = loanDate;
		}

		public Integer getPayDate() {
			return payDate;
		}

		public void setPayDate(Integer payDate) {
			this.payDate = payDate;
		}

}
