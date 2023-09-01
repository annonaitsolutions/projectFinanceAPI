<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

				
<div class="col-sm-6 col-md-9 col-lg-9 body_fixed">
				
				<div class="col-sm-12 col-md-12 col-lg-12">
					
					<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
				</div>
				
				<form:form action="addOrModifyReportInvoiceBranchConfirm" method="post" commandName="disputeReportsForm" enctype="multipart/form-data"  name="interest" onsubmit="return val();">
				
				<form:hidden path ="transactionId" />
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3><spring:message code="label.reports"/></h3>
				</div>
				<table align="center">
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.customerName"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="customerName" readonly="true" class="form-control" placeholder=""  id="customer" onclick="same();"/></td>
					</tr>
					
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.buyerName"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="buyerName" readonly="true" class="form-control" placeholder="Enter Email"  id="customerEmail"/>
						</td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.invoiceKey"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="invoicekey" readonly="true" class="form-control" placeholder="Enter PO Key"  id="purchaseOrderKey"/>
							
						</td>
					</tr>	
				
				</table>
			</div>		
			<div class="col-sm-12 col-md-12 col-lg-12">	
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3><spring:message code="label.goodsInfo"/></h3>
				</div>	
				<table align="center">				
				
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.goodsInfo"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="goodsDetails" readonly="true"  class="form-control" placeholder="Enter Goods Info"  id="goodsInfo"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.goods"/></b></td>
						<td class="col-sm-6">
							<form:input path="goods" readonly="true" class="form-control" placeholder="Enter Total Cost Of Goods"  id="totalCost"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.cost"/></b></td>
						<td class="col-sm-6">
							<form:input path="cost" readonly="true" class="form-control" placeholder="Enter Goods Quantity"  id="quantity"/>
						</td>
					</tr>	
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.insDetails"/></b></td>
						<td class="col-sm-6">
							<form:input path="insDetails" readonly="true" class="form-control" placeholder="Enter Goods Quantity"  id="quantity"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.startDate"/></b></td>
						<td class="col-sm-6">
							<form:input path="inStartDate" readonly="true" class="form-control" id="insStartDate"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.endDate"/></b></td>
						<td class="col-sm-6">
							<form:input path="insEndDate" readonly="true" class="form-control" placeholder="Enter Goods Quantity"  id="quantity"/>
						</td>
					</tr>
				</table>
			</div>				
			<div class="col-sm-12 col-md-12 col-lg-12">
						<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3><spring:message code="label.transInfo"/></h3>
						</div>
				<table align="center">
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.transType"/></b></td>
						<td class="col-sm-6">
							<form:select path="goods"  class="form-control" placeholder=""  id="goods" style="width:205px;">
							<form:option value=""><spring:message code="label.selectValue"/></form:option>
								    <form:option value="Single Mode"><spring:message code="label.singMode"/></form:option>
                                 <form:option value="Multi Mode"><spring:message code="label.multiMode"/></form:option>
								</form:select>
						</td>
					</tr>	
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.transMode"/></b></td>
						<td class="col-sm-6">
							<form:input path="modeOfTransport"  class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.termsAndCon"/></b></td>
						<td class="col-sm-6">
							<form:textarea path="termsCond" class="form-control"   id="supplierEmail"/>
						
						</td>
					</tr>
					<%-- 	<tr>	
						<td class="col-sm-6"><b><spring:message code="label.inclusion"/></b></td>
						<td class="col-sm-6">
							<form:input path="inclusion"  class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.exclusion"/></b></td>
						<td class="col-sm-6">
							<form:input path="exclusion"  class="form-control"  id="supplierName"/>
							
						</td>
					</tr> --%>
				</table>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
						<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3><spring:message code="label.surInfo"/></h3>
						</div>
				<table align="center">
						
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorName"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppSurveyorName"  class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
					
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorCom"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppSurveyorCom"  class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorAdd"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppSurveyorAdd"  class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorEmail"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppSurveyorEmail"  class="form-control"  id="surveyorEmail"/>
							<div id="surveyorEmailError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorPhone"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppSurveyorPhone"  class="form-control"  id="surveyorPhone"/>
							<div id="surveyorPhoneError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
						</td>
					</tr>
				</table>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
						<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3><spring:message code="label.damageInfo"/></h3>
						</div>
				<table align="center">
						
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.defectType"/></b></td>
						<td class="col-sm-6">
							<form:select path="SuppDefectType"  class="form-control" placeholder=""  id="goods" style="width:205px;">
							<form:option value=""><spring:message code="label.selectValue"/></form:option>
								    <form:option value="Missing"><spring:message code="label.missingGoods"/></form:option>
                                 <form:option value="Damaged"><spring:message code="label.damagedGoods"/></form:option>
                                 <form:option value="MisMatch"><spring:message code="label.mismatchGoods"/></form:option>
								</form:select>
						</td>
					</tr>	
					
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.defectQty"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppDefectType"  class="form-control"  id="defectQty"/>
							<div id="defectQtyError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.defectCostForGoods"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppDefectCostForGoods"  class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.damageStatus"/></b></td>
						<td class="col-sm-6">
							<form:select path="suppDamageStatus"  class="form-control" placeholder=""  id="goods" style="width:205px;">
							<form:option value=""><spring:message code="label.selectValue"/></form:option>
								    <form:option value="Partial"><spring:message code="label.partialDam"/></form:option>
                                 <form:option value="Severe"><spring:message code="label.severeDam"/></form:option>
								</form:select>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.replacement"/></b></td>
						<td class="col-sm-6">
							<form:select path="suppReplacement"  class="form-control" placeholder=""  id="goods" style="width:205px;">
							<form:option value=""><spring:message code="label.selectValue"/></form:option>
								    <form:option value="Partial"><spring:message code="label.yes"/></form:option>
                                 <form:option value="Severe"><spring:message code="label.no"/></form:option>
								</form:select>
						</td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.repairCost"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppRepairCost"  class="form-control"  id="repairCost"/>
							<div id="repairCostError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
						</td>
					</tr>
				</table>
		</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="addOrModifyReportCustBranchList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
				
				</form:form>
				
				
				
				
	</div>
			
	<script>
	function val(){
		var repairCost  = document.getElementById('repairCost').value;
		var defectQty  = document.getElementById('defectQty').value;
		var number='^\\d+$';
		var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
		var phoneNum       = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 
		var reg             = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		
		var surveyorPhone  = document.getElementById('surveyorPhone').value;
		var surveyorEmail  = document.getElementById('surveyorEmail').value;
		var number1= /^-?[0-9]+$/;

		var canSubmit = true; 
		
		
		if(surveyorPhone.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/)  || (surveyorPhone == ''))
	  	{
	  		
	  		document.getElementById('surveyorPhoneError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('surveyorPhoneError').style.display='block';
	  	     canSubmit = false;
	  	}

	 	
	 	
	 	
	 	if(surveyorEmail.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/)  || (surveyorEmail == ''))
	  	{
	  		
	  		document.getElementById('surveyorEmailError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('surveyorEmailError').style.display='block';
	  	     canSubmit = false;
	  	}
	
	if(repairCost.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || repairCost.match(/^-?[0-9]+$/)  && !(repairCost == ''))
  	{
  		
  		document.getElementById('repairCostError').style.display='none';
  	
  	}
  	else{
  		document.getElementById('repairCostError').style.display='block';
  	     canSubmit = false;
  	}	
	if(defectQty.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || defectQty.match(/^-?[0-9]+$/) && !(defectQty == ''))
  	{
  		
  		document.getElementById('defectQtyError').style.display='none';
  	
  	}
  	else{
  		document.getElementById('defectQtyError').style.display='block';
  	     canSubmit = false;
  	}
	
	if(canSubmit == false){
		return false;
	}
}


	
</script>					
							
				