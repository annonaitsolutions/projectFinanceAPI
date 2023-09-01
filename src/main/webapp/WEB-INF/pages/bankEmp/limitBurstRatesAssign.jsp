<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.limitBurstRateAssignment"/></h3>
		</div>
<form:form action="limitBurstRatesAssignConfirm" method="post" name="myForm" commandName="limitBurstForm" onsubmit="return val();">

			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
									
									<form:hidden path="id"  />
									<form:hidden path="transactionId"  />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amount"/></b></td>
										<td class="col-sm-6"><form:input path="reqAmt" id="reqAmt" placeholder="Enter cost" readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.tenure"/></b></td>
										<td class="col-sm-6"><form:input path="tenure" readonly="true" placeholder="Enter tenure"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/></b></td>
										<td class="col-sm-6"><form:input path="intRate" value="0" placeholder="Enter Interest" id="interestCharge"></form:input>
									<div id="interestChargeError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
											
									</tr>
									<tr>
										<td class="col-sm-6">
										<input type="button" class="btn btn-success"  value="<spring:message code="label.calculateInterestAmount"/>" onClick="amt1()"/></td>
						         		<td class="col-md-6"><form:input path="totalRate" value="0" readonly="true"/></td>
						        	</tr>
						        	<tr>
						        		 <td class="col-sm-6">
						         
								<input type="button" class="btn btn-success" value="<spring:message code="label.calculateTotalAmount"/>" onClick="totalamt1()"/></td>
								<td class="col-md-6"><form:input path="finalAmt" value="0"  readonly="true"></form:input></td>
							</tr>
				
							</table>
								<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" size="3" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="setLimitBurstRatesList" class="btn btn-success"><spring:message code="label.back"/></a></td>
							
					
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>

		
 </div>
 <style>
input.btn.btn-success {
    width: 96%;
}
</style>
<script>
function val(){
	
	var interestCharge = document.getElementById('interestCharge');
	var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
	
	
	var canSubmit = true;

  if(interestCharge.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || interestCharge.value.match(/^-?[0-9]+$/) &&!(document.getElementById('interestCharge').value == ''))
	  	{
	  		
	  		document.getElementById('interestChargeError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('interestChargeError').style.display='block';
	  	     canSubmit = false;
	  	}
		
		if(canSubmit == false){
		return false;
	}
}
</script>