<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">	
		<h3><spring:message code="label.confirmScreen"/></h3>
	</div>
<form:form action="masterPlanRePaymentAppApproveSave" commandName="repaymentForm" onsubmit="return val();">

			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b> <spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customer" placeholder="Enter name" readonly="true"></form:input></td>
																								
									</tr>
											<form:hidden path="id"  />
									<form:hidden path="transactionId" />
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
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
										<td class="col-sm-6"><form:input path="payOption" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.status"/>:</b></td>
										<td class="col-sm-6"><form:input path="cStatus" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.comment"/>:</b></td>
										<td class="col-sm-6"><form:input path="cComment" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									
									
										
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
