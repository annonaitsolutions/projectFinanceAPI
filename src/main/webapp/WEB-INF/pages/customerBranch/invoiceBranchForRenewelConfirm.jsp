<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		<div class="col-md-12 col-sm-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.invoice"/></h3>
		</div>
<form:form action="invoiceBranchForRenewelPost" commandName="invoiceForm" onsubmit="return val();">

			
								<table align="center">
								<form:hidden path="id"  />
									<form:hidden path="customerHeadEmail"  />
									<form:hidden path="customerBranchEmail"  />
								    <form:hidden path="buyerEmail"  />
								     <form:hidden path="transactionId"  />
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerName"  readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerHeadName" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.buyerName"/>:</b></td>
										<td class="col-sm-7"><form:input path="buyerName" readonly="true"  id="companyName"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-7"><form:input path="masterKey" readonly="true"  id="companyName"></form:input>
									</tr>	
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.goods"/>:</b></td>
										<td class="col-sm-7"><form:input path="goods" readonly="true"  id="companyName"></form:input>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.quantity"/>:</b></td>
										<td class="col-sm-7"><form:input path="quantity" readonly="true"  id="companyName"></form:input>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.amount"/>:</b></td>
										<td class="col-sm-7"><form:input path="amount" readonly="true"  id="companyName"></form:input>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.insType"/>:</b></td>
										<td class="col-sm-7"><form:input path="insuranceType"  readonly="true" id="insuranceType" placeholder="Enter Insurance Type"></form:input>
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.insAmt"/>:</b></td>
										<td class="col-sm-7"><form:input path="insuranceAmount"  readonly="true" id="insuranceAmount" placeholder="Enter Insurance Amount"></form:input>
										
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.insDetails"/>:</b></td>
										<td class="col-sm-7"><form:textarea path="insuranceDetails"  readonly="true" id="insuranceDetails" placeholder="Enter Insurance Details"></form:textarea>
										
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.startDate"/>:</b></td>
										<td class="col-sm-7"><form:input path="startDate"  readonly="true" id="insuranceDetails" placeholder="Enter Insurance Details"></form:input>
										
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.endDate"/>:</b></td>
										<td class="col-sm-7"><form:input path="endDate"  readonly="true" id="insuranceDetails" placeholder="Enter Insurance Details"></form:input>
										
										
									</tr>
									
								
					
						
									</table>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="invoiceBranchListForRenewel" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									
								


</form:form>
</div>
	

