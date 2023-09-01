package annona.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class InvoiceStock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
    private String customerHeadName;
	
	private String customerName;
	
	private String wareHouseName;
	
	 private Float quantity;
	
    private Float usedQuantity;
	
	private Float damaged;
	
	private String transactionId;
	
	private Float overAllQuantity;
	
	private String goodsName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerHeadName() {
		return customerHeadName;
	}

	public void setCustomerHeadName(String customerHeadName) {
		this.customerHeadName = customerHeadName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Float getUsedQuantity() {
		return usedQuantity;
	}

	public void setUsedQuantity(Float usedQuantity) {
		this.usedQuantity = usedQuantity;
	}

	public Float getDamaged() {
		return damaged;
	}

	public void setDamaged(Float damaged) {
		this.damaged = damaged;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Float getOverAllQuantity() {
		return overAllQuantity;
	}

	public void setOverAllQuantity(Float overAllQuantity) {
		this.overAllQuantity = overAllQuantity;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

}
