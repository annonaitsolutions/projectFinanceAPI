<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<script>
		function val(){
		
			var amount  = document.getElementById('amount');
			var number='^\\d+$';
			var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
			var number1= /^-?[0-9]+$/;
			var canSubmit = true; 
			
			
		 
	    	if(amount.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || amount.value.match(/^-?[0-9]+$/) &&!(document.getElementById('amount').value == ''))
		  	{
		  		
		  		document.getElementById('amountError').style.display='none';
		  	
		  	}
		  	else{
		  		document.getElementById('amountError').style.display='block';
		  	     canSubmit = false;
		  	} 

			if(canSubmit == false){
				return false;
			}
		}
			
				
		</script>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.requestForFunds"/></h3>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
			 <div  class="sucess"><b><font color="green">${success}</font></b></div>
	</div>
<form:form action="branchFundsTransfer1Confirm"  commandName="fundsDistributeForm" onsubmit="return val();">


								<table align="center">
								<form:hidden path="id"  />
								<form:hidden path="custHeadEmail"  />
								<form:hidden path="custHeadMngEmail"  />
								<form:hidden path="email"  />
								<form:hidden path="managerEmail"  />
								<form:hidden path="balance"  />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerName" readonly="true"></form:input></td>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerHeadName" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true"  id="companyName"></form:input></td>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.distAmt"/>:</b></td>
										<td class="col-sm-6"><form:input path="distributedAmount" readonly="true"  id="companyName"></form:input></td>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.utilizedAmount"/>:</b></td>
										<td class="col-sm-6"><form:input path="utilizedAmount" readonly="true"  id="companyName"></form:input></td>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.availableAmount"/>:</b></td>
										<td class="col-sm-6"><form:input path="busBalance" readonly="true"  id="companyName"></form:input></td>
									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amount"/>:</b>
										<td id="amountError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="amountError" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
										<td class="col-sm-6"><form:input path="amount"  placeholder="Enter Amount" id="amount"></form:input></td>
									
									</tr>
									
									</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="branchFundsTransfer" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
								


</form:form>

</div>
