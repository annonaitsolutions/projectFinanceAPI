<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
	<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
</div>
<form:form name="myForm" action="masterPlanRePaymentQuarterSave" commandName="fullAmountForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b> <spring:message code="label.customerName"/></b></td>
										<td class="col-sm-6"><form:input path="customer" placeholder="Enter name" readonly="true"></form:input></td>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountType"/></b></td>
										<td class="col-sm-6"><form:input path="amtType" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
									</tr>														
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.paymentOption"/></b></td>
										<td class="col-sm-6"><form:input path="payOption" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.loanDate"/></b></td>
										<td class="col-sm-6"><form:input path="loanDate" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
									</tr>
<%--										<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.amountToPay"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="amount" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input></td>--%>
<%--											--%>
<%--									</tr>--%>
									
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="intRate" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input></td>--%>
<%--											--%>
<%--									</tr>--%>
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.noOfDays"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="payDate" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input></td>--%>
<%--											--%>
<%--									</tr>--%>
									<tr>
										 <td class="col-sm-6"><b><spring:message code="label.totalAmount"/></b></td>
										 <td class="col-md-6"><form:input path="totalAmount"  readonly="true"/></td>
									 </tr>
						         
						       
									</table>
						</div>
						
									<form:hidden path="id"  />
										<form:hidden path="customerEmail"  />
									<form:hidden path="transactionId" />
						<div class="col-sm-12 col-md-12 col-lg-12">
								<p align="center" style="padding-top: 10px;">
								<input type="submit" class="btn btn-primary" value="<spring:message code="label.save"/>">
					
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

