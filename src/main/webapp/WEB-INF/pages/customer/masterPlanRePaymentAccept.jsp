<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


 <script>
function val(){
					
var accept  = document.getElementById('accept');
				
					var canSubmit = true; 
					
					if (document.getElementById('accept').value == ''){
						document.getElementById('acceptError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('acceptError').style.display='none';
					}
					
					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
		<div class="col-sm-9 col-md-9 body_fixed">	
		<div class="col-sm-12 col-md-12 header_customer">
			<h3 align="center"><spring:message code="label.approval"/></h3>
		</div>
<form:form  action="masterPlanRePaymentAcceptConfirm" name="myForm" commandName="repaymentForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b> <spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customer" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amtType"/>:</b></td>
										<td class="col-sm-6"><form:input path="amtType" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>														
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.paymentOption"/>:</b></td>
										<td class="col-sm-6"><form:input path="payOption" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.acc/rej"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="accept" id="accept" style="width:205px;">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="Yes"><spring:message code="label.yes"/></form:option>
												<form:option value="No"><spring:message code="label.no"/></form:option>
												</form:select>
							<div id="acceptError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
							<div id="acceptError" class="error" style="display:none"><spring:message code="label.validation"/></div>
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
							<td><a href="masterPlanRePaymentAcceptList" class="btn btn-success"><spring:message code="label.back"/></a></td>
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

