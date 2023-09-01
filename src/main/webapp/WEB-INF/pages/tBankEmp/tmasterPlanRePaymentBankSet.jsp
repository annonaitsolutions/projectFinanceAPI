<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">	
<div class="col-sm-12 col-md-12">
<div class="heading"><h3 align="center"><spring:message code="label.masterPlan"/></h3></div>
</div>
<form:form action="#" commandName="trepaymentForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-6">
								<table class="theader">
						
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customer"/>:</b></td>
										<td class="col-sm-7"><form:input path="customer" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-7"><form:input path="masterKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.buyCostAmt"/>:</b></td>
										<td class="col-sm-7"><form:input path="buyingCostSanc" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.sancInt"/>:</b></td>
										<td class="col-sm-7"><form:input path="rateOfInt1" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.sancDate"/>:</b></td>
										<fmt:formatDate var='buyingCostDate' value='${repaymentForm.buyingCostDate}' pattern="dd/MM/yyyy" />
										<td class="col-sm-7"><form:input path="buyingCostDate" value="${buyingCostDate}" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.tenure"/>:</b></td>
										<td class="col-sm-7"><form:input path="tenure" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.finalAmount"/>:</b></td>
										<td class="col-sm-7"><form:input path="funalAmt" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
																		
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.paymentOption"/>:</b></td>
										<td class="col-sm-7"><form:input path="payOption" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									</table>
						</div>
						<div class="col-sm-12 col-md-6 link">
							<p><a href="tmasterPlanRePaymentQuater?id=${trepaymentForm.id}"><spring:message code="label.oneTimePayment"/></a></p>
							<p><a href="tmasterPlanRePaymentFull?id=${trepaymentForm.id}"><spring:message code="label.quaterlyPayment"/></a></p>
							<p><a href="tmasterPlanRePaymentHalf?id=${trepaymentForm.id}"><spring:message code="label.halfYearlyPayment"/></a></p>
						</div>
									<form:hidden path="id"  />
										<form:hidden path="customerEmail"  />
									<form:hidden path="transactionId" />
							<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>							
							<td><a href="tmasterPlanRePaymentBank" class="btn btn-success"><spring:message code="label.back"/></a></td>
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

