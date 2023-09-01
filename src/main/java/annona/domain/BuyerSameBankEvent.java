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
public class BuyerSameBankEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	private String customerName;

	private String first;

	private Date date1;

	private String event1;

	private String second;

	private Date date2;

	private String event2;

	private String third;

	private Date date3;

	private String event3;

	private String fourth;

	private Date date4;

	private String event4;

	private String fifth;

	private Date date5;

	private String event5;

	private String six;

	private Date date6;

	private String event6;

	private String seven;

	private Date date7;

	private String event7;

	private String eight;

	private Date date8;

	private String event8;

	private String nine;

	private Date date9;

	private String event9;

	private String ten;

	private Date date10;

	private String event10;

	private String elven;

	private Date date11;

	private String event11;

	private String status;

	private String comment;

	private Date approveDate;

	private String supplier;

	private String supplierBank;

	private String goods;

	private String masterKey;
	
	private String poKey;

	private float sanctionedAmount;
	
	private float utilizedAmount;
	
	private float availableCost;
	
	private String uniqueId;
	
	private String transactionId;
	
	private Date datei;
			
	public Date getDatei() {
		return datei;
	}

	public void setDatei(Date datei) {
		this.datei = datei;
	}

	public String getPoKey() {
		return poKey;
	}

	public void setPoKey(String poKey) {
		this.poKey = poKey;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

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

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public String getEvent1() {
		return event1;
	}

	public void setEvent1(String event1) {
		this.event1 = event1;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public String getEvent2() {
		return event2;
	}

	public void setEvent2(String event2) {
		this.event2 = event2;
	}

	public String getThird() {
		return third;
	}

	public void setThird(String third) {
		this.third = third;
	}

	public Date getDate3() {
		return date3;
	}

	public void setDate3(Date date3) {
		this.date3 = date3;
	}

	public String getEvent3() {
		return event3;
	}

	public void setEvent3(String event3) {
		this.event3 = event3;
	}

	public String getFourth() {
		return fourth;
	}

	public void setFourth(String fourth) {
		this.fourth = fourth;
	}

	public Date getDate4() {
		return date4;
	}

	public void setDate4(Date date4) {
		this.date4 = date4;
	}

	public String getEvent4() {
		return event4;
	}

	public void setEvent4(String event4) {
		this.event4 = event4;
	}

	public String getFifth() {
		return fifth;
	}

	public void setFifth(String fifth) {
		this.fifth = fifth;
	}

	public Date getDate5() {
		return date5;
	}

	public void setDate5(Date date5) {
		this.date5 = date5;
	}

	public String getEvent5() {
		return event5;
	}

	public void setEvent5(String event5) {
		this.event5 = event5;
	}

	public String getSix() {
		return six;
	}

	public void setSix(String six) {
		this.six = six;
	}

	public Date getDate6() {
		return date6;
	}

	public void setDate6(Date date6) {
		this.date6 = date6;
	}

	public String getEvent6() {
		return event6;
	}

	public void setEvent6(String event6) {
		this.event6 = event6;
	}

	public String getSeven() {
		return seven;
	}

	public void setSeven(String seven) {
		this.seven = seven;
	}

	public Date getDate7() {
		return date7;
	}

	public void setDate7(Date date7) {
		this.date7 = date7;
	}

	public String getEvent7() {
		return event7;
	}

	public void setEvent7(String event7) {
		this.event7 = event7;
	}

	public String getEight() {
		return eight;
	}

	public void setEight(String eight) {
		this.eight = eight;
	}

	public Date getDate8() {
		return date8;
	}

	public void setDate8(Date date8) {
		this.date8 = date8;
	}

	public String getEvent8() {
		return event8;
	}

	public void setEvent8(String event8) {
		this.event8 = event8;
	}

	public String getNine() {
		return nine;
	}

	public void setNine(String nine) {
		this.nine = nine;
	}

	public Date getDate9() {
		return date9;
	}

	public void setDate9(Date date9) {
		this.date9 = date9;
	}

	public String getEvent9() {
		return event9;
	}

	public void setEvent9(String event9) {
		this.event9 = event9;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Date getDate10() {
		return date10;
	}

	public void setDate10(Date date10) {
		this.date10 = date10;
	}

	public String getEvent10() {
		return event10;
	}

	public void setEvent10(String event10) {
		this.event10 = event10;
	}

	public String getElven() {
		return elven;
	}

	public void setElven(String elven) {
		this.elven = elven;
	}

	public Date getDate11() {
		return date11;
	}

	public void setDate11(Date date11) {
		this.date11 = date11;
	}

	public String getEvent11() {
		return event11;
	}

	public void setEvent11(String event11) {
		this.event11 = event11;
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

	

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplierBank() {
		return supplierBank;
	}

	public void setSupplierBank(String supplierBank) {
		this.supplierBank = supplierBank;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public float getSanctionedAmount() {
		return sanctionedAmount;
	}

	public void setSanctionedAmount(float sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}

	public float getUtilizedAmount() {
		return utilizedAmount;
	}

	public void setUtilizedAmount(float utilizedAmount) {
		this.utilizedAmount = utilizedAmount;
	}

	public float getAvailableCost() {
		return availableCost;
	}

	public void setAvailableCost(float availableCost) {
		this.availableCost = availableCost;
	}

	
	

}
