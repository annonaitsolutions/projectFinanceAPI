<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">

function validateForm() {
	
	var countryName = document.getElementById('reqAmt');
	var countryName = document.getElementById('tenure');
	var number='^\\d+$';
	var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
	var number1= /^-?[0-9]+$/;
	var canSubmit = true;

   if(reqAmt.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || reqAmt.value.match(/^-?[0-9]+$/) &&!(document.getElementById('reqAmt').value == ''))
	  	{
	  		
	  		document.getElementById('reqAmtError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('reqAmtError').style.display='block';
	  	     canSubmit = false;
	  	}
	if(tenure.value.match('^\\d+$') &&!(document.getElementById('tenure').value == ''))
  	{
  		
  		document.getElementById('tenureError').style.display='none';
  	
  	}
  	else{
  		document.getElementById('tenureError').style.display='block';
  	     canSubmit = false;
  	}
	if(canSubmit == false){
		return false;
	}
	
	
}
$(document).ready(function() {
	$(':input','#newBuyerForm')
	  .not(':button, :submit, :reset, :hidden')
	  .val('')  
});
</script>


<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.limitBurst"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
			</div>		
			<form:form action="poApplyLimitConfirm" name="regulation" commandName="limitBurstForm" onsubmit="return validateForm()">
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
									<tr>
									<form:hidden path="id"/>
									<form:hidden path="customerEmail"/>
									<form:hidden path="transactionId"/>
								
					  <td class="heading_text"><b><spring:message code="label.customerName"/></b></td>
					<td>
					<form:input path="customerHeadName" id="customerHeadName" readonly="true" />
					</td>
				    </tr>
				    <tr>
				     <td class="heading_text"><b><spring:message code="label.supplierName"/></b></td>
					<td>
					<form:input path="supplierName" id="supplierName" readonly="true" />
					</td>
				    </tr>
				    <tr>
					  <td class="heading_text"><b><spring:message code="label.masterKey"/></b></td>
					<td>
					<form:input path="masterKey" id="masterKey" readonly="true" />
					</td>
				    </tr>
				     <tr>
					  <td class="heading_text"><b><spring:message code="label.poKey"/></b></td>
					<td>
					<form:input path="poKey" id="poKey" readonly="true" />
					</td>
				    </tr>
					
						
											<tr>
					  <td class="heading_text"><b><spring:message code="label.amount"/></b></td>
					<td>
					<form:input path="reqAmt" id="reqAmt" placeholder="Enter Required Amount"/>
					<td id="reqAmtError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
				    <td id="reqAmtError" class="error" style="display:none"><spring:message code="label.validation"/></td>
						
				    </tr>
				    						<tr>
					  <td class="heading_text"><b><spring:message code="label.tenure"/></b></td>
					<td>
					<form:input path="tenure" id="tenure" placeholder="Enter Tenure"/>
					<td id="tenureError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					    <td id="tenureError" class="error" style="display:none"><spring:message code="label.validation"/></td>
						
				    </tr>
					</table>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="masterPlanLimitBurstList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
			</form:form>
	</div>