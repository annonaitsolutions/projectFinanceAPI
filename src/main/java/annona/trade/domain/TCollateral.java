package annona.trade.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class TCollateral {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String masterKey;
	
	private String userName;
	
	private String property;
	
	private String propertyType;
	
	private String propertyAddress;
	
	private String propertyArea;
	
	private Integer pCostPerArea;
	
	private Integer propertyPurchaseValue;
	
	private Float propertyCurrValue;
	
	private Float pExisCharge;
	
	private String vehicle;
	
	private String vType;
	
	private Float vPurchaseValue;
	
	private Float VcurrValue;
	
	private String vDeatils;
	
	private String vInsurence;
	
	private Date vInsuranceStart;
	
	private Date vInsuranceEnd;
	
	private String vExistingCharge; 
	
	private String cash;
	
	private Float amount;
	
	private String FinInstitution;
	
	private String branch;
	
	private String location;
	
	private Date fDStartDate;
	
	private Date fDEndDate;
	
	private Float fDInterestRate;
	
	private String cExistimgCharge;
	
	private String insurancePolicy;
	
	private String benficiaryName;
	
	private String nominee;
	
	private String transactionId;
	
	private Date policyStartDate;
	
	private Date policyEndDate;
	
	private String premiumPayment;
	
	private Float valueOfPolicy;
	
	private String policyExisCharge;
	
	private String shares;
	
	private Integer companyShares;
	
	private Float sharesExisPrice;
	
	private String details;
	
	private String turnOver;
	
	private Integer profit;
	
	private String lineOfActivity;
	
	private String sharesExisCharge;
	
	private String machinery;
	
	private String machineryLocation;
	
	private Float machineryPurchaseValue;
	
	private Float machineryMarketValue;
	
	private String machineryDetails;
	
	private String registrationNum;
	
	private String machineryExisCharge;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public String getPropertyArea() {
		return propertyArea;
	}

	public void setPropertyArea(String propertyArea) {
		this.propertyArea = propertyArea;
	}

	public Integer getpCostPerArea() {
		return pCostPerArea;
	}

	public void setpCostPerArea(Integer pCostPerArea) {
		this.pCostPerArea = pCostPerArea;
	}

	public Integer getPropertyPurchaseValue() {
		return propertyPurchaseValue;
	}

	public void setPropertyPurchaseValue(Integer propertyPurchaseValue) {
		this.propertyPurchaseValue = propertyPurchaseValue;
	}

	public Float getPropertyCurrValue() {
		return propertyCurrValue;
	}

	public void setPropertyCurrValue(Float propertyCurrValue) {
		this.propertyCurrValue = propertyCurrValue;
	}

	public Float getpExisCharge() {
		return pExisCharge;
	}

	public void setpExisCharge(Float pExisCharge) {
		this.pExisCharge = pExisCharge;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getvType() {
		return vType;
	}

	public void setvType(String vType) {
		this.vType = vType;
	}

	public Float getvPurchaseValue() {
		return vPurchaseValue;
	}

	public void setvPurchaseValue(Float vPurchaseValue) {
		this.vPurchaseValue = vPurchaseValue;
	}

	public Float getVcurrValue() {
		return VcurrValue;
	}

	public void setVcurrValue(Float vcurrValue) {
		VcurrValue = vcurrValue;
	}

	public String getvDeatils() {
		return vDeatils;
	}

	public void setvDeatils(String vDeatils) {
		this.vDeatils = vDeatils;
	}

	public String getvInsurence() {
		return vInsurence;
	}

	public void setvInsurence(String vInsurence) {
		this.vInsurence = vInsurence;
	}

	public Date getvInsuranceStart() {
		return vInsuranceStart;
	}

	public void setvInsuranceStart(Date vInsuranceStart) {
		this.vInsuranceStart = vInsuranceStart;
	}

	public Date getvInsuranceEnd() {
		return vInsuranceEnd;
	}

	public void setvInsuranceEnd(Date vInsuranceEnd) {
		this.vInsuranceEnd = vInsuranceEnd;
	}

	public String getvExistingCharge() {
		return vExistingCharge;
	}

	public void setvExistingCharge(String vExistingCharge) {
		this.vExistingCharge = vExistingCharge;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getFinInstitution() {
		return FinInstitution;
	}

	public void setFinInstitution(String finInstitution) {
		FinInstitution = finInstitution;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getfDStartDate() {
		return fDStartDate;
	}

	public void setfDStartDate(Date fDStartDate) {
		this.fDStartDate = fDStartDate;
	}

	public Date getfDEndDate() {
		return fDEndDate;
	}

	public void setfDEndDate(Date fDEndDate) {
		this.fDEndDate = fDEndDate;
	}

	public Float getfDInterestRate() {
		return fDInterestRate;
	}

	public void setfDInterestRate(Float fDInterestRate) {
		this.fDInterestRate = fDInterestRate;
	}

	public String getcExistimgCharge() {
		return cExistimgCharge;
	}

	public void setcExistimgCharge(String cExistimgCharge) {
		this.cExistimgCharge = cExistimgCharge;
	}

	public String getInsurancePolicy() {
		return insurancePolicy;
	}

	public void setInsurancePolicy(String insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	public String getBenficiaryName() {
		return benficiaryName;
	}

	public void setBenficiaryName(String benficiaryName) {
		this.benficiaryName = benficiaryName;
	}

	public String getNominee() {
		return nominee;
	}

	public void setNominee(String nominee) {
		this.nominee = nominee;
	}

	public Date getPolicyStartDate() {
		return policyStartDate;
	}

	public void setPolicyStartDate(Date policyStartDate) {
		this.policyStartDate = policyStartDate;
	}

	public Date getPolicyEndDate() {
		return policyEndDate;
	}

	public void setPolicyEndDate(Date policyEndDate) {
		this.policyEndDate = policyEndDate;
	}

	public String getPremiumPayment() {
		return premiumPayment;
	}

	public void setPremiumPayment(String premiumPayment) {
		this.premiumPayment = premiumPayment;
	}

	public Float getValueOfPolicy() {
		return valueOfPolicy;
	}

	public void setValueOfPolicy(Float valueOfPolicy) {
		this.valueOfPolicy = valueOfPolicy;
	}

	public String getPolicyExisCharge() {
		return policyExisCharge;
	}

	public void setPolicyExisCharge(String policyExisCharge) {
		this.policyExisCharge = policyExisCharge;
	}

	public String getShares() {
		return shares;
	}

	public void setShares(String shares) {
		this.shares = shares;
	}

	public Integer getCompanyShares() {
		return companyShares;
	}

	public void setCompanyShares(Integer companyShares) {
		this.companyShares = companyShares;
	}

	public Float getSharesExisPrice() {
		return sharesExisPrice;
	}

	public void setSharesExisPrice(Float sharesExisPrice) {
		this.sharesExisPrice = sharesExisPrice;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getTurnOver() {
		return turnOver;
	}

	public void setTurnOver(String turnOver) {
		this.turnOver = turnOver;
	}

	public Integer getProfit() {
		return profit;
	}

	public void setProfit(Integer profit) {
		this.profit = profit;
	}

	public String getLineOfActivity() {
		return lineOfActivity;
	}

	public void setLineOfActivity(String lineOfActivity) {
		this.lineOfActivity = lineOfActivity;
	}

	public String getSharesExisCharge() {
		return sharesExisCharge;
	}

	public void setSharesExisCharge(String sharesExisCharge) {
		this.sharesExisCharge = sharesExisCharge;
	}

	public String getMachinery() {
		return machinery;
	}

	public void setMachinery(String machinery) {
		this.machinery = machinery;
	}

	public String getMachineryLocation() {
		return machineryLocation;
	}

	public void setMachineryLocation(String machineryLocation) {
		this.machineryLocation = machineryLocation;
	}

	public Float getMachineryPurchaseValue() {
		return machineryPurchaseValue;
	}

	public void setMachineryPurchaseValue(Float machineryPurchaseValue) {
		this.machineryPurchaseValue = machineryPurchaseValue;
	}

	public Float getMachineryMarketValue() {
		return machineryMarketValue;
	}

	public void setMachineryMarketValue(Float machineryMarketValue) {
		this.machineryMarketValue = machineryMarketValue;
	}

	public String getMachineryDetails() {
		return machineryDetails;
	}

	public void setMachineryDetails(String machineryDetails) {
		this.machineryDetails = machineryDetails;
	}

	public String getRegistrationNum() {
		return registrationNum;
	}

	public void setRegistrationNum(String registrationNum) {
		this.registrationNum = registrationNum;
	}

	public String getMachineryExisCharge() {
		return machineryExisCharge;
	}

	public void setMachineryExisCharge(String machineryExisCharge) {
		this.machineryExisCharge = machineryExisCharge;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
	
	
	
	
	
	

}
