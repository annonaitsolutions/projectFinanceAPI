<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript">
	function validateForm() {
		var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.customerHeadSave.transactionId.value = uuid;
	}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">	
		<h3><spring:message code="label.repay"/></h3>
	</div>
<form:form action="tmasterPlanRePaymentSave" name="customerHeadSave" commandName="trepaymentForm" onsubmit="return validateForm();">

			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b> <spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customer" placeholder="Enter name" readonly="true"></form:input></td>
																								
									</tr>
											<form:hidden path="customerEmail"  />
									<form:hidden path="transactionId" value="0" />
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.buyCostAmt"/>:</b></td>
										<td class="col-sm-6"><form:input path="buyingCostSanc" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.sancInt"/>:</b></td>
										<td class="col-sm-6"><form:input path="rateOfInt1" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.sancDate"/>:</b></td>
										<fmt:formatDate var='buyingCostDate' value='${repaymentForm.buyingCostDate}' pattern="dd/MM/yyyy" />
										<td class="col-sm-6"><form:input path="buyingCostDate" value="${buyingCostDate}" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
									</tr>
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.tenure"/>:</b></td>
										<td class="col-sm-6"><form:input path="tenure" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.finalAmount"/>:</b></td>
										<td class="col-sm-6"><form:input path="funalAmt" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
									</tr>
									
																						
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.paymentOption"/>:</b></td>
										<td class="col-sm-6"><form:input path="payOption" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
									</tr>
									
									
									</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="tmasterPlanRePayment" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>

</form:form>


</div>
