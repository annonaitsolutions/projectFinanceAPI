package annona.form;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CustomerBranchForm {

	private Long id;

	private String name;

	private String customerHeadName;

	private String companyName;

	private String gender;

	private String address;

	private String city;
	
	private String roleType;

    private String manager;
	
	private String managerEmail;
	
	private String pincode;

	private String country;

	private String state;

	private String contactNum;

	private String altContactNum;

	private String email;

	private String customerBranchPrefix;

	private Date customerHeadAppDate;

	private String altEmail;

	private String status;
	
	private String aStatus;

	private String comment;

	private String customerPrefix;

	private String companyHeadPrefix;

	private String position;

	private Date dateOfBirth;

	private String customerHeadKey;

	private Date registrationDate;

	private Date approveeDate;

	private String password;

	private String transactionId;

	private String customerBranchKey;

	private String notificationStatus;
	
	private Date accExpiryDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getAltContactNum() {
		return altContactNum;
	}

	public void setAltContactNum(String altContactNum) {
		this.altContactNum = altContactNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAltEmail() {
		return altEmail;
	}

	public void setAltEmail(String altEmail) {
		this.altEmail = altEmail;
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

	public String getCustomerPrefix() {
		return customerPrefix;
	}

	public void setCustomerPrefix(String customerPrefix) {
		this.customerPrefix = customerPrefix;
	}

	public String getCompanyHeadPrefix() {
		return companyHeadPrefix;
	}

	public void setCompanyHeadPrefix(String companyHeadPrefix) {
		this.companyHeadPrefix = companyHeadPrefix;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCustomerHeadKey() {
		return customerHeadKey;
	}

	public void setCustomerHeadKey(String customerHeadKey) {
		this.customerHeadKey = customerHeadKey;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getApproveeDate() {
		return approveeDate;
	}

	public Date getCustomerHeadAppDate() {
		return customerHeadAppDate;
	}

	public void setCustomerHeadAppDate(Date customerHeadAppDate) {
		this.customerHeadAppDate = customerHeadAppDate;
	}

	public String getCustomerBranchPrefix() {
		return customerBranchPrefix;
	}

	public void setCustomerBranchPrefix(String customerBranchPrefix) {
		this.customerBranchPrefix = customerBranchPrefix;
	}

	public void setApproveeDate(Date approveeDate) {
		this.approveeDate = approveeDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCustomerBranchKey() {
		return customerBranchKey;
	}

	public void setCustomerBranchKey(String customerBranchKey) {
		this.customerBranchKey = customerBranchKey;
	}

	public String getCustomerHeadName() {
		return customerHeadName;
	}

	public void setCustomerHeadName(String customerHeadName) {
		this.customerHeadName = customerHeadName;
	}

	public String getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public String getaStatus() {
		return aStatus;
	}

	public void setaStatus(String aStatus) {
		this.aStatus = aStatus;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public Date getAccExpiryDate() {
		return accExpiryDate;
	}

	public void setAccExpiryDate(Date accExpiryDate) {
		this.accExpiryDate = accExpiryDate;
	}
}
