<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script>
function val(){
					
var userName  = document.getElementById('status');

					

					var canSubmit = true; 
					
					if (document.getElementById('status').value == ''){
						document.getElementById('statusError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('statusError').style.display='none';
					}
				
					

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
		<div class="col-sm-9 col-md-9 body_fixed">	
		<div class="col-sm-12 col-md-12 header_customer">
			<h3 align="center"><spring:message code="label.repay"/></h3>
		</div>
<form:form  action="tmasterPlanRePaymentAcceptConfirm" name="myForm" commandName="trepaymentForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customer"/>:</b></td>
										<td class="col-sm-6"><form:input path="customer" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountType"/>:</b></td>
										<td class="col-sm-6"><form:input path="amtType" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>														
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.paymentOption"/>:</b></td>
										<td class="col-sm-6"><form:input path="payOption" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.acc/rej"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="accept" id="status" style="width:205px;">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="Yes"><spring:message code="label.yes"/></form:option>
												<form:option value="No"><spring:message code="label.no"/></form:option>
												</form:select>
										
											</td>	
																									
									</tr>
									</table>
									</div>
						
									<form:hidden path="id"  />
										<form:hidden path="customerEmail"  />
									<form:hidden path="transactionId" />
							<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="tmasterPlanRePaymentAcceptList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									
	</div>

</form:form>
	</div>
<style>
.link p a{
color: tomato;
}
</style>

