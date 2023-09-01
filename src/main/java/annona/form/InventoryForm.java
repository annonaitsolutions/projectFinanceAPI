package annona.form;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import annona.domain.WareHouse;

@Component
public class InventoryForm {
	
	
	private Long id;
	
	private Long inventoryDate;
	
	private String quantity;
	
	private Float amount;
	
	private Float usedQuantity;
	
	private Float damaged;
	
	private Float returned;
	
	private String status;
	
	private String poKey;
	
	private Date poDate;
	
	private String customerHeadName;
	
	private String customerName;

	
	private String wareHouseName;
	
	private List<WareHouse> wareHouseList;
	
	private String supplierName;
	
	private String buyerName;
	
	private String masterKey;
	
	private  Float total;
	
	private  Float avail;
	
	private Integer flag;
	
	private String transactionId;
	
	private String inventoryType;
	
	private String imageName;
	
	private byte[] image;
	
	private MultipartFile file;
	
	private String goods;
	
	private Long warehouseId;


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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	public Float getReturned() {
		return returned;
	}

	public void setReturned(Float returned) {
		this.returned = returned;
	}

	public Float getAvail() {
		return avail;
	}

	public void setAvail(Float avail) {
		this.avail = avail;
	}

	public List<WareHouse> getWareHouseList() {
		return wareHouseList;
	}

	public void setWareHouseList(List<WareHouse> wareHouseList) {
		this.wareHouseList = wareHouseList;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPoDate() {
		return poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}
	
	
	
}
