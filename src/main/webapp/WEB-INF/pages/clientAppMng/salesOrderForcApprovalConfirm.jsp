<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		<div class="col-md-12 col-sm-12 col-lg-12 header_customer">	
		<h3 align="center"><spring:message code="label.confirmScreen"/></h3>
		</div>
<form:form action="salesOrderForcApproval" commandName="salesOrderForm" onsubmit="return val();">

			<div class="col-md-12 col-sm-12 col-lg-12">
								<table align="center">
								<form:hidden path="id"  />
								<form:hidden path="customerHeadEmail"  />
									<form:hidden path="customerBranchEmail"  />
								    <form:hidden path="buyerEmail"  />
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerHeadName" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.buyerName"/>:</b></td>
										<td class="col-sm-7"><form:input path="buyerName" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-7"><form:input path="masterPlanKey" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
									</tr>	
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.goods"/>:</b></td>
										<td class="col-sm-7"><form:input path="goods" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.quantity"/>:</b></td>
										<td class="col-sm-7"><form:input path="quantity" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.amount"/>:</b></td>
										<td class="col-sm-7"><form:input path="amount" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.insType"/>:</b></td>
										<td class="col-sm-7"><form:input path="insuranceType" id="insuranceType" readonly="true" placeholder="Enter Insurance Type"></form:input>
										
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.insAmt"/>:</b></td>
										<td class="col-sm-7"><form:input path="insuranceAmount" id="insuranceAmount" readonly="true" placeholder="Enter Insurance Amount"></form:input>
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.insDetails"/>:</b></td>
										<td class="col-sm-7"><form:textarea path="insuranceDetails" readonly="true" id="insuranceDetails" placeholder="Enter Insurance Details"></form:textarea>
									
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.status"/>:</b></td>
										<td class="col-sm-7"><form:input path="status" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
									</tr>
									<form:hidden path="transactionId"  />
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.comment"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="comment" id="comment" placeholder="Enter Comment" readonly="true"></form:input>
										
										</td>
									</tr>
								
									</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="invoiceListForcApproval" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>
	
</div>
