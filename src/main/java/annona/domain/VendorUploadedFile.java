package annona.domain;



import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Configurable
public class VendorUploadedFile{
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private String document;
	
	private String customerName;
	
	private String customerHeadName;
	
	private String customerHeadKey;
	
    private Date uploadDate;
	
	private String reason;
	
	private String status;
	
	private String comment;
	
	private String poKey;
	
	private String masterKey;
	
	private String transactionId;

/*	@Column(length = 1000)
	private String fileName;*/
	
	
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<byte[]> files;
	
	public Set<byte[]> getFiles() {
		return files;
	}

	public void setFiles(Set<byte[]> files) {
		this.files = files;
	}

	public Set<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(Set<String> fileNames) {
		this.fileNames = fileNames;
	}

	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<String> fileNames;

	



	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCustomerHeadKey() {
		return customerHeadKey;
	}

	public void setCustomerHeadKey(String customerHeadKey) {
		this.customerHeadKey = customerHeadKey;
	}

	/*public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
*/
	public String getPoKey() {
		return poKey;
	}

	public void setPoKey(String poKey) {
		this.poKey = poKey;
	}

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

}