<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 body_fixed">	
<div class="col-sm-12 col-md-12 header_customer">
	<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
</div>

<form:form  action="poPaymentHeadSave" name="myForm" commandName="purchaseOrderForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-12">
								<table align="center">
						<form:hidden path="id"  />
										<form:hidden path="customerHeadEmail"  />
										<form:hidden path="customerBranchEmail"  />
										<form:hidden path="supplierEmail"  />
									<form:hidden path="transactionId" />
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerHeadName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.supplierName"/>:</b></td>
										<td class="col-sm-7"><form:input path="supplierName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.poKey"/>:</b></td>
										<td class="col-sm-7"><form:input path="poKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.amount"/>:</b></td>
										<td class="col-sm-7"><form:input path="amount" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>		
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.balance"/>:</b></td>
										<td class="col-sm-7"><form:input path="payBalance" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>												
							<tr>
										<td class="col-sm-5"><b><spring:message code="label.typeOfPayment"/>:</b></td>
										<td class="col-sm-7"><form:input path="typeOfTrans" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									<c:set var="pay" scope="request" value="${model.purchaseOrderForm.typeOfTrans}" />
									
									<c:if test="${pay == 'DD' || pay == 'Cheque'}" > 
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.chequeNum"/>:</b></td>
										<td class="col-sm-7"><form:input path="chequenum" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.signAuth"/>:</b></td>
										<td class="col-sm-7"><form:input path="signAuth" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.amtLimit"/>:</b></td>
										<td class="col-sm-7"><form:input path="amtLimit" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									 </c:if>
									</table>
									</div>
						
									
							<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="poPaymentHeadList" class="btn btn-success"><spring:message code="label.back"/></a></td>
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

