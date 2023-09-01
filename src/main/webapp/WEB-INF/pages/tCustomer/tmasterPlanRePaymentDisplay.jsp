<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.repay"/></h3>
	</div>
<form:form action="tmasterPlanRePaymentDisplayConfirm" commandName="trepaymentForm" onsubmit="return val();">

			<div class="content">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b> <spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customer"  readonly="true"></form:input></td>
																								
									</tr>
											<form:hidden path="customerEmail"  />
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.buyCostAmt"/>:</b></td>
										<td class="col-sm-6"><form:input path="buyingCostSanc" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.sancInt"/>:</b></td>
										<td class="col-sm-6"><form:input path="rateOfInt1" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountPaid"/>:</b></td>
										<td class="col-sm-6"><form:input path="amountPaid" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.sancDate"/>:</b></td>
										<fmt:formatDate var='buyingCostDate' value='${repaymentForm.buyingCostDate}' pattern="dd/MM/yyyy" />
										<td class="col-sm-6"><form:input path="buyingCostDate" value="${buyingCostDate}" readonly="true"  id="customerPrefix"></form:input>
										</td>	
									</tr>
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.tenure"/>:</b></td>
										<td class="col-sm-6"><form:input path="tenure" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.finalAmount"/>:</b></td>
										<td class="col-sm-6"><form:input path="funalAmt" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
								
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.paymentOption"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="payOption" id="payOption">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="full"><spring:message code="label.oneTimePayment"/></form:option>
												<form:option value="Quarterly"><spring:message code="label.quaterlyPayment"/></form:option>
												<form:option value="HalfYearly"><spring:message code="label.halfYearlyPayment"/></form:option>
												</form:select></td>
										    <td id="payOptionError" class="error" style="display:none;"><font color="red"><spring:message code="label.plzSelectValue"/></font></td>
										    <td id="payOptionError" class="error" style="display:none"><spring:message code="label.plzSelectValue"/></td>
																								
									</tr>
									
									
									</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="tmasterPlanRePayment" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>

</form:form>
</div>
<script>
			
				 
				function val(){
					
					var payOption  = document.getElementById('payOption');
				     var canSubmit = true; 
					
					if (document.getElementById('payOption').value == ''){
						document.getElementById('payOptionError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('payOptionError').style.display='none';
					}
					
				

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>


