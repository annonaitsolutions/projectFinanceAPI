<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
		  		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
				</div>

		<div class="col-sm-12 col-md-12 col-lg-12">	
				
				<form:form action="collateralPost" commandName="collateralForm">
				
				<form:hidden path ="masterKey" id="masterKey"/>
						<form:hidden path ="transactionId" id="transactionId"/>
			
				        <form:hidden path ="userName" id="userName"/>
				 <div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12">			
					<label><%-- <form:checkbox path="property" readonly="true"  value="Cash/FD Amount" style="margin-top:-1px;"/> --%> <b><spring:message code="label.property"/></b></label>
				</div>
						
				<div class="col-sm-3"><b><spring:message code="label.propertyType"/></b><span style="color:red"></span></div>
				<div class="col-sm-9">
					<form:input path="propertyType" class="form-control" readonly="true" id="propertyType"/>
				</div>
				<div class="col-sm-3"><b><spring:message code="label.address"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="propertyAddress" readonly="true"  class="form-control" placeholder="Enter the Address"  id="propertyAddress"/>
						</div>	
						
						<div class="col-sm-3"><b><spring:message code="label.area"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="propertyArea"  readonly="true" class="form-control" placeholder="Enter the Area"  id="propertyArea"/>
						</div>	
						
						<div class="col-sm-3"><b><spring:message code="label.costPerArea"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="pCostPerArea" readonly="true" class="form-control" placeholder="Enter the Cost Per Area"  id="pCostPerArea"/>
						</div>
					<div class="col-sm-3"><b><spring:message code="label.purchaseValue"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="propertyPurchaseValue" readonly="true"  class="form-control" placeholder="Enter the Purchase Value"  id="propertyPurchaseValue"/>
						</div>
						<div class="col-sm-3"><b><spring:message code="label.currentMarketvalue"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="propertyCurrValue"  readonly="true" class="form-control" placeholder="Enter the Current Market Value"  id="propertyCurrValue"/>
						</div>
						<div class="col-sm-3"><b><spring:message code="label.existingCharge"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="pExisCharge" class="form-control" readonly="true" id="pExisCharge"/>
						</div>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12">			
					<label><%-- <form:checkbox path="vehicle" readonly="true"   value="Plot of Land" style="margin-top:-1px;"/> --%> <b><spring:message code="label.vehicle"/></b></label>
				</div>
				<div class="col-sm-3"><b><spring:message code="label.vehicleType"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
								<form:input path="vType" id="vType"  class="form-control" readonly="true" />
						</div>	
						<div class="col-sm-3"><b><spring:message code="label.purchaseValue"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="vPurchaseValue" readonly="true"  class="form-control" placeholder="Enter Purchase Value"  id="vPurchaseValue"/>
						</div>	
						<div class="col-sm-3"><b><spring:message code="label.currentMarketCost"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="VcurrValue" readonly="true"   class="form-control" placeholder="Enter the Current Market Value"  id="VcurrValue"/>
						</div>	
						<div class="col-sm-3"><b><spring:message code="label.details"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="vDeatils"  readonly="true"  class="form-control" placeholder="Enter the Details"  id="vDeatils"/>
						</div>	
						<div class="col-sm-3"><b><spring:message code="label.insurance"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="vInsurence" id="vInsurence" class="form-control" readonly="true" />
											
						</div>
						<div class="col-sm-3"><b><spring:message code="label.startDate"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="vInsuranceStart" readonly="true"  class="form-control" placeholder="Enter the Insurance Start Date"  id="vInsuranceStart"/>
						</div>	
						<div class="col-sm-3"><b><spring:message code="label.endDate"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="vInsuranceEnd" readonly="true"  class="form-control" placeholder="Enter the Insurance End Date"  id="vInsuranceEnd"/>
						</div>
						
							<div class="col-sm-3"><b><spring:message code="label.existingCharge"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="vExistingCharge" id="vExistingCharge"  readonly="true" class="form-control" />
											
						</div>
					</div>
					<div class="col-sm-12 col-md-12"> <hr></div>
					
					<div class="col-sm-12 col-md-12 col-lg-12">
						
						<div class="col-sm-12 col-md-12 col-lg-12">		
							<label><%-- <form:checkbox path="cash" readonly="true"  value="cash" style="margin-top:-1px;"/> --%> <b><spring:message code="label.cashFixedDeposit"/></b></label>
			    		</div>
				
						<div class="col-sm-3"><b><spring:message code="label.amount"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="amount" readonly="true"  class="form-control" placeholder="Enter Amount"  id="amount"/>
						</div>	
							<div class="col-sm-3"><b><spring:message code="label.financialInstitution"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="FinInstitution" class="form-control" id="FinInstitution"  readonly="true" />
						</div>
						<div class="col-sm-3"><b><spring:message code="label.branch"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="branch" readonly="true"  class="form-control" placeholder="Enter Branch"  id="branch"/>
						</div>	
						<div class="col-sm-3"><b><spring:message code="label.location"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="location" readonly="true"   class="form-control" placeholder="Enter Location"  id="location"/>
						</div>	
						<div class="col-sm-3"><b><spring:message code="label.fdStartDate"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="fDStartDate" readonly="true"  class="form-control" placeholder="Enter FD Start Date"  id="fDStartDate"/>
						</div>
						<div class="col-sm-3"><b><spring:message code="label.fdEndDate"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="fDEndDate" readonly="true"  class="form-control" placeholder="Enter FD End Date"  id="fDEndDate"/>
						</div>
						<div class="col-sm-3"><b><spring:message code="label.fdInterestRate"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="fDInterestRate" readonly="true"  class="form-control" placeholder="Enter FD Interest Rate" id="fDInterestRate"/>
						</div>
						
							<div class="col-sm-3"><b><spring:message code="label.existingCharge"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="cExistimgCharge" readonly="true" class="form-control"  id="cExistimgCharge"/>
											 
						</div>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
				
						<div class="col-sm-12 col-md-12 col-lg-12">		
							<label><%-- <form:checkbox path="insurancePolicy" readonly="true"   value="Insurance Policy" style="margin-top:-1px;"/> --%> <b><spring:message code="label.insurancePolicy"/></b></label>
						</div>
						<div class="col-sm-3"><b><spring:message code="label.beneficiaryName"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="benficiaryName" readonly="true"  class="form-control" placeholder="Enter Name" id="benficiaryName"/>
						</div>	
						
						<div class="col-sm-3"><b><spring:message code="label.nominee"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="nominee" readonly="true"  class="form-control" placeholder="Enter Nomimee"  id="nominee"/>
						</div>	
						
						 <div class="col-sm-3"><b><spring:message code="label.policyStartDate"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="policyStartDate" readonly="true"  class="form-control" placeholder="Enter start Date"  id="policyStartDate"/>
						</div>	
						
						<div class="col-sm-3"><b><spring:message code="label.policyEndDate"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="policyEndDate" readonly="true"  class="form-control" placeholder="Enter end Date"  id="policyEndDate"/>
						</div>
						
						
						<div class="col-sm-3"><b><spring:message code="label.premiumPayment"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="premiumPayment" id="premiumPayment" class="form-control" readonly="true" />
						</div>
						<div class="col-sm-3"><b><spring:message code="label.valuePolicy"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="valueOfPolicy" readonly="true"  class="form-control" placeholder="Enter Value"  id="valueOfPolicy"/>
						</div>	
							
							<div class="col-sm-3"><b><spring:message code="label.existingCharge"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="policyExisCharge" id="policyExisCharge" class="form-control" readonly="true" />
						</div>
					</div>
					<div class="col-sm-12 col-md-12 col-lg-12"></div>
					<div class="col-sm-12 col-md-12"> <hr></div>
					
					<div class="col-sm-12 col-md-12 col-lg-12">
				
				 		<div class="col-sm-12 col-md-12 col-lg-12">		
							<label><%-- <form:checkbox path="shares" readonly="true"  value="Shares" style="margin-top:-1px;"/>  --%><b><spring:message code="label.shares"/></b></label>
						</div>
				
						<div class="col-sm-3"><b><spring:message code="label.companySh"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="companyShares" readonly="true"  class="form-control" placeholder="Enter Shares" id="companyShares"/>
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.sharesExistingPrice"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="sharesExisPrice" readonly="true"  class="form-control" placeholder="Enter Existing Price" id="sharesExisPrice"/>
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.companyDetails"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="details" readonly="true"  class="form-control" placeholder="Enter Details"  id="details"/>
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.turnOver"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="turnOver" readonly="true"  class="form-control" placeholder="Enter Turn Over" id="turnOver"/>
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.profit"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="profit" readonly="true"  class="form-control" placeholder="Enter Profit" id="profit"/>
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.lineOfActivities"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="lineOfActivity" readonly="true"  class="form-control" placeholder="Enter Line of Activities" id="lineOfActivity"/>
						</div>
							
							<div class="col-sm-3"><b><spring:message code="label.existingCharge"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="sharesExisCharge" id="sharesExisCharge" readonly="true" class="form-control" />
										
						</div>
				</div>	
						
				<div class="col-sm-12 col-md-12 col-lg-12">
				
					 	<div class="col-sm-12 col-md-12 col-lg-12">		
							<label><%-- <form:checkbox path="machinery" readonly="true"  value="Shares" style="margin-top:-1px;"/>  --%><b><spring:message code="label.plantMachinery"/></b></label>
						</div>	
					
						<div class="col-sm-3"><b><spring:message code="label.location"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="machineryLocation" readonly="true"   class="form-control" placeholder="Enter Location" id="machineryLocation"/>
						</div> 
						
						<div class="col-sm-3"><b><spring:message code="label.purchaseValue"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="machineryPurchaseValue" readonly="true"  class="form-control" placeholder="Enter Purchase Value"  id="machineryPurchaseValue"/>
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.currentMarketvalue"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="machineryMarketValue" readonly="true"  class="form-control" placeholder="Enter Current Market Value" id="machineryMarketValue"/>
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.details"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="machineryDetails" readonly="true"  class="form-control" placeholder="Enter Details" id="machineryDetails"/>
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.registrationNumber"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="registrationNum" readonly="true"   class="form-control" placeholder="Enter Reistration Number" id="registrationNum"/>
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.existingCharge"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="machineryExisCharge" readonly="true"  id="machineryExisCharge" class="form-control" />
											   
						</div>
					
				</div>
				<div class="col-sm-12 col-md-12"> &nbsp;</div>
				
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="masterPlanPending" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>		
		</form:form>
				
				
				
		</div>			
	</div>	
	<style>
	.form-control {
    display: block;
    width: 100%;
    height: 34px;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
	</style>