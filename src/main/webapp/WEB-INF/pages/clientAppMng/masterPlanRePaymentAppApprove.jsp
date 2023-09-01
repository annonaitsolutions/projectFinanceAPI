<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
		
			
				 
				function val(){
					
					var userName  = document.getElementById('status');
					var userName  = document.getElementById('comment');
					

					var canSubmit = true; 
					
					if (document.getElementById('status').value == ''){
						document.getElementById('statusError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('statusError').style.display='none';
					}
					if (document.getElementById('comment').value == ''){
						document.getElementById('commentError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('commentError').style.display='none';
					}
				

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">	
		<h3><spring:message code="label.repay"/></h3>
	</div>
<form:form action="masterPlanRePaymentAppApproveConfirm" commandName="repaymentForm" onsubmit="return val();">

			<div class="content">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customer"  readonly="true"></form:input></td>
																								
									</tr>
											<form:hidden path="customerEmail"  />
									<form:hidden path="transactionId" />
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>

									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountType"/>:</b></td>
										<td class="col-sm-6"><form:input path="amtType" readonly="true" id="amtType"></form:input></td>

									</tr>

									<tr class="buyingCost">
										<td class="col-sm-6"><b><spring:message code="label.buyCostAmt"/>:</b></td>
										<td class="col-sm-6"><form:input path="buyingCostSanc" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									<tr class="buyingCost">
										<td class="col-sm-6"><b><spring:message code="label.sancInt"/>:</b></td>
										<td class="col-sm-6"><form:input path="rateOfInt1" readonly="true" id="customerPrefix"></form:input></td>
											
									</tr>
									<tr class="buyingCost">
										<td class="col-sm-6"><b><spring:message code="label.sancDate"/>:</b></td>
										<fmt:formatDate var='buyingCostDate' value='${repaymentForm.buyingCostDate}' pattern="dd/MM/yyyy" />
										<td class="col-sm-6"><form:input path="buyingCostDate" value="${buyingCostDate}" readonly="true"  id="customerPrefix"></form:input>
											</td>
									</tr>
									
									
									<tr class="buyingCost">
										<td class="col-sm-6"><b><spring:message code="label.tenureindays"/>:</b></td>
										<td class="col-sm-6"><form:input path="tenure" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									<tr class="buyingCost">
										<td class="col-sm-6"><b><spring:message code="label.finalAmount"/>:</b></td>
										<td class="col-sm-6"><form:input path="funalAmt" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									<tr class="workingCapital">
										<td class="col-sm-6"><b><spring:message code="label.wcAmt"/>:</b></td>
										<td class="col-sm-6"><form:input path="wcSancAmount" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									
									<tr class="workingCapital">
										<td class="col-sm-6"><b><spring:message code="label.interest"/>:</b></td>
										<td class="col-sm-6"><form:input path="wcTotalInterest" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>

									<tr class="workingCapital">
										<td class="col-sm-6"><b><spring:message code="label.tenureindays"/>:</b></td>
										<td class="col-sm-6"><form:input path="wcTenure" readonly="true"
																		 id="wcTenure"></form:input></td>

									</tr>

									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.paymentOption"/>:</b></td>
										<td class="col-sm-6"><form:input path="payOption" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.status"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="cStatus" id="status">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="Approved"><spring:message code="label.approve"/></form:option>
												<form:option value="Rejected"><spring:message code="label.reject"/></form:option>
												</form:select>
										<td id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.plzSelectValue"/></font></td>
										    <td id="statusError" class="error" style="display:none"><spring:message code="label.plzSelectValue"/></td>
											
											</td>															
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.comment"/>:</b></td>
										<td class="col-sm-6"><form:textarea path="cComment" id="comment" placeholder="Enter Comment"></form:textarea></td>
										<td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="commentError" class="error" style="display:none"><spring:message code="label.validation"/></td>
												
									</tr>
								
											<form:hidden path="id"  />
									
						
									</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="masterPlanRePaymentAppList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>

</form:form>


</div>



<script>
	$(document).ready(function () {
		var amtType = document.getElementById('amtType').value;


		if (amtType == 'Buying Cost') {
			$(".buyingCost").show();
			$(".workingCapital").hide();

		} else if (amtType == 'Working Capital') {
			$(".workingCapital").show();
			$(".buyingCost").hide();


		} else {
			$(".workingCapital").hide();
			$(".buyingCost").hide();

		}
	});
</script>

