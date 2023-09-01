package annona.trade.form;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class TUploadedFileForm{
	
	MultipartFile file;
	
	private Long id;
	
	private String document;
	
	private String customerName;

	private String customerHeadName;
	
	private String customerHeadKey;
	
	private List<MultipartFile> files;
	
	private Date uploadDate;
	
	private String reason;
	
	private String status;
	
	private String transactionId;
	
	private String comment;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

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

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

}