<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.repayment"/></h3>
	</div>
<form:form action="#" commandName="repaymentForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-6">
								<table class="theader">
						
									<tr>
										<td class="col-sm-5"><b> <spring:message code="label.customerName"/></b></td>
										<td class="col-sm-7"><form:input path="customer"  readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.masterKey"/></b></td>
										<td class="col-sm-7"><form:input path="masterKey" readonly="true" id="customerPrefix"></form:input>
											
									</tr>

									<tr >
										<td class="col-sm-5"><b><spring:message code="label.amountType"/></b></td>
										<td class="col-sm-7"><form:input path="amtType" readonly="true"  id="amtType"></form:input>

									</tr>

									<tr class="buyingCost">
										<td class="col-sm-5"><b><spring:message code="label.buyingCostSanctionedAmount"/></b></td>
										<td class="col-sm-7"><form:input path="buyingCostSanc" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									<tr class="buyingCost">
										<td class="col-sm-5"><b><spring:message code="label.sanctionedInterest"/></b></td>
										<td class="col-sm-7"><form:input path="rateOfInt1" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									<tr class="buyingCost">
										<td class="col-sm-5"><b><spring:message code="label.sanctionedDate"/></b></td>
										<fmt:formatDate var='buyingCostDate' value='${repaymentForm.buyingCostDate}' pattern="dd/MM/yyyy" />
										<td class="col-sm-7"><form:input path="buyingCostDate" value="${buyingCostDate}" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									
									
									<tr class="buyingCost">
										<td class="col-sm-5"><b><spring:message code="label.tenureindays"/></b></td>
										<td class="col-sm-7"><form:input path="tenure" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
<%--									<tr>--%>
<%--										<td class="col-sm-5"><b><spring:message code="label.finalAmount"/></b></td>--%>
<%--										<td class="col-sm-7"><form:input path="funalAmt" readonly="true"  id="customerPrefix"></form:input>--%>
<%--											--%>
<%--									</tr>--%>
									<tr class="buyingCost">
										<td class="col-sm-5"><b><spring:message code="label.otherCharges"/></b></td>
										<td class="col-sm-7"><form:input path="otherCharges" readonly="true"  id="otherCharges"></form:input>

									</tr>
									<tr class="workingCapital">
										<td class="col-sm-5"><b><spring:message code="label.workingCapitalAmount"/></b></td>
										<td class="col-sm-7"><form:input path="wcSancAmount" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									
									<tr class="workingCapital">
										<td class="col-sm-5"><b><spring:message code="label.interest"/></b></td>
										<td class="col-sm-7"><form:input path="wcTotalInterest" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>

									<tr class="workingCapital">
										<td class="col-sm-5"><b><spring:message code="label.tenure"/>:</b></td>
										<td class="col-sm-7"><form:input path="wcTenure" readonly="true"
																		 id="wcTenure"></form:input></td>

									</tr>
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.paymentOption"/></b></td>
										<td class="col-sm-7"><form:input path="payOption" readonly="true"  id="paymentOption"></form:input>
											
									</tr>

									<tr>
										<td class="col-sm-5"><b><spring:message code="label.totalAmount"/></b></td>
										<td class="col-sm-7"><form:input path="totalAmt" readonly="true"  id="totalAmount"></form:input>

									</tr>

									</table>
						</div>
<%--						<div class="col-sm-12 col-md-6 link">--%>
<%--							<p><a href="masterPlanRePaymentQuater?id=${repaymentForm.id}"><spring:message code="label.fullAmount"/></a></p>--%>
<%--							<p><a href="masterPlanRePaymentFull?id=${repaymentForm.id}"><spring:message code="label.quarterlyPayment"/></a></p>--%>
<%--							<p><a href="masterPlanRePaymentHalf?id=${repaymentForm.id}"><spring:message code="label.halfYearlyPayment"/></a></p>--%>
<%--						</div>--%>
									<form:hidden path="id"  />
										<form:hidden path="customerEmail"  />
									<form:hidden path="transactionId" />
							<div class="col-sm-12 col-md-12 col-lg-12">
								<p align="center" style="padding-top: 10px;">

								<a href="" class="btn btn-primary" id="proceed"><spring:message code="label.proceed"/></a>
								<a href="bankEmp" class="btn btn-success"><spring:message code="label.back"/></a>
								</p>
		</div>
									
	</div>

</form:form>
	</div>
<style>
.link p a{
color: tomato;
}
</style>

<script>
	$(document).ready(function () {
		var amtType = document.getElementById('amtType').value;

		if (amtType == 'Buying Cost') {
			$(".buyingCost").show();
			$(".workingCapital").hide();

		} else if (amtType == 'Working Capital') {
			$(".workingCapital").show();
			$(".buyingCost").hide();

		}

		var paymentOption = document.getElementById('paymentOption').value;

		if(paymentOption == 'Quarterly'){
			$("#proceed").attr("href", "masterPlanRePaymentFull?id=${repaymentForm.id}");
		}
		if(paymentOption == 'HalfYearly'){
			$("#proceed").attr("href", "masterPlanRePaymentHalf?id=${repaymentForm.id}");
		}
		if(paymentOption == 'full'){
			$("#proceed").attr("href", "masterPlanRePaymentQuater?id=${repaymentForm.id}");
		}

	});
</script>

