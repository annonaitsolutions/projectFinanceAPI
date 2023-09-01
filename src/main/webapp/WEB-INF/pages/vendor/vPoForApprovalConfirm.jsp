<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		<div class="col-md-12 col-sm-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.confirmScreen"/></h3>
		</div>
	<form:form action="vPoForApprovalPost" commandName="purchaseOrderForm" onsubmit="return val();">

			<div class="content">
								<table align="center">
								<form:hidden path="id"  />
								<form:hidden path="flag"  />
								<form:hidden path="customerHeadEmail"  />
									<form:hidden path="customerBranchEmail"  />
								    <form:hidden path="supplierEmail"  />
								      <form:hidden path="poKey"  />
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerHeadName" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.supplierName"/>:</b></td>
										<td class="col-sm-7"><form:input path="supplierName" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-7"><form:input path="masterKey" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
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
						             <td class="col-sm-5"><b><spring:message code="label.startDate"/></b><span style="color:red">*</span></td>
						                 <td class="col-sm-7">
							                <form:input path="startDate"  class="form-control" readonly="true" placeholder="Enter Date" style="width: 176px;" id="startDate"/></td>
							            
						        </tr>
						        
						        	<tr>
						                <td class="col-sm-5"><b><spring:message code="label.endDate"/></b><span style="color:red">*</span></td>
						                     <td class="col-sm-7">
							                   <form:input path="endDate"  class="form-control" readonly="true" placeholder="Enter Date" style="width: 176px;" id="endDate"/></td>
							              
						        </tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.status"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="vStatus" readonly="true" placeholder="Enter Status" id="companyName" ></form:input>
									</tr>
									<form:hidden path="transactionId"  />
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.comment"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="vComment" id="comment" placeholder="Enter Comment" readonly="true"></form:input>
										
										</td>
									</tr>
								
									</table>
								<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="vPoListForApproval" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>
	
</div>

