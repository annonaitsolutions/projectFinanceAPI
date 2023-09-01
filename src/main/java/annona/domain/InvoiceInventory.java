package annona.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class InvoiceInventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private Long inventoryDate;
	
	private String quantity;
	
	private Float amount;
	
	private Float usedQuantity;
	
	private Float damaged;
	
	private String poKey;
	
	private String customerHeadName;
	
	private String customerName;
	
	private String wareHouseName;
	
	private String buyerName;
	
	private String masterKey;
	
	private  Float total;
	
	private  Float avail;
	
	private Integer flag;
	
	private String transactionId;
	
	private String inventoryType;
	
	private String goods;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInventoryDate() {
		return inventoryDate;
	}

	public void setInventoryDate(Long inventoryDate) {
		this.inventoryDate = inventoryDate;
	}

	

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
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

	public String getPoKey() {
		return poKey;
	}

	public void setPoKey(String poKey) {
		this.poKey = poKey;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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

	public String getCustomerHeadName() {
		return customerHeadName;
	}

	public void setCustomerHeadName(String customerHeadName) {
		this.customerHeadName = customerHeadName;
	}

	
	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public Float getAvail() {
		return avail;
	}

	public void setAvail(Float avail) {
		this.avail = avail;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	
	
	
	
	
	
	
	

}
