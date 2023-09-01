<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script>
$(document).ready(function() {
    $("#accordion").accordion({
    	 autoHeight: false,
        clearstyle: true,
   		collapsible: true, //allow accordion to be completely collapsed showing only the headers.
        active: false //open first accordion (Sports) on load
    });
    $("#accordion1").accordion({
   	 autoHeight: false,
       clearstyle: true,
  		collapsible: true, //allow accordion to be completely collapsed showing only the headers.
       active: false //open first accordion (Sports) on load
   });
    $("#accordion2").accordion({
      	 autoHeight: false,
          clearstyle: true,
     		collapsible: true, //allow accordion to be completely collapsed showing only the headers.
          active: false //open first accordion (Sports) on load
      });
    $("#accordion3").accordion({
      	 autoHeight: false,
          clearstyle: true,
     		collapsible: true, //allow accordion to be completely collapsed showing only the headers.
          active: false //open first accordion (Sports) on load
      });
    $("#accordion4").accordion({
      	 autoHeight: false,
          clearstyle: true,
     		collapsible: true, //allow accordion to be completely collapsed showing only the headers.
          active: false //open first accordion (Sports) on load
      });
    $("#accordion5").accordion({
     	 autoHeight: false,
         clearstyle: true,
    		collapsible: true, //allow accordion to be completely collapsed showing only the headers.
         active: false //open first accordion (Sports) on load
     });
   
});
$('.checkbox input[type="checkbox"]').click(function(e) {
    e.stopPropagation();
});

</script>
<div class="col-sm-9 col-md-9 body_fixed">


                <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.masterPlanStep3"/></h3>
				</div>
				
					
				<h3 style="font-size: 19px; font-weight: 400; color: #2E9AFE;"><spring:message code="label.collateralDocs"/></h3>
				
				 <div class="col-sm-12 col-md-12 col-lg-12">	
				<form:form action="collateralConfirm" commandName="collateralForm" onsubmit="return val();">
				
				<form:hidden path ="masterKey" value="${model.collateralForm.masterKey}"  id="masterKey"/>
				<form:hidden path ="transactionId" value="${model.collateralForm.transactionId}"  id="transactionId"/>
		        <form:hidden path ="id" value="${model.collateralForm.id}"  id="id"/>
		        <form:hidden path ="userName" value="${model.collateralForm.userName}"  id="userName"/>
		        <div class="col-sm-12 col-md-12">
				<div id="accordion">
					   
					  	<div class="col-sm-12 col-md-12">		
							<h4> <b><spring:message code="label.property"/></b><%-- <form:checkbox path="property"  value="Cash/FD Amount"  style="margin-top:-1px;"/> --%></h4>
						</div>
						
					<div class="pane">			
							<div class="col-sm-3"><b><spring:message code="label.propertyType"/></b><span style="color:red"></span></div>
							<div class="col-sm-9">
								<form:select path="propertyType" id="propertyType" multiple="multiple" class="form-control">
							    <form:option value="" class="option-header"><spring:message code="label.selectValue"/></form:option> 
							    <form:option value="commercialProperty"><spring:message code="label.commProp"/></form:option>
								<form:option value="resProperty"><spring:message code="label.resProp"/></form:option>
								<form:option value="land"><spring:message code="label.agriLand"/></form:option>
								</form:select>
								</div>
							
												
						
							<div class="col-sm-3"><b><spring:message code="label.address"/></b><span style="color:red"></span></div>
							<div class="col-sm-9">
								<form:input path="propertyAddress"  class="form-control" placeholder="Enter the Address (In Case of multiple data Please seperate with ,)" id="propertyAddress"/>
							</div>	
									
							<div class="col-sm-3"><b><spring:message code="label.area"/></b><span style="color:red"></span></div>
							<div class="col-sm-9">
								<form:input path="propertyArea"  class="form-control" placeholder="Enter the Area (In Case of multiple data Please seperate with ,)" id="propertyArea"/>
							</div>	
							
							<div class="col-sm-3"><b><spring:message code="label.costPerArea"/></b><span style="color:red"></span></div>
							<div class="col-sm-9">
								<form:input path="pCostPerArea"  class="form-control" placeholder="Enter the Cost Per Area (In Case of multiple data Please seperate with ,)" id="pCostPerArea"/>
								<div id="pCostPerAreaError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
								
							</div>
							<div class="col-sm-3"><b><spring:message code="label.purchaseValue"/></b><span style="color:red"></span></div>
							<div class="col-sm-9">
								<form:input path="propertyPurchaseValue"  class="form-control" placeholder="Enter the Purchase Value (In Case of multiple data Please seperate with ,)" id="propertyPurchaseValue"/>
								<div id="propertyPurchaseValueError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
								
							</div>
							<div class="col-sm-3"><b><spring:message code="label.currentMarketvalue"/></b><span style="color:red"></span></div>
							<div class="col-sm-9">
								<form:input path="propertyCurrValue"  class="form-control" placeholder="Enter the Current Market Value (In Case of multiple data Please seperate with ,)" id="propertyCurrValue"/>
								<div id="propertyCurrValueError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
							
							</div>
							<div class="col-sm-3"><b><spring:message code="label.existingCharge"/></b><span style="color:red"></span></div>
							<div class="col-sm-9">
								<form:select path="pExisCharge" id="pExisCharge" multiple="multiple" class="form-control">
							    <form:option value="" class="option-header"><spring:message code="label.selectValue"/></form:option> 
							    <form:option value="1stType"><spring:message code="label.1stType"/></form:option>
								<form:option value="2ndTypr"><spring:message code="label.2ndType"/></form:option>
								<form:option value="pariPasu"><spring:message code="label.pariPasu"/></form:option>
								</form:select>
								<div id="pExisChargeError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
						
							</div>
					</div>
				</div>
				</div>
				<div class="col-sm-12 col-md-12"> <hr></div>
				<div class="col-sm-12 col-md-12">
				 <div id="accordion1">
					<div class="col-sm-12">		
						<h4><b><spring:message code="label.vehicle"/></b><%-- <form:checkbox path="vehicle"  value="Plot of Land" style="margin-top:-1px;"/>  --%></h4>
					</div>
					<div class="pane">
					<div class="col-sm-3" class="heading_text control-label col-sm-2"><b><spring:message code="label.vehicleType"/></b><span style="color:red"></span></div>
					
					<div class="col-sm-9">
							<form:select path="vType" id="vType" multiple="multiple" class="form-control">
						    <form:option value="" class="option-header"><spring:message code="label.selectValue"/></form:option> 
						    <form:option value="commercial"><spring:message code="label.comVehicle"/></form:option>
							<form:option value="private"><spring:message code="label.priVehicle"/></form:option>
							</form:select>
					</div>	
					<div class="col-sm-3"><b><spring:message code="label.purchaseValue"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input path="vPurchaseValue"  class="form-control" placeholder="Enter Purchase Value (In Case of multiple data Please seperate with ,)" id="vPurchaseValue"/>
							<div id="vPurchaseValueError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
						
					</div>	
					<div class="col-sm-3"><b><spring:message code="label.currentMarketCost"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input path="VcurrValue"  class="form-control" placeholder="Enter the Current Market Value (In Case of multiple data Please seperate with ,)" id="VcurrValue"/>
						<div id="VcurrValueError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
		
					</div>	
					<div class="col-sm-3"><b><spring:message code="label.details"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input path="vDeatils"  class="form-control" placeholder="Enter the Details (In Case of multiple data Please seperate with ,)" id="vDeatils"/>
					</div>	
					<div class="col-sm-3"><b><spring:message code="label.insurance"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:select path="vInsurence" id="vInsurence" multiple="multiple" class="form-control">
					    <form:option value="" class="option-header"><spring:message code="label.selectValue"/></form:option> 
					    <form:option value="yes"><spring:message code="label.yes"/></form:option>
						<form:option value="No"><spring:message code="label.no"/></form:option>
						</form:select>
					</div>
					<div class="col-sm-3"><b><spring:message code="label.startDate"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input id="datepicker" style="width:205px;" path="vInsuranceStart" placeholder="Insurance Start Date (In Case of multiple data Please seperate with ,)" /><i class="fa fa-calendar"></i>
					</div>	
					<div class="col-sm-3"><b><spring:message code="label.endDate"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
				
						<form:input id="datepicker1" path="vInsuranceEnd" placeholder="Insurance End Date" /><i class="fa fa-calendar"></i>
					</div>
					
					<div class="col-sm-3"><b><spring:message code="label.existingCharge"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:select path="vExistingCharge" id="vExistingCharge" multiple="multiple" class="form-control">
					    <form:option value="" class="option-header"><spring:message code="label.selectValue"/></form:option> 
					    <form:option value="1stType"><spring:message code="label.1stType"/></form:option>
							<form:option value="2ndTypr"><spring:message code="label.2ndType"/></form:option>
							<form:option value="pariPasu"><spring:message code="label.pariPasu"/></form:option>
						</form:select>
					</div>
				</div>
				</div>
				</div>
				<div class="col-sm-12 col-md-12"> <hr></div>
				<div class="col-sm-12 col-md-12">
					 <div id="accordion2">	
						<div class="col-sm-12">		
							<h4><%-- <form:checkbox path="cash"  value="cash" style="margin-top:-1px;"/>  --%><b><spring:message code="label.cashFixedDeposit"/></b></h4>
						</div>
					<div class="pane">
						<div class="col-sm-3"><b><spring:message code="label.amount"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="amount"  class="form-control" placeholder="Enter Amount (In Case of multiple data Please seperate with ,)" id="amount"/>
								<div id="amountError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
							
						</div>	
						<div class="col-sm-3"><b><spring:message code="label.financialInstitution"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:select path="FinInstitution" multiple="multiple" id="FinInstitution" class="form-control">
						    <form:option value="" class="option-header"><spring:message code="label.selectValue"/></form:option> 
						    <form:option value="bank"><spring:message code="label.bank"/></form:option>
							<form:option value="company"><spring:message code="label.company"/></form:option>
							</form:select>
						</div>
						<div class="col-sm-3"><b><spring:message code="label.branch"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="branch"  class="form-control" placeholder="Enter Branch (In Case of multiple data Please seperate with ,)" id="branch"/>
						</div>	
						<div class="col-sm-3"><b><spring:message code="label.location"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="location"  class="form-control" placeholder="Enter Location (In Case of multiple data Please seperate with ,)" id="location"/>
						</div>	
						<div class="col-sm-3"><b><spring:message code="label.fdStartDate"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							
						<form:input id="datepicker4" path="fDStartDate" placeholder="Enter fd Start Date" /><i class="fa fa-calendar"></i>
						</div>
						<div class="col-sm-3"><b><spring:message code="label.fdEndDate"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
						<form:input id="datepicker5" path="fDEndDate" placeholder="Enter fD End Date" /><i class="fa fa-calendar"></i>
						</div>
						<div class="col-sm-3"><b><spring:message code="label.fdInterestRate"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="fDInterestRate"  class="form-control" placeholder="Enter FD Interest Rate (In Case of multiple data Please seperate with ,)" id="fDInterestRate"/>
								<div id="fDInterestRateError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
							
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.existingCharge"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:select path="cExistimgCharge" multiple="multiple" id="cExistimgCharge" class="form-control">
						    <form:option value="" class="option-header"><spring:message code="label.selectValue"/></form:option> 
					    <form:option value="1stType"><spring:message code="label.1stType"/></form:option>
							<form:option value="2ndTypr"><spring:message code="label.2ndType"/></form:option>
							<form:option value="pariPasu"><spring:message code="label.pariPasu"/></form:option>
							</form:select>
						</div>
						</div>
					</div>
				</div>		
				<div class="col-sm-12 col-md-12"> <hr></div>
				<div class="col-sm-12 col-md-12">
					 <div id="accordion3">
						 <div class="col-sm-12">		
							<h4><%-- <form:checkbox path="insurancePolicy"  value="Insurance Policy" style="margin-top:-1px;"/>  --%><b><spring:message code="label.insurancePolicy"/></b></h4>
						 </div>
					<div class="pane">
						<div class="col-sm-3"><b><spring:message code="label.beneficiaryName"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="benficiaryName"  class="form-control" placeholder="Enter Name (In Case of multiple data Please seperate with ,)" id="benficiaryName"/>
						</div>	
						
						<div class="col-sm-3"><b><spring:message code="label.nominee"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="nominee"  class="form-control" placeholder="Enter Nomimee (In Case of multiple data Please seperate with ,)" id="nominee"/>
						</div>	
						
						 <div class="col-sm-3"><b><spring:message code="label.policyStartDate"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
					
							<form:input id="datepicker2" path="policyStartDate" placeholder="Enter policy Start Date" /><i class="fa fa-calendar"></i>
						</div>	
						
						<div class="col-sm-3"><b><spring:message code="label.policyEndDate"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
				 
				 		<form:input id="datepicker3" path="policyEndDate" placeholder="Enter end Date" /><i class="fa fa-calendar"></i>
						</div>
						
						
						<div class="col-sm-3"><b><spring:message code="label.premiumPayment"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:select path="premiumPayment" multiple="multiple" id="premiumPayment" class="form-control">
						    <form:option value="" class="option-header"><spring:message code="label.selectValue"/></form:option> 
						    <form:option value="monthly"><spring:message code="label.monthly"/></form:option>
							<form:option value="yearly"><spring:message code="label.yearly"/></form:option>
							</form:select>
						</div>
						<div class="col-sm-3"><b><spring:message code="label.valuePolicy"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="valueOfPolicy"  class="form-control" placeholder="Enter Value (In Case of multiple data Please seperate with ,)" id="valueOfPolicy"/>
							<div id="valueOfPolicyError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
					
						</div>	
							
						<div class="col-sm-3"><b><spring:message code="label.existingCharge"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:select path="policyExisCharge" multiple="multiple" id="policyExisCharge" class="form-control">
						     <form:option value="" class="option-header"><spring:message code="label.selectValue"/></form:option> 
					    <form:option value="1stType"><spring:message code="label.1stType"/></form:option>
							<form:option value="2ndTypr"><spring:message code="label.2ndType"/></form:option>
							<form:option value="pariPasu"><spring:message code="label.pariPasu"/></form:option>
							</form:select>
						</div>
					</div>
					</div>
				</div>
				<div class="col-sm-12 col-md-12"> <hr></div>
			<div class="col-sm-12 col-md-12">
				<div id="accordion4">
					 <div class="col-sm-12">		
						<h4><%-- <form:checkbox path="shares"  value="Shares" style="margin-top:-1px;"/> --%> <b><spring:message code="label.shares"/></b></h4>
					</div> 
				<div class="pane">
					<div class="col-sm-3"><b><spring:message code="label.companySh"/></b></div>
					<div class="col-sm-9">
						<form:input path="companyShares"  class="form-control" placeholder="Enter Shares (In Case of multiple data Please seperate with ,)" id="companyShares"/>
							<div id="companySharesError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
						
					</div>
					
					<div class="col-sm-3"><b><spring:message code="label.sharesExistingPrice"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input path="sharesExisPrice"  class="form-control" placeholder="Enter Existing Price (In Case of multiple data Please seperate with ,)" id="sharesExisPrice"/>
							<div id="sharesExisPriceError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
							
					</div>
					
					<div class="col-sm-3"><b><spring:message code="label.companyDetails"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input path="details"  class="form-control" placeholder="Enter Details (In Case of multiple data Please seperate with ,)" id="details"/>
					</div>
					
					<div class="col-sm-3"><b><spring:message code="label.turnOver"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input path="turnOver"  class="form-control" placeholder="Enter Turn Over (In Case of multiple data Please seperate with ,)" id="turnOver"/>
				<div id="turnOverError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>	
					</div>
					
					<div class="col-sm-3"><b><spring:message code="label.profit"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input path="profit"  class="form-control" placeholder="Enter Profit (In Case of multiple data Please seperate with ,)" id="profit"/>
							<div id="profitError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
					
					</div>
					
					<div class="col-sm-3"><b><spring:message code="label.lineOfActivities"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input path="lineOfActivity"  class="form-control" placeholder="Enter Line of Activities (In Case of multiple data Please seperate with ,)" id="lineOfActivity"/>
					</div>
						
					<div class="col-sm-3"><b><spring:message code="label.existingCharge"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:select path="sharesExisCharge" multiple="multiple" id="sharesExisCharge" class="form-control">
					     <form:option value="" class="option-header"><spring:message code="label.selectValue"/></form:option> 
					    <form:option value="1stType"><spring:message code="label.1stType"/></form:option>
							<form:option value="2ndTypr"><spring:message code="label.2ndType"/></form:option>
							<form:option value="pariPasu"><spring:message code="label.pariPasu"/></form:option>
						</form:select>
					</div>
					</div>
				</div>
				</div>
				<div class="col-sm-12 col-md-12"> <hr></div>
				<div class="col-sm-12 col-md-12">
					 <div id="accordion5">
					 <div class="col-sm-12">		
						<h4><%-- <form:checkbox path="machinery"  value="machinery" style="margin-top:-1px;"/>  --%><b><spring:message code="label.plantMachinery"/></b></h4>
					</div>	
					<div class="pane">
					<div class="col-sm-3"><b><spring:message code="label.location"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input path="machineryLocation"  class="form-control" placeholder="Enter Location (In Case of multiple data Please seperate with ,)" id="machineryLocation"/>
					</div> 
							
					<div class="col-sm-3"><b><spring:message code="label.purchaseValue"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input path="machineryPurchaseValue"  class="form-control" placeholder="Enter Purchase Value (In Case of multiple data Please seperate with ,)" id="machineryPurchaseValue"/>
						<div id="machineryPurchaseValueError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
					
					</div>
					
					<div class="col-sm-3"><b><spring:message code="label.currentMarketvalue"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input path="machineryMarketValue"  class="form-control" placeholder="Enter Current Market Value (In Case of multiple data Please seperate with ,)" id="machineryMarketValue"/>
							<div id="machineryMarketValueError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
							
					</div>
					
					<div class="col-sm-3"><b><spring:message code="label.details"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input path="machineryDetails"  class="form-control" placeholder="Enter Details (In Case of multiple data Please seperate with ,)" id="machineryDetails"/>
					</div>
					
					<div class="col-sm-3"><b><spring:message code="label.registrationNumber"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:input path="registrationNum"  class="form-control" placeholder="Enter Reistration Number (In Case of multiple data Please seperate with ,)" id="registrationNum"/>
					</div>
					
					<div class="col-sm-3"><b><spring:message code="label.existingCharge"/></b><span style="color:red"></span></div>
					<div class="col-sm-9">
						<form:select path="machineryExisCharge" multiple="multiple" id="machineryExisCharge" class="form-control">
					     <form:option value="" class="option-header"><spring:message code="label.selectValue"/></form:option> 
					    <form:option value="1stType"><spring:message code="label.1stType"/></form:option>
							<form:option value="2ndTypr"><spring:message code="label.2ndType"/></form:option>
							<form:option value="pariPasu"><spring:message code="label.pariPasu"/></form:option>
						</form:select>
					</div>
					</div>
					</div>
				</div>
				<div class="col-sm-12 col-md-12"> &nbsp;</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="masterPlanPending" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		</form:form>
				
				
	</div>			
				
</div>	

	<script>
	function val(){
					
					var propertyType  = document.getElementById('pCostPerArea');
					var number='^\\d+$';
					var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
					var number1= /^-?[0-9]+$/;

					var canSubmit = true; 
					
					if(pCostPerArea.value.match('^\\d+$') ||(document.getElementById('pCostPerArea').value == ''))
				  	{
				  		
				  		document.getElementById('pCostPerAreaError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('pCostPerAreaError').style.display='block';
				  	     canSubmit = false;
				  	}
					if(propertyPurchaseValue.value.match('^\\d+$') ||(document.getElementById('propertyPurchaseValue').value == ''))
				  	{
				  		
				  		document.getElementById('propertyPurchaseValueError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('propertyPurchaseValueError').style.display='block';
				  	     canSubmit = false;
				  	}
					if(companyShares.value.match('^\\d+$') ||(document.getElementById('companyShares').value == ''))
				  	{
				  		
				  		document.getElementById('companySharesError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('companySharesError').style.display='block';
				  	     canSubmit = false;
				  	}
					
					if(profit.value.match('^\\d+$') ||(document.getElementById('profit').value == ''))
				  	{
				  		
				  		document.getElementById('profitError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('profitError').style.display='block';
				  	     canSubmit = false;
				  	}
					
				
					if(propertyCurrValue.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || propertyCurrValue.value.match(/^-?[0-9]+$/) ||(document.getElementById('propertyCurrValue').value == ''))
				  	{
				  		
				  		document.getElementById('propertyCurrValueError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('propertyCurrValueError').style.display='block';
				  	     canSubmit = false;
				  	}
					
					
					
					
					if(vPurchaseValue.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || vPurchaseValue.value.match(/^-?[0-9]+$/) ||(document.getElementById('vPurchaseValue').value == ''))
				  	{
				  		
				  		document.getElementById('vPurchaseValueError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('vPurchaseValueError').style.display='block';
				  	     canSubmit = false;
				  	}
					
					
					
					if(VcurrValue.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || VcurrValue.value.match(/^-?[0-9]+$/) ||(document.getElementById('VcurrValue').value == ''))
				  	{
				  		
				  		document.getElementById('VcurrValueError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('VcurrValueError').style.display='block';
				  	     canSubmit = false;
				  	}
					
					if(fDInterestRate.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || fDInterestRate.value.match(/^-?[0-9]+$/) ||(document.getElementById('fDInterestRate').value == ''))
				  	{
				  		
				  		document.getElementById('fDInterestRateError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('fDInterestRateError').style.display='block';
				  	     canSubmit = false;
				  	}	
					
					if(valueOfPolicy.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || valueOfPolicy.value.match(/^-?[0-9]+$/) ||(document.getElementById('valueOfPolicy').value == ''))
				  	{
				  		
				  		document.getElementById('valueOfPolicyError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('valueOfPolicyError').style.display='block';
				  	     canSubmit = false;
				  	}	
					
					if(sharesExisPrice.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || sharesExisPrice.value.match(/^-?[0-9]+$/) ||(document.getElementById('sharesExisPrice').value == ''))
				  	{
				  		
				  		document.getElementById('sharesExisPriceError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('sharesExisPriceError').style.display='block';
				  	     canSubmit = false;
				  	}	
				
					if(machineryPurchaseValue.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || machineryPurchaseValue.value.match(/^-?[0-9]+$/) ||(document.getElementById('machineryPurchaseValue').value == ''))
				  	{
				  		
				  		document.getElementById('machineryPurchaseValueError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('machineryPurchaseValueError').style.display='block';
				  	     canSubmit = false;
				  	}	
					
					if(machineryMarketValue.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || machineryMarketValue.value.match(/^-?[0-9]+$/) ||(document.getElementById('machineryMarketValue').value == ''))
				  	{
				  		
				  		document.getElementById('machineryMarketValueError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('machineryMarketValueError').style.display='block';
				  	     canSubmit = false;
				  	}	
					if(turnOver.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || turnOver.value.match(/^-?[0-9]+$/) ||(document.getElementById('turnOver').value == ''))
				  	{
				  		
				  		document.getElementById('turnOverError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('turnOverError').style.display='block';
				  	     canSubmit = false;
				  	}
				
				if(canSubmit == false){
						return false;
					}
					
					
				}
			
				
		</script>	
<style>
.bankemp_footer.col-sm-12 {
    margin-top: 30px;
}
input[type="checkbox"] {
    display:inline-block;
    width:16px;
    height:16px;
    margin:-1px 4px 0 0;
    vertical-align:middle;
    cursor:pointer;
}
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
h4 {
    margin-left: 28px;
        font-size: 1.2em;
    color: #52829E;
    font-family: inherit;
}
.option-header{
	    color: #FFFFFF;
    background: #083863;
    font-size: 1.1em;
}

</style>

	<script>

      $(function() {
	  $( "#datepicker" ).datepicker({format:'dd/mm/yyyy'});
	  $( "#datepicker1" ).datepicker({format:'dd/mm/yyyy'});
	  $( "#datepicker2" ).datepicker({format:'dd/mm/yyyy'});
	  $( "#datepicker3" ).datepicker({format:'dd/mm/yyyy'});
	  $( "#datepicker4" ).datepicker({format:'dd/mm/yyyy'});
	  $( "#datepicker5" ).datepicker({format:'dd/mm/yyyy'});
	   });
	

    
	   </script>
	   